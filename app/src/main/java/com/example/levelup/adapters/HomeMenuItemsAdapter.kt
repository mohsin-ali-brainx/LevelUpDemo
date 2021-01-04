package com.example.levelup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.levelup.BR
import com.example.levelup.databinding.HomeMenuItemBinding
import com.example.levelup.models.Menu

class HomeMenuItemsAdapter ( private val context: Context, private val menuItems:List<Menu>) :
RecyclerView.Adapter<HomeMenuItemsAdapter.MenuItemsViewHolder>() {



    inner class MenuItemsViewHolder(val itemBinding: ViewDataBinding):RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemsViewHolder {
        val rootView = HomeMenuItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return MenuItemsViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MenuItemsViewHolder, position: Int) {
        val menuItem = menuItems[position]
        holder.itemBinding.apply {
            setVariable(BR.menu,menuItem)
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
       return menuItems.size
    }


}
