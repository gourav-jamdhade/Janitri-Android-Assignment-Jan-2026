package com.example.janitriandroidassignnment.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "vital_entries")
data class VitalsEntry(
    @PrimaryKey(autoGenerate = true) val id:Int =0,
    val systolicBP: Int,
    val diastolicBP: Int,
    val heartRate: Int,
    val weight: Float,
    val kickCount: Int,
    val timestamp: Long = System.currentTimeMillis()
)