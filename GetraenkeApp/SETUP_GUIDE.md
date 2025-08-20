# 🚀 GeträkeApp3.0 - Test Environment Setup Guide

**Simple step-by-step guide for setting up and testing the beverage tracking app**

---

## 📋 What You'll Need

### Required Software (Download Links Included)
1. **Java Development Kit (JDK)**
   - Download: [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [OpenJDK 17](https://adoptium.net/temurin/releases/?version=17)
   - ⚠️ **Important**: Use JDK 11, 17, or 21 (NOT Java 8 or Java 24+)

2. **Android Studio**
   - Download: [Android Studio Official](https://developer.android.com/studio)
   - Latest stable version (Hedgehog 2023.1.1 or newer)

3. **Git** (if not already installed)
   - Download: [Git Official](https://git-scm.com/downloads)

### Hardware Options
- **Option A**: Real Android tablet (Lenovo TabTB311FU or similar)
- **Option B**: Android Studio emulator (we'll set this up)

---

## 🛠️ Step-by-Step Setup

### Step 1: Install Java (5 minutes)
1. Download JDK 17 from the link above
2. Install using the default settings
3. **Verify installation**:
   ```bash
   java -version
   ```
   Should show: `java version "17.x.x"`

### Step 2: Install Android Studio (10 minutes)
1. Download and install Android Studio
2. **First launch setup**:
   - Choose "Standard" installation
   - Let it download Android SDK (this takes 5-10 minutes)
   - Accept all license agreements

### Step 3: Get the App Code (2 minutes)
**Option A - From this computer:**
The app code is already here in: `/Users/bierlich/Getraenkeliste3/GetraenkeApp/`

**Option B - From Git repository:**
```bash
git clone [your-repository-url]
cd GetraenkeApp
```

### Step 4: Open Project in Android Studio (3 minutes)
1. Start Android Studio
2. Click "Open an existing project"
3. Navigate to the GetraenkeApp folder
4. Click "OK"
5. **Wait for Gradle sync** (first time: 5-10 minutes)

---

## 🧪 Testing Options

### Option A: Test on Real Device (Recommended)

#### What you need to do manually:
1. **Enable Developer Options on your Android tablet:**
   - Go to Settings → About tablet
   - Tap "Build number" 7 times
   - Go back to Settings → Developer options
   - Enable "USB debugging"

2. **Connect tablet to computer:**
   - Use USB cable
   - Allow "USB debugging" when prompted on tablet

3. **Run the app:**
   - In Android Studio, click the green "Run" button ▶️
   - Select your connected device
   - App will install and launch automatically

### Option B: Test on Android Emulator

#### I'll set this up automatically:
The emulator configuration is already prepared. You just need to:

1. **Start the emulator:**
   - In Android Studio, click "Device Manager" (phone icon)
   - Click ▶️ next to "Tablet_API_34"
   - Wait for emulator to boot (2-3 minutes first time)

2. **Run the app:**
   - Click the green "Run" button ▶️
   - Select the emulator from the list
   - App will install and launch

---

## ✅ Verification Checklist

### Test the Complete Workflow:
1. **Main Screen**: Tap any member name (e.g., "Max Mueller")
2. **Beverage Screen**: Select a drink (e.g., "Cola 0,33L")
3. **Quantity Screen**: Use +/- buttons, then tap "Bestätigen"
4. **Confirmation**: Should see 1-second success popup

### Test Admin Panel:
1. **Long-press** the app title on main screen
2. **Login**: Enter password `admin123`
3. **Navigate**: Try "Mitglieder" and "Getränke" buttons
4. **Test CRUD**: Add/edit a member or beverage

---

## 🎯 Quick Start Commands

I'll prepare these for you - just copy and paste:

### Build and Test Commands:
```bash
# Navigate to project (if needed)
cd /Users/bierlich/Getraenkeliste3/GetraenkeApp

# Build the app
./gradlew assembleDebug

# Run tests (when Java is properly configured)
./gradlew testDebugUnitTest

# Install on connected device
./gradlew installDebug
```

---

## 🚨 Common Issues & Solutions

### "Java version not supported"
- **Solution**: Install JDK 17 (not JDK 8 or 24+)
- **Check**: Run `java -version` in terminal

### "Gradle sync failed"
- **Solution**: Wait 5-10 minutes for first sync
- **Or**: Click "Try Again" in Android Studio

### "No connected devices"
- **For Real Device**: Enable USB debugging (see Option A above)
- **For Emulator**: Start emulator first, then run app

### "App crashes on startup"
- **Check**: Device has Android 7.0+ (API 24+)
- **Or**: Try clearing app data in device settings

---

## 📞 What You Need to Do Manually

### Essential Manual Tasks:
1. **Download and install JDK 17** (links provided above)
2. **Download and install Android Studio** (link provided above)
3. **Enable USB debugging on test device** (if using real tablet)
4. **Accept Android SDK licenses** (prompted during Android Studio setup)

### Optional Manual Tasks:
1. **Change admin password** from default `admin123`
2. **Add real club member names** via admin panel
3. **Update beverage menu and prices** via admin panel
4. **Test app with actual club members**

---

## 🎉 Success Indicators

You'll know everything is working when:
- ✅ App builds without errors
- ✅ All 4 screens navigate smoothly
- ✅ Member/beverage data persists after closing app
- ✅ Admin panel allows adding/editing data
- ✅ Touch targets are large enough for tablet use

---

## 📚 Next Steps After Testing

1. **Customize for your club**: Add real members and beverages
2. **Deploy to production tablet**: Install APK on Lenovo TabTB311FU
3. **Train club members**: Show them the 4-screen workflow
4. **Set up backup routine**: Admin exports CSV monthly

---

**Need Help?** Check the detailed README.md for technical specifications and troubleshooting.