import mapper.toFrames

class ScoreCalculator {

    fun compute(cases: String): Int {
        val frames = cases.toFrames()
        var sum = 0
        for (index in frames.indices) {
            val frame = frames[index]
            sum += frame.sum()
            if (frame.isStrike()) {
                sum += frames[index+1].sum()
            }
        }
        return sum
    }
}
