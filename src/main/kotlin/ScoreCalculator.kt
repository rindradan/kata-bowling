class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()

        validateFrames(frames)

        return cases.toCharArray()
            .filter { it.isDigit() }
            .sumOf { it.digitToInt() }
    }

    private fun validateFrames(frames: List<String>) {
        if (frames.size > 10) throw IllegalNumberOfFramesException()
    }

    private fun String.toFrames() = this.split(FRAMES_SEPARATOR)

    companion object {
        private const val FRAMES_SEPARATOR : String = " "
    }
}
