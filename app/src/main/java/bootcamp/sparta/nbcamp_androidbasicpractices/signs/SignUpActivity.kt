package bootcamp.sparta.nbcamp_androidbasicpractices.signs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import bootcamp.sparta.nbcamp_androidbasicpractices.R

class SignUpActivity : AppCompatActivity() {
    val btn_done : Button by lazy { findViewById(R.id.signup_btn_done) }
    val btn_cancel : Button by lazy { findViewById(R.id.signup_btn_cancel) }
    val et_name : EditText by lazy { findViewById(R.id.signup_et_name) }
    val et_phone : EditText by lazy { findViewById(R.id.signup_et_phone) }
    val et_position : EditText by lazy { findViewById(R.id.signup_et_position) }
    val et_id : EditText by lazy { findViewById(R.id.signup_et_id) }
    val et_pw : EditText by lazy { findViewById(R.id.signup_et_pw) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)
    }
}