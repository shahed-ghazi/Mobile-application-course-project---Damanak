package com.damanak.ui

import java.time.LocalDate
import java.time.temporal.ChronoUnit

enum class WarrantyStatus(val labelAr: String, val priority: Int) {
    EXPIRING_SOON("قارب ينتهي", 1),
    ACTIVE("ساري", 2),
    EXPIRED("منتهي", 3)
}

data class WarrantyItem(
    val id: String,
    val productName: String,
    val storeName: String,
    val purchaseDate: LocalDate,
    val expiryDate: LocalDate
) {
    fun getStatus(today: LocalDate = LocalDate.now()): WarrantyStatus {
        val daysLeft = ChronoUnit.DAYS.between(today, expiryDate)
        return when {
            daysLeft < 0 -> WarrantyStatus.EXPIRED
            daysLeft <= 30 -> WarrantyStatus.EXPIRING_SOON
            else -> WarrantyStatus.ACTIVE
        }
    }

    fun getRemainingText(today: LocalDate = LocalDate.now()): String {
        val daysLeft = ChronoUnit.DAYS.between(today, expiryDate)
        return when {
            daysLeft < 0 -> "منتهي"
            daysLeft <= 30 -> "قارب ينتهي · $daysLeft يوم"
            daysLeft < 365 -> "ساري · ${daysLeft / 30} شهر"
            else -> "ساري · ${daysLeft / 365} سنة"
        }
    }
}

fun List<WarrantyItem>.sortByUrgency(today: LocalDate = LocalDate.now()): List<WarrantyItem> {
    return this.sortedWith(
        compareBy<WarrantyItem> { it.getStatus(today).priority }
            .thenBy { it.expiryDate }
    )
}