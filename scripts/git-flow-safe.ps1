[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)]
    [string]$FeatureName,

    [Parameter(Mandatory = $true)]
    [string]$CommitMessage,

    [string]$Remote = "origin",
    [string]$DevelopBranch = "develop",
    [string]$MainBranch = "main",
    [switch]$RunTests,
    [switch]$DryRun
)

$ErrorActionPreference = "Stop"

function Format-Args {
    param([string[]]$GitArgs)

    return ($GitArgs | ForEach-Object {
        if ($_ -match "\s") { '"' + $_ + '"' } else { $_ }
    }) -join " "
}

function Invoke-Git {
    param([Parameter(Mandatory = $true)][string[]]$GitArgs)

    Write-Host "> git $(Format-Args -GitArgs $GitArgs)" -ForegroundColor Cyan

    if ($DryRun) {
        return
    }

    & git @GitArgs
    if ($LASTEXITCODE -ne 0) {
        throw "Git command failed: git $(Format-Args -GitArgs $GitArgs)"
    }
}

function Get-CurrentBranch {
    $branch = (& git rev-parse --abbrev-ref HEAD)
    if ($LASTEXITCODE -ne 0) {
        throw "Not a git repository or unable to resolve current branch."
    }
    return ($branch | Out-String).Trim()
}

function Assert-BranchExists {
    param([Parameter(Mandatory = $true)][string]$Branch)

    & git show-ref --verify --quiet "refs/heads/$Branch"
    if ($LASTEXITCODE -ne 0) {
        throw "Local branch '$Branch' not found."
    }
}

function Assert-RemoteExists {
    param([Parameter(Mandatory = $true)][string]$RemoteName)

    $remotes = @(& git remote)
    if ($LASTEXITCODE -ne 0) {
        throw "Unable to list git remotes."
    }

    if (-not $remotes -or -not ($remotes -contains $RemoteName)) {
        throw "Remote '$RemoteName' not found."
    }
}

function Assert-NoUnmergedFiles {
    $unmerged = (& git diff --name-only --diff-filter=U | Out-String).Trim()
    if ($unmerged) {
        throw "There are unmerged files. Resolve conflicts before running this script."
    }
}

function Get-FeatureBranchName {
    param([Parameter(Mandatory = $true)][string]$Name)

    if ($Name -match "^feature/") {
        return $Name
    }

    return "feature/$Name"
}

function Assert-FeatureDoesNotExist {
    param([Parameter(Mandatory = $true)][string]$Branch)

    & git show-ref --verify --quiet "refs/heads/$Branch"
    if ($LASTEXITCODE -eq 0) {
        throw "Local feature branch '$Branch' already exists."
    }

    & git ls-remote --exit-code --heads $Remote $Branch *> $null
    if ($LASTEXITCODE -eq 0) {
        throw "Remote feature branch '$Branch' already exists on '$Remote'."
    }
}

function Get-WorktreeChanges {
    $lines = @(& git status --porcelain)
    return @($lines | Where-Object { -not [string]::IsNullOrWhiteSpace($_) })
}

$featureBranch = Get-FeatureBranchName -Name $FeatureName

Assert-RemoteExists -RemoteName $Remote
Assert-NoUnmergedFiles
Assert-BranchExists -Branch $DevelopBranch
Assert-BranchExists -Branch $MainBranch
Assert-FeatureDoesNotExist -Branch $featureBranch

$currentBranch = Get-CurrentBranch
Write-Host "Current branch: $currentBranch" -ForegroundColor Yellow

Invoke-Git -GitArgs @("fetch", $Remote)
Invoke-Git -GitArgs @("checkout", $DevelopBranch)
Invoke-Git -GitArgs @("pull", $Remote, $DevelopBranch)
Invoke-Git -GitArgs @("checkout", "-b", $featureBranch)

$changes = Get-WorktreeChanges
if (-not $changes -or $changes.Count -eq 0) {
    throw "No local changes found to commit. Make your code changes first."
}

Invoke-Git -GitArgs @("add", "-A")
Invoke-Git -GitArgs @("commit", "-m", $CommitMessage)

if ($RunTests) {
    Write-Host "> .\\gradlew.bat test" -ForegroundColor Cyan
    if (-not $DryRun) {
        & .\gradlew.bat test
        if ($LASTEXITCODE -ne 0) {
            throw "Tests failed. Aborting merge flow."
        }
    }
}

Invoke-Git -GitArgs @("push", "-u", $Remote, $featureBranch)

Invoke-Git -GitArgs @("checkout", $DevelopBranch)
Invoke-Git -GitArgs @("pull", $Remote, $DevelopBranch)
Invoke-Git -GitArgs @("merge", "--no-ff", $featureBranch, "-m", "merge: $featureBranch into $DevelopBranch")
Invoke-Git -GitArgs @("push", $Remote, $DevelopBranch)

Invoke-Git -GitArgs @("checkout", $MainBranch)
Invoke-Git -GitArgs @("pull", $Remote, $MainBranch)
Invoke-Git -GitArgs @("merge", "--no-ff", $DevelopBranch, "-m", "merge: promote $DevelopBranch to $MainBranch")
Invoke-Git -GitArgs @("push", $Remote, $MainBranch)

Write-Host "Done: $featureBranch -> $DevelopBranch -> $MainBranch" -ForegroundColor Green
