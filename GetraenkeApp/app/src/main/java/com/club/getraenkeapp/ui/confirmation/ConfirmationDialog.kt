package com.club.getraenkeapp.ui.confirmation

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.DialogFragment
import com.club.getraenkeapp.databinding.DialogConfirmationBinding
import java.text.NumberFormat
import java.util.*

/**
 * Confirmation Dialog Fragment
 * 
 * PATTERN: DialogFragment with automatic dismissal, display transaction summary for 1 second,
 * auto-return to main screen after confirmation, handle storage success/error feedback.
 * 
 * CRITICAL: 1-second success confirmation with auto-dismiss
 */
class ConfirmationDialog : DialogFragment() {
    
    private var _binding: DialogConfirmationBinding? = null
    private val binding get() = _binding!!
    
    private var onDismissCallback: (() -> Unit)? = null
    private val priceFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    
    companion object {
        private const val ARG_MEMBER_NAME = "member_name"
        private const val ARG_BEVERAGE_NAME = "beverage_name"
        private const val ARG_QUANTITY = "quantity"
        private const val ARG_TOTAL_PRICE = "total_price"
        
        private const val AUTO_DISMISS_DELAY_MS = 1000L // 1 second
        
        fun newInstance(
            memberName: String,
            beverageName: String,
            quantity: Int,
            totalPrice: Double
        ): ConfirmationDialog {
            return ConfirmationDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_MEMBER_NAME, memberName)
                    putString(ARG_BEVERAGE_NAME, beverageName)
                    putInt(ARG_QUANTITY, quantity)
                    putDouble(ARG_TOTAL_PRICE, totalPrice)
                }
            }
        }
    }
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogConfirmationBinding.inflate(layoutInflater)
        
        val memberName = arguments?.getString(ARG_MEMBER_NAME) ?: ""
        val beverageName = arguments?.getString(ARG_BEVERAGE_NAME) ?: ""
        val quantity = arguments?.getInt(ARG_QUANTITY) ?: 1
        val totalPrice = arguments?.getDouble(ARG_TOTAL_PRICE) ?: 0.0
        
        setupUI(memberName, beverageName, quantity, totalPrice)
        
        val dialog = Dialog(requireContext())
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false) // Prevent manual cancellation
        
        // CRITICAL: Auto-dismiss after 1 second
        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded && dialog.isShowing) {
                dismissAllowingStateLoss()
            }
        }, AUTO_DISMISS_DELAY_MS)
        
        return dialog
    }
    
    /**
     * Setup UI with transaction details
     */
    private fun setupUI(memberName: String, beverageName: String, quantity: Int, totalPrice: Double) {
        // PATTERN: Confirmation shows member name, beverage, quantity, and total cost
        val summaryText = getString(
            com.club.getraenkeapp.R.string.confirmation_format,
            memberName,
            beverageName,
            quantity,
            totalPrice
        )
        
        binding.textViewSummary.text = summaryText
    }
    
    /**
     * Set callback for when dialog is dismissed
     */
    fun setOnDismissCallback(callback: () -> Unit) {
        onDismissCallback = callback
    }
    
    override fun onDismiss(dialog: android.content.DialogInterface) {
        super.onDismiss(dialog)
        onDismissCallback?.invoke()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}