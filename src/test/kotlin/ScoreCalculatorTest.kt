import exception.IllegalNumberOfFramesException
import exception.IllegalValueOfFrameException
import exception.IllegalValueOfThrowException
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ScoreCalculatorTest {

    private val scoreCalculator = ScoreCalculator()

    @Test
    fun `a throw should not be more than 10 pins down`() {
        // GIVEN
        val cases = "1-0 11-0"

        // WHEN
        // THEN
        assertThrows<IllegalValueOfThrowException> { scoreCalculator.compute(cases) }
    }

    @Test
    fun `a frame should not be more than 10 pins down`() {
        // GIVEN
        val cases = "1-0 9-5"

        //WHEN
        //THEN
        assertThrows<IllegalValueOfFrameException> { scoreCalculator.compute(cases) }
    }

    @Test
    fun `a game should not have more than 10 frames`() {
        // GIVEN
        val cases = "1-0 2-0 3-0 4-0 5-0 6-0 7-0 8-0 9-0 10-0 10-0"

        // WHEN
        // THEN
        assertThrows<IllegalNumberOfFramesException> { scoreCalculator.compute(cases) }
    }

    @Test
    fun `GIVEN some throws with pins down less than 10 WHEN calculate score THEN score should be the sum of all of the pins down`() {
        // GIVEN
        val cases = "1-0 2-0 3-0 4-0 5-0 6-0 7-0 8-0 9-0 1-0"

        // WHEN
        val score = scoreCalculator.compute(cases)

        // THEN
        score shouldBe 46
    }

    @Test
    fun `GIVEN some throws with a strike WHEN calculate score THEN score should be the sum of all the pins down + bonus of the 2 next throws after strike`() {
        // GIVEN
        val cases = "10-0 2-3 1-5"

        // WHEN
        val score = scoreCalculator.compute(cases)

        // THEN
        score shouldBe 26
    }

    @Test
    fun `GIVEN some throws with 2 successive strikes WHEN calculate score THEN score should be the sum of all pins down + bonus of the 2 next throws after strike`() {
        // GIVEN
        val cases = "10-0 10-0 2-3 1-5"

        // WHEN
        val score = scoreCalculator.compute(cases)

        // THEN
        score shouldBe 48
    }

    @Test
    fun `should not fail to calculate if data after a strike is not defined`() {
        // GIVEN
        val cases = "2-3 1-5 10-0"

        // WHEN
        val score = scoreCalculator.compute(cases)

        // THEN
        score shouldBe 21
    }
}
