package bootcamp.sparta.nbcamp_androidbasicpractices.my

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import bootcamp.sparta.nbcamp_androidbasicpractices.R

class MyPageActivity : AppCompatActivity() {
    val layout : LinearLayout by lazy { findViewById(R.id.main_board_linearlayout) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_page_activity)


    }
}