package com.aglafad.atipaxapp.room

import androidx.room.*
import com.aglafad.atipaxapp.entity.Proveedor

import kotlinx.coroutines.flow.Flow
@Dao
interface ProveedorDao {

    @Query("SELECT * FROM tb_proveedor")
    fun getProveedores(): Flow<List<Proveedor>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(proveedor: Proveedor)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(proveedor: Proveedor)

    @Query("DELETE FROM tb_proveedor where nom_proveedor = :nombre")
    suspend fun delete(nombre: String)

   /* @Query("SELECT * FROM tb_proveedor where  nom_proveedor = :nombre")
    suspend fun searchForName(nombre : String): Flow<List<Proveedor>>*/
}