package com.club.getraenkeapp.ui.main;

/**
 * RecyclerView adapter for member name buttons
 *
 * Uses ListAdapter with DiffUtil for efficient updates and ViewBinding
 * for type-safe view access. Implements Material Design touch feedback.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0011\u0012B/\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/club/getraenkeapp/ui/main/MemberGridAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/club/getraenkeapp/data/database/entities/Member;", "Lcom/club/getraenkeapp/ui/main/MemberGridAdapter$MemberViewHolder;", "onMemberClick", "Lkotlin/Function1;", "", "onMemberLongClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MemberDiffCallback", "MemberViewHolder", "app_debug"})
public final class MemberGridAdapter extends androidx.recyclerview.widget.ListAdapter<com.club.getraenkeapp.data.database.entities.Member, com.club.getraenkeapp.ui.main.MemberGridAdapter.MemberViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onMemberClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onMemberLongClick = null;
    
    public MemberGridAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onMemberClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onMemberLongClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.club.getraenkeapp.ui.main.MemberGridAdapter.MemberViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.ui.main.MemberGridAdapter.MemberViewHolder holder, int position) {
    }
    
    /**
     * DiffUtil callback for efficient list updates
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/club/getraenkeapp/ui/main/MemberGridAdapter$MemberDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/club/getraenkeapp/data/database/entities/Member;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
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
    
    /**
     * ViewHolder for member cards with avatar and name
     *
     * CRITICAL: Touch targets must be 48dp minimum for accessibility
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/club/getraenkeapp/ui/main/MemberGridAdapter$MemberViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/club/getraenkeapp/databinding/ItemMemberButtonBinding;", "onMemberClick", "Lkotlin/Function1;", "Lcom/club/getraenkeapp/data/database/entities/Member;", "", "onMemberLongClick", "(Lcom/club/getraenkeapp/databinding/ItemMemberButtonBinding;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "bind", "member", "generateInitials", "", "fullName", "app_debug"})
    public static final class MemberViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.club.getraenkeapp.databinding.ItemMemberButtonBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onMemberClick = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onMemberLongClick = null;
        
        public MemberViewHolder(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.databinding.ItemMemberButtonBinding binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onMemberClick, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Member, kotlin.Unit> onMemberLongClick) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Member member) {
        }
        
        /**
         * Generate initials from full name (matches getraenke-joy logic)
         */
        private final java.lang.String generateInitials(java.lang.String fullName) {
            return null;
        }
    }
}