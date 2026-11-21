package com.damanak.app.ui.transfer

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.outlined.QrCode2
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun TransferScreen(
    productId: String = "sample-id",
    onBack: () -> Unit = {},
    viewModel: TransferViewModel = viewModel(factory = TransferViewModel.factory(productId))
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Header
        DamanakHeader(
            title = stringResource(R.string.transfer_title),
            subtitle = stringResource(R.string.transfer_subtitle),
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
            // 1. Instructions
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.transfer_instructions_title),
                    style = TextStyle(
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    ),
                    color = Navy900
                )

                Text(
                    text = stringResource(R.string.transfer_instructions_desc),
                    style = TextStyle(
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    color = Neutral500
                )
            }

            // 2. QR card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFE5DFD7))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(251.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        // TODO(M4): replace with a real generated QR code
                        Icon(
                            imageVector = Icons.Outlined.QrCode2,
                            contentDescription = stringResource(R.string.transfer_qr_icon_desc),
                            tint = Navy900,
                            modifier = Modifier.size(200.dp)
                        )
                    }

                    Text(
                        text = stringResource(R.string.transfer_qr_caption),
                        style = TextStyle(
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        color = Neutral500
                    )
                }
            }

            // 3. Device card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
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
                            text = uiState.deviceDetails,
                            style = TextStyle(
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            color = Neutral500
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(width = 44.dp, height = 48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(HeroBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Smartphone,
                            contentDescription = stringResource(R.string.transfer_phone_icon_desc),
                            tint = Brown,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }

            // 4. Note
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(HeroBackground)
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.transfer_note),
                        style = TextStyle(
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        color = Brown,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        imageVector = Icons.Outlined.VerifiedUser,
                        contentDescription = stringResource(R.string.transfer_shield_icon_desc),
                        tint = Brown,
                        modifier = Modifier.size(20.dp)
                    )
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
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, uiState.transferLink)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = stringResource(R.string.transfer_share_icon_desc),
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = stringResource(R.string.transfer_share_button),
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
}

@Preview(showBackground = true)
@Composable
fun TransferScreenPreview() {
    DamanakTheme {
        TransferScreen(
            productId = "sample-123",
            onBack = {}
        )
    }
}
