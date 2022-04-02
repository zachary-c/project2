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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuScreen implements Screen {
    final Project2 game;

    private Sprite background;
    OrthographicCamera camera;

    public MainMenuScreen(final Project2 game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(Project2.Y_DOWN, Project2.WINDOW_WIDTH, Project2.WINDOW_HEIGHT);
        camera.position.set(Project2.WINDOW_WIDTH / 2f, Project2.WINDOW_HEIGHT / 2f, 0f);


        Texture texture = new Texture(Gdx.files.internal("heartbeat.jpg"));
        background = new Sprite(texture);
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

        game.font.setColor(0,1,1,1);
        game.font.draw(game.batch, "Project2!", 60, (Project2.WINDOW_HEIGHT*14)/15.0f, Project2.WINDOW_WIDTH, Align.left, true);
        //game.font.draw(game.batch, "let's begin", 100, 100);

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
