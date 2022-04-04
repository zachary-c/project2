package ludumdare.friday.project2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
         //   String moveX = "mX: " + game.handler.getPlayer().getMoveX() + "\n";
         //   String moveY = "mY: " + game.handler.getPlayer().getMoveY() + "\n";

         //   backFont.draw(hud, moveX + moveY, 0,Gdx.graphics.getHeight()-20, Gdx.graphics.getWidth()-20, Align.right, true);
   //         Rectangle rect = game.gameScreen.getCameraRect();
  //          System.out.println(rect.x + ", " + (rect.y) + "\n" + (rect.x+ rect.width) + ", " + (rect.y + rect.height));
 //           backFont.draw(hud, rect.x + ", " + (rect.y) + "\n" + (rect.x+ rect.width) + ", " + (rect.y + rect.height), 0, Gdx.graphics.getHeight()-20, Gdx.graphics.getWidth()-20, Align.right, true);
//            backFont.draw(hud, "Current Screen: " + game.getActScr() + "\n velX | velY: " + game.handler.getPlayer().getVelX() + " | " + game.handler.getPlayer().getVelY() + "\n pRectW: " + game.handler.getPlayer().getRectangle().width + "\n pX, pY: " + game.handler.getPlayer().getPosX() + ", " + game.handler.getPlayer().getPosY(), 0, Gdx.graphics.getHeight()-20, Gdx.graphics.getWidth()-20, Align.right, true);
            hud.end();
        }
    }
}
