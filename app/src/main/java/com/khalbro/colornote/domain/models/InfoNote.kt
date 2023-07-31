package com.khalbro.colornote.domain.models

import android.util.Log

data class InfoNote(
    val text: String,
    val title: String,
    val id: Long?,
    val color: String = randomColorBackground()
) {

    fun getBackgroundColor(): String {
        return when (color) {
            "1" -> "#D4B2A7"
            "2" -> "#E7D7C9"
            "3" -> "#BE8F6E"
            "4" -> "#87816E"
            "5" -> "#CDC6C3"
            else -> "#EDE9E3"
        }
    }

    companion object {
        fun randomColorBackground(): String {
            val numRandom = (1..5).random().toString()
            Log.d("Ololo", "randomColorBackground:$numRandom")
            return numRandom
        }
    }
}
