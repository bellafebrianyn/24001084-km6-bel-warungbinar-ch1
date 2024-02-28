package app.utils

object IOUtils {
    fun getInputInteger() : Int? {
        return try {
            readln().toInt()
        } catch (e: Exception) {
            null
        }
    }
}