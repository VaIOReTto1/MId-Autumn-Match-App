package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//卡片
@Composable
fun CustomListItem(title: String, content: String, image: Int) {
    var showDialog by remember { mutableStateOf(false) }

    val onCardClick: () -> Unit = {
        showDialog = true
    }

    ClickableCard(onClick = onCardClick) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 左边的标题和内容
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        title,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 20.sp
                    )
                    Text(content, overflow = TextOverflow.Ellipsis, maxLines = 2, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))
                // 右边的图片
                CustomImage(image)
            }
        }
    }

    //点击弹窗
    if (showDialog) {
        MailDialog(
            title = title,
            content = content,
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun CustomImage(image: Int) {
    val defaultImageResource = R.drawable.mid_auttun
    val imageResource = if (image != 0) image else defaultImageResource

    Image(
        painter = painterResource(imageResource),
        contentDescription = null,
        modifier = Modifier
            .size(width = 75.dp, height = 75.dp),
        contentScale = ContentScale.FillHeight,
        alignment = Alignment.CenterEnd,
        alpha = 0.8F,
    )
}

@Composable
fun ClickableCard(onClick: () -> Unit, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        content()
    }
}

//弹窗
@Composable
fun MailDialog(title: String, content: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {},
        text = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    content,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}