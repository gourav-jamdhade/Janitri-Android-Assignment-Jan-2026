package com.example.janitriandroidassignnment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.janitriandroidassignnment.data.VitalsDao
import com.example.janitriandroidassignnment.data.VitalsEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VitalsViewModel(private val dao: VitalsDao) : ViewModel() {

    private val _vitals = MutableStateFlow<List<VitalsEntry>>(emptyList())
    val vitals: StateFlow<List<VitalsEntry>> = _vitals.asStateFlow()

    init {

        viewModelScope.launch {

            dao.getAllVitals().collect {entries->

                _vitals.value = entries
            }
        }
    }

    fun addVitals(entry: VitalsEntry) {
        viewModelScope.launch {
            dao.insertVitals(entry)
        }

    }
}

class VitalsViewModelFactory(private val dao: VitalsDao): ViewModelProvider.Factory{

    override fun <T:ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(VitalsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return VitalsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}