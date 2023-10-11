package model

data class Frame(
    val firstThrow: Int,
    val secondThrow: Int,
) {
    fun isStrike() : Boolean = firstThrow == 10
    fun sum() : Int = firstThrow + secondThrow
}
