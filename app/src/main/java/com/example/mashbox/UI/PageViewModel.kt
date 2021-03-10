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
    var cols = listOf<String>(MediaStore.Audio.Media.DURATION).toTypedArray()

    var rs = applicationContext.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,cols,null,null,null)

    init {
        fetchFileLocation()
    viewModelScope.launch(Dispatchers.Main) {
        getFileList()

    }

    }
    fun fetchFileLocation() = viewModelScope.launch(Dispatchers.IO) {
        fileList = fileLocationRepository.fileLocationDao.getAllImageLocation()
    }

  suspend fun getFileList() {


                if(rs?.moveToNext()!!) {
                    val fileLocationEntityOne = FileLocationEntity()
                    fileLocationEntityOne.location = rs!!.getString(0)
                    withContext(Dispatchers.IO){
                    fileLocationRepository.fileLocationDao.insertAllFileLocation(fileLocationEntityOne)
                    }
                    while (rs!!.moveToNext()){
                        val fileLocationEntity = FileLocationEntity()
                        fileLocationEntity.location = rs!!.getString(0)

                        withContext(Dispatchers.IO){
                            fileLocationRepository.fileLocationDao.insertAllFileLocation(fileLocationEntity)
                        }
            Log.d("list", _fileList.value?.size.toString())
                    }

                }



}
}