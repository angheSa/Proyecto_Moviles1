package com.aglafad.atipaxapp.room

import androidx.room.*
import com.aglafad.atipaxapp.entity.Hotel
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

    @Query("SELECT * FROM tb_tour WHERE destino_tour LIKE '%' || :destino || '%' OR tipo_tour LIKE '%' || :tipo || '%' OR descripcion_tour LIKE '%' || :descripcion || '%'")
    fun getToursXFiltro(destino: String, tipo: String, descripcion: String): List<Tour>

}