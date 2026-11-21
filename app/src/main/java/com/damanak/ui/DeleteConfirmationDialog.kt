
package com.damanak.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

private val Navy = Color(0xFF181C27)
private val Red = Color(0xFFE0524D)
private val LightRed = Color(0xFFFCE5E5)
private val BorderColor = Color(0xFFE5E7EB)

@Composable
fun DeleteConfirmationDialog(
    onCancel: () -> Unit,
    onConfirmDelete: () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(76.dp)
                        .background(LightRed, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.size(32.dp)) {
                        val w = size.width
                        val h = size.height
                        val stroke = 2.5.dp.toPx()

                        drawLine(
                            Red,
                            Offset(w * 0.2f, h * 0.25f),
                            Offset(w * 0.8f, h * 0.25f),
                            stroke
                        )
                        drawLine(
                            Red,
                            Offset(w * 0.4f, h * 0.13f),
                            Offset(w * 0.6f, h * 0.13f),
                            stroke
                        )
                        val bin = Path().apply {
                            moveTo(w * 0.28f, h * 0.30f)
                            lineTo(w * 0.34f, h * 0.88f)
                            lineTo(w * 0.66f, h * 0.88f)
                            lineTo(w * 0.72f, h * 0.30f)
                        }
                        drawPath(
                            path = bin,
                            color = Red,
                            style = Stroke(
                                width = stroke
                            )
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "حذف الضمان؟",
                    color = Navy,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "سيتم حذف المنتج والإيصال وسجل الصيانة نهائياً.",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(28.dp))

                Button(
                    onClick = onConfirmDelete,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red
                    )
                ) {
                    Text(
                        "حذف",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, BorderColor),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Navy
                    )
                ) {
                    Text(
                        "إلغاء",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
