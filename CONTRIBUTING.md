# Contributing

Thanks for helping with OrderFlow API. The goal is to keep the project simple to review and easy to grow.

## Branches

- `main`: stable branch with ready-to-use code.
- `develop`: integration branch where finished work is collected.
- `feature/*`: branch for one task or one small feature.

## Workflow

1. Start from `develop`.
2. Create a branch like `feature/day2-auth-jwt`.
3. Make small commits with clear English messages.
4. Open a PR back to `develop`.
5. After review and tests, merge `develop` into `main`.

## Commit Messages

Use Conventional Commits:

- `feat: ...`
- `fix: ...`
- `docs: ...`
- `test: ...`
- `chore: ...`
- `ci: ...`

Examples:

- `feat: add jwt login endpoint`
- `docs: improve readme with project scope`
- `test: add order service tests`

## Pull Request Checklist

- The change is small and easy to review.
- The branch name matches the task.
- Tests were added or updated when needed.
- Documentation was updated if behavior changed.
- No unrelated files were changed.

## Quick Tips

- Prefer one branch per feature or fix.
- Keep commits readable.
- If the task is not finished, keep it in `develop` until it is ready.

