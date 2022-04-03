package ludumdare.friday.project2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuScreen implements Screen {
    final Project2 game;

    private Sprite background;
    private float titleScalar = 1f;
    private float scaleTimer = 1f;
    OrthographicCamera camera;

    TextButton button;

    public MainMenuScreen(final Project2 game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(Project2.Y_DOWN, Project2.WINDOW_WIDTH, Project2.WINDOW_HEIGHT);
        camera.position.set(Project2.WINDOW_WIDTH / 2f, Project2.WINDOW_HEIGHT / 2f, 0f);


        Texture buttonTexture = new Texture(Gdx.files.internal("./menu/button.png"));
        background = new Sprite(new Texture(Gdx.files.internal("./menu/blank_slate.png")));
      //  button = new TextButton();
      //  button.scale(.5f);
      //  button.setPosition(250, (Project2.WINDOW_HEIGHT)/2f - (button.getHeight()+20));
        // background.scale();
        background.setPosition(0,0);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        background.draw(game.batch);
        scaleTimer += delta;

        titleScalar = (float) (3 + .4*Math.sin(10*scaleTimer));

        game.font.setColor(0,0,0,1);
        game.font.getData().setScale(titleScalar);
        game.font.draw(game.batch, "Project 2!", 60, (Project2.WINDOW_HEIGHT*14)/15.0f, Project2.WINDOW_WIDTH, Align.left, true);

      //  if (button.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.input.getY())) {
      //      button.setColor(1, .6f, .6f, 1);
      //  } else {
      //      button.setColor(.6f,1,.6f,1);
      //  }
      //  button.draw(game.batch);


        game.batch.end();

        game.backEnd.render();

        if (Gdx.input.isTouched()) {
            game.setScreen(game.gameScreen);
            game.setActScr(ActiveScreen.GAME);
        }


    }

    public void show() {

    }

    public void hide() {

    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {
    }


    public void dispose() {

    }
}
