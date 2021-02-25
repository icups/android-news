package com.news.app.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel, VDB : ViewDataBinding>(private val viewModelClass: Class<VM>) : DaggerAppCompatActivity() {

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM
    lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        attachViewDataBinding()

        setupArguments()
        setupToolbar()
        setupView()

        setupAdapter()
        setupListener()
        setupObserver()

        onViewCreated()
        initAPI()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, mFactory).get(viewModelClass)
    }

    private fun attachViewDataBinding() {
        DataBindingUtil.setContentView<VDB>(this@BaseActivity, layoutResources()).apply {
            setFinishOnTouchOutside(false)
            binding = this
        }
    }

    protected abstract fun onViewCreated()

    protected open fun setupArguments() {}
    protected open fun setupView() {}
    protected open fun setupAdapter() {}
    protected open fun setupToolbar() {}
    protected open fun setupListener() {}
    protected open fun setupObserver() {}

    protected open fun initAPI() {}

    @LayoutRes
    protected abstract fun layoutResources(): Int

}