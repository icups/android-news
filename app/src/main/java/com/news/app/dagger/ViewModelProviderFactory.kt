package com.news.app.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProviderFactory(private val viewModel: ViewModel) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return viewModel as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}