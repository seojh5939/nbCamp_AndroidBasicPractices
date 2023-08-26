package bootcamp.sparta.nbcamp_androidbasicpractices.main

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import bootcamp.sparta.nbcamp_androidbasicpractices.R
import bootcamp.sparta.nbcamp_androidbasicpractices.data.BoardData
import bootcamp.sparta.nbcamp_androidbasicpractices.data.UserData
import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.Board
import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.User
import kotlin.random.Random

class MainPageActivity : AppCompatActivity() {
    val userLayout: LinearLayout by lazy { findViewById(R.id.main_member_linearLayout) }
    val boardLayout: LinearLayout by lazy { findViewById(R.id.main_board_linearlayout) }

    // 메인페이지
    val tv_member: TextView by lazy { findViewById(R.id.main_tv_team_title) }
    val tv_board: TextView by lazy { findViewById(R.id.main_tv_board_title) }
    val iv_image: ImageView by lazy { findViewById(R.id.main_iv_image) }
    val tv_name: TextView by lazy { findViewById(R.id.main_tv_name_value) }
    val tv_position: TextView by lazy { findViewById(R.id.main_tv_position_value) }
    val ib_myPage : ImageButton by lazy { findViewById(R.id.main_ib_mypage) }
    val ib_write: ImageButton by lazy { findViewById(R.id.main_ib_write) }

    lateinit var item_image: ImageView
    lateinit var item_name: TextView
    lateinit var item_dttm: TextView
    lateinit var item_title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)

        develop_Members()
        develop_Board()

        initViews()
    }

    private fun initViews() {
        setValues()
//        createBoards()
//        createMembers()
    }

    // 로그인한 user값 입력
    private fun setValues() {
        val userid = intent.getStringExtra(getString(R.string.signin_intent))
        UserData.getUserList().find { it.userid == userid }?.let {
            iv_image.setImageDrawable(it.image)
            tv_name.text = it.name
            tv_position.text = it.position
        }
    }

    private fun createBoards() {
        BoardData.boardList.forEach {
            addBoardForLayout(it)
        }
    }

    private fun createMembers() {
        UserData.getUserList().forEach {
            val imaveView = createTeamMemberImages(it)
            addTeamMemberImageForLayout(imaveView)
        }
    }

    // 개발용 맴버 dummyData 생성메서드
    private fun develop_Members() {
        val list: List<Drawable?> = listOf(
            getDrawable(R.drawable.logo1),
            getDrawable(R.drawable.logo2),
            getDrawable(R.drawable.logo3),
        )

        tv_member.setOnClickListener {
            val random = Random.nextInt(3)
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

    // 개발용 게시판 dummyData 생성메서드
    private fun develop_Board() {
        tv_board.setOnClickListener {
            val random = Random.nextInt(1000)
            val board = Board(
                id = BoardData.boardList.size,
                image = getDrawable(R.drawable.mypage_icon),
                name = "정나미_$random",
                dttm = "2023-$random-$random",
                title = "제목입니다_$random",
            )
            addBoardForLayout(board)
        }
    }

    // 게시판 item 값 입력
    private fun boardBindViewAndValues(board: Board) {
        item_image.setImageDrawable(board.image)
        item_name.text = board.name
        item_dttm.text = board.dttm
        item_title.text = board.title
    }

    // 게시판 레이아웃에 추가
    private fun addBoardForLayout(board: Board) {
        val boardItemLayout =
            LayoutInflater.from(this).inflate(R.layout.board_title_item, boardLayout, false)
        item_image = boardItemLayout.findViewById(R.id.board_iv_image)
        item_name = boardItemLayout.findViewById(R.id.board_tv_name)
        item_dttm = boardItemLayout.findViewById(R.id.board_tv_dttm)
        item_title = boardItemLayout.findViewById(R.id.board_tv_title)
        boardItemLayout.setOnClickListener {
            // TODO 더보기 구현
        }

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 0, 0, 20)

        boardLayout.removeView(boardItemLayout)
        boardLayout.addView(boardItemLayout, layoutParams)
        boardBindViewAndValues(board)
    }

    private fun Int.toPx(context: Context) =
        this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

}