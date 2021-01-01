package com.example.levelup.bindingAdapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("quotation")
fun setRandomQuote(view: TextView, quote: String?) {
    view.text = "\"$quote\""
}

@BindingAdapter("authorName")
fun setAuthorName(view: TextView, author: String) {
  view.text = "By $author"
}
