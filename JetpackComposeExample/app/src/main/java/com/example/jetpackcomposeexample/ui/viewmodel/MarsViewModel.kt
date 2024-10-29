package com.example.jetpackcomposeexample.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeexample.network.MarsApi
import com.example.jetpackcomposeexample.network.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    private fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = try {
            // 先获取原始响应， 尝试手动解析
//            val rawResponse = MarsApi.retrofitService.getPhotosAsString()
////                    Log.d("MarsViewModel", "Raw Response: $rawResponse")
//            val parsedPhotos = Json.decodeFromString<List<MarsPhoto>>(rawResponse)
            val photos = MarsApi.retrofitService.getPhotos()
//            Log.d("MarsViewModel", "Parsed successfully: ${photos.size} photos")
            MarsUiState.Success(photos)
                // 获取原始响应
//                val rawResponse = MarsApi.retrofitService.getPhotosAsString()
//                Log.d("MarsViewModel", "Raw Response: $rawResponse")
//
//                // 使用 kotlinx.serialization 解析 JSON 数组
//                val parsedPhotos = json.decodeFromString<List<MarsPhoto>>(rawResponse)
//                Log.d("MarsViewModel", "Parsed successfully: ${parsedPhotos.size} photos")
//
//                MarsUiState.Error
            } catch (e: IOException) {
                Log.e("MarsViewModel", "IOException: ${e.message}")
                MarsUiState.Error
            } catch (e: Exception) {
                Log.e("MarsViewModel", "Error: ${e.javaClass.simpleName} - ${e.message}")
                Log.e("MarsViewModel", "Detailed error:", e)  // 打印完整堆栈
                MarsUiState.Error
            }
        }
    }
}


sealed interface MarsUiState {
    data class Success(val photos: List<MarsPhoto>): MarsUiState
    object Error: MarsUiState
    object Loading: MarsUiState
}
