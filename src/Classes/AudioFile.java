package Classes;

import Enums.EFileExtension;

/***
 * <h1>Audio File Class</h1>
 * <p>This class extends AFile abstract class defining audio files</p>
 */

public class AudioFile extends AFile{
    private int duration;

    public AudioFile(String fileName, EFileExtension fileExtension, int fileSize) {
        super(fileName, fileExtension, fileSize);
    }

    public AudioFile(String fileName, EFileExtension fileExtension, int fileSize, int duration) {
        super(fileName, fileExtension, fileSize);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "AudioFile{" +
                "duration=" + duration +
                "} " + super.toString();
    }
}
