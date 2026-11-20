package com.damanak.app.ui.care

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.damanak.app.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class CareInterval(val months: Int, val labelResId: Int) {
    THREE_MONTHS(3, R.string.care_interval_3_months),
    SIX_MONTHS(6, R.string.care_interval_6_months)
}

data class CareScheduleUiState(
    val deviceName: String = "iPhone 15 Pro",
    val careTask: String = "فحص البطارية وتنظيف الجهاز",
    val selectedInterval: CareInterval = CareInterval.THREE_MONTHS,
    val isDropdownExpanded: Boolean = false,
    val alertsEnabled: Boolean = true,
    val nextReminderDate: String = ""
)

class CareScheduleViewModel(
    val productId: String = "sample-id"
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CareScheduleUiState(
            nextReminderDate = calculateNextReminderDate(CareInterval.THREE_MONTHS)
        )
    )
    val uiState: StateFlow<CareScheduleUiState> = _uiState.asStateFlow()

    fun toggleDropdown() {
        _uiState.update { currentState ->
            currentState.copy(isDropdownExpanded = !currentState.isDropdownExpanded)
        }
    }

    fun selectInterval(interval: CareInterval) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedInterval = interval,
                isDropdownExpanded = false,
                nextReminderDate = calculateNextReminderDate(interval)
            )
        }
    }

    fun setAlertsEnabled(enabled: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(alertsEnabled = enabled)
        }
    }

    fun save() {
        // TODO: persist to Firestore
    }

    private fun calculateNextReminderDate(interval: CareInterval): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, interval.months)
        val arabicLocale = Locale.forLanguageTag("ar")
        val formatter = SimpleDateFormat("d MMMM yyyy", arabicLocale)
        return formatter.format(calendar.time)
    }

    companion object {
        fun factory(productId: String): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CareScheduleViewModel(productId) as T
            }
        }
    }
}
