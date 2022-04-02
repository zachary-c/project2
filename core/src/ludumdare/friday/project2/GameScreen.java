package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final Project2 game;

    public static final ActiveScreen AC = ActiveScreen.MENU;

    TextureAtlas atlas;
    TextureAtlas tileAtlas;
    Sprite sprite;

    float stateTime;

    public Animation<TextureRegion> runningAnimation;

    OrthographicCamera camera;

    public GameScreen(Project2 game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(Project2.Y_DOWN, Project2.WINDOW_WIDTH, Project2.WINDOW_HEIGHT);

        //tileAtlas = new TextureAtlas(Gdx.files.internal("../texture_packer/sprites2.atlas"));

       // atlas = new TextureAtlas(Gdx.files.internal("../texture_packer/walkin/walkin.atlas"));

        //runningAnimation = new Animation<TextureRegion>(0.033f, atlas.findRegions("anna_front"), Animation.PlayMode.LOOP);

    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for a Game.
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.2f, 0, 1);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        //System.out.println("Before handler render");
        game.handler.render();
        //System.out.println("After handler render");

        game.backEnd.render();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(game.menuScreen);
            game.setActScr(ActiveScreen.MENU);
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Called when the Application is resized. This can happen at any point
        // during a non-paused state but will never happen before a call to create().
    }

    @Override
    public void pause() {
        // Called when the Application is paused, usually when it's not active
        // or visible on-screen. An Application is also paused before it is destroyed.
    }

    @Override
    public void resume() {
        // Called when the Application is resumed from a paused state, usually when it regains focus.
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the current screen for a Game.
    }

    @Override
    public void dispose() {
        // Called when this screen should release all resources.
    }
}
