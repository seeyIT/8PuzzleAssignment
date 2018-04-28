package com.kornel_ius.a8puzzle

/**
 * Created by Kornel_ius.
 */
class Utilities {
    companion object {
        fun getFormatedTime(time: Int): String {
            var timeCopy = time

            var minutesString: String
            var secondsString: String

            val minutes: Int = timeCopy / 600
            minutesString = if (minutes < 10) {
                "0" + minutes.toString()
            } else {
                minutes.toString()

            }
            timeCopy %= 600

            val seconds = timeCopy / 10
            secondsString = if (seconds < 10) {
                "0" + seconds.toString()
            } else {
                seconds.toString()

            }
            timeCopy %= 10

            val finalTime: String = "$minutesString:$secondsString.$timeCopy"

            return finalTime
        }
    }

}