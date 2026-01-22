package com.example.janitriandroidassignnment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface VitalsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVitals(entry: VitalsEntry)

    @Query("SELECT * FROM vital_entries ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<VitalsEntry>>
}