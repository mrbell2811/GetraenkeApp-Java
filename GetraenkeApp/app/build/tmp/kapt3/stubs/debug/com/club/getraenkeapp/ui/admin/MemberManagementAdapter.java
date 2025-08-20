package com.club.getraenkeapp.ui.admin;

/**
 * RecyclerView Adapter for Member Management List
 *
 * PATTERN: ListAdapter with DiffUtil for efficient updates,
 * item actions for edit/delete operations with callbacks
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0011\u0012B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/MemberManagementAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/club/getraenkeapp/data/database/entities/Member;", "Lcom/club/getraenkeapp/ui/admin/MemberManagementAdapter$MemberViewHolder;", "onEditClick", "Lkotlin/Function1;", "", "onDeleteClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MemberDiffCallback", "MemberViewHolder", "app_debug"})
public final class MemberManagementAdapter extends androidx.recyclerview.widget.ListAdapter<com.club.getraenkeapp.data.database.entities.Member, com.club.getraenkeapp.ui.admin.MemberManagementAdapter.MemberViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onEditClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onDeleteClick = null;
    
    public MemberManagementAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onDeleteClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.club.getraenkeapp.ui.admin.MemberManagementAdapter.MemberViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.ui.admin.MemberManagementAdapter.MemberViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/MemberManagementAdapter$MemberDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/club/getraenkeapp/data/database/entities/Member;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    static final class MemberDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.club.getraenkeapp.data.database.entities.Member> {
        
        public MemberDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Member oldItem, @org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Member newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Member oldItem, @org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Member newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/MemberManagementAdapter$MemberViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/club/getraenkeapp/databinding/ItemMemberManagementBinding;", "onEditClick", "Lkotlin/Function1;", "Lcom/club/getraenkeapp/data/database/entities/Member;", "", "onDeleteClick", "(Lcom/club/getraenkeapp/databinding/ItemMemberManagementBinding;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "dateFormatter", "Ljava/text/SimpleDateFormat;", "bind", "member", "app_debug"})
    public static final class MemberViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.club.getraenkeapp.databinding.ItemMemberManagementBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onEditClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onDeleteClick = null;
        @org.jetbrains.annotations.NotNull()
        private final java.text.SimpleDateFormat dateFormatter = null;
        
        public MemberViewHolder(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.databinding.ItemMemberManagementBinding binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onDeleteClick) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Member member) {
        }
    }
}