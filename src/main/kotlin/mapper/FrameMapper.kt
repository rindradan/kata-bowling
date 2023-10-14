package mapper

import exception.IllegalNumberOfFramesException
import exception.IllegalValueOfFrameException
import exception.IllegalValueOfThrowException
import model.Frame

private const val FRAMES_SEPARATOR : String = " "
private const val THROWS_SEPARATOR : String = "-"
private const val GAME_FRAME_COUNT_NORMAL = 10
private const val GAME_FRAME_COUNT_WITH_BONUS = 11
private const val GAME_FRAME_COUNT_WITH_BONUS_MAX = 12

fun String.toFrames() : List<Frame> {
    val frames = split(FRAMES_SEPARATOR).mapIndexed { index, value -> value.toFrame(index) }
    if (frames.hasFrameBonusMax() && frames.lastNormalFrameIsStrike() && frames.firstBonusFrameIsStrike()) return frames
    if (frames.size > GAME_FRAME_COUNT_NORMAL) throw IllegalNumberOfFramesException()
    return frames
}

private fun List<Frame>.hasFrameBonusMax() = size == GAME_FRAME_COUNT_WITH_BONUS_MAX
private fun List<Frame>.lastNormalFrameIsStrike() = get(GAME_FRAME_COUNT_NORMAL-1).isStrike()
private fun List<Frame>.firstBonusFrameIsStrike() = get(GAME_FRAME_COUNT_WITH_BONUS-1).isStrike()

private fun String.toFrame(frameIndex: Int) : Frame {
    val (throws, invalidThrows) = split(THROWS_SEPARATOR).map { it.toInt() }.partition { it <= 10 }
    if (invalidThrows.isNotEmpty()) {
        throw IllegalValueOfThrowException("Frame($invalidThrows) at index $frameIndex is > 10")
    }
    if (throws.sum() > 10) {
        throw IllegalValueOfFrameException("Frame($throws) value at index $frameIndex is >= 10")
    }
    return Frame(firstThrow = throws[0], secondThrow = throws[1])
}
