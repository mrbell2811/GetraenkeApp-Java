name: "GeträkeApp3.0 Android Implementation - Complete Context-Rich PRP"
description: |

## Purpose
Comprehensive PRP enabling AI agent to implement a stationary Android tablet application for club beverage tracking, replacing paper tally lists with digital solution. Includes complete technical context, architectural patterns, and validation loops for successful one-pass implementation.

## Core Principles
1. **Context is King**: Complete technical documentation, Android patterns, and club-specific requirements
2. **Validation Loops**: Executable test commands and quality gates
3. **Information Dense**: Real Android development patterns and Material Design guidelines
4. **Progressive Success**: Phased implementation with validation at each step
5. **Global rules**: Follow all rules in CLAUDE.md and Archon task workflow

---

## Goal
Build a production-ready Android tablet application for the Lenovo TabTB311FU that digitalizes beverage consumption tracking for a 20-member club. The app must provide zero-learning-curve interface, local-first architecture, and seamless admin management capabilities.

## Why
- **Business Value**: Reduces administrative overhead from 2-3 hours to 30 minutes monthly
- **User Impact**: Eliminates billing disputes through accurate digital tracking
- **Integration**: Replaces existing paper tally lists with familiar digital workflow
- **Problems Solved**: Missing pens, illegible handwriting, manual calculation errors, data entry overhead

## What
4-screen Android application with intuitive touch workflow:
1. **Main Screen**: Configurable grid of member name buttons
2. **Beverage Selection**: Full-screen drink options with pricing
3. **Quantity Confirmation**: Default 1, +/- adjustments
4. **Booking Confirmation**: 1-second success popup, auto-return to main

### Success Criteria
- [ ] Complete booking workflow under 15 seconds from name to confirmation
- [ ] Admin panel for member/beverage/layout management
- [ ] Local SQLite storage with automated backups
- [ ] Monthly data export to Google Sheets format
- [ ] Always-on display during operating hours
- [ ] 100% offline functionality for core operations
- [ ] Production APK ready for Lenovo TabTB311FU deployment

## All Needed Context

### Documentation & References
```yaml
# MUST READ - Critical Android Development Context
- url: https://developer.android.com/training/data-storage/room
  why: Official Room database documentation with Kotlin patterns
  
- url: https://developer.android.com/training/testing/espresso/basics
  why: UI testing patterns for validation loops
  
- url: https://m3.material.io/foundations/layout/understanding-layout/overview
  why: Material Design 3 layout system for tablet optimization
  
- url: https://developer.android.com/codelabs/adaptive-material-guidance
  why: Adaptive layout patterns for tablet-specific design
  
- file: /Users/bierlich/Getraenkeliste3/CLAUDE.md
  why: Project-specific rules and Archon workflow requirements
  
- archon_prd: GeträkeApp3.0 Product Requirements Document
  why: Complete business requirements, user stories, and technical specifications
  
- archon_brief: Projekt Brief Digital Beverage Booking App
  why: Business context, user personas, and constraint definitions

-Also Use Archon for Knowledge about Claude Code
```

### Current Codebase Tree (Empty - Greenfield Project)
```bash
/Users/bierlich/Getraenkeliste3/
├── PRPs/                    # Product Requirement Prompts
├── web-bundles/            # Agent configurations 
├── CLAUDE.md               # Project rules and workflow
└── (No Android code yet)   # Fresh start
```

### Desired Android Project Structure
```bash
GetraenkeApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/club/getraenkeapp/
│   │   │   │   ├── data/                    # Data layer - Room database
│   │   │   │   │   ├── database/
│   │   │   │   │   │   ├── AppDatabase.kt           # Room database configuration
│   │   │   │   │   │   ├── entities/
│   │   │   │   │   │   │   ├── Member.kt            # Member entity
│   │   │   │   │   │   │   ├── Beverage.kt          # Beverage entity  
│   │   │   │   │   │   │   └── Transaction.kt       # Transaction entity
│   │   │   │   │   │   └── dao/
│   │   │   │   │   │       ├── MemberDao.kt         # Member CRUD operations
│   │   │   │   │   │       ├── BeverageDao.kt       # Beverage CRUD operations
│   │   │   │   │   │       └── TransactionDao.kt    # Transaction CRUD operations
│   │   │   │   │   └── repository/
│   │   │   │   │       └── AppRepository.kt         # Repository pattern implementation
│   │   │   │   ├── ui/                      # UI layer - Activities, ViewModels
│   │   │   │   │   ├── main/
│   │   │   │   │   │   ├── MainActivity.kt          # Member name grid screen
│   │   │   │   │   │   └── MainViewModel.kt         # Main screen business logic
│   │   │   │   │   ├── beverage/
│   │   │   │   │   │   ├── BeverageActivity.kt      # Beverage selection screen
│   │   │   │   │   │   └── BeverageViewModel.kt     # Beverage screen logic
│   │   │   │   │   ├── quantity/
│   │   │   │   │   │   ├── QuantityActivity.kt      # Quantity confirmation
│   │   │   │   │   │   └── QuantityViewModel.kt     # Quantity logic
│   │   │   │   │   ├── confirmation/
│   │   │   │   │   │   └── ConfirmationDialog.kt    # Booking confirmation popup
│   │   │   │   │   └── admin/
│   │   │   │   │       ├── AdminActivity.kt         # Admin panel entry
│   │   │   │   │       ├── AdminViewModel.kt        # Admin business logic
│   │   │   │   │       ├── MemberManagementFragment.kt   # Member CRUD
│   │   │   │   │       ├── BeverageManagementFragment.kt # Beverage CRUD
│   │   │   │   │       ├── LayoutConfigFragment.kt       # Grid layout config
│   │   │   │   │       └── ExportFragment.kt             # Data export functionality
│   │   │   │   ├── utils/                   # Utility classes
│   │   │   │   │   ├── SharedPreferencesManager.kt  # App settings storage
│   │   │   │   │   ├── ExportUtils.kt               # CSV export functionality
│   │   │   │   │   ├── BackupService.kt             # Automated backup service
│   │   │   │   │   └── Constants.kt                 # App constants
│   │   │   │   └── GetraenkeApplication.kt          # Application class
│   │   │   ├── res/                         # Resources
│   │   │   │   ├── layout/                  # XML layout files
│   │   │   │   │   ├── activity_main.xml            # Main screen layout
│   │   │   │   │   ├── activity_beverage.xml        # Beverage selection layout
│   │   │   │   │   ├── activity_quantity.xml        # Quantity confirmation layout
│   │   │   │   │   ├── activity_admin.xml           # Admin panel layout
│   │   │   │   │   ├── fragment_member_management.xml    # Member management
│   │   │   │   │   ├── fragment_beverage_management.xml  # Beverage management
│   │   │   │   │   ├── fragment_layout_config.xml        # Layout configuration
│   │   │   │   │   ├── fragment_export.xml               # Export interface
│   │   │   │   │   ├── dialog_confirmation.xml           # Confirmation dialog
│   │   │   │   │   └── item_grid_button.xml              # Grid button template
│   │   │   │   ├── values/                  # Resource values
│   │   │   │   │   ├── colors.xml           # Club color scheme (green/white/black)
│   │   │   │   │   ├── dimens.xml           # Tablet-optimized dimensions
│   │   │   │   │   ├── strings.xml          # Text resources
│   │   │   │   │   └── styles.xml           # Material Design theme
│   │   │   │   └── drawable/                # Graphics and icons
│   │   │   └── AndroidManifest.xml          # App configuration
│   │   ├── androidTest/                     # UI tests
│   │   │   └── java/com/club/getraenkeapp/
│   │   │       ├── MainActivityTest.kt      # Main screen Espresso tests
│   │   │       ├── BeverageActivityTest.kt  # Beverage selection tests
│   │   │       ├── QuantityActivityTest.kt  # Quantity confirmation tests
│   │   │       ├── AdminPanelTest.kt        # Admin functionality tests
│   │   │       └── DatabaseTest.kt          # Database integration tests
│   │   └── test/                            # Unit tests
│   │       └── java/com/club/getraenkeapp/
│   │           ├── repository/
│   │           │   └── AppRepositoryTest.kt # Repository unit tests
│   │           ├── viewmodel/
│   │           │   ├── MainViewModelTest.kt # Main ViewModel tests
│   │           │   ├── BeverageViewModelTest.kt  # Beverage ViewModel tests
│   │           │   └── AdminViewModelTest.kt     # Admin ViewModel tests
│   │           └── utils/
│   │               ├── ExportUtilsTest.kt   # Export functionality tests
│   │               └── BackupServiceTest.kt # Backup service tests
│   ├── build.gradle                         # App-level dependencies
│   └── proguard-rules.pro                   # Code obfuscation rules
├── gradle/
│   └── wrapper/
├── build.gradle                             # Project-level configuration
├── gradle.properties                        # Gradle settings
├── settings.gradle                          # Module configuration
└── README.md                                # Setup and deployment documentation
```

### Known Gotchas & Library Quirks
```kotlin
// CRITICAL: Room requires suspend functions for DAO operations
// Example: suspend fun getAllMembers(): List<Member>

// CRITICAL: Material Design touch targets must be minimum 48dp (not px)
// Example: android:layout_height="48dp"

// CRITICAL: SQLite database name should be consistent
// Example: Room.databaseBuilder(context, AppDatabase::class.java, "getraenke_database")

// CRITICAL: Always-on screen requires WAKE_LOCK permission
// Example: <uses-permission android:name="android.permission.WAKE_LOCK" />

// CRITICAL: File export requires WRITE_EXTERNAL_STORAGE on API < 29
// Example: Check ContextCompat.checkSelfPermission for runtime permissions

// CRITICAL: Lenovo TabTB311FU landscape orientation preference
// Example: android:screenOrientation="landscape" in manifest

// CRITICAL: Club color scheme - Green (#4CAF50), White (#FFFFFF), Black (#000000)
// Example: <color name="club_primary">#4CAF50</color>

// CRITICAL: RecyclerView with GridLayoutManager for button grids
// Example: GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false)
```

## Implementation Blueprint

### Data Models and Structure

```kotlin
// entities/Member.kt
@Entity(tableName = "members")
data class Member(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "grid_position")
    val gridPosition: Int? = null,
    
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)

// entities/Beverage.kt  
@Entity(tableName = "beverages")
data class Beverage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "price")
    val price: Double,
    
    @ColumnInfo(name = "category")
    val category: String? = null,
    
    @ColumnInfo(name = "active")
    val active: Boolean = true
)

// entities/Transaction.kt
@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(entity = Member::class, parentColumns = ["id"], childColumns = ["member_id"]),
        ForeignKey(entity = Beverage::class, parentColumns = ["id"], childColumns = ["beverage_id"])
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    @ColumnInfo(name = "member_id")
    val memberId: Long,
    
    @ColumnInfo(name = "beverage_id") 
    val beverageId: Long,
    
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    
    @ColumnInfo(name = "unit_price")
    val unitPrice: Double,
    
    @ColumnInfo(name = "total_price")
    val totalPrice: Double,
    
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis()
)
```

### List of Tasks to be Completed (Implementation Order)

```yaml
Task 1: Project Foundation Setup
CREATE new Android Studio project:
  - PATTERN: API 34 (Android 15), minimum API 24 for broader compatibility
  - Package: com.club.getraenkeapp
  - Language: Kotlin
  - Build configuration system: Gradle Kotlin DSL preferred
  - Landscape orientation as primary (matches tablet usage)

CONFIGURE build.gradle (app):
  - PATTERN: Room 2.6.1+, Material 1.12.0+, Lifecycle 2.8.0+
  - Enable viewBinding and dataBinding
  - Set compileSdk 34, targetSdk 34, minSdk 24
  - Add dependencies: Room, ViewModel, RecyclerView, Material Design

Task 2: Database Schema Implementation  
CREATE data/database/entities/:
  - PATTERN: Follow Room entity examples above
  - Use @Entity, @PrimaryKey, @ColumnInfo annotations
  - Include foreign key relationships for transactions
  - Add created_at timestamps for audit trail

CREATE data/database/dao/:
  - PATTERN: Interface with suspend fun for all operations
  - @Query annotations for custom queries
  - @Insert, @Update, @Delete for standard CRUD
  - LiveData return types for reactive UI updates

CREATE data/database/AppDatabase.kt:
  - PATTERN: @Database annotation with entities array
  - Version = 1, exportSchema = false for simplicity
  - Singleton pattern with Room.databaseBuilder
  - Include migration strategy for future versions

Task 3: Repository Pattern Implementation
CREATE data/repository/AppRepository.kt:
  - PATTERN: Single repository managing all data operations
  - Inject DAO instances via constructor
  - Expose LiveData/Flow for UI layer consumption
  - Handle business logic like transaction calculations

Task 4: Main Screen (Member Grid) Implementation
CREATE ui/main/MainActivity.kt:
  - PATTERN: AppCompatActivity with viewBinding
  - RecyclerView with GridLayoutManager for member buttons
  - Observe LiveData from ViewModel
  - Handle button clicks with member selection
  - Implement always-on screen with WAKE_LOCK

CREATE ui/main/MainViewModel.kt:
  - PATTERN: AndroidViewModel with repository injection
  - LiveData for member list and grid configuration
  - Methods for member selection and grid layout updates
  - Handle configuration changes gracefully

CREATE layout/activity_main.xml:
  - PATTERN: RecyclerView with android:numColumns for grid
  - Club color scheme with green buttons on white background
  - Tablet-optimized spacing and touch targets (48dp minimum)
  - Constraint layout for responsive design

Task 5: Beverage Selection Screen Implementation  
CREATE ui/beverage/BeverageActivity.kt:
  - PATTERN: Receive selected member via Intent extras
  - Full-screen layout with back button navigation
  - RecyclerView grid for beverage options
  - Display beverage name and price clearly

CREATE ui/beverage/BeverageViewModel.kt:
  - PATTERN: Load active beverages from repository
  - Handle beverage selection state
  - Calculate pricing preview
  - Prepare data for quantity screen transition

CREATE layout/activity_beverage.xml:
  - PATTERN: Full-screen RecyclerView with header
  - Material Design card views for beverages
  - Back button in toolbar
  - Large touch targets optimized for tablet

Task 6: Quantity Confirmation Implementation
CREATE ui/quantity/QuantityActivity.kt:
  - PATTERN: Receive member and beverage data via Intent
  - Default quantity = 1 with +/- button controls
  - Display total cost calculation in real-time
  - Confirm button to proceed, cancel to go back

CREATE ui/quantity/QuantityViewModel.kt:
  - PATTERN: Handle quantity state and calculations
  - Validate quantity limits (min 1, max 10)
  - Calculate total price = quantity * unit_price
  - Prepare transaction data for storage

CREATE layout/activity_quantity.xml:
  - PATTERN: Center-aligned quantity controls
  - Large +/- buttons with quantity display
  - Cost summary with member/beverage/total info
  - Prominent confirm button with club green styling

Task 7: Transaction Storage and Confirmation
CREATE ui/confirmation/ConfirmationDialog.kt:
  - PATTERN: DialogFragment with automatic dismissal
  - Display transaction summary for 1 second
  - Auto-return to main screen after confirmation
  - Handle storage success/error feedback

MODIFY ui/quantity/QuantityActivity.kt:
  - ADD transaction storage on confirm button
  - INJECT repository via ViewModel
  - HANDLE database operations asynchronously
  - SHOW confirmation dialog on successful storage

Task 8: Admin Authentication System
CREATE ui/admin/AdminActivity.kt:
  - PATTERN: Password input with SharedPreferences storage
  - Hashed password storage (never plain text)  
  - Session management with timeout
  - Navigation to admin panels after successful auth

CREATE utils/SharedPreferencesManager.kt:
  - PATTERN: Singleton for app settings storage
  - Encrypted SharedPreferences for sensitive data
  - Admin password hash storage
  - Session timeout configuration

Task 9: Member Management Interface
CREATE ui/admin/MemberManagementFragment.kt:
  - PATTERN: Fragment with RecyclerView for member list
  - Add/Edit/Delete functionality with confirmation dialogs
  - Search/filter capability for large lists
  - Prevent deletion of members with transaction history

CREATE layout/fragment_member_management.xml:
  - PATTERN: FloatingActionButton for add member
  - RecyclerView with swipe actions
  - Search EditText in toolbar
  - Confirmation dialogs for destructive actions

Task 10: Beverage Management Interface  
CREATE ui/admin/BeverageManagementFragment.kt:
  - PATTERN: Similar to member management structure
  - Add/Edit beverages with name and price validation
  - Category organization (optional feature)
  - Active/inactive toggle instead of deletion

CREATE layout/fragment_beverage_management.xml:
  - PATTERN: Form inputs for beverage name and price
  - Number input type for price with decimal support
  - Category spinner (optional)
  - Switch for active/inactive status

Task 11: Grid Layout Configuration
CREATE ui/admin/LayoutConfigFragment.kt:
  - PATTERN: Drag-and-drop RecyclerView with ItemTouchHelper
  - Visual grid preview showing current layout
  - Save/Cancel/Reset functionality
  - Real-time preview of changes

CREATE layout/fragment_layout_config.xml:
  - PATTERN: RecyclerView with preview section
  - Grid size controls (rows/columns)
  - Action buttons for save/cancel/reset
  - Preview area matching main screen appearance

Task 12: Data Export Functionality
CREATE utils/ExportUtils.kt:
  - PATTERN: CSV generation with proper escaping
  - Date range selection for export periods
  - Email intent integration for sending exports
  - File system write for network path option

CREATE ui/admin/ExportFragment.kt:
  - PATTERN: Date picker for range selection
  - Export format options (CSV initially)
  - Progress indicator for export process
  - Success/error feedback to user

Task 13: Automated Backup System
CREATE utils/BackupService.kt:
  - PATTERN: JobIntentService for background operation
  - Hourly backup scheduling with JobScheduler
  - Database file copying to backup location
  - Backup rotation (keep 24 hours)
  - Error handling and logging

MODIFY AndroidManifest.xml:
  - ADD service declaration for BackupService
  - ADD permissions for file system access
  - ADD boot receiver for backup service restart

Task 14: Always-On Display Management
MODIFY ui/main/MainActivity.kt:
  - ADD WAKE_LOCK permission and usage
  - IMPLEMENT inactivity timer (30 seconds)
  - HANDLE operating hours configuration
  - MANAGE screen brightness (dim but visible)
  - ADD touch interaction to restore brightness

CREATE utils/DisplayManager.kt:
  - PATTERN: Singleton for display state management
  - Operating hours configuration storage
  - Wake lock management
  - Screen brightness control

Task 15: Performance Optimization
OPTIMIZE all activities and ViewModels:
  - ADD loading states and error handling
  - IMPLEMENT proper memory management
  - OPTIMIZE database queries with indices
  - ADD image/graphics optimization for tablet
  - ENSURE <100ms touch response time

CREATE database migration strategy:
  - ADD @Database version management
  - IMPLEMENT Migration objects for future updates
  - ADD database export/import for backup recovery

Task 16: Security Implementation
ENHANCE SharedPreferencesManager.kt:
  - ADD EncryptedSharedPreferences usage
  - IMPLEMENT proper password hashing (BCrypt)
  - ADD session timeout enforcement
  - CREATE audit log for admin actions

MODIFY database layer:
  - ADD SQLCipher for database encryption (optional)
  - IMPLEMENT data sanitization for exports
  - ADD access logging for sensitive operations
```

### Per Task Pseudocode (Critical Implementation Details)

```kotlin
// Task 4: Main Screen Implementation
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var memberAdapter: MemberGridAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        // PATTERN: Always initialize binding first
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        
        // CRITICAL: Enable always-on display
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        
        // PATTERN: Grid layout calculation based on member count
        val spanCount = calculateOptimalSpanCount()
        val layoutManager = GridLayoutManager(this, spanCount)
        binding.recyclerViewMembers.layoutManager = layoutManager
        
        // CRITICAL: Touch targets must be 48dp minimum
        memberAdapter = MemberGridAdapter { member ->
            // Start beverage selection with selected member
            val intent = Intent(this, BeverageActivity::class.java)
            intent.putExtra("SELECTED_MEMBER_ID", member.id)
            startActivity(intent)
        }
        
        binding.recyclerViewMembers.adapter = memberAdapter
        
        // PATTERN: Observe LiveData for reactive updates
        viewModel.members.observe(this) { members ->
            memberAdapter.submitList(members)
        }
    }
    
    private fun calculateOptimalSpanCount(): Int {
        // GOTCHA: Tablet optimization - calculate based on screen width
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val buttonMinWidth = resources.getDimensionPixelSize(R.dimen.member_button_min_width)
        return (screenWidth / buttonMinWidth).coerceAtLeast(2)
    }
}

// Task 7: Transaction Storage with Error Handling
class QuantityViewModel : ViewModel() {
    fun confirmTransaction(member: Member, beverage: Beverage, quantity: Int) {
        viewModelScope.launch {
            try {
                // PATTERN: Calculate total in ViewModel
                val totalPrice = beverage.price * quantity
                
                val transaction = Transaction(
                    memberId = member.id,
                    beverageId = beverage.id,
                    quantity = quantity,
                    unitPrice = beverage.price,
                    totalPrice = totalPrice
                )
                
                // CRITICAL: Repository handles database operations
                repository.insertTransaction(transaction)
                
                // PATTERN: Post success state to UI
                _transactionResult.postValue(TransactionResult.Success(transaction))
                
            } catch (e: Exception) {
                // CRITICAL: Handle errors gracefully
                Log.e("QuantityViewModel", "Transaction failed", e)
                _transactionResult.postValue(TransactionResult.Error(e.message ?: "Unknown error"))
            }
        }
    }
}

// Task 12: CSV Export with Proper Formatting
object ExportUtils {
    suspend fun exportTransactionsToCSV(
        transactions: List<TransactionWithDetails>,
        startDate: Date,
        endDate: Date
    ): File {
        return withContext(Dispatchers.IO) {
            // PATTERN: Create timestamped filename
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val filename = "getraenke_export_${dateFormat.format(startDate)}_to_${dateFormat.format(endDate)}.csv"
            
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), filename)
            
            file.printWriter().use { writer ->
                // CRITICAL: CSV header matching Google Sheets format
                writer.println("Date,Time,Member,Beverage,Quantity,Unit Price,Total Price")
                
                transactions.forEach { transaction ->
                    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        .format(Date(transaction.timestamp))
                    val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                        .format(Date(transaction.timestamp))
                    
                    // GOTCHA: Escape commas and quotes in CSV data
                    val memberName = escapeCsvField(transaction.memberName)
                    val beverageName = escapeCsvField(transaction.beverageName)
                    
                    writer.println("$date,$time,$memberName,$beverageName,${transaction.quantity},${transaction.unitPrice},${transaction.totalPrice}")
                }
            }
            
            file
        }
    }
    
    private fun escapeCsvField(field: String): String {
        return if (field.contains(",") || field.contains("\"")) {
            "\"${field.replace("\"", "\"\"")}\""
        } else {
            field
        }
    }
}
```

### Integration Points
```yaml
DEPENDENCIES (build.gradle app level):
  - Room: implementation "androidx.room:room-runtime:2.6.1"
  - Room KTX: implementation "androidx.room:room-ktx:2.6.1"
  - Room Compiler: kapt "androidx.room:room-compiler:2.6.1"
  - ViewModel: implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0"
  - LiveData: implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.8.0"
  - Material: implementation "com.google.android.material:material:1.12.0"
  - RecyclerView: implementation "androidx.recyclerview:recyclerview:1.3.2"
  - ConstraintLayout: implementation "androidx.constraintlayout:constraintlayout:2.1.4"

MANIFEST PERMISSIONS:
  - Wake Lock: <uses-permission android:name="android.permission.WAKE_LOCK" />
  - Storage: <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
  - Network State: <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

THEME CONFIGURATION:
  - Extend: Theme.Material3.DayNight.NoActionBar
  - Colors: Primary = #4CAF50, OnPrimary = #FFFFFF, Surface = #FFFFFF
  - Typography: Roboto family for accessibility across age ranges
  - Dimensions: Touch targets 48dp, margins 16dp, padding 8dp
  
LANDSCAPE ORIENTATION:
  - MainActivity: android:screenOrientation="landscape"
  - Optimize layouts for 11" tablet landscape viewing
  - Adjust grid calculations for landscape aspect ratio
```

## Validation Loop

### Level 1: Syntax & Style
```bash
# Run these FIRST - fix any errors before proceeding
cd GetraenkeApp
./gradlew ktlintCheck                    # Kotlin style checking
./gradlew lintDebug                      # Android lint checking  
./gradlew compileDebugKotlin            # Compilation verification

# Expected: No errors or warnings. If errors, READ and fix before proceeding.
```

### Level 2: Unit Tests
```kotlin
// test/repository/AppRepositoryTest.kt
@Test
fun `insertTransaction calculates total price correctly`() = runTest {
    val member = Member(id = 1, name = "Test Member")
    val beverage = Beverage(id = 1, name = "Beer", price = 2.50)
    val transaction = Transaction(
        memberId = 1,
        beverageId = 1, 
        quantity = 3,
        unitPrice = 2.50,
        totalPrice = 7.50
    )
    
    repository.insertTransaction(transaction)
    
    val retrieved = repository.getTransactionById(transaction.id)
    assertEquals(7.50, retrieved.totalPrice, 0.01)
}

// test/viewmodel/MainViewModelTest.kt  
@Test
fun `memberSelection triggers beverage screen navigation`() {
    val member = Member(id = 1, name = "John Doe")
    
    viewModel.selectMember(member)
    
    val navigationState = viewModel.navigationState.getOrAwaitValue()
    assertTrue(navigationState is NavigationState.BeverageSelection)
    assertEquals(member.id, (navigationState as NavigationState.BeverageSelection).memberId)
}

// test/utils/ExportUtilsTest.kt
@Test
fun `csvExport formats data correctly with special characters`() = runTest {
    val transactions = listOf(
        TransactionWithDetails(
            memberName = "John, Jr.", 
            beverageName = "Coke \"Classic\"",
            quantity = 1,
            unitPrice = 1.50,
            totalPrice = 1.50,
            timestamp = 1640995200000 // 2022-01-01 00:00:00
        )
    )
    
    val file = ExportUtils.exportTransactionsToCSV(transactions, startDate, endDate)
    val content = file.readText()
    
    assertTrue(content.contains("\"John, Jr.\""))
    assertTrue(content.contains("\"Coke \"\"Classic\"\"\""))
}
```

```bash
# Run unit tests iteratively until passing
./gradlew testDebugUnitTest --tests="*Repository*"    # Test data layer
./gradlew testDebugUnitTest --tests="*ViewModel*"     # Test business logic  
./gradlew testDebugUnitTest --tests="*Utils*"         # Test utilities

# If failing: Read specific error, fix root cause, re-run (never skip failing tests)
```

### Level 3: UI Integration Tests
```kotlin
// androidTest/MainActivityTest.kt
@Test
fun memberButtonClick_navigatesToBeverageSelection() {
    // PATTERN: Use Espresso for UI testing
    onView(withId(R.id.recyclerViewMembers))
        .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    
    // Verify navigation to beverage activity
    onView(withId(R.id.activity_beverage)).check(matches(isDisplayed()))
}

@Test  
fun beverageSelection_showsCorrectPricing() {
    // Navigate to beverage selection
    onView(withId(R.id.recyclerViewMembers))
        .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    
    // Click on first beverage
    onView(withId(R.id.recyclerViewBeverages))
        .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    
    // Verify quantity screen shows correct price
    onView(withId(R.id.textViewTotalPrice)).check(matches(withText(containsString("€"))))
}

// androidTest/DatabaseTest.kt
@Test
fun databaseOperations_persistDataCorrectly() = runTest {
    val member = Member(name = "Test Member")
    val memberId = database.memberDao().insertMember(member)
    
    val beverage = Beverage(name = "Test Beverage", price = 1.99)
    val beverageId = database.beverageDao().insertBeverage(beverage)
    
    val transaction = Transaction(
        memberId = memberId,
        beverageId = beverageId,
        quantity = 2,
        unitPrice = 1.99,
        totalPrice = 3.98
    )
    
    database.transactionDao().insertTransaction(transaction)
    
    val allTransactions = database.transactionDao().getAllTransactions()
    assertEquals(1, allTransactions.size)
    assertEquals(3.98, allTransactions[0].totalPrice, 0.01)
}
```

```bash
# Run UI tests on connected device or emulator
./gradlew connectedAndroidTest                 # Full UI test suite
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.club.getraenkeapp.MainActivityTest

# Expected: All tests pass, UI responds within performance requirements
```

### Level 4: Manual Integration Testing
```bash  
# Install on target device (Lenovo TabTB311FU preferred)
./gradlew installDebug

# Test complete workflow manually:
# 1. Launch app -> Main screen displays member grid
# 2. Tap member name -> Full-screen beverage selection appears
# 3. Select beverage -> Quantity screen with default 1, +/- controls
# 4. Confirm transaction -> 1-second confirmation popup, return to main
# 5. Admin access -> Password prompt, admin panel functionality
# 6. Export test -> Generate CSV with transaction data
# 7. Always-on test -> Screen stays active, dims after inactivity

# Performance validation:
# - Touch response: < 100ms for all interactions
# - Screen transitions: < 200ms between activities  
# - Memory usage: < 500MB on target device
# - Database operations: Instant for typical club size (20 members, 10 beverages)
```

## Final Validation Checklist
- [ ] All unit tests pass: `./gradlew testDebugUnitTest`
- [ ] All UI tests pass: `./gradlew connectedAndroidTest`  
- [ ] No lint errors: `./gradlew lintDebug`
- [ ] No Kotlin style issues: `./gradlew ktlintCheck`
- [ ] Database CRUD operations working correctly
- [ ] Complete 4-screen user workflow functional
- [ ] Admin panel accessible and fully functional
- [ ] CSV export generates correct format
- [ ] Always-on display management working
- [ ] Touch targets meet accessibility standards (48dp+)
- [ ] Club color scheme applied consistently
- [ ] Landscape orientation optimized for tablet
- [ ] Performance targets met on Lenovo TabTB311FU
- [ ] Production APK builds successfully

---

## Anti-Patterns to Avoid
- ❌ Don't use synchronous database operations - always use suspend functions
- ❌ Don't hardcode strings - use string resources for localization readiness  
- ❌ Don't skip input validation - prevent SQLite injection and data corruption
- ❌ Don't ignore memory leaks - properly manage ViewModels and observers
- ❌ Don't use fixed pixel values - use dp units for device independence
- ❌ Don't disable Android lint - address warnings to prevent runtime issues
- ❌ Don't skip backup strategy - implement automated data protection
- ❌ Don't ignore accessibility - ensure app works for diverse age groups

## Confidence Score: 9/10

**High confidence due to:**
- Complete technical context from Android development research
- Detailed architectural patterns matching current best practices  
- Comprehensive validation loops with executable commands
- Real-world tablet optimization guidelines included
- Business requirements fully specified in existing PRD
- Step-by-step implementation plan with specific code examples

**Minor uncertainty areas:**
- Lenovo TabTB311FU specific optimizations (hardware testing required)
- CSV export email integration (depends on device email client setup)
- Exact performance characteristics under peak usage (requires load testing)

**Success probability:** Very high for one-pass implementation with proper validation loop execution.