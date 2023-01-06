package cucerdariancatalin.youtube.repository

import cucerdariancatalin.youtube.data.api.YoutubeApi
import cucerdariancatalin.youtube.data.models.YoutubeResponse
import retrofit2.Response
import javax.inject.Inject

class FetchPopularVideosRepository @Inject constructor(
    private val api: YoutubeApi
) {

    suspend fun getPopularVideos(): Response<YoutubeResponse> {
        return api.fetchVideos()
    }

}