package com.aglafad.atipaxapp.room

import androidx.room.*
import com.aglafad.atipaxapp.entity.Tour
import kotlinx.coroutines.flow.Flow
@Dao
interface TourDao {

    @Query("SELECT * FROM tb_tour")
    fun getTours(): Flow<List<Tour>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tour: Tour)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(tour: Tour)

    @Query("DELETE FROM tb_tour where id_tour = :id_tour")
    suspend fun delete(id_tour: Int)
}