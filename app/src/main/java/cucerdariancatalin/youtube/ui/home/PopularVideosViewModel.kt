package cucerdariancatalin.youtube.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cucerdariancatalin.youtube.data.models.YoutubeResponse
import cucerdariancatalin.youtube.repository.FetchPopularVideosRepository
import cucerdariancatalin.youtube.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PopularVideosViewModel @Inject constructor(
    private val repository: FetchPopularVideosRepository
): ViewModel() {

    private val _popularVideos: MutableLiveData<Resource<YoutubeResponse>> = MutableLiveData()
    var popularVideos: LiveData<Resource<YoutubeResponse>> = _popularVideos

    init {
        fetchPopularVideos()
    }

    private fun fetchPopularVideos() = viewModelScope.launch {
        _popularVideos.postValue(Resource.Loading())
        val response = repository.getPopularVideos()
        _popularVideos.postValue(handleYoutubeResponse(response))
    }

    private fun handleYoutubeResponse(response: Response<YoutubeResponse>): Resource<YoutubeResponse> {
        if (response.isSuccessful) {
            response.body()?.let { youtubeResponse ->
                return Resource.Success(youtubeResponse)
            }
        }
        return Resource.Error(response.message())
    }
}