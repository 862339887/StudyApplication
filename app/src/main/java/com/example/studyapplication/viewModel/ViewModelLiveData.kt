package com.example.studyapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelLiveData : ViewModel() {

    var liveData = MutableLiveData<Int>().apply {
        value = 1
    }
}