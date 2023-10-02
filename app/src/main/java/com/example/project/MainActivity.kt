package com.example.project

import com.example.project.MidAutumnTabViewPage.MidAutumnHomeTab
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.project.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                MidAutumnHomeTab()
            }
        }
    }
}

