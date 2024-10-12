package com.example.jetpackcomposeexample.screens

import android.content.res.Configuration
import android.provider.Telephony.Sms.Conversations
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// R 只会生成在顶级包的命名空间下
import com.example.jetpackcomposeexample.R
import com.example.jetpackcomposeexample.data.MessageSampleData
import com.example.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme

@Composable
fun BasicComposeScreen() {
    CommonScreen(title = "基本的Compose 组件演示") {
        Conversation(MessageSampleData.conversationSample)
    }
}


data class Message(val author: String, val body: String)


@Composable
fun MessageCard(msg: Message) {
    var isExpanded by remember { mutableStateOf(false) }
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    )

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.jesus_christ),
            contentDescription = "Contact profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
                .clip(CircleShape)
                .background(Color.Yellow)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                        .clickable { isExpanded = !isExpanded }
                        .animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                )
            }
        }
    }

}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}


@Preview(
    name = "暗黑模式",
//    backgroundColor = 0xFFFFFFFF, // 白色背景
    showBackground = true, // 显示背景
//    showSystemUi = true, // 显示系统UI
    uiMode = Configuration.UI_MODE_NIGHT_YES // 暗黑模式
)
@Composable
fun PreviewMessageCard() {
    val msg = Message(
        "Jesus Christ",
        "So in everything, do to others what you would have them do to you, for this sums up the Law and the Prophets."
    )

    JetpackComposeExampleTheme {
        Surface {
            MessageCard(msg)
        }
    }
}
@Preview(
    name = "日间模式",
//    backgroundColor = 0xFFFFFFFF, // 白色背景
    showBackground = true, // 显示背景
//    showSystemUi = true, // 显示系统UI
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewMessageCard2() {
    val msg = Message(
        "Jesus Christ",
        "So in everything, do to others what you would have them do to you, for this sums up the Law and the Prophets."
    )

    JetpackComposeExampleTheme {
        Surface {
            MessageCard(msg)
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun PreviewCoversation() {
    JetpackComposeExampleTheme {
        Conversation(MessageSampleData.conversationSample)
    }
}
