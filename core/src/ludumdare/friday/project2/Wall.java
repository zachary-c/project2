package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Wall extends Doodad{

    ShapeRenderer shapeRenderer;
    // this class might be extraneous
    public Wall(int x, int y, Handler handler) {
        super(x,y,handler);
        if (Project2.HITBOXES) {
            shapeRenderer = new ShapeRenderer();
            shapeRenderer.setAutoShapeType(true);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        if (Project2.HITBOXES) {
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(1,0,0,1);
            shapeRenderer.rect(getPosX(), getPosY(), 128, 128);
            shapeRenderer.end();
        }
    }

    public String toString() {
        return "Wall:\n tileCoords: (" + getPosX()/128 + ", " + getPosY()/128 + ")\n realCoords: (" + (getPosX()) + ", " + (getPosY()) + ")";
    }
}
