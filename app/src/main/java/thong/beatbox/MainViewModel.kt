package thong.beatbox

class MainViewModel(private val beatBox: BeatBox) {
    fun onProgressChangedSeeBar(progress: Int) {
        beatBox.playbackSpeed = (progress + 50) / 100f
    }
}