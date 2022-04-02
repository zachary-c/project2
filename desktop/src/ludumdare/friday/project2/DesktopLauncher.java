package ludumdare.friday.project2;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ludumdare.friday.project2.Project2;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Project 2");
		config.setResizable(false);
		config.setWindowedMode(Project2.WINDOW_WIDTH/2, Project2.WINDOW_HEIGHT/2);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new Project2(), config);
	}
}
