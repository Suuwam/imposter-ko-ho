# Imposter Ko Ho? 🕵️ (इम्पोस्टर को हो?)

Pass-and-play Nepali imposter party game for Android — Rajesh Hamal & Nepali memes, day-to-day words, traditional stuff, and places. Romanized Nepali + नेपाली (toggle on home screen).

**Download:** [imposter-ko-ho.apk](../../releases/latest/download/imposter-ko-ho.apk) (Android 8+, allow "install unknown apps").

## How to play
1. Set players (3–15), imposters, timer, and categories. Add names.
2. Pass the phone around — each player taps the card to see the secret word. The imposter only sees *"Timi Imposter Hau!"* (plus the category, if enabled).
3. Discuss — each person says one thing about the word. The imposter tries to blend in.
4. Vote, then tap **Imposter Dekhau** to reveal.

## Build
Needs Android SDK + JDK 17, no Gradle: `./build.sh` → `imposter-ko-ho.apk`.
Game is a single file: `assets/index.html` (add words to `CATS` there).
