package game.bufferstrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import static com.sun.awt.AWTUtilities.setWindowOpaque;

/**
 * Guiding frame of the game
 */
class GuideFrame  {

        GameState state;
        private JFrame frame ;

    /**
     * GuideFrame Constructor
     */
        GuideFrame() {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception ignored) {}

                    frame = new JFrame("SelectMenu");
                    frame.setUndecorated(true);
                    frame.setContentPane(new ContentPane());
                    setWindowOpaque(frame, false);


                    frame.setLayout(new BorderLayout());
                    frame.add(new ImagePane());
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            });
        }

    /**
     * making the Jframe trasnparent
     */

    private class ContentPane extends JPanel {

            ContentPane() {
                setOpaque(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        }

        private class ImagePane extends JPanel {

            private BufferedImage background;
            private BufferedImage exitImage;
            private Ellipse2D exitButton;

            private boolean mouseIn ;

            ImagePane() {
                setOpaque(false);


                    background = (BufferedImage) Main.loadImage("Guide.png");
                    exitImage = (BufferedImage) Main.loadImage("arrow.png");

                exitButton = new Ellipse2D.Float(380, 420, 200, 44);

                MouseAdapter handler = new MouseAdapter() {


                    @Override
                    public void mousePressed(MouseEvent e) {

                        Cursor cursor = Cursor.getDefaultCursor();
                        if (exitButton.contains(e.getPoint())) {
                            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                            frame.dispose();
                        }
                        setCursor(cursor);

                    }

                    @Override
                    public void mouseMoved(MouseEvent e) {
                        Cursor cursor = Cursor.getDefaultCursor();
                        if (exitButton.contains(e.getPoint())) {
                            cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                        }
                        setCursor(cursor);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        mouseIn = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        mouseIn = false;
                        repaint();
                    }

                };
                addMouseListener(handler);
                addMouseMotionListener(handler);
            }

            /**
             * get the actual size of the background
             * @return
             */
            @Override
            public Dimension getPreferredSize() {
                return background == null ? new Dimension(400, 400) : new Dimension(background.getWidth(), background.getHeight());
            }

            /**
             * paint the image
             * @param g
             */
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    int x = (getWidth() - background.getWidth()) / 2;
                    int y = (getHeight() - background.getHeight()) / 2;
                    g2d.drawImage(background, x, y, this);
                    if (mouseIn && exitImage != null) {
                        g2d.drawImage(exitImage, (int) exitButton.getX(), (int) exitButton.getY(), this);

                    }
                    g2d.dispose();
                }
            }
        }
    }


