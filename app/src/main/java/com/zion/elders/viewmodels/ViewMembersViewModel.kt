//package com.example.elders.viewmodels
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.backend.ServerAPI
//import com.example.backend.models.ReceivedMemberData
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//
//class ViewMembersViewModel: ViewModel() {
//    private val _members = MutableStateFlow<List<ReceivedMemberData>>(emptyList())
//    val members: StateFlow<List<ReceivedMemberData>> = _members.asStateFlow()
//
//    init {
//        fetchMembers()
//    }
//
//    private fun fetchMembers(){
//        viewModelScope.launch(Dispatchers.IO) {
//            val serverAPI = ServerAPI()
//            val membersList = serverAPI.getAllMembers()
//            _members.value = membersList
//        }
//    }
//}