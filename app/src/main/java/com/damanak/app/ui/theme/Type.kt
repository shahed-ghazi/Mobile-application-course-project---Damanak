package com.damanak.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.damanak.app.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val CairoFontName = GoogleFont("Cairo")

val CairoFontFamily = FontFamily(
    Font(googleFont = CairoFontName, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = CairoFontName, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = CairoFontName, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = CairoFontName, fontProvider = provider, weight = FontWeight.Bold)
)

val H1 = TextStyle(
    fontFamily = CairoFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 32.sp,
    lineHeight = 40.sp
)

val H2 = TextStyle(
    fontFamily = CairoFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 32.sp
)

val H3 = TextStyle(
    fontFamily = CairoFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp,
    lineHeight = 28.sp
)

val BodyLarge = TextStyle(
    fontFamily = CairoFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp
)

val BodyMedium = TextStyle(
    fontFamily = CairoFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp
)

val Caption = TextStyle(
    fontFamily = CairoFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp
)

val Label = TextStyle(
    fontFamily = CairoFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 20.sp
)

val Typography = Typography(
    headlineLarge = H1,
    headlineMedium = H2,
    headlineSmall = H3,
    bodyLarge = BodyLarge,
    bodyMedium = BodyMedium,
    bodySmall = Caption,
    labelLarge = Label
)
