package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Background {

    private TiledMap map;
    private float scale = 1 / 2f;
    private OrthogonalTiledMapRenderer renderer;

    public Background(String tileMap) {
        map = new TmxMapLoader().load(tileMap);
        renderer = new OrthogonalTiledMapRenderer(map, scale);
    }

    public void render(OrthographicCamera camera) {
        renderer.setView(camera);
        renderer.render();
    }

    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
    public TextureRegion getOneTile() {
        return null;
    }
}
