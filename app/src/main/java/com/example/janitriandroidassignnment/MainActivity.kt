package com.example.janitriandroidassignnment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.janitriandroidassignnment.data.VitalsDatabase
import com.example.janitriandroidassignnment.screens.MainScreen
import com.example.janitriandroidassignnment.ui.theme.JanitriAndroidAssignnmentTheme
import com.example.janitriandroidassignnment.viewmodel.VitalsViewModel
import com.example.janitriandroidassignnment.viewmodel.VitalsViewModelFactory
import com.example.janitriandroidassignnment.work.VitalsReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Lifecycle.Event.ON_CREATE
        Lifecycle.State.CREATED

        //enableEdgeToEdge()
        val showDialog = intent?.getBooleanExtra("SHOW_LOG_DIALOG", false) ?: false


        val workRequest = PeriodicWorkRequestBuilder<VitalsReminderWorker>(5, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "VitalsReminder",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )




        setContent {
            val context = LocalContext.current

            val db = remember { VitalsDatabase.getDatabase(context) }
            val dao = remember { db.vitalsDao() }

            val viewModel: VitalsViewModel = viewModel(
                factory = VitalsViewModelFactory(dao)
            )

            var forceShowDialog by remember { mutableStateOf(showDialog) }
            MainScreen(viewModel, forceShowDialog)

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JanitriAndroidAssignnmentTheme {
        Greeting("Android")
    }
}