package com.aglafad.atipaxapp.repository

import com.aglafad.atipaxapp.entity.Hotel
import com.aglafad.atipaxapp.entity.Proveedor
import com.aglafad.atipaxapp.room.HotelDao
import com.aglafad.atipaxapp.room.ProveedorDao
import kotlinx.coroutines.flow.Flow

class ProveedorRepository(private val proveedorDao : ProveedorDao) {
    val listaProveedor : Flow<List<Proveedor>> = proveedorDao.getProveedores()

    suspend fun guardar(proveedor: Proveedor) {
        proveedorDao.insert(proveedor)
    }
    suspend fun actualizar(proveedor: Proveedor) {
        proveedorDao.update(proveedor)
    }
    suspend fun eliminar(id: Int) {
        proveedorDao.delete(id)
    }

}