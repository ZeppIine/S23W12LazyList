package kr.ac.kumoh.ce.s20180147.s23w12lazylist

import retrofit2.http.GET

interface SongApi {
    @GET("song")
    suspend fun getSongs(): List<Song>
}