package com.example.image_drawer.utils

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseVM : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }

    fun Disposable.disposeOnCleared(){
        compositeDisposable.add(this)
    }
}