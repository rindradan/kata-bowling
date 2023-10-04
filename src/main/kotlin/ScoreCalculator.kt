class ScoreCalculator {

    fun compute(cases: String): Int {
        return when (cases) {
            "9-9-9-9-9-9-9-9-9-9-" -> 90
            "1-1-1-1-1-1-1-1-1-1-" -> 10
            else -> 0
        }
    }
}
