# ArtAgent-android

ArtAgent-android is an Android client for the ArtAgent system. It combines GPT-4, Stable Diffusion and a finetuned VisualGLM-6B model to provide intelligent conversation and image generation on mobile devices.

## Features
- Chat with AI characters powered by GPT-4/VisualGLM-6B
- Generate images through Stable Diffusion
- Floating window service for convenient access
- Custom drawing canvas with single and double click support

## Requirements
- Android Studio (2022.1 or newer) or command line Gradle
- JDK 8+
- Android device or emulator running Android 8.0+ (minSdk 26, targetSdk 32)

## Build
```bash
chmod +x gradlew
./gradlew assembleDebug  # builds app/build/outputs/apk/debug/app-debug.apk
```

To install the generated APK on a connected device or emulator:

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Configuration & Usage
The client communicates with a backend service that exposes GPT-4, Stable Diffusion and VisualGLM-6B endpoints. The default host and port are defined in `app/src/main/assets/backend_config.json`.

If you host your own server, edit `backend_config.json` to point to your instance and ensure the service provides the following endpoints:

- `/gpt4_sd_draw`
- `/gpt4_mode_2`
- `/gpt4_predict`
- `/image_edit_topic`
- `/save_sketch`
- `/get_user_env`
- `/gpt4_sd_edit`

The server must be reachable from the Android device.

More documentation and a user manual are available [here](https://open-spur-1bb.notion.site/ArtAgent-791e0a7408f2423e9605aee195905dd5).

## License
This project is released under the MIT License. See [LICENSE](LICENSE) for details.

