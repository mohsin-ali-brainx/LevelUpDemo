package com.example.levelup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.levelup.BR
import com.example.levelup.databinding.OnboardingItemBinding
import com.example.levelup.models.OnBoardingModel

class OnBoardingViewPagerAdapter( private val context: Context, private val onBoardingSlides:List<OnBoardingModel>) :
    RecyclerView.Adapter<OnBoardingViewPagerAdapter.OnBoardingViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val rootView = OnboardingItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return OnBoardingViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return  onBoardingSlides.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
       val slideItem =  onBoardingSlides[position]
        holder.itemBinding.apply {
            setVariable(BR.slideItem,slideItem)
            executePendingBindings()
        }
    }

    inner class OnBoardingViewHolder(val itemBinding: ViewDataBinding):RecyclerView.ViewHolder(itemBinding.root)

}