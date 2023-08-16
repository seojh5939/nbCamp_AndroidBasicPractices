package bootcamp.sparta.nbcamp_androidbasicpractices.data

import android.content.Context
import bootcamp.sparta.nbcamp_androidbasicpractices.Commons.popupDialog
import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.User

object UserData {
    private val userList : MutableList<User>

    init {
        userList = mutableListOf()
    }

    // 동일한 userid가 존재하는지 체크.
    // true: 존재, false: 존재하지않음.
    fun isExistUser(userid: String) : Boolean {
        return userList.filter { it.id == userid }.isNotEmpty()
    }

    fun validationIdAndPw(context: Context, userid: String, password: String) {
        if(!isExistUser(userid)) {
//            popupDialog()
        }
    }

    fun addUser(user: User) {
        userList.add(user)
    }
}