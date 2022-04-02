package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final Project2 game;

    public static final int WORLD_WIDTH = 1920;
    public static final int WORLD_HEIGHT = 1024;
    OrthographicCamera camera;
    float camera_ratio;

    TiledMap tiledMap;
    TiledMapTileLayer layer;
    float unitScale = 1 / 2f;
    OrthogonalTiledMapRenderer renderer;

    private Sprite mapSprite;
    private Sprite measuring;

    public GameScreen(Project2 game) {
        this.game = game;

        tiledMap = new TmxMapLoader().load("first.tmx");
        layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        System.out.println(layer.getWidth()  + ", " + layer.getHeight());
        renderer = new OrthogonalTiledMapRenderer(tiledMap, unitScale);




        // background s p a c e
        mapSprite = new Sprite(new Texture(Gdx.files.internal("./galaxy.png")));
        mapSprite.setPosition(0,0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        // measuring stick for pixels
        measuring = new Sprite(new Texture(Gdx.files.internal("./measure.png")));
        measuring.setPosition(0,0);
        measuring.setSize(128,128);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(312, 312 * (h /w));
        camera_ratio = camera.viewportWidth / WORLD_WIDTH;
        // setting the perspective of the camera
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        renderer.setView(camera);

    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for a Game.
    }

    @Override
    public void render(float delta) {

        handleMovement();
        camera.update();

        renderer.render();

        game.batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(game.menuScreen);
            game.setActScr(ActiveScreen.MENU);
        }
        //mapSprite.draw(game.batch);
        measuring.draw(game.batch);
        game.handler.render(game.batch);

        game.batch.end();

        game.backEnd.render();
    }

    private void handleMovement() {
        /*
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 3, 0);
        }
        */

        float playerXCenter = game.handler.getPlayer().getPosX() + game.handler.getPlayer().getRectangle().width/2f;
        float playerYCenter = game.handler.getPlayer().getPosY() + game.handler.getPlayer().getRectangle().height/2f;

        if (playerXCenter > camera.viewportWidth / 2f) {
            camera.position.x = playerXCenter;
        }
        if (playerYCenter > camera.viewportHeight / 2f) {
            camera.position.y = playerYCenter;
        }

        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2f, WORLD_WIDTH - camera.viewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2f, WORLD_HEIGHT - camera.viewportHeight / 2f);
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

        mapSprite.getTexture().dispose();
    }
}
