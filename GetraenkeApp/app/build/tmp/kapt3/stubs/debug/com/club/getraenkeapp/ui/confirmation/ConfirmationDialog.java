package com.club.getraenkeapp.ui.confirmation;

/**
 * Confirmation Dialog Fragment
 *
 * PATTERN: DialogFragment with automatic dismissal, display transaction summary for 1 second,
 * auto-return to main screen after confirmation, handle storage success/error feedback.
 *
 * CRITICAL: 1-second success confirmation with auto-dismiss
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u0000  2\u00020\u0001:\u0001 B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0014\u0010\u0016\u001a\u00020\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\tJ(\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/club/getraenkeapp/ui/confirmation/ConfirmationDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "_binding", "Lcom/club/getraenkeapp/databinding/DialogConfirmationBinding;", "binding", "getBinding", "()Lcom/club/getraenkeapp/databinding/DialogConfirmationBinding;", "onDismissCallback", "Lkotlin/Function0;", "", "priceFormatter", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "setOnDismissCallback", "callback", "setupUI", "memberName", "", "beverageName", "quantity", "", "totalPrice", "", "Companion", "app_debug"})
public final class ConfirmationDialog extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.Nullable()
    private com.club.getraenkeapp.databinding.DialogConfirmationBinding _binding;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onDismissCallback;
    private final java.text.NumberFormat priceFormatter = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_MEMBER_NAME = "member_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_BEVERAGE_NAME = "beverage_name";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_QUANTITY = "quantity";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_TOTAL_PRICE = "total_price";
    private static final long AUTO_DISMISS_DELAY_MS = 1000L;
    @org.jetbrains.annotations.NotNull()
    public static final com.club.getraenkeapp.ui.confirmation.ConfirmationDialog.Companion Companion = null;
    
    public ConfirmationDialog() {
        super();
    }
    
    private final com.club.getraenkeapp.databinding.DialogConfirmationBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    /**
     * Setup UI with transaction details
     */
    private final void setupUI(java.lang.String memberName, java.lang.String beverageName, int quantity, double totalPrice) {
    }
    
    /**
     * Set callback for when dialog is dismissed
     */
    public final void setOnDismissCallback(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> callback) {
    }
    
    @java.lang.Override()
    public void onDismiss(@org.jetbrains.annotations.NotNull()
    android.content.DialogInterface dialog) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/club/getraenkeapp/ui/confirmation/ConfirmationDialog$Companion;", "", "()V", "ARG_BEVERAGE_NAME", "", "ARG_MEMBER_NAME", "ARG_QUANTITY", "ARG_TOTAL_PRICE", "AUTO_DISMISS_DELAY_MS", "", "newInstance", "Lcom/club/getraenkeapp/ui/confirmation/ConfirmationDialog;", "memberName", "beverageName", "quantity", "", "totalPrice", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.ui.confirmation.ConfirmationDialog newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String memberName, @org.jetbrains.annotations.NotNull()
        java.lang.String beverageName, int quantity, double totalPrice) {
            return null;
        }
    }
}