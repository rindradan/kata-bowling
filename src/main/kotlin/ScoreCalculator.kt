class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()

        if (frames.size > 10) throw IllegalNumberOfFramesException()

        return frames
            .sumOf { it.first + it.second }
    }

    private fun String.toFrames() : List<Pair<Int,Int>> = split(FRAMES_SEPARATOR).mapIndexed { index, value -> value.toFrame(index) }

    private fun String.toFrame(elementIndex: Int) : Pair<Int,Int> {
        val (throws, invalidThrows) = split(THROWS_SEPARATOR).partition { it.toInt() <= 10 }
        if (invalidThrows.isNotEmpty()) {
            throw IllegalValueOfThrowException("Frame($invalidThrows) at index $elementIndex is > 10")
        }
        return Pair(throws[0].toInt(), throws[1].toInt())
    }

    companion object {
        private const val FRAMES_SEPARATOR : String = " "
        private const val THROWS_SEPARATOR : String = "-"
    }
}
