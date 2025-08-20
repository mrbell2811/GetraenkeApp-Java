package com.club.getraenkeapp.ui.webview;

/**
 * WebView Activity - Loads React Frontend
 *
 * Integrates the beautiful React frontend with Android backend using WebView
 * and JavaScript bridge for data communication
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0013\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0016J\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0015J\b\u0010\u0011\u001a\u00020\fH\u0014J\b\u0010\u0012\u001a\u00020\fH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/club/getraenkeapp/ui/webview/WebViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "database", "Lcom/club/getraenkeapp/data/database/AppDatabase;", "gson", "Lcom/google/gson/Gson;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "webView", "Landroid/webkit/WebView;", "loadReactApp", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupWebView", "AndroidBridge", "CustomWebViewClient", "app_debug"})
public final class WebViewActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.webkit.WebView webView;
    private com.club.getraenkeapp.data.database.AppDatabase database;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
    
    public WebViewActivity() {
        super();
    }
    
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    private final void setupWebView() {
    }
    
    private final void loadReactApp() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    /**
     * JavaScript Bridge for React <-> Android Communication
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0007J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J \u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\fH\u0007J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\fH\u0007J\b\u0010\u0012\u001a\u00020\u0004H\u0007J\b\u0010\u0013\u001a\u00020\u0004H\u0007J\b\u0010\u0014\u001a\u00020\u0004H\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0004H\u0007J(\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0007J\u0018\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a8\u0006\u001a"}, d2 = {"Lcom/club/getraenkeapp/ui/webview/WebViewActivity$AndroidBridge;", "", "(Lcom/club/getraenkeapp/ui/webview/WebViewActivity;)V", "createBeverage", "", "name", "price", "", "category", "createMember", "createTransaction", "memberId", "", "beverageId", "quantity", "deleteBeverage", "id", "deleteMember", "getBeverages", "getMembers", "getTransactions", "showToast", "", "message", "updateBeverage", "updateMember", "app_debug"})
    public final class AndroidBridge {
        
        public AndroidBridge() {
            super();
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMembers() {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBeverages() {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getTransactions() {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createTransaction(int memberId, int beverageId, int quantity) {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createMember(@org.jetbrains.annotations.NotNull()
        java.lang.String name) {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String createBeverage(@org.jetbrains.annotations.NotNull()
        java.lang.String name, double price, @org.jetbrains.annotations.NotNull()
        java.lang.String category) {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String updateMember(int id, @org.jetbrains.annotations.NotNull()
        java.lang.String name) {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String updateBeverage(int id, @org.jetbrains.annotations.NotNull()
        java.lang.String name, double price, @org.jetbrains.annotations.NotNull()
        java.lang.String category) {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String deleteMember(int id) {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String deleteBeverage(int id) {
            return null;
        }
        
        @android.webkit.JavascriptInterface()
        public final void showToast(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u001c\u0010\t\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016\u00a8\u0006\r"}, d2 = {"Lcom/club/getraenkeapp/ui/webview/WebViewActivity$CustomWebViewClient;", "Landroid/webkit/WebViewClient;", "()V", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "shouldOverrideUrlLoading", "", "request", "Landroid/webkit/WebResourceRequest;", "app_debug"})
    static final class CustomWebViewClient extends android.webkit.WebViewClient {
        
        public CustomWebViewClient() {
            super();
        }
        
        @java.lang.Override()
        public boolean shouldOverrideUrlLoading(@org.jetbrains.annotations.Nullable()
        android.webkit.WebView view, @org.jetbrains.annotations.Nullable()
        android.webkit.WebResourceRequest request) {
            return false;
        }
        
        @java.lang.Override()
        public void onPageFinished(@org.jetbrains.annotations.Nullable()
        android.webkit.WebView view, @org.jetbrains.annotations.Nullable()
        java.lang.String url) {
        }
    }
}