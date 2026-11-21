package com.damanak.app.ui.navigation

object Routes {
    const val SPLASH = "splash"
    const val ONBOARDING = "onboarding"
    const val AUTH = "auth"
    const val SIGN_UP = "sign_up"
    const val HOME = "home"
    const val ADD_EDIT = "add_edit"
    const val DETAIL = "detail/{productId}"
    const val NOTIFICATIONS = "notifications"
    const val SEARCH = "search"
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"
    const val RESET_PASSWORD = "reset_password"
    const val MAINTENANCE_LOG = "maintenance_log/{productId}"
    const val TRANSFER = "transfer/{productId}"
    const val CARE_SCHEDULE = "care_schedule/{productId}"

    fun detail(productId: String) = "detail/$productId"
    fun maintenanceLog(productId: String) = "maintenance_log/$productId"
    fun transfer(productId: String) = "transfer/$productId"
    fun careSchedule(productId: String) = "care_schedule/$productId"
}
