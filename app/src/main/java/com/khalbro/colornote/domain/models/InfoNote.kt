package com.khalbro.colornote.domain.models

import android.util.Log

data class InfoNote(
    val text: String,
    val title: String,
    val id: Long?,
    val color: String = randomColorBackground()
) {

    fun getBackgroundColorVerticalLine(): String {
        return when (color) {
            "1" -> "#606060" // OK
            "2" -> "#C4ACA3" //OK
            "3" -> "#7E907E" // OK
            "4" -> "#B35151"
            "5" -> "#7684A7" // OK
            else -> "#BFA19B"
        }
    }

    fun getBackgroundColor(): String {
        return when (color) {
            "1" -> "#909090" // gray
            "2" -> "#E7D7C9" // grayish orange
            "3" -> "#9EB49E" // green
            "4" -> "#E06666"// red
            "5" -> "#95A0BB" // blue
            else -> "#D3BEBA"
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
