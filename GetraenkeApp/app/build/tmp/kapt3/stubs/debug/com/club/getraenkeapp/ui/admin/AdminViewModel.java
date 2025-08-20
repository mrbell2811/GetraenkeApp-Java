package com.club.getraenkeapp.ui.admin;

/**
 * ViewModel for Admin Activity
 *
 * PATTERN: AndroidViewModel with SharedPreferencesManager integration for authentication,
 * session management, and admin panel access control.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0003$%&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0007J\u0006\u0010\u001e\u001a\u00020\u0018J\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u0018J\u0006\u0010#\u001a\u00020\u0018R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_isAuthenticated", "", "_isLoading", "_navigationEvent", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent;", "errorMessage", "Landroidx/lifecycle/LiveData;", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "isAuthenticated", "isLoading", "navigationEvent", "getNavigationEvent", "prefsManager", "Lcom/club/getraenkeapp/utils/SharedPreferencesManager;", "checkAuthenticationStatus", "", "getAdminOptions", "", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOptionItem;", "login", "password", "logout", "onAdminOptionSelected", "option", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOption;", "onErrorMessageShown", "onNavigationEventHandled", "AdminOption", "AdminOptionItem", "NavigationEvent", "app_debug"})
public final class AdminViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.utils.SharedPreferencesManager prefsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isAuthenticated = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isAuthenticated = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent> _navigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent> navigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    
    public AdminViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isAuthenticated() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent> getNavigationEvent() {
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
     * Check current authentication status
     */
    private final void checkAuthenticationStatus() {
    }
    
    /**
     * Attempt admin login
     */
    public final void login(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    /**
     * Logout admin user
     */
    public final void logout() {
    }
    
    /**
     * Handle admin option selection
     */
    public final void onAdminOptionSelected(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption option) {
    }
    
    /**
     * Get admin options for display
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem> getAdminOptions() {
        return null;
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
     * Admin options enum
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOption;", "", "(Ljava/lang/String;I)V", "MEMBERS", "BEVERAGES", "LAYOUT", "EXPORT", "BACKUP", "SETTINGS", "app_debug"})
    public static enum AdminOption {
        /*public static final*/ MEMBERS /* = new MEMBERS() */,
        /*public static final*/ BEVERAGES /* = new BEVERAGES() */,
        /*public static final*/ LAYOUT /* = new LAYOUT() */,
        /*public static final*/ EXPORT /* = new EXPORT() */,
        /*public static final*/ BACKUP /* = new BACKUP() */,
        /*public static final*/ SETTINGS /* = new SETTINGS() */;
        
        AdminOption() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption> getEntries() {
            return null;
        }
    }
    
    /**
     * Admin option item for display
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u00a8\u0006\u001a"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOptionItem;", "", "option", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOption;", "icon", "", "title", "description", "(Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOption;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getIcon", "getOption", "()Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOption;", "getTitle", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class AdminOptionItem {
        @org.jetbrains.annotations.NotNull()
        private final com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption option = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String icon = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String title = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String description = null;
        
        public AdminOptionItem(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption option, @org.jetbrains.annotations.NotNull()
        java.lang.String icon, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String description) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption getOption() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem copy(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption option, @org.jetbrains.annotations.NotNull()
        java.lang.String icon, @org.jetbrains.annotations.NotNull()
        java.lang.String title, @org.jetbrains.annotations.NotNull()
        java.lang.String description) {
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
    
    /**
     * Navigation events for UI to handle
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0003\u0004\u0005\u0006\u0007\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0006\t\n\u000b\f\r\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent;", "", "()V", "AppSettings", "BackupManagement", "BeverageManagement", "DataExport", "LayoutConfiguration", "MemberManagement", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$AppSettings;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$BackupManagement;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$BeverageManagement;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$DataExport;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$LayoutConfiguration;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$MemberManagement;", "app_debug"})
    public static abstract class NavigationEvent {
        
        private NavigationEvent() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$AppSettings;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class AppSettings extends com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent.AppSettings INSTANCE = null;
            
            private AppSettings() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$BackupManagement;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class BackupManagement extends com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent.BackupManagement INSTANCE = null;
            
            private BackupManagement() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$BeverageManagement;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class BeverageManagement extends com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent.BeverageManagement INSTANCE = null;
            
            private BeverageManagement() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$DataExport;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class DataExport extends com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent.DataExport INSTANCE = null;
            
            private DataExport() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$LayoutConfiguration;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class LayoutConfiguration extends com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent.LayoutConfiguration INSTANCE = null;
            
            private LayoutConfiguration() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent$MemberManagement;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class MemberManagement extends com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.admin.AdminViewModel.NavigationEvent.MemberManagement INSTANCE = null;
            
            private MemberManagement() {
            }
        }
    }
}