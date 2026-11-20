package com.damanak.app.ui.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TransferUiState(
    val deviceName: String = "iPhone 15 Pro",
    val deviceDetails: String = "تيتانيوم طبيعي · ٢٥٦ جيجابايت",
    val transferLink: String = ""
)

class TransferViewModel(
    val productId: String = "sample-id"
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        TransferUiState(
            deviceName = "iPhone 15 Pro",
            deviceDetails = "تيتانيوم طبيعي · ٢٥٦ جيجابايت",
            // TODO(M4): replace with a real generated QR code and transfer link logic
            transferLink = "https://damanak.app/transfer/$productId"
        )
    )
    val uiState: StateFlow<TransferUiState> = _uiState.asStateFlow()

    companion object {
        fun factory(productId: String): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TransferViewModel(productId) as T
            }
        }
    }
}
