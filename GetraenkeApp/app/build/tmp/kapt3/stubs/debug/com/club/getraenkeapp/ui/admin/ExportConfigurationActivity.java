package com.club.getraenkeapp.ui.admin;

/**
 * Activity for configuring export settings including local and network paths
 * and automatic backup intervals.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001f"}, d2 = {"Lcom/club/getraenkeapp/ui/admin/ExportConfigurationActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/club/getraenkeapp/databinding/ActivityExportConfigurationBinding;", "directoryPickerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "viewModel", "Lcom/club/getraenkeapp/ui/admin/ExportConfigurationViewModel;", "getViewModel", "()Lcom/club/getraenkeapp/ui/admin/ExportConfigurationViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "browseForLocalDirectory", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "performMonthlyClosing", "saveConfiguration", "setupClickListeners", "setupInputValidation", "setupObservers", "testLocalExport", "testNetworkPath", "updateBackupStatus", "config", "Lcom/club/getraenkeapp/utils/ExportUtils$ExportConfiguration;", "updateNextBackupDisplay", "app_debug"})
public final class ExportConfigurationActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.club.getraenkeapp.databinding.ActivityExportConfigurationBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> directoryPickerLauncher = null;
    
    public ExportConfigurationActivity() {
        super();
    }
    
    private final com.club.getraenkeapp.ui.admin.ExportConfigurationViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupInputValidation() {
    }
    
    private final void saveConfiguration() {
    }
    
    private final void browseForLocalDirectory() {
    }
    
    private final void testLocalExport() {
    }
    
    private final void testNetworkPath() {
    }
    
    private final void performMonthlyClosing() {
    }
    
    private final void updateBackupStatus(com.club.getraenkeapp.utils.ExportUtils.ExportConfiguration config) {
    }
    
    private final void updateNextBackupDisplay() {
    }
}