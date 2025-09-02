# debug_screen_on

A Flutter plugin that automatically keeps the screen on during debugging sessions when a debugger is attached or ADB is enabled, but only in DEBUG builds.

## Features

- Automatically keeps screen on when debugger is connected
- Automatically keeps screen on when ADB is enabled  
- Only active in DEBUG builds for security
- Automatically turns off screen keep-on when debugging stops
- Logs debug information to help track state changes

## Usage

Add this to your `pubspec.yaml`:

```yaml
dependencies:
  debug_screen_on: ^1.0.0
```

That's it! No code changes needed. The plugin automatically hooks into your app's lifecycle and manages the screen keep-on behavior during debugging.

## Platform Support

- ✅ Android
- ❌ iOS (not applicable - iOS handles debugging differently)

## How it Works

The plugin checks if:
1. A debugger is connected (`Debug.isDebuggerConnected()`)
2. ADB is enabled in system settings

If either condition is true in a DEBUG build, it sets `FLAG_KEEP_SCREEN_ON` on the window. When debugging stops, the flag is automatically cleared.