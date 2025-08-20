package com.club.getraenkeapp.ui.beverage;

/**
 * ViewModel for Beverage Selection Activity
 *
 * PATTERN: Load active beverages from repository, handle beverage selection state,
 * calculate pricing preview, prepare data for quantity screen transition.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0007H\u0002J\u0006\u0010$\u001a\u00020\"J\u000e\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0013J\u0006\u0010\'\u001a\u00020\"J\u0006\u0010(\u001a\u00020\"J\u0016\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020+2\u0006\u0010#\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_isLoading", "", "_memberInfo", "Lcom/club/getraenkeapp/data/database/entities/Member;", "_memberStatistics", "Lcom/club/getraenkeapp/data/repository/AppRepository$MemberStatistics;", "_navigationEvent", "Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent;", "beverages", "Landroidx/lifecycle/LiveData;", "", "Lcom/club/getraenkeapp/data/database/entities/Beverage;", "getBeverages", "()Landroidx/lifecycle/LiveData;", "errorMessage", "getErrorMessage", "isLoading", "memberInfo", "getMemberInfo", "memberStatistics", "getMemberStatistics", "navigationEvent", "getNavigationEvent", "repository", "Lcom/club/getraenkeapp/data/repository/AppRepository;", "loadMemberStatistics", "", "memberName", "onBackPressed", "onBeverageSelected", "beverage", "onErrorMessageShown", "onNavigationEventHandled", "setMemberInfo", "memberId", "", "NavigationEvent", "app_debug"})
public final class BeverageViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.repository.AppRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Beverage>> beverages = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.club.getraenkeapp.ui.beverage.BeverageViewModel.NavigationEvent> _navigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.club.getraenkeapp.ui.beverage.BeverageViewModel.NavigationEvent> navigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.club.getraenkeapp.data.database.entities.Member> _memberInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.club.getraenkeapp.data.database.entities.Member> memberInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.club.getraenkeapp.data.repository.AppRepository.MemberStatistics> _memberStatistics = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.club.getraenkeapp.data.repository.AppRepository.MemberStatistics> memberStatistics = null;
    
    public BeverageViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Beverage>> getBeverages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.club.getraenkeapp.ui.beverage.BeverageViewModel.NavigationEvent> getNavigationEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.club.getraenkeapp.data.database.entities.Member> getMemberInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.club.getraenkeapp.data.repository.AppRepository.MemberStatistics> getMemberStatistics() {
        return null;
    }
    
    /**
     * Initialize with member information
     */
    public final void setMemberInfo(long memberId, @org.jetbrains.annotations.NotNull()
    java.lang.String memberName) {
    }
    
    private final void loadMemberStatistics(java.lang.String memberName) {
    }
    
    /**
     * Handle beverage selection
     */
    public final void onBeverageSelected(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Beverage beverage) {
    }
    
    /**
     * Handle back navigation
     */
    public final void onBackPressed() {
    }
    
    /**
     * Clear navigation event after handling
     */
    public final void onNavigationEventHandled() {
    }
    
    /**
     * Clear error message after showing
     */
    public final void onErrorMessageShown() {
    }
    
    /**
     * Navigation events for the UI to handle
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent;", "", "()V", "Back", "QuantitySelection", "Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent$Back;", "Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent$QuantitySelection;", "app_debug"})
    public static abstract class NavigationEvent {
        
        private NavigationEvent() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent$Back;", "Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class Back extends com.club.getraenkeapp.ui.beverage.BeverageViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.beverage.BeverageViewModel.NavigationEvent.Back INSTANCE = null;
            
            private Back() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent$QuantitySelection;", "Lcom/club/getraenkeapp/ui/beverage/BeverageViewModel$NavigationEvent;", "memberId", "", "memberName", "", "beverageId", "beverageName", "unitPrice", "", "(JLjava/lang/String;JLjava/lang/String;D)V", "getBeverageId", "()J", "getBeverageName", "()Ljava/lang/String;", "getMemberId", "getMemberName", "getUnitPrice", "()D", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class QuantitySelection extends com.club.getraenkeapp.ui.beverage.BeverageViewModel.NavigationEvent {
            private final long memberId = 0L;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String memberName = null;
            private final long beverageId = 0L;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String beverageName = null;
            private final double unitPrice = 0.0;
            
            public QuantitySelection(long memberId, @org.jetbrains.annotations.NotNull()
            java.lang.String memberName, long beverageId, @org.jetbrains.annotations.NotNull()
            java.lang.String beverageName, double unitPrice) {
            }
            
            public final long getMemberId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMemberName() {
                return null;
            }
            
            public final long getBeverageId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getBeverageName() {
                return null;
            }
            
            public final double getUnitPrice() {
                return 0.0;
            }
            
            public final long component1() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            public final long component3() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component4() {
                return null;
            }
            
            public final double component5() {
                return 0.0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.ui.beverage.BeverageViewModel.NavigationEvent.QuantitySelection copy(long memberId, @org.jetbrains.annotations.NotNull()
            java.lang.String memberName, long beverageId, @org.jetbrains.annotations.NotNull()
            java.lang.String beverageName, double unitPrice) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
}