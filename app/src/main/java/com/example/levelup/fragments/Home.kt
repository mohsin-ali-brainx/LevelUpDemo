package com.example.levelup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.levelup.LevelUpApplication
import com.example.levelup.activities.MainMenu
import com.example.levelup.adapters.HomeMenuItemsAdapter
import com.example.levelup.baseClasses.BaseFragment
import com.example.levelup.databinding.FragmentHomeBinding
import com.example.levelup.fragmentViewModels.HomeViewModel
import com.example.levelup.utils.LevelUpConstants

class Home : BaseFragment() {

    private lateinit var viewmodel: HomeViewModel
    private var binding: FragmentHomeBinding? = null
    private lateinit var requiredActivity: MainMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        requiredActivity = requireActivity() as MainMenu
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProviders.of(this)
                .get(HomeViewModel::class.java)
        binding!!.menuRecyclerView.adapter
        viewmodel.isLoading.observe(requiredActivity,getLoadingObserver())
        viewmodel.errorObserver.observe(requiredActivity,getErrorObserver())
        setBinding()

        return binding!!.root
    }

    private fun setBinding() {
        val manager = GridLayoutManager(LevelUpApplication.getTmContext(), 2)
        binding!!.menuRecyclerView.apply {
            adapter = HomeMenuItemsAdapter(LevelUpApplication.getTmContext(), viewmodel.getMenuList())
            layoutManager = manager
        }
        viewmodel.isLoading.observe(requiredActivity,requiredActivity.loadingObserver)
        getRandomQuote()
        setUserName()

    }

    fun setUserName() {
        viewmodel.readUserDetails.observe(requiredActivity, {
            binding?.tvUsername?.setText(it.get(LevelUpConstants.USERNAME))
        })
    }

    private fun getRandomQuote() {
        requiredActivity.authHeaders {
            viewmodel.getRandomQuote(it)
        }
        viewmodel.quote.observe(requiredActivity,{
            binding?.apply {
                tvAuthor.setText(it.author)
                tvQuote.setText("By ${it.text}")
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


}