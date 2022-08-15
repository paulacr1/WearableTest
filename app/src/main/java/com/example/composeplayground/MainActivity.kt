package com.example.composeplayground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import com.example.composeplayground.playing.RowColumnUnderstanding
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    ComposeExample(LocalContext.current)
                }
            }
        }
    }
}

fun notification(context: Context) {
    val name = "name"
    val descriptionText = "description"
    val channelId = "channel_id_0"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val notificationManager: NotificationManager = context.
    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channel = NotificationChannel(channelId, name, importance).apply {
        description = descriptionText
    }
    // Register the channel with the system
    notificationManager.createNotificationChannel(channel)

    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val style = NotificationCompat.BigPictureStyle().bigPicture(
        BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground
        ))
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("My notification")
        .setContentText("Hello World!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .addAction(
            R.drawable.ic_launcher_foreground,
            "My action",
            pendingIntent
        )
        .setStyle(style)
        .setAutoCancel(true)



    notificationManager.notify(0, builder.build())
}

@Composable
fun ComposeExample(context: Context) {
    RowColumnUnderstanding(context)
}

@Preview(
    showBackground = true,
    name = "Compose Workshop - Default",
    widthDp = 450,
    heightDp = 100,
    group = "Home"
    )
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
        ComposeExample(LocalContext.current)
    }
}

@Preview(
    showBackground = true,
    name = "Compose Workshop - Night",
    widthDp = 450,
    heightDp = 100,
    group = "Work",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun SecondPreview() {
    ComposePlaygroundTheme {
        ComposeExample(LocalContext.current)
    }
}