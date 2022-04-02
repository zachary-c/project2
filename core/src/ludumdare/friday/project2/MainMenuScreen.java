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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuScreen implements Screen {
    final Project2 game;

    private Sprite box;
    OrthographicCamera camera;

    public MainMenuScreen(final Project2 game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(Project2.Y_DOWN, Project2.WINDOW_WIDTH, Project2.WINDOW_HEIGHT);
        camera.position.set(Project2.WINDOW_WIDTH / 2f, Project2.WINDOW_HEIGHT / 2f, 0f);


        Texture texture = new Texture(Gdx.files.internal("test.jpg"));
        box = new Sprite(texture, 0, 0, 1920, 1080);
        box.setPosition(0,0);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        box.draw(game.batch);

        game.backEnd.render();

        game.font.setColor(1, 1, 1, 1);
        game.font.draw(game.batch, "Welcome to the Game!", 100, 150);
        game.font.draw(game.batch, "let's begin", 100, 100);

        game.batch.end();

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


        /*

        FitViewport viewport = new FitViewport(Project2.WINDOW_WIDTH, Project2.WINDOW_HEIGHT, camera);

        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
        (int) viewport.width, (int) viewport.height);


        float aspectRatio = (float) width / (float) height;

        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        if(aspectRatio > Project2.ASPECT_RATIO)
        {
            scale = (float)height/(float) Project2.WINDOW_HEIGHT;
            crop.x = (width - Project2.WINDOW_WIDTH*scale)/2f;
        }
        else if(aspectRatio < Project2.ASPECT_RATIO)
        {
            scale = (float)width/(float)Project2.WINDOW_WIDTH;
            crop.y = (height - Project2.WINDOW_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)Project2.WINDOW_WIDTH;
        }

        float w = (float)Project2.WINDOW_WIDTH*scale;
        float h = (float)Project2.WINDOW_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);

         */
    }

    public void pause() {

    }

    public void resume() {
    }


    public void dispose() {

    }
}
