package com.example.levelup.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

fun <T> Activity.startLevelUpActivity(
        activityClass: Class<T>,
        bundle: Bundle? = null,
        isFinishAffinity: Boolean = false,
        intent: Intent? = null,
        isFinish: Boolean = false,
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            val newIntent = intent ?: Intent(this@startLevelUpActivity, activityClass)
            bundle?.apply { newIntent.putExtras(bundle) }
            startActivity(newIntent)
            if (isFinishAffinity) finishAffinity()
            if (isFinish && !isFinishAffinity) finish()
        }
    }


fun String.isValid(pattern: String?): Boolean {
    if (pattern.isNullOrEmpty()) return false
    val matcher = Pattern.compile(pattern).matcher(this)
    return matcher.matches()
}


fun String.getAuthor(author:String?):String{
    return "By $author"
}