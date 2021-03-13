package com.example.mashbox.UI

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.*

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PageViewModel(val applicationContext: Context,val fileLocationRepository:FileLocationRepository) : ViewModel() {
    var _fileList = MutableLiveData<MutableList<FileLocationEntity>>()
    lateinit var fileList:LiveData<List<FileLocationEntity>>
    val selection = MediaStore.Audio.Media.IS_MUSIC+"!=0"
    var cols = listOf<String>(MediaStore.Audio.Media.DATA).toTypedArray()

    var rs = applicationContext.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,cols,selection,null,null)

    init {
    //   deleteAllFileLocation()
        fetchFileLocation()
    viewModelScope.launch(Dispatchers.Main) {
        getFileList()

    }

    }
    fun fetchFileLocation() = viewModelScope.launch(Dispatchers.IO) {
        fileList = fileLocationRepository.getAllImageLocation()
    }

    fun deleteAllFileLocation() = viewModelScope.launch(Dispatchers.IO) {
        fileLocationRepository.deleteAllLocations()
    }

  suspend fun getFileList() {


                if(rs?.moveToNext()!!) {
                    val fileLocationEntityOne = FileLocationEntity()
                    fileLocationEntityOne.location = rs!!.getString(0)
                    withContext(Dispatchers.IO){
                    fileLocationRepository.insertFileLocation(fileLocationEntityOne)
                    }
                    while (rs!!.moveToNext()){
                        val fileLocationEntity = FileLocationEntity()
                        fileLocationEntity.location = rs!!.getString(0)

                        withContext(Dispatchers.IO){
                            fileLocationRepository.insertFileLocation(fileLocationEntity)
                        }
            Log.d("list", _fileList.value?.size.toString())
                    }

                }



}
}