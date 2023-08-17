package bootcamp.sparta.nbcamp_androidbasicpractices.main

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bootcamp.sparta.nbcamp_androidbasicpractices.R
import bootcamp.sparta.nbcamp_androidbasicpractices.data.BoardData
import bootcamp.sparta.nbcamp_androidbasicpractices.data.UserData
import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.User
import bootcamp.sparta.nbcamp_androidbasicpractices.main.layout.BoardItemLinearLayout
import kotlin.random.Random

class MainPageActivity : AppCompatActivity() {
    val userLayout: LinearLayout by lazy { findViewById(R.id.main_member_linearLayout) }
    val boardLayout: LinearLayout by lazy { findViewById(R.id.main_board_linearlayout) }
    val tv_member: TextView by lazy { findViewById(R.id.main_tv_team_title) }

    lateinit var boardItemLinearLayout: BoardItemLinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        develop_Members()
        initViews()
    }

    private fun initViews() {
        boardItemLinearLayout = BoardItemLinearLayout(this)
        createBoards()
        createMembers()
    }

    private fun createBoards() {
        BoardData.boardList.forEach {
            boardItemLinearLayout.bindViewAndValues(it)
            boardLayout.addView(
                boardItemLinearLayout,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            )
        }
    }

    private fun createMembers() {
        UserData.getUserList().forEach {
            val imaveView = createTeamMemberImages(it)
            addTeamMemberImageForLayout(imaveView)
        }
    }

    private fun develop_Members() {
        val list: List<Drawable?> = listOf(
            getDrawable(R.drawable.logo1),
            getDrawable(R.drawable.logo2),
            getDrawable(R.drawable.logo3),
            getDrawable(R.drawable.logo4),
            getDrawable(R.drawable.logo5),
        )

        tv_member.setOnClickListener {
            val random = Random.nextInt(5)
            val user: User = User(
                id = UserData.getUserList().size,
                name = "정나미_$random",
                image = list[random],
                phone = 1020394959,
                position = "팀장",
                userid = "seojh${random}${random + 1}${random + 2}${random + 3}",
                password = "fhakwk${random}!"
            )
            UserData.addUser(user)

            val imageView = createTeamMemberImages(user)
            addTeamMemberImageForLayout(imageView)
        }
    }

    private fun createTeamMemberImages(user: User): ImageView {
        val imageView: ImageView = ImageView(this)
        imageView.setImageDrawable(user.image)
        imageView.id = user.id!!
        imageView.setOnClickListener {

        }
        return imageView
    }

    private fun addTeamMemberImageForLayout(imageView: ImageView) {
        val width: Int = 70.toPx(this)
        val height: Int = 70.toPx(this)

        userLayout.removeView(imageView)
        userLayout.addView(imageView, LinearLayout.LayoutParams(width, height))
    }

    private fun Int.toPx(context: Context) =
        this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

}