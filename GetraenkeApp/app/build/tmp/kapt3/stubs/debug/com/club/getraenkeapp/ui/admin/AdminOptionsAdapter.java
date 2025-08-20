package com.club.getraenkeapp.ui.admin;

/**
 * RecyclerView adapter for admin options
 *
 * Displays admin management options in a grid layout with icons and descriptions.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0011\u0012B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminOptionsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOptionItem;", "Lcom/club/getraenkeapp/ui/admin/AdminOptionsAdapter$AdminOptionViewHolder;", "onOptionClick", "Lkotlin/Function1;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOption;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "AdminOptionDiffCallback", "AdminOptionViewHolder", "app_debug"})
public final class AdminOptionsAdapter extends androidx.recyclerview.widget.ListAdapter<com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem, com.club.getraenkeapp.ui.admin.AdminOptionsAdapter.AdminOptionViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption, kotlin.Unit> onOptionClick = null;
    
    public AdminOptionsAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption, kotlin.Unit> onOptionClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.club.getraenkeapp.ui.admin.AdminOptionsAdapter.AdminOptionViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.ui.admin.AdminOptionsAdapter.AdminOptionViewHolder holder, int position) {
    }
    
    /**
     * DiffUtil callback for efficient list updates
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminOptionsAdapter$AdminOptionDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOptionItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    static final class AdminOptionDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem> {
        
        public AdminOptionDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem oldItem, @org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem oldItem, @org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem newItem) {
            return false;
        }
    }
    
    /**
     * ViewHolder for admin options
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/AdminOptionsAdapter$AdminOptionViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/club/getraenkeapp/databinding/ItemAdminOptionBinding;", "onOptionClick", "Lkotlin/Function1;", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOption;", "", "(Lcom/club/getraenkeapp/databinding/ItemAdminOptionBinding;Lkotlin/jvm/functions/Function1;)V", "bind", "item", "Lcom/club/getraenkeapp/ui/admin/AdminViewModel$AdminOptionItem;", "app_debug"})
    public static final class AdminOptionViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.club.getraenkeapp.databinding.ItemAdminOptionBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption, kotlin.Unit> onOptionClick = null;
        
        public AdminOptionViewHolder(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.databinding.ItemAdminOptionBinding binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOption, kotlin.Unit> onOptionClick) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.ui.admin.AdminViewModel.AdminOptionItem item) {
        }
    }
}