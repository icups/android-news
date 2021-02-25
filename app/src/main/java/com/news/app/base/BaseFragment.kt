package com.news.app.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, VDB : ViewDataBinding>(private val viewModelClass: Class<VM>) : DaggerFragment() {

    @Inject
    lateinit var mFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM
    lateinit var binding: VDB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResources(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
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