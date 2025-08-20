package com.club.getraenkeapp.ui.admin;

/**
 * Member Management Activity for Admin Panel
 *
 * PATTERN: Admin interface for member CRUD operations with validation,
 * search/filter capabilities, confirmation dialogs, and automatic updates.
 *
 * CRITICAL: Prevent deletion of members with transaction history
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001c"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/MemberManagementActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/club/getraenkeapp/databinding/ActivityMemberManagementBinding;", "memberAdapter", "Lcom/club/getraenkeapp/ui/admin/MemberManagementAdapter;", "viewModel", "Lcom/club/getraenkeapp/ui/admin/MemberManagementViewModel;", "getViewModel", "()Lcom/club/getraenkeapp/ui/admin/MemberManagementViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setupClickListeners", "setupObservers", "setupRecyclerView", "setupToolbar", "showAddMemberDialog", "showDeleteConfirmationDialog", "member", "Lcom/club/getraenkeapp/data/database/entities/Member;", "showEditMemberDialog", "app_debug"})
public final class MemberManagementActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.club.getraenkeapp.databinding.ActivityMemberManagementBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.club.getraenkeapp.ui.admin.MemberManagementAdapter memberAdapter;
    
    public MemberManagementActivity() {
        super();
    }
    
    private final com.club.getraenkeapp.ui.admin.MemberManagementViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showAddMemberDialog() {
    }
    
    private final void showEditMemberDialog(com.club.getraenkeapp.data.database.entities.Member member) {
    }
    
    private final void showDeleteConfirmationDialog(com.club.getraenkeapp.data.database.entities.Member member) {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
}