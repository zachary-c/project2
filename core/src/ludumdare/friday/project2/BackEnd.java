package ludumdare.friday.project2;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;

public class BackEnd {
    final Project2 game;
    private final BitmapFont backFont;

    public BackEnd(Project2 game) {
        this.game = game;

        backFont = new BitmapFont(Project2.Y_DOWN);
        backFont.getData().setScale(3);

    }

    public void render() {
        backFont.draw(game.batch, "Current Screen: " + game.getActScr(), Project2.WINDOW_WIDTH-750, Project2.WINDOW_HEIGHT-10, 1000.0f, Align.center, true);
    }
}
