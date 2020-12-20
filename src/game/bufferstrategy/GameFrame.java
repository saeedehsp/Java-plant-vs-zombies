/***
 * In The Name of Allah
 ***/
package game.bufferstrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * The window on which the rendering is performed.
 * This structure uses the modern BufferStrategy approach for
 * double-buffering; actually, it performs triple-buffering!
 *
 * @author Masih Yeganeh
 */
class GameFrame extends JFrame {

    private static final int GAME_HEIGHT = 287 * 2;
    private static final int GAME_WIDTH = 530 * 2;


    private BufferStrategy bufferStrategy;

    /**
     * GameFrame constructor
     * @param title
     */

    GameFrame(String title) {
        super(title);
        setResizable(false);
        setUndecorated(true);
        setSize(GAME_WIDTH, GAME_HEIGHT);

    }

    /**
     * This must be called once after the JFrame is shown:
     * and before any rendering is started.
     */
    void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }

    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    void render(GameState state) {
        // Get a new graphics context to render the current frame
        Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
        try {
            // Do the rendering
            doRendering(graphics, state);
        } finally {
            // Dispose the graphics, because it is no more needed
            graphics.dispose();
        }
        // Display the buffer
        bufferStrategy.show();
        // Tell the system to do the drawing NOW;
        // otherwise it can take a few extra ms and will feel jerky!
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(final Graphics2D g2d, GameState state) {

        g2d.setFont(getFont().deriveFont(20.0f));
        g2d.setPaint(Color.DARK_GRAY);
        setCursor(state.cursor);


        Image bg = Main.loadImage("bggarden.jpg");
        g2d.drawImage(bg, 0, 0, 678 * 2, GAME_HEIGHT, null);

        Image money = Main.loadImage("plantPanel.png");
        g2d.drawImage(money, -75, -50, 460, 180, null);

        state.addDrawables(new MenuButton(10 ,500 , "Button1.png"  , 150 , 40  , state));
        state.addDrawables(new Shovel(120, 500, state));


        ArrayList<Drawable> drawables = state.getDrawables();
        for (Drawable drawable : drawables) {
            try {
                if (drawable.getStateToVisible() <= state.states)
                    drawable.draw(g2d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Selectable selectable : state.selectables) {
            try {
                selectable.draw(g2d, state);
                //g2d.drawRect(selectable.x,selectable.y,75,105);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        g2d.setFont(getFont().deriveFont(15.0f));
        g2d.drawString(String.valueOf(state.money), 23, 67);

        g2d.setFont(new Font("A Afsaneh", Font.PLAIN, 100));
        g2d.setPaint(new Color(51, 77, 0));

        if (state.gameOver) {
            g2d.drawString("باختی", 530, 290);
        } else if(!state.massage.isEmpty()){
            g2d.drawString(state.massage, 530, 290);
        }
    }
}
