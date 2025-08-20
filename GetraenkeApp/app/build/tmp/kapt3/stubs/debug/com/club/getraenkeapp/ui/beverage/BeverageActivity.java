package com.club.getraenkeapp.ui.beverage;

/**
 * Beverage Selection Activity
 *
 * PATTERN: Receive selected member via Intent extras, full-screen layout with back button,
 * RecyclerView grid for beverage options, display beverage name and price clearly.
 *
 * CRITICAL: Full-screen approach eliminates distractions, large touch targets for tablet
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0012H\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "beverageAdapter", "Lcom/club/getraenkeapp/ui/beverage/BeverageGridAdapter;", "binding", "Lcom/club/getraenkeapp/databinding/ActivityBeverageBinding;", "memberStatisticsAdapter", "Lcom/club/getraenkeapp/ui/beverage/MemberStatisticsAdapter;", "viewModel", "Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel;", "getViewModel", "()Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "calculateOptimalSpanCount", "", "handleNavigationEvent", "", "event", "Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupClickListeners", "setupObservers", "setupRecyclerView", "Companion", "app_debug"})
public final class BeverageActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.club.getraenkeapp.databinding.ActivityBeverageBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.club.getraenkeapp.ui.beverage.BeverageGridAdapter beverageAdapter;
    private com.club.getraenkeapp.ui.beverage.MemberStatisticsAdapter memberStatisticsAdapter;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MEMBER_ID = "extra_member_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MEMBER_NAME = "extra_member_name";
    @org.jetbrains.annotations.NotNull()
    public static final com.club.getraenkeapp.ui.beverage.BeverageActivity.Companion Companion = null;
    
    public BeverageActivity() {
        super();
    }
    
    private final com.club.getraenkeapp.ui.beverage.BeverageViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Setup RecyclerView with GridLayoutManager and adapter
     */
    private final void setupRecyclerView() {
    }
    
    /**
     * Setup observers for ViewModel LiveData
     */
    private final void setupObservers() {
    }
    
    /**
     * Setup click listeners for UI elements
     */
    private final void setupClickListeners() {
    }
    
    /**
     * Handle navigation events from ViewModel
     */
    private final void handleNavigationEvent(com.club.getraenkeapp.ui.beverage.BeverageViewModel.NavigationEvent event) {
    }
    
    /**
     * Calculate optimal span count for beverage grid - Orientation aware
     */
    private final int calculateOptimalSpanCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageActivity$Companion;", "", "()V", "EXTRA_MEMBER_ID", "", "EXTRA_MEMBER_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}