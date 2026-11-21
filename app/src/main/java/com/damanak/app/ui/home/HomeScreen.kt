package com.damanak.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

private val NavyDark = Color(0xFF111827)
private val WarmBrown = Color(0xFF7C5E4A)
private val CreamBg = Color(0xFFF8F6F3)
private val CardWhite = Color(0xFFFFFFFF)
private val TextMuted = Color(0xFF6B7280)

private val GreenMain = Color(0xFF22C55E)
private val GreenSoft = Color(0xFFDCFCE7)
private val YellowMain = Color(0xFFF59E0B)
private val YellowSoft = Color(0xFFFEF3C7)
private val RedMain = Color(0xFFEF4444)
private val RedSoft = Color(0xFFFEE2E2)

enum class HomeFilter(val labelAr: String) {
    ALL("الكل"),
    ACTIVE("ساري"),
    EXPIRING_SOON("قارب ينتهي"),
    EXPIRED("منتهي")
}

@Composable
fun HomeScreen(
    userName: String = "شهد",
    warranties: List<WarrantyItem> = sampleWarranties(),
    isOffline: Boolean = false,
    onAddClick: () -> Unit = {},
    onWarrantyClick: (WarrantyItem) -> Unit = {},
    onTabClick: (Int) -> Unit = {} // 1 = Search, 2 = Notifications, 3 = Profile
) {
    var selectedFilter by remember { mutableStateOf(HomeFilter.ALL) }
    var selectedTab by remember { mutableStateOf(0) }

    val today = LocalDate.now()
    val sortedWarranties = remember(warranties) { warranties.sortByUrgency(today) }

    val expiredCount = sortedWarranties.count { it.getStatus(today) == WarrantyStatus.EXPIRED }
    val expiringCount = sortedWarranties.count { it.getStatus(today) == WarrantyStatus.EXPIRING_SOON }
    val totalCount = sortedWarranties.size

    val filteredList = remember(sortedWarranties, selectedFilter) {
        when (selectedFilter) {
            HomeFilter.ALL -> sortedWarranties
            HomeFilter.ACTIVE -> sortedWarranties.filter { it.getStatus(today) == WarrantyStatus.ACTIVE }
            HomeFilter.EXPIRING_SOON -> sortedWarranties.filter { it.getStatus(today) == WarrantyStatus.EXPIRING_SOON }
            HomeFilter.EXPIRED -> sortedWarranties.filter { it.getStatus(today) == WarrantyStatus.EXPIRED }
        }
    }

    Scaffold(
        containerColor = CreamBg,
        floatingActionButton = {
            if (sortedWarranties.isNotEmpty()) {
                FloatingActionButton(
                    onClick = onAddClick,
                    containerColor = WarmBrown,
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "إضافة ضمان")
                }
            }
        },
        bottomBar = {
            NavigationBar(containerColor = CardWhite) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "الرئيسية") },
                    label = { Text("الرئيسية", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { onTabClick(1) },
                    icon = { Icon(Icons.Default.Search, contentDescription = "بحث") },
                    label = { Text("بحث", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { onTabClick(2) },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "تنبيهات") },
                    label = { Text("تنبيهات", fontSize = 11.sp) }
                )
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { onTabClick(3) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "حسابي") },
                    label = { Text("حسابي", fontSize = 11.sp) }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(NavyDark)
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "مساء الخير، $userName",
                        color = Color(0xFFD6B88C),
                        fontSize = 13.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ضماناتك",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (isOffline) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Surface(
                            color = YellowSoft,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "لا يوجد اتصال بالإنترنت — سيتم المزامنة عند عودة الاتصال",
                                color = Color(0xFF92400E),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }

            if (sortedWarranties.isEmpty()) {
                EmptyHomeContent(onAddClick = onAddClick)
            } else {
                Card(
                    colors = CardDefaults.cardColors(containerColor = CardWhite),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .offset(y = (-12).dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 14.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SummaryStat(count = expiredCount.toString(), label = "منتهي", color = RedMain)
                        SummaryStat(count = expiringCount.toString(), label = "قارب ينتهي", color = YellowMain)
                        SummaryStat(count = totalCount.toString(), label = "الكل", color = NavyDark)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    HomeFilter.entries.forEach { filter ->
                        val isSelected = selectedFilter == filter
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedFilter = filter },
                            label = {
                                Text(
                                    text = filter.labelAr,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            shape = RoundedCornerShape(50),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = NavyDark,
                                selectedLabelColor = Color.White,
                                containerColor = CardWhite,
                                labelColor = NavyDark
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 84.dp)
                ) {
                    items(filteredList, key = { it.id }) { item ->
                        WarrantyCardItem(
                            item = item,
                            today = today,
                            onClick = { onWarrantyClick(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SummaryStat(count: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count,
            color = color,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = TextMuted,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun WarrantyCardItem(
    item: WarrantyItem,
    today: LocalDate,
    onClick: () -> Unit
) {
    val status = item.getStatus(today)
    val badgeText = item.getRemainingText(today)

    val (accentColor, softBg) = when (status) {
        WarrantyStatus.ACTIVE -> GreenMain to GreenSoft
        WarrantyStatus.EXPIRING_SOON -> YellowMain to YellowSoft
        WarrantyStatus.EXPIRED -> RedMain to RedSoft
    }

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(softBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (status == WarrantyStatus.ACTIVE) {
                        Icons.Outlined.Shield
                    } else {
                        Icons.Outlined.WarningAmber
                    },
                    contentDescription = status.labelAr,
                    tint = accentColor
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = item.productName,
                    color = NavyDark,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.storeName,
                    color = TextMuted,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    color = softBg,
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = badgeText,
                        color = accentColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(54.dp)
                    .clip(RoundedCornerShape(50))
                    .background(accentColor)
            )
        }
    }
}

@Composable
private fun EmptyHomeContent(onAddClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color(0xFFECE6DF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Description,
                contentDescription = null,
                tint = WarmBrown,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "ما عندك ضمانات بعد",
            color = NavyDark,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "أضف أول ضمان لك — صوّر الفاتورة وخلّ ضمانك يتذكّر عنك.",
            color = TextMuted,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onAddClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = NavyDark,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("أضف ضمان", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun sampleWarranties(): List<WarrantyItem> {
    val today = LocalDate.now()
    return listOf(
        WarrantyItem("1", "آيفون 15 برو", "أبل ستور", today.minusMonths(1), today.plusMonths(11)),
        WarrantyItem("2", "غسالة LG", "إكسترا", today.minusYears(1), today.plusDays(18)),
        WarrantyItem("3", "شاشة سامسونج", "جرير", today.minusYears(2), today.minusDays(5)),
        WarrantyItem("4", "لابتوب ديل XPS", "مكتبة جرير", today, today.plusYears(2))
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
