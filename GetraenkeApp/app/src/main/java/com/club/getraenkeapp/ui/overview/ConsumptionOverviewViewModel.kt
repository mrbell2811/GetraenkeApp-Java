package com.club.getraenkeapp.ui.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.club.getraenkeapp.data.Consumption
import com.club.getraenkeapp.data.database.AppDatabase
import com.club.getraenkeapp.data.repository.AppRepository

class ConsumptionOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository
    val consumptionData: LiveData<List<Consumption>>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = AppRepository(database.memberDao(), database.beverageDao(), database.transactionDao(), database.archivedTransactionDao())
        consumptionData = repository.getConsumptionOverview()
    }
}
