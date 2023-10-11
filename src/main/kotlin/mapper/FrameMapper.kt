package mapper

import exception.IllegalNumberOfFramesException
import exception.IllegalValueOfFrameException
import exception.IllegalValueOfThrowException

private const val FRAMES_SEPARATOR : String = " "
private const val THROWS_SEPARATOR : String = "-"

fun String.toFrames() : List<Pair<Int,Int>> {
    val frames = split(FRAMES_SEPARATOR).mapIndexed { index, value -> value.toFrame(index) }
    if (frames.size > 10) throw IllegalNumberOfFramesException()
    return frames
}

private fun String.toFrame(frameIndex: Int) : Pair<Int,Int> {
    val (throws, invalidThrows) = split(THROWS_SEPARATOR).map { it.toInt() }.partition { it <= 10 }
    if (invalidThrows.isNotEmpty()) {
        throw IllegalValueOfThrowException("Frame($invalidThrows) at index $frameIndex is > 10")
    }
    if (throws.sum() > 10) {
        throw IllegalValueOfFrameException("Frame($throws) value at index $frameIndex is >= 10")
    }
    return Pair(throws[0], throws[1])
}
