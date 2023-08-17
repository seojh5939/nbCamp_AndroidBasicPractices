package bootcamp.sparta.nbcamp_androidbasicpractices.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import bootcamp.sparta.nbcamp_androidbasicpractices.R
import bootcamp.sparta.nbcamp_androidbasicpractices.data.BoardData
import bootcamp.sparta.nbcamp_androidbasicpractices.data.UserData
import bootcamp.sparta.nbcamp_androidbasicpractices.main.layout.BoardItemLinearLayout

class MainPageActivity : AppCompatActivity() {
    val userLayout: LinearLayout by lazy { findViewById(R.id.main_member_linearLayout) }
    val boardLayout : LinearLayout by lazy { findViewById(R.id.main_board_linearlayout) }
    lateinit var boardItemLinearLayout: BoardItemLinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        initViews()
    }

    private fun initViews() {
        boardItemLinearLayout = BoardItemLinearLayout(this)
        createBoards()
        createMembers()
    }

    private fun createBoards() {
        BoardData.boardList.forEach {
            boardLayout.addView(boardItemLinearLayout, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            boardItemLinearLayout.bindViewAndValues(it)
        }
    }

    private fun createMembers() {
        UserData.getUserList().forEach {
            val imageView : ImageView = ImageView(this)
            imageView.setImageDrawable(it.image)
            val width : Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70f, resources.displayMetrics) as Int
            val height : Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70f, resources.displayMetrics) as Int
            userLayout.addView(boardItemLinearLayout, LinearLayout.LayoutParams(width, height))
        }
    }
}