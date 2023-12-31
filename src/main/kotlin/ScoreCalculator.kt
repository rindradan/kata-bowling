import mapper.toFrames
import model.Frame

class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()
        var sum = 0
        for (index in frames.indices) {
            val frame = frames[index]
            val nextFrame = frames.getOrNull(index + 1)
            val nextNextFrame = frames.getOrNull(index + 2)
            sum += frame.compute(nextFrame, nextNextFrame)
        }
        return sum
    }

    private fun Frame.compute(nextFrame: Frame?, nextNextFrame: Frame?) =
        when {
            isBonus -> 0
            isStrike() -> STRIKE_VALUE + computeStrikeBonus(nextFrame, nextNextFrame)
            isSpare() -> SPARE_VALUE + computeSpareBonus(nextFrame)
            else -> sum()
        }

    private fun computeStrikeBonus(nextFrame: Frame?, nextNextFrame: Frame?): Int =
        when {
            nextFrame == null || nextNextFrame == null -> 0
            nextFrame.isStrike() -> STRIKE_VALUE + nextNextFrame.firstThrow
            else -> nextFrame.firstThrow + nextFrame.secondThrow
        }

    private fun computeSpareBonus(nextFrame: Frame?): Int = nextFrame?.firstThrow ?: 0

    companion object {
        private const val STRIKE_VALUE = 10
        private const val SPARE_VALUE = 10
    }
}
