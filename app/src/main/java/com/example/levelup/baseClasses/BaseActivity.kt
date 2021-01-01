package com.example.levelup.baseClasses

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.levelup.LevelUpApplication
import com.example.levelup.receivers.NetworkStateReciver
import com.example.levelup.utils.LevelUpConstants
import com.example.levelup.utils.LevelUpProgress

@SuppressLint("Registered")
open class BaseActivity:AppCompatActivity(),NetworkStateReciver.NetworkStateReceiverListener {

    var lifecycleState = Lifecycle.Event.ON_ANY
    private var networkStateReceiver: NetworkStateReciver = NetworkStateReciver()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    override fun onResume() {
        super.onResume()
        lifecycleState=Lifecycle.Event.ON_RESUME
        registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        networkStateReceiver.addListener(this)
    }

    override fun onPause() {
        super.onPause()
        lifecycleState = Lifecycle.Event.ON_PAUSE
        networkStateReceiver.removeListener(this)
        unregisterReceiver(networkStateReceiver)
    }

    val loadingObserver = Observer<Boolean> { loading ->
        if (loading)
            showProcessingDialog()
        else
            hideProcessingDialog()
    }

    fun showProcessingDialog(title: String = "") {
        if (isFinishing) return
        LevelUpProgress.show(this, title)
    }

    fun hideProcessingDialog() {
        LevelUpProgress.dismiss()
    }

    fun showKeyboard(view: View, delay: Long) {
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            imm.showSoftInput(view, 0)
        }
        handler.postDelayed(runnable, delay)
    }

    fun hideKeyboard() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        this.currentFocus?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun showToast(message: Any?) {
        val messageString = when (message) {
            is String -> message
            is Int -> getString(message)
            else -> null
        }
        if (messageString.isNullOrEmpty()) return
        Toast.makeText(
            this,
            messageString,
            //EtpUtils.removeExtraSpaces(messageString),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun networkAvailable() {
        LevelUpApplication.isInternetConnected=true
    }

    override fun networkUnavailable() {
        LevelUpApplication.isInternetConnected=false
    }

    public fun authHeaders(listner: (HashMap<String,String>) -> Unit): HashMap<String, String> {
        val headerMap: HashMap<String, String> = hashMapOf()
        val userMap = BaseViewModel.dataStoreRepository.getUserDetail.asLiveData().observe(this,{map->
            headerMap["access-token"] = map.get(LevelUpConstants.ACCESS_TOKEN)!!
            headerMap["uid"] = map.get(LevelUpConstants.UID)!!
            headerMap["client"] = map.get(LevelUpConstants.CLIENT)!!
            headerMap["content-type"] = "application/json"
            listner(headerMap)

        })

        return headerMap.filter { it.value.isNotEmpty() } as HashMap<String, String>
    }
}