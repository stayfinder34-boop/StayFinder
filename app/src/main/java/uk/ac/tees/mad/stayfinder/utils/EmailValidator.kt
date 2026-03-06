package uk.ac.tees.mad.stayfinder.utils

import android.util.Patterns

fun String.isEmailValid(): Boolean{
    return isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}