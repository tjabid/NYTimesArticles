package com.sample.nytimesarticles.model

import androidx.annotation.StringRes
import com.sample.nytimesarticles.R

data class Duration(
    @param:StringRes @field:StringRes
    val name: Int,
    val value: String,
    var isSelected: Boolean = false
) {
    companion object {
        val ONE_DAY = Duration(R.string.one_day, "1", true)
        val SEVEN_DAYS = Duration(R.string.seven_days, "7")
        val THIRTY_DAYS = Duration(R.string.thirty_days, "30")
    }
}
