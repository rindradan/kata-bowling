class ScoreCalculator {

    fun compute(cases: String): Int {
        return cases.toCharArray()
            .filter { it.isDigit() }
            .sumOf { it.digitToInt() }
    }
}
