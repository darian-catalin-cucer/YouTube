package cucerdariancatalin.youtube.data.api

import cucerdariancatalin.youtube.data.models.YoutubeResponse
import cucerdariancatalin.youtube.util.Constants.Companion.DETAILS
import cucerdariancatalin.youtube.util.Constants.Companion.KEY
import cucerdariancatalin.youtube.util.Constants.Companion.LIST_OF_VIDEOS
import cucerdariancatalin.youtube.util.Constants.Companion.MOST_POPULAR
import cucerdariancatalin.youtube.util.Constants.Companion.REGION_CODE
import cucerdariancatalin.youtube.util.Constants.Companion.SNIPPET
import cucerdariancatalin.youtube.util.Constants.Companion.STATISTICS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    //This endpoint gets the popular videos
    @GET(LIST_OF_VIDEOS)
    suspend fun fetchVideos(
        @Query("part") part: String = "$SNIPPET,$DETAILS,$STATISTICS",
        @Query("chart") chart: String = MOST_POPULAR,
        @Query("regionCode") regionCode: String = REGION_CODE,
        @Query("key") key: String = KEY
    ): Response<YoutubeResponse>

}