package kr.ac.kumoh.ce.s20180147.s23w12lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
fun RatingInt(rating: Int) {
    Text("  ⭐$rating", fontSize = 30.sp)
}

@Composable
fun TitleText(title: String) {
    Text(title, fontSize = 30.sp)
}

@Composable
fun SingerText(singer: String) {
    Text(singer, fontSize = 20.sp)
}

@Composable
fun SongItem(song: Song) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(Color(0xfffffcc))
            .padding(16.dp)
    ){
        AsyncImage(
            model = "https://picsum.photos/300/300?random=${song.id}",
            contentDescription = "노래 앨범 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
//                .clip(CircleShape),
                .clip(RoundedCornerShape(percent = 10)),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                TitleText(song.title)
//                RatingInt(song.rating)
            }
            SingerText(song.singer)
        }
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