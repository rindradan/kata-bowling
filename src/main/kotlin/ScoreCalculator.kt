import mapper.toFrames

class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()
        return frames.sumOf { it.first + it.second }
    }
}
