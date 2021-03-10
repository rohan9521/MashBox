package com.example.mashbox.UI

import androidx.lifecycle.LiveData

class FileLocationRepository(val fileLocationDao: FileLocationDao) {
    suspend fun getAllImageLocation():LiveData<List<FileLocationEntity>>{
        return  fileLocationDao.getAllImageLocation()
    }
}