package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.ui.theme.ProjectTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.TabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   TabbedPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabbedPage() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")

    Column(modifier = Modifier.fillMaxSize()) {
        // TabView
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            contentColor = Color.Gray
        ) {
            tabs.forEachIndexed { index, text ->
                Tab(
                    text = { Text(text) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    }
                )
            }
        }

        // 下方的页面显示
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {
                items(20) { itemIndex ->
                    ListItem(
                        headlineText = { Text("Item $itemIndex") }
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectTheme {
        TabbedPage()
    }
}