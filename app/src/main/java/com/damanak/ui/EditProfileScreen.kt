package com.example.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF111827)
private val Brown = Color(0xFF7C5E4A)
private val Bg = Color(0xFFF8F6F4)
private val Beige = Color(0xFFECE7E1)
@Composable
fun EditProfileScreen(
    name: String = "اسم المستخدم",
    email: String = "user@example.com",
    onBack: () -> Unit = {},
    onSave: (String, String) -> Unit = { _, _ -> }
) {
    var editedName by rememberSaveable(name) {
        mutableStateOf(name)
    }

    var editedEmail by rememberSaveable(email) {
        mutableStateOf(email)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .background(
                    Navy,
                    RoundedCornerShape(
                        bottomStart = 24.dp,
                        bottomEnd = 24.dp
                    )
                )
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "تعديل الملف الشخصي",
                    fontSize = 21.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    "معلومات حسابك كما تحب أن تظهر",
                    fontSize = 11.sp,
                    color = Color.LightGray
                )
            }

            Text(
                text = "→",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { onBack() }
                    .padding(20.dp),
                color = Color.White,
                fontSize = 25.sp
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(25.dp))

            // Profile avatar
            Box(
                modifier = Modifier
                    .size(82.dp)
                    .background(Brown, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    editedName.firstOrNull()?.toString() ?: "م",
                    color = Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(9.dp))

            Text(
                "تغيير الصورة الشخصية",
                fontSize = 12.sp,
                color = Brown
            )

            Spacer(Modifier.height(28.dp))

            InputLabel("الاسم الكامل")

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = editedName,
                onValueChange = { editedName = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Right,
                    fontSize = 14.sp
                ),
                shape = RoundedCornerShape(14.dp),
                colors = fieldColors()
            )

            Spacer(Modifier.height(22.dp))

            InputLabel("البريد الإلكتروني")

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = editedEmail,
                onValueChange = { editedEmail = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Right,
                    fontSize = 14.sp
                ),
                shape = RoundedCornerShape(14.dp),
                colors = fieldColors()
            )

            Spacer(Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Beige,
                        RoundedCornerShape(13.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "نستخدم بريدك الإلكتروني لإرسال تنبيهات الضمان واستعادة حسابك بأمان.",
                    color = Brown,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Button(
            onClick = {
                onSave(editedName, editedEmail)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 22.dp
                )
                .height(50.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Navy
            )
        ) {
            Text(
                "حفظ التغييرات",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun InputLabel(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Right,
        fontSize = 12.sp,
        color = Navy
    )
}

@Composable
private fun fieldColors() =
    OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedBorderColor = Brown,
        unfocusedBorderColor = Color(0xFFD5DAE2)
    )