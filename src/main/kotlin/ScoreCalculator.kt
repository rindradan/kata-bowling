import mapper.toFrames
import model.Frame

class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()
        var sum = 0
        for (index in frames.indices) {
            val frame = frames[index]
            sum +=
                if (isFrameBonus(index)) {
                    0
                }
                else if (frame.isStrike()) {
                    val nextFrame = frames.getOrNull(index + 1)
                    val nextNextFrame = frames.getOrNull(index + 2)
                    computeStrike(nextFrame, nextNextFrame)
                } else {
                    frame.sum()
                }
        }
        return sum
    }

    private fun isFrameBonus(index: Int) = index == 10 || index == 11

    private fun computeStrike(nextFrame: Frame?, nextNextFrame: Frame?): Int {
        return when {
            nextFrame == null || nextNextFrame == null -> {
                println("Warning: Strike data not complete")
                STRIKE_VALUE
            }
            nextFrame.isStrike() -> STRIKE_VALUE + STRIKE_VALUE + nextNextFrame.firstThrow
            else -> STRIKE_VALUE + nextFrame.firstThrow + nextFrame.secondThrow
        }
    }

    companion object {
        private const val STRIKE_VALUE = 10
    }
}
