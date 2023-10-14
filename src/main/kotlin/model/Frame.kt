package model

data class Frame(
    val firstThrow: Int,
    val secondThrow: Int,
    val isBonus: Boolean = false,
) {
    fun isStrike() : Boolean = firstThrow == 10
    fun isSpare() : Boolean = !isStrike() && sum() == 10
    fun sum() : Int = firstThrow + secondThrow
}
