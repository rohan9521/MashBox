package com.example.mashbox.UI

import androidx.lifecycle.LiveData

class FileLocationRepository(private val fileLocationDao: FileLocationDao) {
    suspend fun getAllImageLocation():LiveData<List<FileLocationEntity>>{
        return  fileLocationDao.getAllImageLocation()
    }
    suspend fun insertFileLocation(fileLocationEntity: FileLocationEntity){
        fileLocationDao.insertAllFileLocation(fileLocationEntity)
    }
    suspend fun deleteAllLocations(){
        fileLocationDao.deleteAll()
    }
}