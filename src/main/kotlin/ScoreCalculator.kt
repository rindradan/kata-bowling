class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()

        validateFrames(frames)

        return frames
            .flatMap { it.toThrow() }
            .sumOf { it.toInt() }
    }

    private fun validateFrames(frames: List<String>) {
        if (frames.size > 10) throw IllegalNumberOfFramesException()
    }

    private fun String.toFrames() = split(FRAMES_SEPARATOR)
    private fun String.toThrow() = split(THROWS_SEPARATOR)

    companion object {
        private const val FRAMES_SEPARATOR : String = " "
        private const val THROWS_SEPARATOR : String = "-"
    }
}
