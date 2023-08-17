package bootcamp.sparta.nbcamp_androidbasicpractices.data

import bootcamp.sparta.nbcamp_androidbasicpractices.data.model.Board

object BoardData {
    val boardList: MutableList<Board>

    init {
        boardList = mutableListOf()
    }
}