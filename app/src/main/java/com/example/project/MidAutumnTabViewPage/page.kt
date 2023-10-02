package com.example.project.MidAutumnTabViewPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project.CustomListItem
import com.example.project.StaggeredVerticalGrid

@Composable
fun MidAutumnFeature() {
    MidAutumnCategory(featureList)
}

@Composable
fun MidAutumnPoem() {
    MidAutumnCategory(poemList)
}

@Composable
fun MidAutumnFood() {
    MidAutumnCategory(foodList)
}

@Composable
fun MidAutumnCategory(items: List<Item>) {
    Box(modifier = Modifier.fillMaxSize()) {
        StaggeredVerticalGrid(
            modifier = Modifier
                .padding(4.dp)
                .verticalScroll(rememberScrollState())
        ) {
            repeat(items.size) {
                CustomListItem(items[it].title, items[it].content, items[it].imageResId)
            }
        }
    }
}


