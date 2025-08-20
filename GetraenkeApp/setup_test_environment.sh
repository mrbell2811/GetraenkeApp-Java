#!/bin/bash

# GeträkeApp3.0 Test Environment Setup Script
# Automates the technical setup tasks

echo "🚀 GeträkeApp3.0 Test Environment Setup"
echo "======================================"
echo

# Check if we're in the right directory
if [ ! -f "app/build.gradle" ]; then
    echo "❌ Error: Please run this script from the GetraenkeApp project root directory"
    exit 1
fi

echo "📋 Checking Prerequisites..."

# Check Java version
echo "Checking Java installation..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | grep -oE 'version "[0-9]+'| cut -d'"' -f2 | cut -d'.' -f1)
    if [ "$JAVA_VERSION" -ge 11 ] && [ "$JAVA_VERSION" -le 21 ]; then
        echo "✅ Java $JAVA_VERSION detected (compatible)"
    else
        echo "⚠️  Java version $JAVA_VERSION detected"
        echo "   Recommended: Java 11, 17, or 21"
        echo "   Download from: https://adoptium.net/temurin/releases/?version=17"
    fi
else
    echo "❌ Java not found"
    echo "   Please install JDK 17 from: https://adoptium.net/temurin/releases/?version=17"
    exit 1
fi

# Check Android Studio / SDK
echo "Checking Android SDK..."
if [ -n "$ANDROID_HOME" ] && [ -d "$ANDROID_HOME" ]; then
    echo "✅ Android SDK found at: $ANDROID_HOME"
else
    echo "⚠️  Android SDK not found"
    echo "   Please install Android Studio from: https://developer.android.com/studio"
fi

echo
echo "🔧 Setting up project..."

# Make gradlew executable
if [ -f "./gradlew" ]; then
    chmod +x ./gradlew
    echo "✅ Gradle wrapper configured"
else
    echo "❌ Gradle wrapper not found"
    exit 1
fi

# Clean and prepare project
echo "Preparing Gradle build..."
./gradlew clean --quiet

echo
echo "🧪 Testing build system..."

# Test compilation
echo "Testing Kotlin compilation..."
if ./gradlew compileDebugKotlin --quiet; then
    echo "✅ Kotlin compilation successful"
else
    echo "⚠️  Kotlin compilation failed"
    echo "   This may be due to Java version compatibility"
    echo "   Try installing JDK 17 if you haven't already"
fi

echo
echo "📱 Preparing test configurations..."

# Create debug signing config if needed
if [ ! -f "app/keystore.debug" ]; then
    echo "Creating debug keystore..."
    keytool -genkey -v -keystore app/keystore.debug \
        -storepass android -alias androiddebugkey \
        -keypass android -keyalg RSA -keysize 2048 \
        -validity 10000 -dname "CN=Debug, OU=Debug, O=Debug, L=Debug, S=Debug, C=US" \
        2>/dev/null || echo "Debug keystore creation skipped (optional)"
fi

echo
echo "✅ SETUP COMPLETE!"
echo "=================="
echo
echo "📋 Manual tasks still needed:"
echo "  1. Install JDK 17 (if not done): https://adoptium.net/temurin/releases/?version=17"
echo "  2. Install Android Studio: https://developer.android.com/studio"
echo "  3. For real device testing: Enable USB debugging on your tablet"
echo
echo "🎯 Next steps:"
echo "  1. Open project in Android Studio"
echo "  2. Wait for Gradle sync to complete"
echo "  3. Click Run ▶️ button to test the app"
echo
echo "📚 For detailed instructions, see: SETUP_GUIDE.md"
echo