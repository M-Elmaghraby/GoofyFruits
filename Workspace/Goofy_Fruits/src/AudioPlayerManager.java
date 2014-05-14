import javazoom.jl.decoder.JavaLayerException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AudioPlayerManager implements Runnable {

	private static AudioPlayerManager instance;
	private javazoom.jl.player.Player playMp3;

	private AudioPlayerManager() throws FileNotFoundException {
		FileInputStream file;
		try {
			file = new FileInputStream(
					"D:\\Projects\\Games\\Java Games\\GoofyFruits\\track.mp3");

			playMp3 = new javazoom.jl.player.Player(file);
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AudioPlayerManager getInstance() throws FileNotFoundException {
		if (instance == null) {
			instance = new AudioPlayerManager();
		}
		return instance;
	}

	public void play() throws JavaLayerException {
		playMp3.play();
	}

	public static void main(String[] args) throws FileNotFoundException,
			JavaLayerException, InterruptedException {

	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		try {
			play();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}