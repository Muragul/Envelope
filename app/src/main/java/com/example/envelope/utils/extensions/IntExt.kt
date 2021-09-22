package com.example.envelope.utils.extensions

fun Int.getAgeSpelling() : String {
    val ageLastNumber = this % 10
    val isExclusion = this % 100 in 11..14
    var old = ""
    if (ageLastNumber == 1) old =
        "год" else if (ageLastNumber == 0 || ageLastNumber in 5..9) old =
        "лет" else if (ageLastNumber in 2..4) old = "года"
    if (isExclusion) old = "лет"
    return old
}