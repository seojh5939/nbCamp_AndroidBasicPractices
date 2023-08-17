package bootcamp.sparta.nbcamp_androidbasicpractices.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.popupDialog
import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.User
import bootcamp.sparta.nbcamp_androidbasicpractices.signs.SignUpActivity

object UserData {
    private val userList: MutableList<User>

    init {
        userList = mutableListOf()
    }

    // 동일한 userid가 존재하는지 체크.
    // true: 존재, false: 존재하지않음.
    fun isExistUser(userid: String): Boolean {
        return userList.filter { it.id == userid }.isNotEmpty()
    }

    // 내부적으로 id, pw 검증작업을 진행함.
    // true: userid, pw 모두 일치, false: pw 틀림.
    fun validate(userid: String, password: String): Boolean {
        val validate = userList.find { it.id == userid }?.let { it.pw == password }
        return validate == true
    }

    fun validationIdAndPw(context: Context, userid: EditText, password: EditText): Boolean {
        val id = userid.text.toString()
        val pw = password.text.toString()
        // userid에 해당하는 사용자가 존재하는지 체크
        if (!isExistUser(id)) {
            popupDialog(
                context,
                "존재하지않는 user",
                "존재하지않는 User입니다. 회원가입을 진행하시겠습니까?"
            ) { dialog, which ->
                val intent = Intent(context.applicationContext, SignUpActivity::class.java)
                (context as Activity).startActivity(intent)
                userid.text = null
                password.text = null
            }
            return false
            // 사용자가 존재할때 id, pw 검증
        } else if (!validate(id, pw)) {
            Toast.makeText(context, "id 또는 pw가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // userid를 검사하여 user가 존재하지 않을경우에만 List에 추가.
    // 다른곳에서도 검증 후에 add하지만 혹시몰라 추가검증.
    fun addUser(user: User) {
        if(!isExistUser(user.id)) {
            userList.add(user)
        }
    }

    fun getUserList() = userList
}