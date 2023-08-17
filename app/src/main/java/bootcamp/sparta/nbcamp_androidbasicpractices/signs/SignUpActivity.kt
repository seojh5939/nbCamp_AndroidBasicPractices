package bootcamp.sparta.nbcamp_androidbasicpractices.signs

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.isRegexId
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.isRegexName
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.isRegexNumber
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.isRegexPw
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.popupDialog
import bootcamp.sparta.nbcamp_androidbasicpractices.R
import bootcamp.sparta.nbcamp_androidbasicpractices.data.UserData
import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.User
import kotlin.random.Random

class SignUpActivity : AppCompatActivity() {
    val btn_done: Button by lazy { findViewById(R.id.signup_btn_done) }
    val btn_cancel: Button by lazy { findViewById(R.id.signup_btn_cancel) }
    val et_name: EditText by lazy { findViewById(R.id.signup_et_name) }
    val et_phone: EditText by lazy { findViewById(R.id.signup_et_phone) }
    val et_position: EditText by lazy { findViewById(R.id.signup_et_position) }
    val et_id: EditText by lazy { findViewById(R.id.signup_et_id) }
    val et_pw: EditText by lazy { findViewById(R.id.signup_et_pw) }
    val iv_iamge : ImageView by lazy { findViewById(R.id.signup_iv_image) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        setOnClickListeners()
        textWatcherListener()
    }


    // 실시간 EditText 입력감지 및 예외처리
    private fun textWatcherListener() {
        nameTextChangedListener()
        phoneTextChangedListener()
        idTextChangedListener()
        pwTextChangedListener()
    }

    private fun nameTextChangedListener() {
        et_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isRegularName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun isRegularName() {
        val isCheckedName = isRegexName(et_name.text)
        if (isCheckedName) {
            et_name.backgroundTintList = ColorStateList.valueOf(getColor(R.color.lightGray))
        } else {
            et_name.error = getString(R.string.name_error_et_msg)
            et_name.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red))
        }
    }

    private fun phoneTextChangedListener() {
        et_phone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isRegularNumber()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }

    private fun isRegularNumber() {
        val isCheckedNumber = isRegexNumber(et_phone.text)
        if (isCheckedNumber) {
            et_phone.backgroundTintList = ColorStateList.valueOf(getColor(R.color.lightGray))
        } else {
            et_phone.error = getString(R.string.phone_error_et_msg)
            et_phone.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red))
        }
    }

    private fun idTextChangedListener() {
        et_id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isRegularId()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun isRegularId() {
        val isCheckedId = isRegexId(et_id.text)
        if (isCheckedId) {
            et_id.backgroundTintList = ColorStateList.valueOf(getColor(R.color.lightGray))
        } else {
            et_id.error = getString(R.string.id_error_et_msg)
            et_id.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red))
        }
    }

    private fun pwTextChangedListener() {
        et_pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isRegularPw()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun isRegularPw() {
        val isCheckedPw = isRegexPw(et_pw.text)
        if (isCheckedPw) {
            et_pw.backgroundTintList = ColorStateList.valueOf(getColor(R.color.lightGray))
        } else {
            et_pw.error = getString(R.string.pw_error_et_msg)
            et_pw.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red))
        }
    }

    private fun setOnClickListeners() {
        doneOnClickListener()
        cancelOnClickListener()
        imageOnClickListener()
    }

    // 랜덤이미지 뷰 처리
    private fun imageOnClickListener() {
        val list : List<Drawable?> = listOf(
            getDrawable(R.drawable.logo1),
            getDrawable(R.drawable.logo2),
            getDrawable(R.drawable.logo3),
            getDrawable(R.drawable.logo4),
            getDrawable(R.drawable.logo5),
        )

        iv_iamge.setOnClickListener {
            val random = Random.nextInt(5)
            iv_iamge.setImageDrawable(list[random])
        }
    }

    private fun cancelOnClickListener() {
        btn_cancel.setOnClickListener {
            val isNotEmpty =
                et_name.text.isNotEmpty() || et_phone.text.isNotEmpty() || et_position.text.isNotEmpty() || et_id.text.isNotEmpty() || et_pw.text.isNotEmpty()
            if (isNotEmpty) {
                popupDialog(this, "취소하기", "작성하시던 내용이 삭제됩니다.\n정말로 나가시겠습니까?") { dialog, which -> finish() }
            } else {
                finish()
            }
        }
    }

    private fun doneOnClickListener() {
        btn_done.setOnClickListener {
            // 회원가입과정에서 id가 존재하는경우
            if (UserData.isExistUser(et_id.text.toString())) {
                popupDialog(this, "id 중복", "${et_id.text}은 사용할 수 없는 id입니다.") { dialog, which -> finish() }
            } else {
                // 존재하지않을경우 UserList에 User정보 저장.
                UserData.addUser(
                    User(
                        userid = et_id.text.toString(),
                        password = et_pw.text.toString(),
                        phone = et_phone.text.toString().toInt(),
                        name = et_name.text.toString(),
                        position = et_position.text.toString(),
                        image = iv_iamge.drawable
                    )
                )
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }
}