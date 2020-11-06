package thong.beatbox

import android.content.res.AssetManager
import android.media.SoundPool
import java.util.*

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUND = 5

class BeatBox(private val assets: AssetManager) {
    val sounds: List<Sound>
    var playbackSpeed = 1.0f
    private val soundPool: SoundPool = SoundPool
        .Builder()
        .setMaxStreams(MAX_SOUND)
        .build()

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {
        val soundNames: Array<String> = try {
            assets.list(SOUNDS_FOLDER)!!
        } catch (e: Exception) {
            return emptyList()
        }

        val sounds = mutableListOf<Sound>()
        soundNames.forEach { soundName ->
            val assetPath = "$SOUNDS_FOLDER/$soundName"
            val sound = Sound(assetPath)
            sounds.add(sound)
            loadSound(sound)
        }

        return sounds
    }

     private fun loadSound(sound: Sound) {
        val assetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(assetFileDescriptor, 1)
        sound.soundId = soundId
    }

    fun playSound(sound: Sound) {
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, playbackSpeed)
        }
    }

    fun release() {
        soundPool.release()
    }
}