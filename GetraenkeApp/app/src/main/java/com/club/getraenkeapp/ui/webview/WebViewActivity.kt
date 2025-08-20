package com.club.getraenkeapp.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.JavascriptInterface
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.club.getraenkeapp.R
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.database.entities.Member
import com.club.getraenkeapp.data.database.entities.Beverage
import com.club.getraenkeapp.data.database.entities.Transaction
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.util.*

/**
 * WebView Activity - Loads React Frontend
 * 
 * Integrates the beautiful React frontend with Android backend using WebView
 * and JavaScript bridge for data communication
 */
class WebViewActivity : AppCompatActivity() {
    
    private lateinit var webView: WebView
    private lateinit var database: AppDatabase
    private val gson = Gson()
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Enable always-on display for club use
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        
        // Initialize database
        database = AppDatabase.getDatabase(this)
        
        // Create WebView
        webView = WebView(this)
        setContentView(webView)
        
        setupWebView()
        loadReactApp()
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.webViewClient = CustomWebViewClient()
        webView.webChromeClient = WebChromeClient()
        
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = true
        settings.allowContentAccess = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.builtInZoomControls = false
        settings.displayZoomControls = false
        
        // Add JavaScript bridge - temporarily disabled for testing
        // webView.addJavaScriptInterface(AndroidBridge(), "AndroidBridge")
    }
    
    private fun loadReactApp() {
        // Load the React app from assets
        webView.loadUrl("file:///android_asset/webapp/index.html")
    }
    
    private class CustomWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return false
        }
        
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            // React app loaded - can inject initial data if needed
        }
    }
    
    /**
     * JavaScript Bridge for React <-> Android Communication
     */
    inner class AndroidBridge {
        
        @JavascriptInterface
        fun getMembers(): String {
            return try {
                // Return mock data for now - we'll need to add suspend methods to DAO
                val mockMembers = listOf(
                    mapOf("id" to 1, "name" to "Alex Weber"),
                    mapOf("id" to 2, "name" to "Sarah Schmidt"),
                    mapOf("id" to 3, "name" to "Michael Müller")
                )
                gson.toJson(mockMembers)
            } catch (e: Exception) {
                "[]"
            }
        }
        
        @JavascriptInterface
        fun getBeverages(): String {
            return try {
                // Return mock data for now
                val mockBeverages = listOf(
                    mapOf("id" to 1, "name" to "Pilsner", "price" to 3.50, "category" to "beer"),
                    mapOf("id" to 2, "name" to "Weizen", "price" to 4.00, "category" to "beer"),
                    mapOf("id" to 3, "name" to "Cola", "price" to 2.50, "category" to "soft-drinks")
                )
                gson.toJson(mockBeverages)
            } catch (e: Exception) {
                "[]"
            }
        }
        
        @JavascriptInterface
        fun getTransactions(): String {
            return try {
                // Return empty for now
                gson.toJson(emptyList<Any>())
            } catch (e: Exception) {
                "[]"
            }
        }
        
        @JavascriptInterface
        fun createTransaction(memberId: Int, beverageId: Int, quantity: Int): String {
            return try {
                runBlocking {
                    val member = database.memberDao().getMemberById(memberId.toLong())
                    val beverage = database.beverageDao().getBeverageById(beverageId.toLong())
                    
                    if (member != null && beverage != null) {
                        val transaction = Transaction(
                            memberId = memberId.toLong(),
                            beverageId = beverageId.toLong(),
                            quantity = quantity,
                            unitPrice = beverage.price,
                            totalPrice = beverage.price * quantity,
                            timestamp = System.currentTimeMillis()
                        )
                        
                        val transactionId = database.transactionDao().insertTransaction(transaction)
                        gson.toJson(mapOf("success" to true, "transactionId" to transactionId))
                    } else {
                        gson.toJson(mapOf("success" to false, "error" to "Member or beverage not found"))
                    }
                }
            } catch (e: Exception) {
                gson.toJson(mapOf("success" to false, "error" to e.message))
            }
        }
        
        @JavascriptInterface
        fun createMember(name: String): String {
            return try {
                runBlocking {
                    val member = Member(name = name)
                    val memberId = database.memberDao().insertMember(member)
                    gson.toJson(mapOf("success" to true, "memberId" to memberId))
                }
            } catch (e: Exception) {
                gson.toJson(mapOf("success" to false, "error" to e.message))
            }
        }
        
        @JavascriptInterface
        fun createBeverage(name: String, price: Double, category: String): String {
            return try {
                runBlocking {
                    val beverage = Beverage(
                        name = name,
                        price = price,
                        category = category,
                        active = true
                    )
                    val beverageId = database.beverageDao().insertBeverage(beverage)
                    gson.toJson(mapOf("success" to true, "beverageId" to beverageId))
                }
            } catch (e: Exception) {
                gson.toJson(mapOf("success" to false, "error" to e.message))
            }
        }
        
        @JavascriptInterface
        fun updateMember(id: Int, name: String): String {
            return try {
                runBlocking {
                    val member = database.memberDao().getMemberById(id.toLong())
                    if (member != null) {
                        val updatedMember = member.copy(name = name)
                        database.memberDao().updateMember(updatedMember)
                        gson.toJson(mapOf("success" to true))
                    } else {
                        gson.toJson(mapOf("success" to false, "error" to "Member not found"))
                    }
                }
            } catch (e: Exception) {
                gson.toJson(mapOf("success" to false, "error" to e.message))
            }
        }
        
        @JavascriptInterface
        fun updateBeverage(id: Int, name: String, price: Double, category: String): String {
            return try {
                runBlocking {
                    val beverage = database.beverageDao().getBeverageById(id.toLong())
                    if (beverage != null) {
                        val updatedBeverage = beverage.copy(
                            name = name,
                            price = price,
                            category = category
                        )
                        database.beverageDao().updateBeverage(updatedBeverage)
                        gson.toJson(mapOf("success" to true))
                    } else {
                        gson.toJson(mapOf("success" to false, "error" to "Beverage not found"))
                    }
                }
            } catch (e: Exception) {
                gson.toJson(mapOf("success" to false, "error" to e.message))
            }
        }
        
        @JavascriptInterface
        fun deleteMember(id: Int): String {
            return try {
                runBlocking {
                    // Members don't have active field - just delete by ID
                    database.memberDao().deleteMemberById(id.toLong())
                    gson.toJson(mapOf("success" to true))
                }
            } catch (e: Exception) {
                gson.toJson(mapOf("success" to false, "error" to e.message))
            }
        }
        
        @JavascriptInterface
        fun deleteBeverage(id: Int): String {
            return try {
                runBlocking {
                    // Use deactivation instead of deletion for beverages
                    database.beverageDao().deactivateBeverage(id.toLong())
                    gson.toJson(mapOf("success" to true))
                }
            } catch (e: Exception) {
                gson.toJson(mapOf("success" to false, "error" to e.message))
            }
        }
        
        @JavascriptInterface
        fun showToast(message: String) {
            runOnUiThread {
                android.widget.Toast.makeText(this@WebViewActivity, message, android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
    
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}