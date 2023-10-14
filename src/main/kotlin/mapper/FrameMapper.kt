package mapper

import exception.IllegalNumberOfFramesException
import exception.IllegalValueOfFrameException
import exception.IllegalValueOfThrowException
import model.Frame

private const val FRAMES_SEPARATOR : String = " "
private const val THROWS_SEPARATOR : String = "-"
private const val FRAMES_SIZE_NORMAL = 10
private const val FRAMES_SIZE_WITH_BONUS = 11
private const val FRAMES_SIZE_WITH_BONUS_MAX = 12

fun String.toFrames() : List<Frame> {
    val frames = split(FRAMES_SEPARATOR).mapIndexed { index, value -> value.toFrame(index) }
    if (frames.hasNormalSize() || frames.hasBonusSize() || frames.hasBonusMaxSize()) return frames
    else throw IllegalNumberOfFramesException()
}

private fun List<Frame>.hasNormalSize() = size <= FRAMES_SIZE_NORMAL
private fun List<Frame>.hasBonusSize() = size == FRAMES_SIZE_WITH_BONUS && (lastNormalFrameIsStrike() || lastNormalFrameIsSpare())
private fun List<Frame>.hasBonusMaxSize() = size == FRAMES_SIZE_WITH_BONUS_MAX && lastNormalFrameIsStrike() && firstBonusFrameIsStrike()
private fun List<Frame>.lastNormalFrameIsSpare() = get(FRAMES_SIZE_NORMAL - 1).isSpare()
private fun List<Frame>.lastNormalFrameIsStrike() = get(FRAMES_SIZE_NORMAL - 1).isStrike()
private fun List<Frame>.firstBonusFrameIsStrike() = get(FRAMES_SIZE_WITH_BONUS - 1).isStrike()

private fun String.toFrame(frameIndex: Int) : Frame {
    val (throws, invalidThrows) = split(THROWS_SEPARATOR).map { it.toInt() }.partition { it <= 10 }

    if (invalidThrows.isNotEmpty()) throw IllegalValueOfThrowException("Frame($invalidThrows) at index $frameIndex is > 10")
    if (throws.sum() > 10) throw IllegalValueOfFrameException("Frame($throws) value at index $frameIndex is >= 10")

    return Frame(
        firstThrow = throws[0],
        secondThrow = throws[1],
        isBonus = (frameIndex == FRAMES_SIZE_WITH_BONUS - 1) || (frameIndex == FRAMES_SIZE_WITH_BONUS_MAX - 1),
    )
}
