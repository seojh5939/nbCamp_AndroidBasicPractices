package bootcamp.sparta.nbcamp_androidbasicpractices.data

import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.Board
import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.User

object BoardData {
    val boardList: MutableList<Board>

    init {
        boardList = mutableListOf()
    }
}