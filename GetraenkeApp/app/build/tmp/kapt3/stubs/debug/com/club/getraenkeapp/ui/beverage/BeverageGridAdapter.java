package com.club.getraenkeapp.ui.beverage;

/**
 * RecyclerView adapter for beverage selection buttons
 *
 * Uses ListAdapter with DiffUtil for efficient updates and ViewBinding
 * for type-safe view access. Displays beverage name and price clearly.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0013\u0014B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageGridAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/club/getraenkeapp/data/database/entities/Beverage;", "Lcom/club/getraenkeapp/ui/beverage/BeverageGridAdapter$BeverageViewHolder;", "onBeverageClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "priceFormatter", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BeverageDiffCallback", "BeverageViewHolder", "app_debug"})
public final class BeverageGridAdapter extends androidx.recyclerview.widget.ListAdapter<com.club.getraenkeapp.data.database.entities.Beverage, com.club.getraenkeapp.ui.beverage.BeverageGridAdapter.BeverageViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Beverage, kotlin.Unit> onBeverageClick = null;
    private final java.text.NumberFormat priceFormatter = null;
    
    public BeverageGridAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Beverage, kotlin.Unit> onBeverageClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.club.getraenkeapp.ui.beverage.BeverageGridAdapter.BeverageViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.ui.beverage.BeverageGridAdapter.BeverageViewHolder holder, int position) {
    }
    
    /**
     * DiffUtil callback for efficient list updates
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageGridAdapter$BeverageDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/club/getraenkeapp/data/database/entities/Beverage;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    static final class BeverageDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.club.getraenkeapp.data.database.entities.Beverage> {
        
        public BeverageDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Beverage oldItem, @org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Beverage newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Beverage oldItem, @org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Beverage newItem) {
            return false;
        }
    }
    
    /**
     * ViewHolder for beverage buttons
     *
     * CRITICAL: Touch targets must be 48dp minimum for accessibility
     * PATTERN: Material Design card views for beverages with name and price
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0006J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/club/getraenkeapp/ui/beverage/BeverageGridAdapter$BeverageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/club/getraenkeapp/databinding/ItemBeverageButtonBinding;", "onBeverageClick", "Lkotlin/Function1;", "Lcom/club/getraenkeapp/data/database/entities/Beverage;", "", "priceFormatter", "Ljava/text/NumberFormat;", "(Lcom/club/getraenkeapp/databinding/ItemBeverageButtonBinding;Lkotlin/jvm/functions/Function1;Ljava/text/NumberFormat;)V", "bind", "beverage", "getCategoryDescription", "", "category", "getCategoryIcon", "", "app_debug"})
    public static final class BeverageViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.club.getraenkeapp.databinding.ItemBeverageButtonBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.club.getraenkeapp.data.database.entities.Beverage, kotlin.Unit> onBeverageClick = null;
        @org.jetbrains.annotations.NotNull()
        private final java.text.NumberFormat priceFormatter = null;
        
        public BeverageViewHolder(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.databinding.ItemBeverageButtonBinding binding, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.club.getraenkeapp.data.database.entities.Beverage, kotlin.Unit> onBeverageClick, @org.jetbrains.annotations.NotNull()
        java.text.NumberFormat priceFormatter) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.database.entities.Beverage beverage) {
        }
        
        /**
         * Get category icon based on beverage category (matches getraenke-joy logic)
         */
        private final int getCategoryIcon(java.lang.String category) {
            return 0;
        }
        
        /**
         * Get category description for accessibility
         */
        private final java.lang.String getCategoryDescription(java.lang.String category) {
            return null;
        }
    }
}