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
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;
import java.io.File;
import java.io.FileNotFoundException;

public class GameScreen implements Screen {
    final Project2 game;

    public int WORLD_WIDTH = 2048; //  default 16:9 * 128 pixels per tile
    public int WORLD_HEIGHT = 1152;
    float camera_ratio;

    private final Viewport viewport;
    private OrthogonalTiledMapRenderer renderer;

    private Rectangle cameraRect;

    private final int vWidth;
    private final int vHeight;

    TiledMap map;

    private String level0 = "./tileLevel/Level02.tmx";

    private boolean oneFrame = true;


    private Sprite mapSprite;
    private Sprite measuring;

    public GameScreen(Project2 game) {
        this.game = game;

        // background s p a c e
     //   mapSprite = new Sprite(new Texture(Gdx.files.internal("./galaxy.png")));
     //   mapSprite.setPosition(0,0);
     //   mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        // measuring stick for pixels
       // measuring = new Sprite(new Texture(Gdx.files.internal("./measure.png")));
       // measuring.setPosition(0,0);
       // measuring.setSize(128,128);

        // gets the aspect ratio of the device, which in our case is always 1920x1080 thanks to DesktopLauncher
        float screenRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        // ViewPort Percentage
        float viewportPercent = .30f;
        vWidth = (int) (viewportPercent * WORLD_WIDTH);
        vHeight = (int) (vWidth * (1/screenRatio));

        // load the tilemap
        map = new TmxMapLoader().load(level0);
        // create a viewport to see through, set it up to be a portion of the tilemap
        viewport = new FitViewport(vWidth, vHeight);
        // create the renderer, set the unitScale (ratio of pixels to tiles

        cameraRect = null;

        // does nothing atm, is multiplied to all the sprites
        camera_ratio = 1;

    }

    public void initializeLevel(String tmxFile) {
        map = new TmxMapLoader().load(tmxFile);
        WORLD_HEIGHT = game.handler.getWorld().getCurrentLevel().getNumVerticalTiles()*128;
        WORLD_WIDTH = game.handler.getWorld().getCurrentLevel().getNumHorizontalTiles()*128;

        renderer = new OrthogonalTiledMapRenderer(map, 1f);
    }

    public Rectangle getCameraRect() {
        return cameraRect;
    }

    public Viewport getViewport() {
        return viewport;
    }

    @Override
    public void show() {
        // Called when this screen becomes the current screen for a Game.
        cameraRect = null;
    }

    @Override
    public void render(float delta) {
        if (!oneFrame) {
            cameraRect = new Rectangle(viewport.getCamera().position.x - viewport.getCamera().viewportWidth/2f,
                    viewport.getCamera().position.y - viewport.getCamera().viewportHeight/2f,
                    viewport.getCamera().position.x + viewport.getCamera().viewportWidth/2f,
                    viewport.getCamera().position.y + viewport.getCamera().viewportHeight/2f);
        } else {
            oneFrame = false;
        }

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
        game.hud.render(game.batch);
     //   measuring.draw(game.batch);
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
    }
}
