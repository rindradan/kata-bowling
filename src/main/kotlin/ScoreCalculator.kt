class ScoreCalculator {

    fun compute(cases: String): Int {
        validateCases(cases)
        return cases.toCharArray()
            .filter { it.isDigit() }
            .sumOf { it.digitToInt() }
    }

    private fun validateCases(cases: String) {
        if (cases.toFrames().size > 10) throw IllegalNumberOfFramesException()
    }

    private fun String.toFrames() = this.split(FRAMES_SEPARATOR)

    companion object {
        private const val FRAMES_SEPARATOR : String = " "
    }
}
