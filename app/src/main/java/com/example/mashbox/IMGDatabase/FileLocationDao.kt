package com.example.mashbox.UI
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface FileLocationDao {

    @Query("select * from MusicFileLocationTable")
    fun getAllImageLocation():LiveData<List<FileLocationEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllFileLocation(fileLocation: FileLocationEntity)

    @Query("DELETE FROM MusicFileLocationTable")
    fun deleteAll()

}