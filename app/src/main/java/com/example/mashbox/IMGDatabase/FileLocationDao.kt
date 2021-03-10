package com.example.mashbox.UI
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FileLocationDao {

    @Query("select * from FileLocationTable")
    fun getAllImageLocation():LiveData<List<FileLocationEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllFileLocation(fileLocation: FileLocationEntity)


}