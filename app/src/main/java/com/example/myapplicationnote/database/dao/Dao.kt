package com.example.myapplicationnote.database.entity.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplicationnote.database.entity.EntityItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    fun insertItem(item: EntityItem)

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<EntityItem>>

    @Query("DELETE FROM items")
    fun deleteAll()

}
