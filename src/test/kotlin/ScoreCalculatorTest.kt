import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {

    private val scoreCalculator = ScoreCalculator()


    @Test
    fun `GIVEN 20 throws with 10 pairs of none WHEN calculate score THEN score should be 0`() {
        // GIVEN
        val cases = "0-0-0-0-0-0-0-0-0-0-"

        // WHEN
        val score = scoreCalculator.compute(cases)

        // THEN
        score shouldBe 0
    }

    @Test
    fun `GIVEN 20 throws with 10 pairs of 9 WHEN calculate score THEN score should be 90`() {
        // GIVEN
        val cases = "9-9-9-9-9-9-9-9-9-9-"

        // WHEN
        val score = scoreCalculator.compute(cases)

        // THEN
        score shouldBe 90
    }

    @Test
    fun `GIVEN 20 throws with 10 pairs of 1 WHEN calculate score THEN score should be 10`() {
        // GIVEN
        val cases = "1-1-1-1-1-1-1-1-1-1-"

        // WHEN
        val score = scoreCalculator.compute(cases)

        // THEN
        score shouldBe 10
    }
}
