package kr.ac.kumoh.ce.s20180147.s23w12lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20180147.s23w12lazylist.ui.theme.S23W12LazyListTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SongViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyScreen(viewModel)
        }
    }
}

@Composable
fun MyScreen(viewModel: SongViewModel) {
    val songList by viewModel.songList.observeAsState(emptyList())
    S23W12LazyListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyList(songList)
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
        TitleText(song.title)
        SingerText("이 노래를 부른 가수는 ${song.singer}입니다")
    }
}
@Composable
fun MyList(list: List<Song>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(list)  {
                song -> SongItem(song)
        }
    }
}