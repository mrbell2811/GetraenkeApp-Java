package com.club.getraenkeapp.ui.main;

/**
 * ViewModel for Main Activity
 *
 * PATTERN: AndroidViewModel with repository injection, LiveData for member list
 * and grid configuration, methods for member selection and grid layout updates,
 * handling configuration changes gracefully.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u000e\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u0013J\u0006\u0010\"\u001a\u00020\u001eR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/club/getraenkeapp/ui/main/MainViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_isLoading", "", "_navigationEvent", "Lcom/club/getraenkeapp/ui/main/MainViewModel$NavigationEvent;", "errorMessage", "Landroidx/lifecycle/LiveData;", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "isLoading", "members", "", "Lcom/club/getraenkeapp/data/database/entities/Member;", "getMembers", "navigationEvent", "getNavigationEvent", "repository", "Lcom/club/getraenkeapp/data/repository/AppRepository;", "calculateOptimalSpanCount", "", "screenWidthPx", "buttonMinWidthPx", "onAdminAccess", "", "onErrorMessageShown", "onMemberSelected", "member", "onNavigationEventHandled", "NavigationEvent", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.repository.AppRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Member>> members = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.club.getraenkeapp.ui.main.MainViewModel.NavigationEvent> _navigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.club.getraenkeapp.ui.main.MainViewModel.NavigationEvent> navigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Member>> getMembers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.club.getraenkeapp.ui.main.MainViewModel.NavigationEvent> getNavigationEvent() {
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
    
    /**
     * Handle member button click
     */
    public final void onMemberSelected(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member) {
    }
    
    /**
     * Handle admin button click
     */
    public final void onAdminAccess() {
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
     * Calculate optimal grid span count based on screen width and orientation
     * GOTCHA: Portrait vs Landscape optimization - different max columns for each
     */
    public final int calculateOptimalSpanCount(int screenWidthPx, int buttonMinWidthPx) {
        return 0;
    }
    
    /**
     * Navigation events for the UI to handle
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/club/getraenkeapp/ui/main/MainViewModel$NavigationEvent;", "", "()V", "AdminAccess", "BeverageSelection", "Lcom/club/getraenkeapp/ui/main/MainViewModel$NavigationEvent$AdminAccess;", "Lcom/club/getraenkeapp/ui/main/MainViewModel$NavigationEvent$BeverageSelection;", "app_debug"})
    public static abstract class NavigationEvent {
        
        private NavigationEvent() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/main/MainViewModel$NavigationEvent$AdminAccess;", "Lcom/club/getraenkeapp/ui/main/MainViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class AdminAccess extends com.club.getraenkeapp.ui.main.MainViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.main.MainViewModel.NavigationEvent.AdminAccess INSTANCE = null;
            
            private AdminAccess() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/club/getraenkeapp/ui/main/MainViewModel$NavigationEvent$BeverageSelection;", "Lcom/club/getraenkeapp/ui/main/MainViewModel$NavigationEvent;", "memberId", "", "memberName", "", "(JLjava/lang/String;)V", "getMemberId", "()J", "getMemberName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class BeverageSelection extends com.club.getraenkeapp.ui.main.MainViewModel.NavigationEvent {
            private final long memberId = 0L;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String memberName = null;
            
            public BeverageSelection(long memberId, @org.jetbrains.annotations.NotNull()
            java.lang.String memberName) {
            }
            
            public final long getMemberId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMemberName() {
                return null;
            }
            
            public final long component1() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.ui.main.MainViewModel.NavigationEvent.BeverageSelection copy(long memberId, @org.jetbrains.annotations.NotNull()
            java.lang.String memberName) {
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