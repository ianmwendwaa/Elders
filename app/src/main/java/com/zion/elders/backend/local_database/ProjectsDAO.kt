package com.zion.elders.backend.local_database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy.Companion.ABORT
import androidx.room3.Query

@Dao
interface ProjectsDAO {
    @Insert(onConflict = ABORT)
    suspend fun createProject(projectData: Project)

    @Query("SELECT * FROM registered_projects")
    suspend fun getAllProjects(): List<Project>
}