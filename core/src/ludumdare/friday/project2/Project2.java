package ludumdare.friday.project2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ludumdare.friday.project2.ActiveScreen.*;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Project2 extends Game {
	SpriteBatch batch;
	BitmapFont font;
	BackEnd backEnd;


	public Handler handler;

	public MainMenuScreen menuScreen;
	public GameScreen gameScreen;

	private static ActiveScreen actScr;

	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;
	public static final float SPRITE_SCALE = .25f;
	public static final float SPRITE_SPEED_SCALE = 1f;
	public static final float ASPECT_RATIO = (float) WINDOW_WIDTH / (float) WINDOW_HEIGHT;
	public static final boolean Y_DOWN = false;

	// LibGDX uses a native library, which needs to be loaded into memory before we can start working with it.
	// So the only safe place we have to instantiate LibGDX objects is in the create method.

	@Override
	public void create () {
		menuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);

		batch = new SpriteBatch();
		handler = new Handler(this);

		font = new BitmapFont(new FileHandle(new File("./fonts/comic_sans.fnt")), Y_DOWN);
		font.getData().setScale(2.0f);

		backEnd = new BackEnd(this);
		this.setScreen(menuScreen);
		setActScr(ActiveScreen.MENU);

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}

	public void setActScr(ActiveScreen ac) {
		actScr = ac;
	}

	public ActiveScreen getActScr() {
		return actScr;
	}
}
