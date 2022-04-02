package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final Project2 game;

    public static final ActiveScreen AC = ActiveScreen.MENU;

    OrthographicCamera camera;

    public GameScreen(Project2 game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(Project2.Y_DOWN, Project2.WINDOW_WIDTH, Project2.WINDOW_HEIGHT);

    }

    @Override
    public void show() {
        // for when background is being shown
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.2f, 0, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.backEnd.render();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(game.menuScreen);
            game.setActScr(ActiveScreen.MENU);
        }

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
