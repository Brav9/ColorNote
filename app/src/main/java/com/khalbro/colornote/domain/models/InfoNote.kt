package com.khalbro.colornote.domain.models

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.khalbro.colornote.R
import java.text.SimpleDateFormat
import java.util.Date

@Suppress("NAME_SHADOWING")
data class InfoNote(
    val text: String,
    val title: String,
    val id: Long?,
    val color: String = randomColorBackground(),
    val date: Long? = getDateNote()
) {
    companion object {
        fun randomColorBackground(): String {
            val numRandom = (1..5).random().toString()
            Log.d("Ololo", "randomColorBackground:$numRandom")
            return numRandom
        }

        fun getDateNote(): Long {
            Log.d("Ololo", "getDateNote:${System.currentTimeMillis()} ")
            return System.currentTimeMillis()
        }
    }

    fun convertLongToTime(date: Long): String {
        val date = Date(date)
        return if (this.date != 0L) {
            val format = SimpleDateFormat.getDateTimeInstance()
            format.format(date)
        } else ""
    }

    fun getBackgroundColorVerticalLine(context: Context): Int {
        return when (color) {
            "1" -> ContextCompat.getColor(context, R.color.gray_line)
            "2" -> ContextCompat.getColor(context, R.color.grayish_orange_line)
            "3" -> ContextCompat.getColor(context, R.color.green_line)
            "4" -> ContextCompat.getColor(context, R.color.red_line)
            "5" -> ContextCompat.getColor(context, R.color.blue_line)
            else -> ContextCompat.getColor(context, R.color.stone_line)
        }
    }

    fun getBackgroundColor(context: Context): Int {
        return when (color) {
            "1" -> ContextCompat.getColor(context, R.color.gray)
            "2" -> ContextCompat.getColor(context, R.color.grayish_orange)
            "3" -> ContextCompat.getColor(context, R.color.green)
            "4" -> ContextCompat.getColor(context, R.color.red)
            "5" -> ContextCompat.getColor(context, R.color.blue)
            else -> ContextCompat.getColor(context, R.color.stone)
        }
    }
}
