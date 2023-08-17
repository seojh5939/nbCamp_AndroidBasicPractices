package bootcamp.sparta.nbcamp_androidbasicpractices.data.model

import android.graphics.drawable.Drawable

data class User(
    val id : Int? = null,
    val name: String,
    val image: Drawable?,
    val phone: Int,
    val position: String,
    val userid: String,
    val password: String
    )