package com.zion.elders.local_db

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zion.elders.backend.local_database.AppDatabase
import com.zion.elders.backend.local_database.Member
import com.zion.elders.backend.local_database.MemberDAO
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDbTests {
    private lateinit var transactionsDao: MemberDAO
    private lateinit var appDatabase: AppDatabase
    val mockUserPayload = Member(
        memberName = "Ian Mwendwa",
        memberTelephone = "0791930433",
        generatedMemberNumber = "ELDR-IANM-0433"
    )
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder<AppDatabase>(context)
            .setDriver(BundledSQLiteDriver())
            .build()

        transactionsDao = appDatabase.memberDAO()
    }
    @Test
    fun insertMockUser() {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        scope.launch {
            transactionsDao.registerMember(mockUserPayload)
            val ascertainInsertion = transactionsDao.findMember("Ian Mwendwa")
            assertEquals(ascertainInsertion, mockUserPayload)
        }
    }
}