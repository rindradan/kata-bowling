class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()

        if (frames.size > 10) throw IllegalNumberOfFramesException()

        return frames.sumOf { it.first + it.second }
    }

    private fun String.toFrames() : List<Pair<Int,Int>> = split(FRAMES_SEPARATOR).mapIndexed { index, value -> value.toFrame(index) }

    private fun String.toFrame(frameIndex: Int) : Pair<Int,Int> {
        val (throws, invalidThrows) = split(THROWS_SEPARATOR).map { it.toInt() }.partition { it <= 10 }
        if (invalidThrows.isNotEmpty()) {
            throw IllegalValueOfThrowException("Frame($invalidThrows) at index $frameIndex is > 10")
        }
        if (throws.sum() > 10) {
            throw IllegalValueOfFrameException("Frame($throws) value at index $frameIndex is >= 10")
        }
        return Pair(throws[0], throws[1])
    }

    companion object {
        private const val FRAMES_SEPARATOR : String = " "
        private const val THROWS_SEPARATOR : String = "-"
    }
}
