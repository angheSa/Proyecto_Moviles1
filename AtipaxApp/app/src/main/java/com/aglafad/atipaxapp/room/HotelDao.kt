package com.aglafad.atipaxapp.room

import androidx.room.*
import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {

    @Query("SELECT * FROM tb_hotel")
    fun getHoteles(): Flow<List<Hotel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hotel: Hotel)

    @Update
    suspend fun update(hotel: Hotel)

    @Query("DELETE FROM tb_hotel where id = :id")
    suspend fun delete(id: Int)
}