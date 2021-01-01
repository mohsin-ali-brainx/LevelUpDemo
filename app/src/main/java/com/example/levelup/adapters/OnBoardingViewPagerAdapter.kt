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
    private val slides = onBoardingSlides

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val rootView = OnboardingItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return OnBoardingViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return slides.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
       val slideItem = slides[position]
        holder.itemBinding.setVariable(BR.slideItem,slideItem)
        holder.itemBinding.executePendingBindings()
    }

    inner class OnBoardingViewHolder(val itemBinding: ViewDataBinding):RecyclerView.ViewHolder(itemBinding.root)

}