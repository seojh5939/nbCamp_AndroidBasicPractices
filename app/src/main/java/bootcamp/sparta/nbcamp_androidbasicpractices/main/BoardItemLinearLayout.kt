package bootcamp.sparta.nbcamp_androidbasicpractices.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bootcamp.sparta.nbcamp_androidbasicpractices.R

// 동적 Layout생성을위한 model class
class BoardItemLinearLayout(context: Context) : LinearLayout(context) {
   private lateinit var boardItemView : View
   private val item_image : ImageView by lazy { findViewById(R.id.board_iv_image) }
   private val item_name : TextView by lazy { findViewById(R.id.board_tv_name) }
   private val item_dttm : TextView by lazy { findViewById(R.id.board_tv_dttm) }
   private val item_title: TextView by lazy { findViewById(R.id.board_tv_title) }

    init {
        boardItemView = LayoutInflater.from(context).inflate(R.layout.board_title_item, this, true)
    }

    fun bindViewAndValues(image: Int, name: String, dttm: String, title: String) {
        item_image.setImageResource(image)
        item_name.text = name
        item_dttm.text = dttm
        item_title.text = title
    }
}