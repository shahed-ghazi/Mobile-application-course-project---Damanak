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
fun SignUpScreen(
    onSignUpSuccess: () -> Unit = {},
    onNavigateToSignIn: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var nationalId by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "إنشاء حساب جديد",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "أدخل بياناتك للانضمام إلى منصة ضمانك",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        LabeledInputField(
            label = "الاسم الكامل",
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "أدخل اسمك الكامل"
        )

        Spacer(modifier = Modifier.height(14.dp))

        LabeledInputField(
            label = "رقم الهوية الوطنية / الإقامة",
            value = nationalId,
            onValueChange = { nationalId = it },
            placeholder = "10XXXXXXXX",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(14.dp))

        LabeledInputField(
            label = "رقم الجوال",
            value = phone,
            onValueChange = { phone = it },
            placeholder = "05XXXXXXXX",
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(14.dp))

        LabeledInputField(
            label = "البريد الإلكتروني",
            value = email,
            onValueChange = { email = it },
            placeholder = "example@email.com",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(14.dp))

        LabeledInputField(
            label = "كلمة المرور",
            value = password,
            onValueChange = { password = it },
            placeholder = "••••••••",
            isPassword = true,
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onSignUpSuccess() },
            colors = ButtonDefaults.buttonColors(containerColor = DarkNavy),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = "إنشاء الحساب",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "لديك حساب بالفعل؟ ", color = TextSecondary, fontSize = 14.sp)
            Text(
                text = "تسجيل الدخول",
                color = WarmBrown,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable { onNavigateToSignIn() }
            )
        }
    }
}

