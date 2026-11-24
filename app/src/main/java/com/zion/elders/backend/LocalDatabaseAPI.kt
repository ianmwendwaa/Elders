package com.zion.elders.backend

import androidx.test.core.app.ApplicationProvider
import com.zion.elders.backend.local_database.AppDatabase

class LocalDatabaseAPI {
    val localDb = AppDatabase.getDatabase(ApplicationProvider.getApplicationContext()).memberDAO()
}