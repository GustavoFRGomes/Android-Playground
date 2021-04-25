package me.ggomes.demo.common.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    protected val internalErrorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> = internalErrorLiveData
}