package com.example.project.MidAutumnTabViewPage

import androidx.annotation.FloatRange
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import com.example.project.ui.theme.ProjectTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.lerp
import kotlinx.coroutines.launch
import kotlin.math.abs

private val backgroundColor = Color(0xFFF2F5F9)
private val selectedContentColor = Color(0xFF333333)
private val unselectedContentColor = Color(0xFF999999)
private val indicatorColor = Color(0xFFFFD700)
private const val indicatorPercent = 0.6f
private val indicatorHeight = 5.dp

//主页面
@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
)
@Composable
fun MidAutumnHomeTab() {
    val noteFolders = listOf("且喜人间好时节", "月是故乡明", "小饼如嚼月")
    val pagerState = rememberPagerState(initialPage = 0)
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.background(backgroundColor)) {
        //tab页面
        ScrollableTabRow(
            modifier = Modifier,
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                if (tabPositions.isNotEmpty()) {
                    PagerTabIndicator(tabPositions = tabPositions, pagerState = pagerState)
                }
            },
            containerColor = backgroundColor,
            divider = {}
        ) {
            noteFolders.forEachIndexed { index, title ->
                val selected = (pagerState.currentPage == index)
                Tab(
                    selected = selected,
                    selectedContentColor = selectedContentColor,
                    unselectedContentColor = unselectedContentColor,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(9.dp)
                    )
                }
            }
        }
        HorizontalPagerContent(pagerState = pagerState, noteFolders = noteFolders)
    }
}

//显示内容
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HorizontalPagerContent(
    pagerState: PagerState,
    noteFolders: List<String>
) {
    HorizontalPager(
        pageCount = noteFolders.size,
        state = pagerState,
        beyondBoundsPageCount = 20,
        modifier = Modifier.fillMaxSize().background(backgroundColor)
    ) {
        when (it) {
            0 -> MidAutumnFeature()
            1 -> MidAutumnPoem()
            2 -> MidAutumnFood()
        }
    }
}

//滑动线条
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerTabIndicator(
    tabPositions: List<TabPosition>,
    pagerState: PagerState,
    color: Color = Color(0xFFFFD700),
    @FloatRange(from = 0.0, to = 1.0) percent: Float = 0.6f,
    height: Dp = 5.dp,
) {
    val currentPage by rememberUpdatedState(newValue = pagerState.currentPage)
    val fraction by rememberUpdatedState(newValue = pagerState.currentPageOffsetFraction)
    val currentTab = tabPositions[currentPage]
    val previousTab = tabPositions.getOrNull(currentPage - 1)
    val nextTab = tabPositions.getOrNull(currentPage + 1)
    Canvas(
        modifier = Modifier.fillMaxSize(),
        onDraw = {
            val indicatorWidth = currentTab.width.toPx() * percent
            //是否滑动判断
            val indicatorOffset = if (fraction > 0 && nextTab != null) {
                lerp(currentTab.left, nextTab.left, fraction).toPx()
            } else if (fraction < 0 && previousTab != null) {
                lerp(currentTab.left, previousTab.left, -fraction).toPx()
            } else {
                currentTab.left.toPx()
            }

            val canvasHeight = size.height
            drawRoundRect(
                color = color,
                topLeft = Offset(
                    indicatorOffset + (currentTab.width.toPx() * (1 - percent) / 2),
                    canvasHeight - height.toPx()
                ),
                size = Size(indicatorWidth + indicatorWidth * abs(fraction), height.toPx()),
                cornerRadius = CornerRadius(50f)
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectTheme {
        MidAutumnHomeTab()
    }
}