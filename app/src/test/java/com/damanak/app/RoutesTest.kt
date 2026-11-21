package com.damanak.app

import com.damanak.app.ui.navigation.Routes
import org.junit.Assert.assertEquals
import org.junit.Test

class RoutesTest {

    @Test
    fun testRouteConstants() {
        assertEquals("splash", Routes.SPLASH)
        assertEquals("onboarding", Routes.ONBOARDING)
        assertEquals("auth", Routes.AUTH)
        assertEquals("home", Routes.HOME)
        assertEquals("add_edit", Routes.ADD_EDIT)
        assertEquals("detail/{productId}", Routes.DETAIL)
        assertEquals("notifications", Routes.NOTIFICATIONS)
        assertEquals("search", Routes.SEARCH)
        assertEquals("profile", Routes.PROFILE)
        assertEquals("edit_profile", Routes.EDIT_PROFILE)
        assertEquals("reset_password", Routes.RESET_PASSWORD)
        assertEquals("maintenance_log/{productId}", Routes.MAINTENANCE_LOG)
        assertEquals("transfer/{productId}", Routes.TRANSFER)
        assertEquals("care_schedule/{productId}", Routes.CARE_SCHEDULE)
    }

    @Test
    fun testParameterizedRouteHelperFunctions() {
        assertEquals("detail/123", Routes.detail("123"))
        assertEquals("maintenance_log/456", Routes.maintenanceLog("456"))
        assertEquals("transfer/789", Routes.transfer("789"))
        assertEquals("care_schedule/101", Routes.careSchedule("101"))
    }
}
