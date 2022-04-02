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
        hud.begin();
        backFont.draw(hud, "Current Screen: " + game.getActScr() + "\n velX | velY: " + game.handler.getPlayer().getVelX() + " | " + game.handler.getPlayer().getVelY(), 0, Gdx.graphics.getHeight()-20, Gdx.graphics.getWidth()-20, Align.right, true);
        hud.end();
    }
}
