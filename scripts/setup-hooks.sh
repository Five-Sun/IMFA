#!/bin/bash
HOOK_PATH=".git/hooks/pre-commit"

if [ ! -f "$HOOK_PATH" ]; then
  echo "Installing pre-commit hook..."
  cp scripts/pre-commit "$HOOK_PATH"
  chmod +x "$HOOK_PATH"
  echo "pre-commit hook installed successfully!"
else
  echo "pre-commit hook already exists!"
fi
