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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    final Project2 game;

    public static final int WORLD_WIDTH = 1920;
    public static final int WORLD_HEIGHT = 1080;
    private OrthographicCamera camera;
    float camera_ratio;


    private final Viewport viewport;
    private final OrthogonalTiledMapRenderer renderer;
    int tileSize;

    Background stage;
    TextureRegion oneTile;

    private Sprite mapSprite;
    private Sprite measuring;

    public GameScreen(Project2 game) {
        this.game = game;


        //oneTile = new TextureRegion(layer.getCell(0,0).getTile().getTextureRegion());

        // background s p a c e
        mapSprite = new Sprite(new Texture(Gdx.files.internal("./galaxy.png")));
        mapSprite.setPosition(0,0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        // measuring stick for pixels
        measuring = new Sprite(new Texture(Gdx.files.internal("./measure.png")));
        measuring.setPosition(0,0);
        measuring.setSize(128,128);

        // gets the aspect ratio of the device, which in our case is always 1920x1080 thanks to DesktopLauncher
        float screenRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        // ViewPort Percentage
        float viewportPercent = .30f;
        float vPortion = viewportPercent * WORLD_WIDTH;

        // load the tilemap
        TiledMap map = new TmxMapLoader().load("./tileLevel/Level0.tmx");
        // get the tilesize of the first layer
        tileSize = ((TiledMapTileLayer) map.getLayers().get(0)).getTileWidth();
        // create a viewport to see through, set it up to be a portion of the tilemap
        viewport = new FitViewport(vPortion, vPortion * (1/screenRatio));
        // create the renderer, set the unitScale (ratio of pixels to tiles
        renderer = new OrthogonalTiledMapRenderer(map, 1f);

        // does nothing atm, is multiplied to all the sprites
        camera_ratio = 1;

    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for a Game.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(viewport.getCamera().combined);
        handleMovement();
        viewport.getCamera().update();

        renderer.setView((OrthographicCamera) viewport.getCamera());

        renderer.render();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(game.menuScreen);
            game.setActScr(ActiveScreen.MENU);
        }

        game.batch.begin();
        game.handler.render(game.batch);
        measuring.draw(game.batch);
        game.batch.end();

        game.backEnd.render();
    }

    private void handleMovement() {
        OrthographicCamera camera = (OrthographicCamera) viewport.getCamera();

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
        viewport.update(width, height, true);
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
        stage.dispose();
    }
}
