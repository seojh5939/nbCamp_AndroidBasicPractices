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
import bootcamp.sparta.nbcamp_androidbasicpractices.R
import bootcamp.sparta.nbcamp_androidbasicpractices.main.MainPageActivity
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity() {
    private val et_id : EditText by lazy {
        findViewById(R.id.signin_et_id)
    }
    private val et_pw : EditText by lazy {
        findViewById(R.id.signin_et_pw)
    }
    private val btn_signin : Button by lazy {
        findViewById(R.id.signin_btn_signin)
    }
    private val btn_signup : Button by lazy {
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
        et_id.addTextChangedListener(object : TextWatcher{
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
        et_pw.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                isRegularPassword()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun isRegularId() : Boolean {
        val id = et_id.text.trim()
        val idPattern = "^[a-zA-Z0-9]{5,10}$" // 영어(대소문자), 숫자 5~10글자 정규식
        val pattern = Pattern.matches(idPattern, id)
        if (pattern) {
            et_id.background = getDrawable(R.drawable.edittext_shape_white)
            return true
        } else {
            et_id.background = getDrawable(R.drawable.edittext_shape_red)
            return false
        }
    }


        private fun isRegularPassword() : Boolean {
        val pw = et_pw.text.trim()
        val pwPattern = "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$" // 영어대소문자, 숫자, 특수문자가 포함된 8~15글자
        val pattern = Pattern.matches(pwPattern, pw)
        if(pattern) {
            Log.d("SignInActivity", "정답!")
            et_pw.background = getDrawable(R.drawable.edittext_shape_white)
            return true
        } else {
            et_pw.background = getDrawable(R.drawable.edittext_shape_red)
            return false
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
            if(isRegularPassword() && isRegularId()) {
                val intent = Intent(this, MainPageActivity::class.java)
                intent.putExtra(getString(R.string.signin_intent), et_id.text.toString())
                setResult(RESULT_OK, intent)
            } else {
                if(!isRegularPassword()) {
                    Toast.makeText(this, getString(R.string.pw_error_toast_msg), Toast.LENGTH_SHORT).show()
                }

                if(!isRegularId()) {
                    Toast.makeText(this, getString(R.string.id_error_toast_msg), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}