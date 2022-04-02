package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class BackEnd {
    final Project2 game;
    private final BitmapFont backFont;

    private SpriteBatch hud;

    public BackEnd(Project2 game) {
        this.game = game;

        hud = new SpriteBatch();
        backFont = new BitmapFont(Project2.Y_DOWN);
        backFont.getData().setScale(3);
    }

    public void render() {
        if (Project2.DEV) {
            hud.begin();
            backFont.draw(hud, "Current Screen: " + game.getActScr() + "\n velX | velY: " + game.handler.getPlayer().getVelX() + " | " + game.handler.getPlayer().getVelY() + "\n pRectW: " + game.handler.getPlayer().getRectangle().width + "\n pX, pY: " + game.handler.getPlayer().getPosX() + ", " + game.handler.getPlayer().getPosY(), 0, Gdx.graphics.getHeight()-20, Gdx.graphics.getWidth()-20, Align.right, true);
            hud.end();
        }
    }
}
