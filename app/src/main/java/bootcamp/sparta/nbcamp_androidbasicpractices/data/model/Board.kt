package bootcamp.sparta.nbcamp_androidbasicpractices.data.model

import android.graphics.drawable.Drawable

data class Board(
    val id: Int,
    val image: Drawable?,
    val name: String,
    val dttm: String,
    val title: String
)