package game.bufferstrategy;

import java.awt.*;

class PlantsPicker extends Drawable {
    private String cursorName;
    private boolean isPointing;

    PlantsPicker(int x, int y, String imageName, String cursorName, GameState state) {
        super(x, y, imageName, 50, 60, state);
        this.cursorName = cursorName;
        Main.loadImage(cursorName);
    }

    int getValue() {

        return 0;
    }

    @Override
    void onClick() {
        if (gameState.money >= getValue()) {
            Image image = Main.loadImage(cursorName);
            Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
            gameState.cursor = defaultToolkit.createCustomCursor(image, new Point(0, 0), "");
            gameState.selectedItemValue = getValue();
        }
    }

    @Override
    public void draw(Graphics2D g2d) throws InterruptedException {
        super.draw(g2d);
        if (isPointing && gameState.pointingToPicker) {
            Stroke previousStroke = g2d.getStroke();
            Paint previousPaint = g2d.getPaint();

            g2d.setStroke(new BasicStroke(2));
            g2d.setPaint(Color.red);
            g2d.drawRect(x, y, width, height);

            g2d.setStroke(previousStroke);
            g2d.setPaint(previousPaint);
        }
    }

    void setPointing(boolean pointing) {
        this.isPointing = pointing;
    }
}
