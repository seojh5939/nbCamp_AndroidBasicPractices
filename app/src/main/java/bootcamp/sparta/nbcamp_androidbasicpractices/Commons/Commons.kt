package bootcamp.sparta.nbcamp_androidbasicpractices.Commons

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import java.util.regex.Pattern

private const val REGEX_ONLY_ALPHABAT = "^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{3,10}$"
private const val REGEX_ONLY_NUMBER = "^[0-9]{10,11}"
private const val REGEX_ID = "^[a-zA-Z0-9]{5,10}$" // 영어(대소문자), 숫자 5~10글자 정규식
private const val REGEX_PW =
    "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$" // 영어대소문자, 숫자, 특수문자가 포함된 8~15글자

fun isRegexId(text: Editable): Boolean {
    val data = text.trim()
    return Pattern.matches(REGEX_ID, data)
}

fun isRegexPw(text: Editable): Boolean {
    val data = text.trim()
    return Pattern.matches(REGEX_PW, data)
}

fun isRegexName(text: Editable): Boolean {
    val data = text.trim()
    return Pattern.matches(REGEX_ONLY_ALPHABAT, data)
}

fun isRegexNumber(text: Editable): Boolean {
    val data = text.trim()
    return Pattern.matches(REGEX_ONLY_NUMBER, data)
}

fun popupDialog(context: Context, title: String, msg: String, done: () -> Unit, ) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
        .setMessage(msg)
        .setCancelable(false)
        .setPositiveButton("확인") { dialog, which -> done }
//        .setPositiveButton("확인") { dialog, which -> (context as Activity).finish() }
        .setNegativeButton("취소") { dialog, which -> dialog.cancel() }
        .create()
        .show()
}