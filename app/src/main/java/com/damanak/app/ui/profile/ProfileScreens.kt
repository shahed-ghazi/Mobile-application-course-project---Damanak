package com.damanak.app.ui.profile





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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colors
private val Navy = Color(0xFF111827)
private val Brown = Color(0xFF7C5E4A)
private val Bg = Color(0xFFF8F6F4)
private val Beige = Color(0xFFECE7E1)
private val Gray = Color(0xFF8D95A3)
private val Red = Color(0xFFBD191D)

// Common avatar
@Composable
fun Avatar(
    name: String,
    size: Int = 72
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .background(Brown, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.firstOrNull()?.toString() ?: "م",
            color = Color.White,
            fontSize = (size * 0.43f).sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// PROFILE SCREEN
@Composable
fun ProfileScreen(
    name: String,
    email: String,
    onEdit: () -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp)
                    .background(
                        Navy,
                        RoundedCornerShape(
                            bottomStart = 24.dp,
                            bottomEnd = 24.dp
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            name,
                            color = Color.White,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(5.dp))

                        Text(
                            email,
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )

                        Spacer(Modifier.height(12.dp))

                        Text(
                            "آخر مزامنة: اليوم",
                            color = Gray,
                            fontSize = 10.sp
                        )
                    }

                    Spacer(Modifier.width(15.dp))

                    Avatar(name, 65)
                }
            }

            // Warranty summary
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .offset(y = (-18).dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(14.dp)
                    )
                    .padding(vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SummaryValue("12", "الكل")
                SummaryValue("3", "قريب الانتهاء")
                SummaryValue("2", "منتهي")
            }

            Spacer(Modifier.height(6.dp))

            SectionLabel("الإعدادات")

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(14.dp)
                    )
            ) {
                SettingItem("اللغة", "العربية")
                HorizontalDivider(
                    color = Bg,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
                SettingItem("التنبيهات", "مفعلة")
            }

            Spacer(Modifier.height(24.dp))

            SectionLabel("الحساب")

            Spacer(Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(14.dp)
                    )
            ) {
                SettingItem(
                    title = "تعديل الملف الشخصي",
                    value = "‹",
                    onClick = onEdit
                )

                HorizontalDivider(
                    color = Bg,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )

                SettingItem(
                    title = "تغيير كلمة المرور",
                    value = "‹",
                    onClick = onReset
                )
            }

            Spacer(Modifier.height(45.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .height(45.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red
                )
            ) {
                Text("تسجيل الخروج")
            }

            Spacer(Modifier.height(20.dp))
        }

        // Bottom navigation (visual only)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavText("حسابي", "●")
            NavText("تنبيهات", "○")
            NavText("بحث", "⌕")
            NavText("الرئيسية", "⌂")
        }
    }
}

@Composable
fun SummaryValue(
    number: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            number,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Navy
        )

        Spacer(Modifier.height(3.dp))

        Text(
            label,
            fontSize = 10.sp,
            color = Gray
        )
    }
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        textAlign = TextAlign.Right,
        fontSize = 12.sp,
        color = Gray
    )
}

@Composable
fun SettingItem(
    title: String,
    value: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            value,
            color = Gray,
            fontSize = 12.sp
        )

        Text(
            title,
            color = Navy,
            fontSize = 13.sp
        )
    }
}

@Composable
fun NavText(
    title: String,
    symbol: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            symbol,
            fontSize = 21.sp,
            color = Navy
        )
        Text(
            title,
            fontSize = 10.sp,
            color = Gray
        )
    }
}

// Header for other screens
@Composable
fun ScreenHeader(
    title: String,
    subtitle: String,
    onBack: () -> Unit
) {
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
                title,
                fontSize = 21.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(5.dp))

            Text(
                subtitle,
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
}

// EDIT PROFILE SCREEN
@Composable
fun EditProfileScreen(
    name: String,
    email: String,
    onBack: () -> Unit,
    onSave: (String, String) -> Unit
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
        ScreenHeader(
            title = "تعديل الملف الشخصي",
            subtitle = "معلومات حسابك كما تحب أن تظهر",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(25.dp))

            Avatar(editedName, 82)

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
                textStyle = androidx.compose.ui.text.TextStyle(
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
                textStyle = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Right,
                    fontSize = 14.sp
                ),
                shape = RoundedCornerShape(14.dp),
                colors = fieldColors()
            )

            Spacer(Modifier.height(24.dp))

            NoticeBox(
                "نستخدم بريدك الإلكتروني لإرسال تنبيهات الضمان واستعادة حسابك بأمان."
            )
        }

        BottomButton(
            text = "حفظ التغييرات",
            onClick = {
                onSave(editedName, editedEmail)
            }
        )
    }
}

// RESET PASSWORD SCREEN
@Composable
fun ResetPasswordScreen(
    email: String,
    onBack: () -> Unit
) {
    var inputEmail by rememberSaveable {
        mutableStateOf(email)
    }

    var showMessage by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg)
    ) {
        ScreenHeader(
            title = "إعادة تعيين كلمة المرور",
            subtitle = "خطوة بسيطة لاستعادة حسابك",
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(86.dp)
                    .background(Beige, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "🔒",
                    fontSize = 33.sp
                )
            }

            Spacer(Modifier.height(22.dp))

            Text(
                "نسيت كلمة المرور؟",
                color = Navy,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(12.dp))

            Text(
                "أدخل بريدك الإلكتروني لتلقي رابط إعادة تعيين كلمة المرور",
                color = Gray,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Spacer(Modifier.height(30.dp))

            InputLabel("البريد الإلكتروني")

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = inputEmail,
                onValueChange = {
                    inputEmail = it
                    showMessage = false
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = fieldColors()
            )

            Spacer(Modifier.height(22.dp))

            NoticeBox(
                "تحقق من صندوق الوارد والبريد غير المرغوب فيه بعد إرسال الرابط"
            )

            Spacer(Modifier.height(22.dp))

            Text(
                "العودة إلى تسجيل الدخول",
                color = Brown,
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    onBack()
                }
            )

            if (showMessage) {
                Spacer(Modifier.height(15.dp))

                Text(
                    "هذه واجهة تجريبية فقط",
                    color = Brown,
                    fontSize = 12.sp
                )
            }
        }

        BottomButton(
            text = "إرسال الرابط",
            onClick = {
                showMessage = true
            }
        )
    }
}

@Composable
fun InputLabel(text: String) {
    Text(
        text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Right,
        fontSize = 12.sp,
        color = Navy
    )
}

@Composable
fun fieldColors() =
    OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedBorderColor = Brown,
        unfocusedBorderColor = Color(0xFFD5DAE2)
    )

@Composable
fun NoticeBox(text: String) {
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
            text = text,
            color = Brown,
            fontSize = 12.sp,
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun BottomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
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
            text,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}


