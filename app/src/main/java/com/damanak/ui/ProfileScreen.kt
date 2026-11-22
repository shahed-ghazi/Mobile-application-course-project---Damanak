package com.example.ui




import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF111827)
private val Brown = Color(0xFF7C5E4A)
private val Bg = Color(0xFFF8F6F4)
private val Gray = Color(0xFF8D95A3)
private val Red = Color(0xFFBD191D)

@Composable
fun ProfileScreen(
    name: String = "اسم المستخدم",
    email: String = "user@example.com",
    onEdit: () -> Unit = {},
    onReset: () -> Unit = {}
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
                    verticalAlignment = Alignment.CenterVertically
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

                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .background(Brown, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            name.firstOrNull()?.toString() ?: "م",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

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
private fun SummaryValue(
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
private fun SectionLabel(text: String) {
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
private fun SettingItem(
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
private fun NavText(
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

