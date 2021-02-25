package com.news.app.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BasePagerAdapter<T>(context: FragmentActivity) : FragmentStateAdapter(context) {

    private val mContents: MutableList<T> = ArrayList()

    fun initialize(list: List<T>) {
        mContents.clear()
        mContents.addAll(list)
        notifyDataSetChanged()
    }

    override fun createFragment(position: Int): Fragment {
        return initiateFragment(mContents[position])
    }

    abstract fun initiateFragment(data: T): Fragment

    override fun getItemCount(): Int {
        return mContents.size
    }

}