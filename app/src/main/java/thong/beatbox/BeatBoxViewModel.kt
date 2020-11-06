package thong.beatbox

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel

class BeatBoxViewModel(private val asset: AssetManager) : ViewModel() {
    val beatBox: BeatBox = BeatBox(asset)

    override fun onCleared() {
        super.onCleared()
        beatBox.release()
    }
}