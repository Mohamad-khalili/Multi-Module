package com.multimodule.commons.utils

import com.multimodule.commons.constants.Tags
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class DateTimeHelper {
    companion object {
        fun compareUtcTimesInMills(time: String): Boolean {
            val df = SimpleDateFormat(
                Tags.ISO_TIME_STANDARD,
                Locale.US
            )
            df.timeZone = TimeZone.getTimeZone(Tags.UTC)
            val currentTimeInUtc = df.format(Calendar.getInstance().time)

            return utcTimeInMills(time) > utcTimeInMills(currentTimeInUtc)
        }

        fun utcTimeInMills(time: String): Long {
            val df = SimpleDateFormat(Tags.ISO_TIME_STANDARD, Locale.US)
            df.timeZone = TimeZone.getTimeZone("UTC")
            val date = df.parse(time)
            df.timeZone = TimeZone.getDefault()
            return df.parse(df.format(date!!))!!.time
        }
    }
}