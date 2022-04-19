package com.kgstrivers.ziv.Callbacks

import android.R
import android.accessibilityservice.GestureDescription
import android.content.Context
import android.graphics.Canvas
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kgstrivers.ziv.Activities.MainActivity
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


abstract class SwipeGesture(context: Context)
    :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT)
    {

        val delcol = ContextCompat.getColor(context, android.R.color.holo_red_light)
        var delicon = R.drawable.ic_menu_delete
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
        ): Boolean {

            return false
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {


        }
    }