package com.example.mashbox.UI

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FileLocationTable")
class FileLocationEntity {
    @PrimaryKey
    var location:String = ""
}