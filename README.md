A small three-screen Android app that passes text between activities with Intents, counts the words in it, and shares it with other apps.

What the app does:
The main screen shows a text area and three buttons:
1. Įvesti norimą tekstą (Enter the text you want) - opens SecondActivity with an input field, pre-filled with the current text. The text goes back to the main screen when you press the confirm button or the Back button.
2. Suskaičiuoti žodžius (Count words) - opens ThirdActivity, which receives the current text, and counts its words (whitespace-separated) when you press its button. It shows a sentence such as Sakinyje "..." yra N žodžiai and returns that sentence to the main screen.
3. Siūsti tekstą su rezultatais (Send the text with results)	Opens the Android share sheet and sends whatever is currently shown on the main screen as plain text.
The app also receives shared text: it declares an ACTION_SEND / text/plain intent filter, so you can share text from another app and pick this app. The text then appears on the main screen.

Tech stack
- Java 11, Android SDK (min 33, target and compile 36)
- AndroidX AppCompat 1.7.1, Activity 1.12.1, Material 1.13.0, ConstraintLayout 2.2.1
- Gradle 8.13 and Android Gradle Plugin 8.13.2
- Android Studio

Requirements
- Android Studio (a recent release that supports Android Gradle Plugin 8.13) with Android SDK Platform 36 installed
- JDK 17 or newer (the JDK bundled with Android Studio is fine)
- An emulator or phone running Android 13 (API 33) or newer, because minSdk is 33

Steps
- git clone https://github.com/Aumila123/android-intent-communication.git
- Open the folder in Android Studio and wait for the Gradle sync to finish.
- Pick a device in the device selector and press Run.
