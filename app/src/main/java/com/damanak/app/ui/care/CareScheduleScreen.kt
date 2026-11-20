package com.damanak.app.ui.care

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.damanak.app.R
import com.damanak.app.ui.components.DamanakHeader
import com.damanak.app.ui.theme.Background
import com.damanak.app.ui.theme.Brown
import com.damanak.app.ui.theme.CairoFontFamily
import com.damanak.app.ui.theme.DamanakTheme
import com.damanak.app.ui.theme.HeroBackground
import com.damanak.app.ui.theme.Navy900
import com.damanak.app.ui.theme.Neutral500
import com.damanak.app.ui.theme.Neutral700

@Composable
fun CareScheduleScreen(
    productId: String = "sample-id",
    onBack: () -> Unit = {},
    onSaved: () -> Unit = {},
    viewModel: CareScheduleViewModel = viewModel(factory = CareScheduleViewModel.factory(productId))
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Header
        DamanakHeader(
            title = stringResource(R.string.care_title),
            subtitle = stringResource(R.string.care_subtitle),
            onBack = onBack
        )

        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // 1. Device summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HeroBackground)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = uiState.deviceName,
                            style = TextStyle(
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            ),
                            color = Navy900
                        )

                        Text(
                            text = uiState.careTask,
                            style = TextStyle(
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            color = Brown
                        )
                    }

                    Icon(
                        imageVector = Icons.Outlined.Smartphone,
                        contentDescription = stringResource(R.string.transfer_phone_icon_desc),
                        tint = Brown,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // 2. Reminder card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFE5DFD7))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Heading & Description
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.care_reminder_heading),
                            style = TextStyle(
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            ),
                            color = Navy900
                        )

                        Text(
                            text = stringResource(R.string.care_reminder_desc),
                            style = TextStyle(
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            ),
                            color = Neutral500
                        )
                    }

                    // Interval dropdown
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color(0xFFD1D5DB))
                    ) {
                        Column {
                            // Header row
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .clickable { viewModel.toggleDropdown() }
                                    .padding(horizontal = 14.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(uiState.selectedInterval.labelResId),
                                    style = TextStyle(
                                        fontFamily = CairoFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    ),
                                    color = Navy900,
                                    modifier = Modifier.weight(1f)
                                )

                                Icon(
                                    imageVector = if (uiState.isDropdownExpanded) {
                                        Icons.Outlined.KeyboardArrowUp
                                    } else {
                                        Icons.Outlined.KeyboardArrowDown
                                    },
                                    contentDescription = stringResource(R.string.care_chevron_desc),
                                    tint = Navy900,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            // Options when expanded
                            if (uiState.isDropdownExpanded) {
                                HorizontalDivider(
                                    color = Color(0xFFE5E7EB),
                                    thickness = 1.dp
                                )

                                CareInterval.entries.forEach { interval ->
                                    val isSelected = interval == uiState.selectedInterval
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(47.dp)
                                            .background(if (isSelected) HeroBackground else Color.White)
                                            .clickable { viewModel.selectInterval(interval) }
                                            .padding(horizontal = 14.dp),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (isSelected) {
                                            Icon(
                                                imageVector = Icons.Outlined.Check,
                                                contentDescription = stringResource(R.string.care_check_desc),
                                                tint = Brown,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        } else {
                                            Spacer(modifier = Modifier.size(18.dp))
                                        }

                                        Text(
                                            text = stringResource(interval.labelResId),
                                            style = TextStyle(
                                                fontFamily = CairoFontFamily,
                                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                                fontSize = 14.sp
                                            ),
                                            color = if (isSelected) Brown else Neutral700
                                        )
                                    }
                                }
                            }
                        }
                    }

                    HorizontalDivider(
                        color = Color(0xFFE5E7EB),
                        thickness = 1.dp
                    )

                    // Alerts row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.care_alerts_title),
                                style = TextStyle(
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                ),
                                color = Navy900
                            )

                            Text(
                                text = if (uiState.alertsEnabled) {
                                    stringResource(R.string.care_alerts_status_on)
                                } else {
                                    stringResource(R.string.care_alerts_status_off)
                                },
                                style = TextStyle(
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                                color = if (uiState.alertsEnabled) Color(0xFF15803D) else Neutral500
                            )
                        }

                        Switch(
                            checked = uiState.alertsEnabled,
                            onCheckedChange = { viewModel.setAlertsEnabled(it) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color(0xFF22C55E),
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color(0xFFD1D5DB)
                            )
                        )
                    }
                }
            }

            // 3. Next reminder card (only when alerts are enabled)
            if (uiState.alertsEnabled) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFEFFAF2))
                        .padding(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.care_next_reminder_caption),
                                style = TextStyle(
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                                color = Color(0xFF15803D)
                            )

                            Text(
                                text = uiState.nextReminderDate,
                                style = TextStyle(
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                ),
                                color = Navy900
                            )
                        }

                        Icon(
                            imageVector = Icons.Outlined.EventAvailable,
                            contentDescription = stringResource(R.string.care_calendar_desc),
                            tint = Color(0xFF15803D),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        // Bottom action pinned to bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 24.dp, end = 24.dp, bottom = 8.dp)
                .navigationBarsPadding()
        ) {
            Button(
                onClick = {
                    viewModel.save()
                    onSaved()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Navy900,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = stringResource(R.string.care_save_button),
                    style = TextStyle(
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CareScheduleScreenPreview() {
    DamanakTheme {
        CareScheduleScreen(
            productId = "sample-123",
            onBack = {},
            onSaved = {}
        )
    }
}
