package com.club.getraenkeapp.ui.admin;

/**
 * ViewModel for Member Management
 *
 * PATTERN: MVVM with Repository pattern, business logic validation,
 * search/filter functionality, and error handling
 *
 * CRITICAL: Prevent deletion of members with transaction history
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0007J\u000e\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\fJ\u001e\u0010$\u001a\u00020 2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010&\u001a\u00020\u0007H\u0002J\b\u0010\'\u001a\u00020 H\u0014J\u0006\u0010(\u001a\u00020 J\u0006\u0010)\u001a\u00020 J\u000e\u0010*\u001a\u00020 2\u0006\u0010&\u001a\u00020\u0007J\u000e\u0010+\u001a\u00020 2\u0006\u0010#\u001a\u00020\fR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015\u00a8\u0006,"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/MemberManagementViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_isLoading", "", "_members", "", "Lcom/club/getraenkeapp/data/database/entities/Member;", "_searchQuery", "_successMessage", "allMembers", "Landroidx/lifecycle/LiveData;", "database", "Lcom/club/getraenkeapp/data/database/AppDatabase;", "errorMessage", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "isLoading", "members", "getMembers", "repository", "Lcom/club/getraenkeapp/data/repository/AppRepository;", "searchQuery", "getSearchQuery", "successMessage", "getSuccessMessage", "addMember", "", "name", "deleteMember", "member", "filterMembers", "membersList", "query", "onCleared", "onErrorMessageShown", "onSuccessMessageShown", "searchMembers", "updateMember", "app_debug"})
public final class MemberManagementViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.database.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.repository.AppRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Member>> allMembers = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Member>> _members = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Member>> members = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _successMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> successMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> searchQuery = null;
    
    public MemberManagementViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Member>> getMembers() {
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
    public final androidx.lifecycle.LiveData<java.lang.String> getSuccessMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getSearchQuery() {
        return null;
    }
    
    /**
     * Search members by name
     */
    public final void searchMembers(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    /**
     * Filter members based on search query
     */
    private final void filterMembers(java.util.List<com.club.getraenkeapp.data.database.entities.Member> membersList, java.lang.String query) {
    }
    
    /**
     * Add new member with validation
     */
    public final void addMember(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    /**
     * Update existing member with validation
     */
    public final void updateMember(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member) {
    }
    
    /**
     * Delete member with transaction history validation
     */
    public final void deleteMember(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member) {
    }
    
    /**
     * Clear error message after showing to user
     */
    public final void onErrorMessageShown() {
    }
    
    /**
     * Clear success message after showing to user
     */
    public final void onSuccessMessageShown() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}