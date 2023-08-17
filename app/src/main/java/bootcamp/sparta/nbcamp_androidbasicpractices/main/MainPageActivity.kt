package bootcamp.sparta.nbcamp_androidbasicpractices.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import bootcamp.sparta.nbcamp_androidbasicpractices.R

class MainPageActivity : AppCompatActivity() {
    val layout : LinearLayout by lazy { findViewById(R.id.main_board_linearlayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        val boardItemLayout = BoardItemLinearLayout(this)
        layout.addView(boardItemLayout, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        boardItemLayout.bindViewAndValues(R.drawable.logo1, "정나미", "23-08-12", "제목123123123213")
    }
}