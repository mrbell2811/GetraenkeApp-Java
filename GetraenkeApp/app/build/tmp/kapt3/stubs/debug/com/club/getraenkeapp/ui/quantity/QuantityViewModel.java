package com.club.getraenkeapp.ui.quantity;

/**
 * ViewModel for Quantity Confirmation Activity
 *
 * PATTERN: Handle quantity state and calculations, validate quantity limits (min 1, max 10),
 * calculate total price, prepare transaction data for storage.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002./B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010#\u001a\u00020$H\u0002J\u0006\u0010%\u001a\u00020$J\u0006\u0010&\u001a\u00020$J\u0006\u0010\'\u001a\u00020$J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020$J\u0006\u0010+\u001a\u00020$J\u0006\u0010,\u001a\u00020$J.\u0010-\u001a\u00020$2\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\r0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u000e\u0010\"\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_isLoading", "", "_navigationEvent", "Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$NavigationEvent;", "_quantity", "", "_totalPrice", "", "beverageId", "", "beverageName", "errorMessage", "Landroidx/lifecycle/LiveData;", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "isLoading", "memberId", "memberName", "navigationEvent", "getNavigationEvent", "quantity", "getQuantity", "repository", "Lcom/club/getraenkeapp/data/repository/AppRepository;", "totalPrice", "getTotalPrice", "unitPrice", "calculateTotalPrice", "", "cancelTransaction", "confirmTransaction", "decreaseQuantity", "getTransactionInfo", "Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$TransactionInfo;", "increaseQuantity", "onErrorMessageShown", "onNavigationEventHandled", "setTransactionData", "NavigationEvent", "TransactionInfo", "app_debug"})
public final class QuantityViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.repository.AppRepository repository = null;
    private long memberId = -1L;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String memberName = "";
    private long beverageId = -1L;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String beverageName = "";
    private double unitPrice = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _quantity = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> quantity = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _totalPrice = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalPrice = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.club.getraenkeapp.ui.quantity.QuantityViewModel.NavigationEvent> _navigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.club.getraenkeapp.ui.quantity.QuantityViewModel.NavigationEvent> navigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    
    public QuantityViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getQuantity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalPrice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.club.getraenkeapp.ui.quantity.QuantityViewModel.NavigationEvent> getNavigationEvent() {
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
     * Initialize with transaction data
     */
    public final void setTransactionData(long memberId, @org.jetbrains.annotations.NotNull()
    java.lang.String memberName, long beverageId, @org.jetbrains.annotations.NotNull()
    java.lang.String beverageName, double unitPrice) {
    }
    
    /**
     * Increase quantity by 1 (max 10)
     */
    public final void increaseQuantity() {
    }
    
    /**
     * Decrease quantity by 1 (min 1)
     */
    public final void decreaseQuantity() {
    }
    
    /**
     * Calculate total price based on current quantity
     */
    private final void calculateTotalPrice() {
    }
    
    /**
     * Confirm transaction and store in database
     */
    public final void confirmTransaction() {
    }
    
    /**
     * Cancel transaction and go back
     */
    public final void cancelTransaction() {
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
     * Get transaction display info
     */
    @org.jetbrains.annotations.NotNull()
    public final com.club.getraenkeapp.ui.quantity.QuantityViewModel.TransactionInfo getTransactionInfo() {
        return null;
    }
    
    /**
     * Navigation events for the UI to handle
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$NavigationEvent;", "", "()V", "Back", "TransactionSuccess", "Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$NavigationEvent$Back;", "Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$NavigationEvent$TransactionSuccess;", "app_debug"})
    public static abstract class NavigationEvent {
        
        private NavigationEvent() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$NavigationEvent$Back;", "Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$NavigationEvent;", "()V", "app_debug"})
        public static final class Back extends com.club.getraenkeapp.ui.quantity.QuantityViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            public static final com.club.getraenkeapp.ui.quantity.QuantityViewModel.NavigationEvent.Back INSTANCE = null;
            
            private Back() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\bH\u00c6\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$NavigationEvent$TransactionSuccess;", "Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$NavigationEvent;", "memberName", "", "beverageName", "quantity", "", "totalPrice", "", "(Ljava/lang/String;Ljava/lang/String;ID)V", "getBeverageName", "()Ljava/lang/String;", "getMemberName", "getQuantity", "()I", "getTotalPrice", "()D", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "app_debug"})
        public static final class TransactionSuccess extends com.club.getraenkeapp.ui.quantity.QuantityViewModel.NavigationEvent {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String memberName = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String beverageName = null;
            private final int quantity = 0;
            private final double totalPrice = 0.0;
            
            public TransactionSuccess(@org.jetbrains.annotations.NotNull()
            java.lang.String memberName, @org.jetbrains.annotations.NotNull()
            java.lang.String beverageName, int quantity, double totalPrice) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMemberName() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getBeverageName() {
                return null;
            }
            
            public final int getQuantity() {
                return 0;
            }
            
            public final double getTotalPrice() {
                return 0.0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            public final int component3() {
                return 0;
            }
            
            public final double component4() {
                return 0.0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.ui.quantity.QuantityViewModel.NavigationEvent.TransactionSuccess copy(@org.jetbrains.annotations.NotNull()
            java.lang.String memberName, @org.jetbrains.annotations.NotNull()
            java.lang.String beverageName, int quantity, double totalPrice) {
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
    
    /**
     * Transaction information data class
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011\u00a8\u0006\u001e"}, d2 = {"Lcom/club/getraenkeapp/ui/quantity/QuantityViewModel$TransactionInfo;", "", "memberName", "", "beverageName", "unitPrice", "", "quantity", "", "totalPrice", "(Ljava/lang/String;Ljava/lang/String;DID)V", "getBeverageName", "()Ljava/lang/String;", "getMemberName", "getQuantity", "()I", "getTotalPrice", "()D", "getUnitPrice", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    public static final class TransactionInfo {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String memberName = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String beverageName = null;
        private final double unitPrice = 0.0;
        private final int quantity = 0;
        private final double totalPrice = 0.0;
        
        public TransactionInfo(@org.jetbrains.annotations.NotNull()
        java.lang.String memberName, @org.jetbrains.annotations.NotNull()
        java.lang.String beverageName, double unitPrice, int quantity, double totalPrice) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMemberName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBeverageName() {
            return null;
        }
        
        public final double getUnitPrice() {
            return 0.0;
        }
        
        public final int getQuantity() {
            return 0;
        }
        
        public final double getTotalPrice() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final double component5() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.ui.quantity.QuantityViewModel.TransactionInfo copy(@org.jetbrains.annotations.NotNull()
        java.lang.String memberName, @org.jetbrains.annotations.NotNull()
        java.lang.String beverageName, double unitPrice, int quantity, double totalPrice) {
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