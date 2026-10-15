#!/usr/bin/env bash
# Builds imposter-ko-ho.apk with raw Android SDK tools (no Gradle).
set -euo pipefail
SDK=${ANDROID_HOME:-$HOME/Android/Sdk}
BT=$SDK/build-tools/${BUILD_TOOLS:-36.1.0}
JAR=$SDK/platforms/${PLATFORM:-android-34}/android.jar
KS=${KEYSTORE:-$HOME/.android/debug.keystore}
cd "$(dirname "$0")"; rm -rf build; mkdir -p build/res build/classes build/dex
"$BT/aapt2" compile --dir res -o build/res.zip
"$BT/aapt2" link -I "$JAR" --manifest AndroidManifest.xml -A assets -o build/unsigned.apk build/res.zip
javac --release 11 -cp "$JAR" -d build/classes src/np/imposter/*.java
"$BT/d8" --min-api 26 --lib "$JAR" --output build/dex build/classes/np/imposter/*.class
(cd build/dex && zip -q ../unsigned.apk classes.dex)
"$BT/zipalign" -f 4 build/unsigned.apk build/aligned.apk
"$BT/apksigner" sign --ks "$KS" --ks-pass pass:${KS_PASS:-android} --out imposter-ko-ho.apk build/aligned.apk
echo "Built $(pwd)/imposter-ko-ho.apk"
