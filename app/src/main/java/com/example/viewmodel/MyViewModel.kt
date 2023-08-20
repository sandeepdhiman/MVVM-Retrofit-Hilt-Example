package com.example.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Album
import com.example.repository.MyRepository
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository):ViewModel() {

    private val albums = MutableLiveData<Resource<List<Album>>>()
    val photos: LiveData<Resource<List<Album>>>get() = albums

    init {
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            albums.postValue(Resource.loading(null))
            repository.getAlbumList().let {
                if (it.isSuccessful){
                    albums.postValue(Resource.success(it.body()))
                }else{
                    albums.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }

        }
        }

}