package com.damanak.app.ui.onboarding

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.damanak.app.R
import com.damanak.app.ui.theme.Background
import com.damanak.app.ui.theme.Brown
import com.damanak.app.ui.theme.CairoFontFamily
import com.damanak.app.ui.theme.DamanakTheme
import com.damanak.app.ui.theme.HeroBackground
import com.damanak.app.ui.theme.Navy900
import com.damanak.app.ui.theme.Neutral300
import com.damanak.app.ui.theme.Neutral500
import kotlinx.coroutines.launch

data class OnboardingPage(
    val title: String,
    val description: String
)

@Composable
fun OnboardingScreen(
    onFinishOnboarding: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pages = listOf(
        OnboardingPage(
            title = stringResource(R.string.onboarding_page1_title),
            description = stringResource(R.string.onboarding_page1_description)
        ),
        OnboardingPage(
            // TODO: Replace with final string for page 2 title
            title = stringResource(R.string.onboarding_page2_title),
            // TODO: Replace with final string for page 2 description
            description = stringResource(R.string.onboarding_page2_description)
        ),
        OnboardingPage(
            // TODO: Replace with final string for page 3 title
            title = stringResource(R.string.onboarding_page3_title),
            // TODO: Replace with final string for page 3 description
            description = stringResource(R.string.onboarding_page3_description)
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Pager containing top hero area + text section
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { pageIndex ->
            val page = pages[pageIndex]
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Top hero area: full width, 380dp tall, color #EDE7E0
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                        .background(HeroBackground),
                    contentAlignment = Alignment.Center
                ) {
                    // White card 200x240dp with 20dp corner radius
                    Box(
                        modifier = Modifier
                            .size(200.dp, 240.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                    ) {
                        // Top-start with 20dp/24dp padding
                        Box(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 24.dp)
                                .size(88.dp, 28.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFDCFCE7)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.onboarding_chip_active),
                                style = TextStyle(
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp
                                ),
                                color = Color(0xFF15803D)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(60.dp))

                // Text section with 32dp horizontal padding
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = page.title,
                        style = TextStyle(
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        ),
                        color = Navy900,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = page.description,
                        style = TextStyle(
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        ),
                        color = Neutral500,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        // Page indicator 60dp below text, start-aligned
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pages.size) { index ->
                val isSelected = index == pagerState.currentPage
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .width(if (isSelected) 24.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Brown else Neutral300)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                )
            }
        }

        Spacer(modifier = Modifier.weight(0.2f))

        // Bottom buttons section with 32dp horizontal padding
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Full-width primary button 56dp tall, corner radius 16dp, Navy #111827
            Button(
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinishOnboarding()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Navy900,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = if (pagerState.currentPage < pages.size - 1) {
                        stringResource(R.string.onboarding_button_next)
                    } else {
                        stringResource(R.string.onboarding_button_start)
                    },
                    style = TextStyle(
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Text button "تخطي" start-aligned
            Text(
                text = stringResource(R.string.onboarding_button_skip),
                style = TextStyle(
                    fontFamily = CairoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                ),
                color = Neutral500,
                modifier = Modifier.clickable {
                    onFinishOnboarding()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    DamanakTheme {
        OnboardingScreen(onFinishOnboarding = {})
    }
}
