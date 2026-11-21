package com.damanak.auth.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.damanak.auth.ui.theme.*

@Composable
fun PasswordResetScreen(
    onResetSent: () -> Unit = {},
    onNavigateBack: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var isSent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "استعادة كلمة المرور",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "أدخل بريدك الإلكتروني لإرسال رابط إعادة تعيين كلمة المرور",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(28.dp))

        LabeledInputField(
            label = "البريد الإلكتروني",
            value = email,
            onValueChange = { email = it },
            placeholder = "example@email.com",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                isSent = true
                onResetSent()
            },
            colors = ButtonDefaults.buttonColors(containerColor = DarkNavy),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = "إرسال رابط الاستعادة",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        if (isSent) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "تم إرسال رابط استعادة كلمة المرور إلى بريدك الإلكتروني بنجاح.",
                color = StatusSuccess,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "العودة لتسجيل الدخول",
            color = WarmBrown,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onNavigateBack() }
        )
    }
}
