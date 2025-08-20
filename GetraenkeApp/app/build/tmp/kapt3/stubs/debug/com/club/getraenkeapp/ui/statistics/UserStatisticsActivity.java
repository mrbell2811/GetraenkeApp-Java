package com.club.getraenkeapp.ui.statistics;

/**
 * User Statistics Activity - Display consumption statistics since last monthly closing
 *
 * Shows user consumption data in an attractive visual format including:
 * - Total consumption summary
 * - Beverage breakdown with quantities and costs
 * - Period information (since last monthly closing)
 * - Visual progress indicators
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lcom/club/getraenkeapp/ui/statistics/UserStatisticsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "beverageStatisticsAdapter", "Lcom/club/getraenkeapp/ui/statistics/BeverageStatisticsAdapter;", "binding", "Lcom/club/getraenkeapp/databinding/ActivityUserStatisticsBinding;", "viewModel", "Lcom/club/getraenkeapp/ui/statistics/UserStatisticsViewModel;", "getViewModel", "()Lcom/club/getraenkeapp/ui/statistics/UserStatisticsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupClickListeners", "setupObservers", "setupRecyclerView", "setupUI", "memberName", "", "updateStatisticsDisplay", "statistics", "Lcom/club/getraenkeapp/ui/statistics/UserStatisticsViewModel$UserStatisticsData;", "Companion", "app_debug"})
public final class UserStatisticsActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.club.getraenkeapp.databinding.ActivityUserStatisticsBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.club.getraenkeapp.ui.statistics.BeverageStatisticsAdapter beverageStatisticsAdapter;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_MEMBER_NAME = "member_name";
    @org.jetbrains.annotations.NotNull()
    public static final com.club.getraenkeapp.ui.statistics.UserStatisticsActivity.Companion Companion = null;
    
    public UserStatisticsActivity() {
        super();
    }
    
    private final com.club.getraenkeapp.ui.statistics.UserStatisticsViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI(java.lang.String memberName) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void updateStatisticsDisplay(com.club.getraenkeapp.ui.statistics.UserStatisticsViewModel.UserStatisticsData statistics) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/club/getraenkeapp/ui/statistics/UserStatisticsActivity$Companion;", "", "()V", "EXTRA_MEMBER_NAME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}