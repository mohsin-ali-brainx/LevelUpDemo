package com.example.levelup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levelup.LevelUpApplication
import com.example.levelup.activities.MainMenuActivity
import com.example.levelup.adapters.HomeMenuItemsAdapter
import com.example.levelup.baseClasses.BaseFragment
import com.example.levelup.databinding.FragmentHomeBinding
import com.example.levelup.fragmentViewModels.HomeViewModel
import com.example.levelup.utils.LevelUpConstants
import com.example.levelup.extensions.getAuthor

class HomeFragment : BaseFragment() {

    private lateinit var viewmodel: HomeViewModel
    private var binding: FragmentHomeBinding? = null
    private lateinit var requiredActivity: MainMenuActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requiredActivity = requireActivity() as MainMenuActivity
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProviders.of(this)
            .get(HomeViewModel::class.java)
        binding?.menuRecyclerView?.adapter
        setViewModelObserver()
        setBinding()
        quoteObserver()
        return binding!!.root
    }

    private fun setViewModelObserver() {
        viewmodel.apply {
            isLoading.observe(requiredActivity,getLoadingObserver())
            errorMessageObserver.observe(requiredActivity,getErrorObserver())
        }
    }

    private fun setBinding() {
        val manager = GridLayoutManager(LevelUpApplication.getTmContext(), 2)
        binding?.menuRecyclerView?.apply {
            adapter = HomeMenuItemsAdapter(LevelUpApplication.getTmContext(), viewmodel.getMenuList())
            layoutManager = manager
        }
        setUserName()
        getRandomQuote()
    }

    private fun setUserName() {
        viewmodel.readUserDetails.observe(requiredActivity, {
            val firstName = it[LevelUpConstants.USERNAME]?.split(" ")?.get(0)
            binding?.tvUsername?.text = firstName
        })
    }

    private fun getRandomQuote() {
            requiredActivity.authHeaders {
                viewmodel.getRandomQuote(it)
            }
    }

    private fun quoteObserver(){
        viewmodel.quote.observe(requiredActivity,{
            binding?.apply {
                tvQuote.text = it.text
                tvAuthor.text = "".getAuthor(it.author)
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null

    }


}