package com.zion.elders.backend.local_database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query

@Dao
interface MemberDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerMember(memberSchema: Member)

    @Query("SELECT * FROM zion_elders_table")
    suspend fun getAllMembers(): List<Member>

    @Query("SELECT * FROM zion_elders_table WHERE " +
            "memberName LIKE :name")
    suspend fun findMember(name: String): Member
}