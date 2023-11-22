package kr.ac.kumoh.ce.s20180147.s23w12lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20180147.s23w12lazylist.ui.theme.S23W12LazyListTheme

data class Song(var title: String, var singer: String)
private val songs = mutableListOf<Song>()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeat(10) {
            songs.add(Song("상냥함의 기억", "윤하"))
            songs.add(Song("Thanks to", "윤하"))
            songs.add(Song("에브리데이 함께", "아로나"))
        }

        setContent {
            MyScreen()
        }
    }
}

@Composable
fun MyScreen() {
    S23W12LazyListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyList()
        }
    }
}

@Composable
fun TitleText(title: String) {
    Text(title, fontSize = 30.sp)
}

@Composable
fun SingerText(title: String) {
    Text(title, fontSize = 20.sp)
}

@Composable
fun SongItem(song: Song) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xfffffcc))
            .padding(16.dp)
    ) {
        TitleText("노래 ${song.title}")
        SingerText("이 노래를 부른 가수는 ${song.singer} 입니다")
    }
}
@Composable
fun MyList() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(songs)  {
                song -> SongItem(song)
        }
    }
}