package com.avmogame.appcent.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener : RecyclerView.OnScrollListener() {
     private val PAGE_START = 1
    lateinit var layoutManager: LinearLayoutManager

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        this.layoutManager?.let { itLayoutManager->
            if (itLayoutManager.itemCount == (layoutManager.findLastVisibleItemPosition()+1) && itLayoutManager.itemCount > 1){

            }

        }
    }
}