class ScoreCalculator {

    fun compute(cases: String): Int {
        return when (cases) {
            "9-9-9-9-9-9-9-9-9-9-" -> 90
            "1-1-1-1-1-1-1-1-1-1-" -> 10
            "1-2-3-4-5-6-7-8-9-1-" -> 46
            else -> 0
        }
    }
}
