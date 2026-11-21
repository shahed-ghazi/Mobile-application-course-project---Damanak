package com.damanak.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.temporal.ChronoUnit

private val Navy = Color(0xFF181C27)
private val Bg = Color(0xFFF7F5F3)
private val Green = Color(0xFF228B4E)
private val LightGreen = Color(0xFFE3FBEA)
private val Red = Color(0xFFE0524D)
private val LightRed = Color(0xFFFCE5E5)
private val Muted = Color(0xFF9296A0)
private val BorderColor = Color(0xFFE5E7EB)

data class ServiceVisit(
    val date: String,
    val description: String,
    val covered: Boolean,
    val cost: Double
)

@Composable
fun WarrantyDetailScreen(
    onNavigateHome: () -> Unit = {}
) {
    var productName by remember {
        mutableStateOf("آيفون 15 برو")
    }

    var storeName by remember {
        mutableStateOf("آبل ستور")
    }

    var expiryDate by remember {
        mutableStateOf("2027-03-15")
    }

    var showDelete by remember {
        mutableStateOf(false)
    }

    var showAdd by remember {
        mutableStateOf(false)
    }

    var showEdit by remember {
        mutableStateOf(false)
    }

    var showReceipt by remember {
        mutableStateOf(false)
    }

    var deleted by remember {
        mutableStateOf(false)
    }

    val visits = remember {
        mutableStateListOf(
            ServiceVisit(
                date = "2026-03-12",
                description = "الشاشة لا تعمل",
                covered = true,
                cost = 0.0
            ),
            ServiceVisit(
                date = "2026-03-20",
                description = "كسر في الشاشة",
                covered = false,
                cost = 350.0
            )
        )
    }

    val daysRemaining = remember(expiryDate) {
        try {
            ChronoUnit.DAYS.between(
                LocalDate.now(),
                LocalDate.parse(expiryDate)
            )
        } catch (_: Exception) {
            0L
        }
    }

    val isActive = daysRemaining > 0

    val monthsRemaining = if (isActive) {
        (daysRemaining / 30).toInt()
    } else {
        0
    }

    LaunchedEffect(deleted) {
        if (deleted) {
            delay(2000)
            onNavigateHome()
        }
    }

    if (deleted) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "✓",
                    fontSize = 54.sp,
                    color = Green
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "تم حذف الضمان بنجاح",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Navy
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "جاري العودة للرئيسية...",
                    color = Muted,
                    fontSize = 14.sp
                )
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg)
            .verticalScroll(rememberScrollState())
    ) {

        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                )
                .background(Navy)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            showDelete = true
                        }
                    ) {
                        TrashIcon()
                    }

                    Text(
                        text = "←",
                        color = Color.White,
                        fontSize = 26.sp,
                        modifier = Modifier.clickable {
                            onNavigateHome()
                        }
                    )
                }

                Spacer(Modifier.height(25.dp))

                Text(
                    text = productName,
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = "$storeName · تم الشراء في 15 مارس 2025",
                    color = Color(0xFFB0B3BD),
                    fontSize = 13.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Warranty status
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
                .offset(y = (-35).dp),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(75.dp)
                        .background(
                            if (isActive) LightGreen else LightRed,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isActive) {
                            "$monthsRemaining"
                        } else {
                            "!"
                        },
                        color = if (isActive) Green else Red,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = if (isActive) {
                            "الضمان ساري"
                        } else {
                            "الضمان منتهي"
                        },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        text = if (isActive) {
                            "باقي $daysRemaining يوم · ينتهي $expiryDate"
                        } else {
                            "انتهى في $expiryDate"
                        },
                        fontSize = 12.sp,
                        color = Muted,
                        textAlign = TextAlign.End
                    )
                }
            }
        }

        // Receipt and maintenance
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-10).dp)
                .padding(horizontal = 22.dp)
        ) {
            SectionTitle("الفاتورة")

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFECE7E2))
                    .clickable {
                        showReceipt = true
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .height(110.dp)
                        .clip(RoundedCornerShape(9.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "إيصال",
                        color = Muted,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(Modifier.height(28.dp))

            SectionTitle("سجل الصيانة")

            Spacer(Modifier.height(10.dp))

            if (visits.isEmpty()) {
                Text(
                    text = "لا توجد زيارات صيانة حتى الآن",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    textAlign = TextAlign.Center,
                    color = Muted
                )
            } else {
                visits
                    .sortedByDescending { it.date }
                    .forEach { visit ->
                        VisitCard(visit)
                        Spacer(Modifier.height(10.dp))
                    }
            }

            Spacer(Modifier.height(10.dp))

            OutlineAction("إضافة زيارة") {
                showAdd = true
            }

            Spacer(Modifier.height(12.dp))

            OutlineAction("تمديد / تعديل") {
                showEdit = true
            }

            Spacer(Modifier.height(35.dp))
        }
    }

    // Delete confirmation
    if (showDelete) {
        DeleteConfirmationDialog(
            onCancel = {
                showDelete = false
            },
            onConfirmDelete = {
                showDelete = false
                deleted = true
            }
        )
    }

    // Add maintenance visit
    if (showAdd) {
        AddVisitDialog(
            onDismiss = {
                showAdd = false
            },
            onSave = { visit ->
                visits.add(visit)
                showAdd = false
            }
        )
    }

    // Edit warranty
    if (showEdit) {
        EditWarrantyDialog(
            currentName = productName,
            currentStore = storeName,
            currentExpiry = expiryDate,
            onDismiss = {
                showEdit = false
            },
            onSave = { name, store, expiry ->
                productName = name
                storeName = store
                expiryDate = expiry
                showEdit = false
            }
        )
    }

    // Receipt placeholder
    if (showReceipt) {
        AlertDialog(
            onDismissRequest = {
                showReceipt = false
            },
            containerColor = Color.White,
            title = {
                Text(
                    text = "الفاتورة",
                    color = Navy
                )
            },
            text = {
                Text(
                    text = "هذه معاينة تجريبية. سيتم عرض صورة الفاتورة الفعلية عند ربط البيانات.",
                    color = Navy
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showReceipt = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Navy
                    )
                ) {
                    Text("إغلاق")
                }
            }
        )
    }
}

@Composable
private fun TrashIcon() {
    Canvas(
        modifier = Modifier.size(24.dp)
    ) {
        val w = size.width
        val h = size.height
        val sw = 2.dp.toPx()

        drawLine(
            Color.White,
            Offset(w * .2f, h * .25f),
            Offset(w * .8f, h * .25f),
            sw
        )

        drawLine(
            Color.White,
            Offset(w * .4f, h * .15f),
            Offset(w * .6f, h * .15f),
            sw
        )

        drawLine(
            Color.White,
            Offset(w * .3f, h * .25f),
            Offset(w * .35f, h * .85f),
            sw
        )

        drawLine(
            Color.White,
            Offset(w * .7f, h * .25f),
            Offset(w * .65f, h * .85f),
            sw
        )

        drawLine(
            Color.White,
            Offset(w * .35f, h * .85f),
            Offset(w * .65f, h * .85f),
            sw
        )
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Navy
    )
}

@Composable
private fun VisitCard(visit: ServiceVisit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, BorderColor),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (visit.covered) {
                                LightGreen
                            } else {
                                LightRed
                            }
                        )
                        .padding(
                            horizontal = 9.dp,
                            vertical = 6.dp
                        )
                ) {
                    Text(
                        text = if (visit.covered) {
                            "مشمول بالضمان"
                        } else {
                            "غير مشمول"
                        },
                        color = if (visit.covered) Green else Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = visit.date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Navy
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = visit.description,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                color = Muted,
                fontSize = 14.sp
            )

            if (visit.cost > 0) {
                Spacer(Modifier.height(5.dp))

                val formattedCost =
                    if (visit.cost % 1.0 == 0.0) {
                        visit.cost.toLong().toString()
                    } else {
                        visit.cost.toString()
                    }

                Text(
                    text = "$formattedCost ريال",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 12.sp,
                    color = Navy
                )
            }
        }
    }
}

@Composable
private fun OutlineAction(
    label: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, BorderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Navy
        )
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold
        )
    }
}

// Shared input style
@Composable
private fun DamanakInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    supportingText: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        modifier = modifier.fillMaxWidth(),
        isError = isError,
        singleLine = singleLine,
        minLines = minLines,
        textStyle = TextStyle(
            textDirection = TextDirection.ContentOrRtl
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        supportingText = if (supportingText == null) {
            null
        } else {
            {
                Text(
                    text = supportingText,
                    fontSize = 11.sp
                )
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Navy,
            focusedLabelColor = Navy,
            focusedTextColor = Navy,
            unfocusedTextColor = Navy,
            unfocusedBorderColor = BorderColor,
            unfocusedLabelColor = Muted,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            errorBorderColor = Red,
            errorLabelColor = Red,
            cursorColor = Navy
        )
    )
}

// Shared dialog design
@Composable
private fun DamanakDialogFrame(
    title: String,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 22.dp,
                        vertical = 24.dp
                    ),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Navy
                )

                Spacer(Modifier.height(18.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 420.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.End,
                    content = content
                )

                Spacer(Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.Start,
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss,
                        colors =
                            ButtonDefaults.textButtonColors(
                                contentColor = Navy
                            )
                    ) {
                        Text("إلغاء")
                    }

                    Spacer(Modifier.width(10.dp))

                    Button(
                        onClick = onSave,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Navy
                        )
                    ) {
                        Text(
                            text = "حفظ",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

// Add maintenance visit
@Composable
private fun AddVisitDialog(
    onDismiss: () -> Unit,
    onSave: (ServiceVisit) -> Unit
) {
    val today = remember {
        LocalDate.now().toString()
    }

    var date by remember {
        mutableStateOf(today)
    }

    var description by remember {
        mutableStateOf("")
    }

    var covered by remember {
        mutableStateOf(true)
    }

    var cost by remember {
        mutableStateOf("0")
    }

    var attempted by remember {
        mutableStateOf(false)
    }

    val parsedDate = try {
        LocalDate.parse(date)
    } catch (_: Exception) {
        null
    }

    val validDate =
        parsedDate != null &&
                !parsedDate.isAfter(LocalDate.now())

    val costNumber = cost.trim().toDoubleOrNull()

    val validCost =
        costNumber != null &&
                costNumber.isFinite() &&
                costNumber >= 0.0

    DamanakDialogFrame(
        title = "إضافة زيارة صيانة",
        onDismiss = onDismiss,
        onSave = {
            attempted = true

            if (
                validDate &&
                description.isNotBlank() &&
                validCost
            ) {
                onSave(
                    ServiceVisit(
                        date = date,
                        description = description.trim(),
                        covered = covered,
                        cost = costNumber!!
                    )
                )
            }
        }
    ) {
        DamanakInput(
            value = date,
            onValueChange = {
                date = it
            },
            label = "تاريخ الزيارة",
            isError = attempted && !validDate,
            supportingText =
                if (attempted && !validDate) {
                    "أدخلي تاريخًا صحيحًا غير مستقبلي (YYYY-MM-DD)"
                } else {
                    "YYYY-MM-DD"
                }
        )

        Spacer(Modifier.height(8.dp))

        DamanakInput(
            value = description,
            onValueChange = {
                description = it
            },
            label = "وصف الصيانة",
            singleLine = false,
            minLines = 2,
            isError =
                attempted && description.isBlank(),
            supportingText =
                if (
                    attempted &&
                    description.isBlank()
                ) {
                    "وصف الصيانة مطلوب"
                } else {
                    null
                }
        )

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "مشمولة بالضمان",
                color = Navy
            )

            Checkbox(
                checked = covered,
                onCheckedChange = {
                    covered = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Navy
                )
            )
        }

        Spacer(Modifier.height(8.dp))

        DamanakInput(
            value = cost,
            onValueChange = {
                cost = it
            },
            label = "التكلفة بالريال",
            keyboardType = KeyboardType.Decimal,
            isError = attempted && !validCost,
            supportingText =
                if (attempted && !validCost) {
                    "أدخلي تكلفة صحيحة، صفر أو أكثر"
                } else {
                    null
                }
        )
    }
}

// Edit warranty
@Composable
private fun EditWarrantyDialog(
    currentName: String,
    currentStore: String,
    currentExpiry: String,
    onDismiss: () -> Unit,
    onSave: (String, String, String) -> Unit
) {
    var name by remember {
        mutableStateOf(currentName)
    }

    var store by remember {
        mutableStateOf(currentStore)
    }

    var expiry by remember {
        mutableStateOf(currentExpiry)
    }

    var attempted by remember {
        mutableStateOf(false)
    }

    val validExpiry = try {
        LocalDate.parse(expiry)
        true
    } catch (_: Exception) {
        false
    }

    DamanakDialogFrame(
        title = "تعديل بيانات الضمان",
        onDismiss = onDismiss,
        onSave = {
            attempted = true

            if (
                name.isNotBlank() &&
                store.isNotBlank() &&
                validExpiry
            ) {
                onSave(
                    name.trim(),
                    store.trim(),
                    expiry
                )
            }
        }
    ) {
        DamanakInput(
            value = name,
            onValueChange = {
                name = it
            },
            label = "اسم المنتج",
            isError = attempted && name.isBlank(),
            supportingText =
                if (attempted && name.isBlank()) {
                    "اسم المنتج مطلوب"
                } else {
                    null
                }
        )

        Spacer(Modifier.height(9.dp))

        DamanakInput(
            value = store,
            onValueChange = {
                store = it
            },
            label = "اسم المتجر",
            isError = attempted && store.isBlank(),
            supportingText =
                if (attempted && store.isBlank()) {
                    "اسم المتجر مطلوب"
                } else {
                    null
                }
        )

        Spacer(Modifier.height(9.dp))

        DamanakInput(
            value = expiry,
            onValueChange = {
                expiry = it
            },
            label = "تاريخ انتهاء الضمان",
            isError = attempted && !validExpiry,
            supportingText =
                if (attempted && !validExpiry) {
                    "أدخلي التاريخ بصيغة YYYY-MM-DD"
                } else {
                    "YYYY-MM-DD"
                }
        )
    }
}