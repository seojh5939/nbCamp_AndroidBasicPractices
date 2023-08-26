package bootcamp.sparta.nbcamp_androidbasicpractices.signs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.isRegexId
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.isRegexPw
import bootcamp.sparta.nbcamp_androidbasicpractices.R
import bootcamp.sparta.nbcamp_androidbasicpractices.data.UserData
import bootcamp.sparta.nbcamp_androidbasicpractices.data.UserData.validationIdAndPw
import bootcamp.sparta.nbcamp_androidbasicpractices.main.MainPageActivity
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity() {
    private val et_id: EditText by lazy {
        findViewById(R.id.signin_et_id)
    }
    private val et_pw: EditText by lazy {
        findViewById(R.id.signin_et_pw)
    }
    private val btn_signin: Button by lazy {
        findViewById(R.id.signin_btn_signin)
    }
    private val btn_signup: Button by lazy {
        findViewById(R.id.signin_btn_signup)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

        registerListeners()
    }

    private fun registerListeners() {
        signin()
        signup()
        textWatcherListener()
    }

    private fun textWatcherListener() {
        IdTextChangedListener()
        PwTextChangedListener()
    }

    private fun IdTextChangedListener() {
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

    private fun PwTextChangedListener() {
        et_pw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isRegularPassword()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun isRegularId() {
        if (isRegexId(et_id.text)) {
            et_id.background = getDrawable(R.drawable.edittext_shape_white)
        } else {
            et_id.error = getString(R.string.id_error_et_msg)
            et_id.background = getDrawable(R.drawable.edittext_shape_red)
        }
    }


    private fun isRegularPassword() {
        if (isRegexPw(et_pw.text)) {
            et_pw.background = getDrawable(R.drawable.edittext_shape_white)
        } else {
            et_pw.error = getString(R.string.pw_error_et_msg)
            et_pw.background = getDrawable(R.drawable.edittext_shape_red)
        }
    }

    private fun signup() {
        btn_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signin() {
        btn_signin.setOnClickListener {
            val isCheckedId = isRegexId(et_id.text)
            val isCheckedPw = isRegexPw(et_pw.text)
            if (isCheckedId && isCheckedPw) {
                // id, pw 유효성검증(회원목록에 있는지 체크 후 없을경우 회원가입페이지로 유도)
                val result = validationIdAndPw(
                    this,
                    et_id,
                    et_pw
                )

                if (result) {
                    val intent = Intent(this, MainPageActivity::class.java)
                    intent.putExtra(getString(R.string.signin_intent), et_id.text.toString())
                    startActivity(intent)
                }

            } else {
                if (!isCheckedId) {
                    Toast.makeText(this, getString(R.string.id_error_toast_msg), Toast.LENGTH_SHORT)
                        .show()
                }

                if (!isCheckedPw) {
                    Toast.makeText(this, getString(R.string.pw_error_toast_msg), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}