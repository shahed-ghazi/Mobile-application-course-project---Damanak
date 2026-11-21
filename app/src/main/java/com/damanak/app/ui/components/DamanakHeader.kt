package com.damanak.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.damanak.app.R
import com.damanak.app.ui.theme.CairoFontFamily
import com.damanak.app.ui.theme.DamanakTheme
import com.damanak.app.ui.theme.Navy900

@Composable
fun DamanakHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Navy900,
                shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
            )
            .statusBarsPadding()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    ),
                    color = Color.White
                )

                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = TextStyle(
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        color = Color(0xFFB9C0CD)
                    )
                }
            }

            if (onBack != null) {
                Box(
                    modifier = Modifier
                        .size(32.dp, 40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable(onClick = onBack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_desc),
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DamanakHeaderPreview() {
    DamanakTheme {
        DamanakHeader(
            title = "نقل الملكية الذكي",
            subtitle = "انقل الضمان مع الجهاز إلى مالكه الجديد",
            onBack = {}
        )
    }
}
