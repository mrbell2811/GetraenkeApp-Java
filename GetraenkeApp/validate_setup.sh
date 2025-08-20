#!/bin/bash

# GeträkeApp3.0 Setup Validation Script
# Quick check to verify everything is working

echo "🔍 GeträkeApp3.0 Setup Validation"
echo "================================="
echo

# Check if we're in the right directory
if [ ! -f "app/build.gradle" ]; then
    echo "❌ Error: Please run this script from the GetraenkeApp project root directory"
    exit 1
fi

VALIDATION_SCORE=0
TOTAL_CHECKS=6

echo "Running validation checks..."
echo

# Check 1: Java version
echo "1. Java Version Check..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | grep -oE 'version "[0-9]+'| cut -d'"' -f2 | cut -d'.' -f1)
    if [ "$JAVA_VERSION" -ge 11 ] && [ "$JAVA_VERSION" -le 21 ]; then
        echo "   ✅ Java $JAVA_VERSION (compatible)"
        ((VALIDATION_SCORE++))
    else
        echo "   ⚠️  Java $JAVA_VERSION (may cause issues)"
        echo "      Recommended: Java 11, 17, or 21"
    fi
else
    echo "   ❌ Java not found"
fi

# Check 2: Gradle wrapper
echo "2. Gradle Wrapper Check..."
if [ -x "./gradlew" ]; then
    echo "   ✅ Gradle wrapper is executable"
    ((VALIDATION_SCORE++))
else
    echo "   ❌ Gradle wrapper not executable"
    chmod +x ./gradlew 2>/dev/null || echo "      Failed to make gradlew executable"
fi

# Check 3: Project structure
echo "3. Project Structure Check..."
if [ -d "app/src/main/java/com/club/getraenkeapp" ] && [ -f "app/src/main/AndroidManifest.xml" ]; then
    echo "   ✅ Android project structure valid"
    ((VALIDATION_SCORE++))
else
    echo "   ❌ Android project structure incomplete"
fi

# Check 4: Core source files
echo "4. Core Source Files Check..."
CORE_FILES_COUNT=0
for file in "MainActivity.kt" "BeverageActivity.kt" "QuantityActivity.kt" "AdminActivity.kt" "AppDatabase.kt"; do
    if find app/src -name "$file" -type f | grep -q .; then
        ((CORE_FILES_COUNT++))
    fi
done

if [ $CORE_FILES_COUNT -eq 5 ]; then
    echo "   ✅ All core source files present ($CORE_FILES_COUNT/5)"
    ((VALIDATION_SCORE++))
else
    echo "   ⚠️  Some core source files missing ($CORE_FILES_COUNT/5)"
fi

# Check 5: Android SDK (if available)
echo "5. Android SDK Check..."
if [ -n "$ANDROID_HOME" ] && [ -d "$ANDROID_HOME" ]; then
    echo "   ✅ Android SDK found at $ANDROID_HOME"
    ((VALIDATION_SCORE++))
else
    echo "   ⚠️  Android SDK not found (install Android Studio)"
fi

# Check 6: Build system test (if Java is compatible)
echo "6. Build System Test..."
if [ "$JAVA_VERSION" -ge 11 ] && [ "$JAVA_VERSION" -le 21 ] 2>/dev/null; then
    if ./gradlew tasks --quiet >/dev/null 2>&1; then
        echo "   ✅ Gradle build system functional"
        ((VALIDATION_SCORE++))
    else
        echo "   ⚠️  Gradle build system has issues"
    fi
else
    echo "   ⚠️  Skipped (Java compatibility issue)"
fi

echo
echo "📊 VALIDATION RESULTS"
echo "===================="
echo "Score: $VALIDATION_SCORE/$TOTAL_CHECKS checks passed"
echo

if [ $VALIDATION_SCORE -eq $TOTAL_CHECKS ]; then
    echo "🎉 PERFECT SETUP!"
    echo "   Your environment is ready for development and testing."
    echo "   Next: Open the project in Android Studio and click Run ▶️"
elif [ $VALIDATION_SCORE -ge 4 ]; then
    echo "✅ GOOD SETUP!"
    echo "   Your environment should work for most testing."
    echo "   Address the warnings above for optimal experience."
elif [ $VALIDATION_SCORE -ge 2 ]; then
    echo "⚠️  PARTIAL SETUP"
    echo "   Some components are missing. Check the failed items above."
    echo "   You may be able to test basic functionality."
else
    echo "❌ SETUP NEEDS WORK"
    echo "   Please address the issues above before testing."
    echo "   Refer to SETUP_GUIDE.md for detailed instructions."
fi

echo
echo "📋 Quick Action Items:"
if [ "$JAVA_VERSION" -lt 11 ] || [ "$JAVA_VERSION" -gt 21 ] 2>/dev/null; then
    echo "   • Install JDK 17: https://adoptium.net/temurin/releases/?version=17"
fi
if [ -z "$ANDROID_HOME" ]; then
    echo "   • Install Android Studio: https://developer.android.com/studio"
fi
if [ $VALIDATION_SCORE -lt 6 ]; then
    echo "   • Run ./setup_test_environment.sh for automated fixes"
fi
echo "   • See SETUP_GUIDE.md for complete instructions"
echo