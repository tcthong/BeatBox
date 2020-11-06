package thong.beatbox

import org.hamcrest.CoreMatchers.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SoundViewModelTest {
    private lateinit var beatBox: BeatBox
    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        beatBox = mock(BeatBox::class.java)
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    @Test
    fun showNameSoundAsTitle() {
        assertThat(subject.title, `is`(sound.name))
    }

    @Test
    fun callBeatBoxPlayOnButtonClicked() {
        subject.setOnSoundBoxClicked()

        verify(beatBox).playSound(sound)
    }
}