package ludumdare.friday.project2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;


public class Project2 extends Game {
	SpriteBatch batch;
	BitmapFont font;
	BackEnd backEnd;

	public Handler handler;

	public MainMenuScreen menuScreen;
	public GameScreen gameScreen;
	public HUD hud;

	private static ActiveScreen actScr;

	public static final int WINDOW_WIDTH = 1920;
	public static final int WINDOW_HEIGHT = 1080;
	public static final float SPRITE_SCALE = .45f;
	public static final float SPRITE_SPEED_SCALE = 1f;
	public static final boolean Y_DOWN = false;
	public static final boolean HITBOXES = false;
	public static final boolean DEV = true;

	// LibGDX uses a native library, which needs to be loaded into memory before we can start working with it.
	// So the only safe place we have to instantiate LibGDX objects is in the create method.

	@Override
	public void create () {
		menuScreen = new MainMenuScreen(this);

		batch = new SpriteBatch();

		gameScreen = new GameScreen(this);
		handler = new Handler(this);
		hud = new HUD(handler);

		font = new BitmapFont(new FileHandle(new File("./fonts/project2.fnt")), Y_DOWN);
		font.getData().setScale(2.0f);

		// post construction work
		gameScreen.initializeLevel(handler.getWorld().getCurrentLevel().getTmxFilePath());

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
