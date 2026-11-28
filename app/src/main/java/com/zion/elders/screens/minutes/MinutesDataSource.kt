package com.zion.elders.screens.minutes

import com.zion.elders.viewmodels.MeetingDataEntry

class MinutesDataSource {
    private val meetingDataEntry: MeetingDataEntry ?= null

    fun getEditorData(): MeetingDataEntry? {
        return meetingDataEntry
    }
}