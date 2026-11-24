package com.zion.elders.backend.local_database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.room3.migration.Migration
import androidx.sqlite.SQLiteConnection

@Database(entities = [Member::class, Meeting::class, Project::class], version = 1, autoMigrations = [], exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun memberDAO(): MemberDAO
    abstract fun projectsDAO(): ProjectsDAO

    companion object {
        val MIGRATION_STRATS = object : Migration(1, 2){
            override suspend fun migrate(connection: SQLiteConnection) {
                // Make table amendments
            }
        }
        @Volatile
        private var INSTANCE: AppDatabase?= null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "zion_elders_management_db"
                ).addMigrations(MIGRATION_STRATS).build()
                INSTANCE = instance
                instance
            }
        }
    }
}