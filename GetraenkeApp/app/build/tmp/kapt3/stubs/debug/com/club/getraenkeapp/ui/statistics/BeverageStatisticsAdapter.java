package com.club.getraenkeapp.ui.statistics;

/**
 * RecyclerView Adapter for displaying beverage consumption statistics
 *
 * Shows individual beverage consumption with visual indicators
 * including quantity, total cost, and relative consumption bars.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0014\u0015B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\bH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\r\u001a\u00020\bH\u0016J\u001c\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0014\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/club/getraenkeapp/ui/statistics/BeverageStatisticsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/club/getraenkeapp/ui/statistics/BeverageStatisticsAdapter$BeverageStatisticsViewHolder;", "()V", "beverageStats", "", "Lcom/club/getraenkeapp/data/repository/AppRepository$BeverageConsumption;", "maxQuantity", "", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateData", "newBeverageStats", "BeverageStatisticsDiffCallback", "BeverageStatisticsViewHolder", "app_debug"})
public final class BeverageStatisticsAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.club.getraenkeapp.ui.statistics.BeverageStatisticsAdapter.BeverageStatisticsViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> beverageStats;
    private int maxQuantity = 1;
    
    public BeverageStatisticsAdapter() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.club.getraenkeapp.ui.statistics.BeverageStatisticsAdapter.BeverageStatisticsViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.ui.statistics.BeverageStatisticsAdapter.BeverageStatisticsViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void updateData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> newBeverageStats) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/club/getraenkeapp/ui/statistics/BeverageStatisticsAdapter$BeverageStatisticsDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldList", "", "Lcom/club/getraenkeapp/data/repository/AppRepository$BeverageConsumption;", "newList", "(Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "app_debug"})
    static final class BeverageStatisticsDiffCallback extends androidx.recyclerview.widget.DiffUtil.Callback {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> oldList = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> newList = null;
        
        public BeverageStatisticsDiffCallback(@org.jetbrains.annotations.NotNull()
        java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> oldList, @org.jetbrains.annotations.NotNull()
        java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> newList) {
            super();
        }
        
        @java.lang.Override()
        public int getOldListSize() {
            return 0;
        }
        
        @java.lang.Override()
        public int getNewListSize() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/club/getraenkeapp/ui/statistics/BeverageStatisticsAdapter$BeverageStatisticsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/club/getraenkeapp/databinding/ItemBeverageStatisticBinding;", "(Lcom/club/getraenkeapp/ui/statistics/BeverageStatisticsAdapter;Lcom/club/getraenkeapp/databinding/ItemBeverageStatisticBinding;)V", "bind", "", "beverageConsumption", "Lcom/club/getraenkeapp/data/repository/AppRepository$BeverageConsumption;", "app_debug"})
    public final class BeverageStatisticsViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.club.getraenkeapp.databinding.ItemBeverageStatisticBinding binding = null;
        
        public BeverageStatisticsViewHolder(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.databinding.ItemBeverageStatisticBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption beverageConsumption) {
        }
    }
}