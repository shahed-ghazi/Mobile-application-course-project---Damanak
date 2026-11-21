
package com.damanak.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val Navy = Color(0xFF111827)
private val Cream = Color(0xFFF8F6F3)
private val FieldBorder = Color(0xFFE5E7EB)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditWarrantyScreen(
    onBackClick: () -> Unit = {},
    isEditMode: Boolean = false,
    onSaveClick: () -> Unit = {}
) {
    var productName by remember { mutableStateOf("") }
    var storeName by remember { mutableStateOf("") }
    var purchaseDate by remember { mutableStateOf("") }
    var warrantyDuration by remember { mutableStateOf("سنة") }
    var customMonths by remember { mutableStateOf("") }
    var reminderEnabled by remember { mutableStateOf(true) }
    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {

        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Navy,
                    RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                )
                .padding(
                    horizontal = 24.dp,
                    vertical = 24.dp
                ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isEditMode) "تعديل الضمان" else "ضمان جديد",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "➜",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.clickable {
                    onBackClick()
                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {

            Spacer(modifier = Modifier.height(28.dp))

            // Receipt upload
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        Color(0xFFF0EBE5),
                        RoundedCornerShape(12.dp)
                    )
                    .border(
                        BorderStroke(
                            1.dp,
                            Color(0xFFE5C78E)
                        ),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "▣",
                        fontSize = 32.sp,
                        color = Color(0xFF80634F)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "صوّر الفاتورة أو ارفعها",
                        fontSize = 14.sp,
                        color = Color(0xFF80634F),
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "حتى ٥ ميجا • JPG أو PDF",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Product name
            WarrantyTextField(
                label = "اسم المنتج",
                value = productName,
                onValueChange = { productName = it },
                placeholder = "مثال: آيفون 15 برو"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Store
            WarrantyTextField(
                label = "المتجر",
                value = storeName,
                onValueChange = { storeName = it },
                placeholder = "مثال: إكسترا"
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Purchase date
            Text(
                text = "تاريخ الشراء",
                fontSize = 14.sp,
                color = Navy,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = { showDatePicker = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White
                ),
                border = BorderStroke(1.dp, FieldBorder)
            ) {
                Text(
                    text = purchaseDate.ifEmpty {
                        "اختر تاريخ الشراء  📅"
                    },
                    color = Navy,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Warranty duration
            Text(
                text = "مدة الضمان",
                fontSize = 14.sp,
                color = Navy,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "مخصص",
                    "٣ سنوات",
                    "سنتين",
                    "سنة"
                ).forEach { duration ->

                    val selected = warrantyDuration == duration

                    OutlinedButton(
                        onClick = {
                            warrantyDuration = duration
                        },
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(
                            horizontal = 2.dp,
                            vertical = 10.dp
                        ),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor =
                                if (selected) Navy else Color.White,
                            contentColor =
                                if (selected) Color.White else Navy
                        ),
                        border = BorderStroke(
                            1.dp,
                            if (selected) Navy else FieldBorder
                        )
                    ) {
                        Text(
                            text = duration,
                            fontSize = 12.sp,
                            maxLines = 1
                        )
                    }
                }
            }

            if (warrantyDuration == "مخصص") {
                Spacer(modifier = Modifier.height(12.dp))

                WarrantyTextField(
                    label = "مدة الضمان بالأشهر",
                    value = customMonths,
                    onValueChange = {
                        customMonths = it.filter { char ->
                            char.isDigit()
                        }
                    },
                    placeholder = "مثال: 18"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Reminder
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ذكّرني قبل الانتهاء بـ٣٠ يوم",
                    fontSize = 14.sp,
                    color = Navy
                )

                Spacer(modifier = Modifier.width(12.dp))

                Switch(
                    checked = reminderEnabled,
                    onCheckedChange = {
                        reminderEnabled = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Navy
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Save button
            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Navy
                )
            ) {
                Text(
                    text = if (isEditMode)
                        "حفظ التعديلات"
                    else
                        "حفظ الضمان",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Date picker
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            purchaseDate = SimpleDateFormat(
                                "dd/MM/yyyy",
                                Locale.getDefault()
                            ).apply {
                                timeZone = java.util.TimeZone.getTimeZone("UTC")
                            }.format(Date(it))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("تأكيد")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("إلغاء")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}

@Composable
private fun WarrantyTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Navy,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Navy,
                unfocusedBorderColor = FieldBorder
            )
        )
    }
}
