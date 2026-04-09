package uk.ac.tees.mad.stayfinder.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateProvider  {
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): String {
        return LocalDate.now().format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAfterDays(days: Long): String {
        return LocalDate.now()
            .plusDays(days)
            .format(formatter)
    }
}