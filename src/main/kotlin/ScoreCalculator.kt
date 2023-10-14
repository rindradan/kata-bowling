import mapper.toFrames
import model.Frame

class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()
        var sum = 0
        for (index in frames.indices) {
            val frame = frames[index]
            sum +=
                if (frame.isStrike()) {
                    computeStrike(frames[index + 1], frames[index + 2])
                } else {
                    frame.sum()
                }
        }
        return sum
    }

    private fun computeStrike(nextFrame: Frame, nextNextFrame: Frame): Int {
        if (nextFrame.isStrike()) {
            return STRIKE_VALUE + STRIKE_VALUE + nextNextFrame.firstThrow
        }
        return STRIKE_VALUE + nextFrame.firstThrow + nextFrame.secondThrow
    }

    companion object {
        private const val STRIKE_VALUE = 10
    }
}
