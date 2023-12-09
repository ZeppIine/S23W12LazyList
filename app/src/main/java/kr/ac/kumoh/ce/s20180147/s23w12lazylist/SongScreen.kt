package kr.ac.kumoh.ce.s20180147.s23w12lazylist

import android.graphics.drawable.Icon
import android.widget.RatingBar
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage

enum class SongScreen {
    List,
    Detail
}

@Composable
fun SongApp(songList: List<Song>){
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = SongScreen.List.name,
    ){
        composable(SongScreen.List.name){
            MyList(navController ,songList)
        }
        composable(
            route = "${SongScreen.Detail.name}/{index}",
            arguments = listOf( navArgument("index") { type = NavType.IntType } )
        ){
            val index = it.arguments?.getInt("index") ?: -1
            MyDetail(songList[index])
        }
    }
}

@Composable
fun RatingInt(rating: Int) {
    Row{
        repeat(rating){
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "rating stars",
                modifier = Modifier.size(36.dp),
                tint = Color.Yellow
            )
        }
    }
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
fun LyricsText(singer: String) {
    Text(singer, fontSize = 20.sp)
}

@Composable
fun SongItem(navController: NavController, songList: List<Song>, index: Int) {
//    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.clickable { navController.navigate("${SongScreen.Detail.name}/${index}") },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
//                .background(Color(0xfffffcc))
                .padding(8.dp)
        ) {
            AsyncImage(
                model = "https://picsum.photos/300/300?random=${songList[index].id}",
                contentDescription = "노래 앨범 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
//                .clip(CircleShape),
                    .clip(RoundedCornerShape(percent = 10)),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row {
                    TitleText(songList[index].title)
//                RatingInt(song.rating)
                }
                SingerText(songList[index].singer)
            }
        }
//        AnimatedVisibility(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFF39C5BB))
//                .padding(8.dp),
//            visible = expanded
//        ) {
//            song.lyrics?.let { LyricsText(it) }
//        }
    }
}

@Composable
fun MyList(navController: NavController ,list: List<Song>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(list.size) {
            SongItem(navController, list, it)
        }
    }
}

@Composable
fun MyDetail(songList: Song){
    Column (
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RatingInt(songList.rating)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            songList.title,
            fontSize = 40.sp,
            lineHeight = 45.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            model = "https://picsum.photos/300/300?random=${songList.id}",
            contentDescription = "노래 앨범 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(300.dp)
//                .clip(CircleShape),
                .clip(RoundedCornerShape(percent = 10)),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = "https://i.pravatar.cc/100?u=${songList.singer}",
                contentDescription = "가수 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                songList.singer,
                fontSize = 30.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        songList.lyrics?.let {
            Text(
                it,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                lineHeight = 35.sp
            )
        }
    }
}