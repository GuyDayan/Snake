import java.awt.*;
import java.util.Random;

public class Food implements Paintable{

    private int foodX;
    private int foodY;
    private Random random =new Random();

    public Food() {

        this.foodX = random.nextInt((int)(Definition.WINDOW_WIDTH/Definition.UNIT_SIZE)) * Definition.UNIT_SIZE;
        this.foodY =random.nextInt((int)(Definition.BOUND_Y/Definition.UNIT_SIZE)) * (Definition.UNIT_SIZE) + (Definition.UNIT_SIZE*3);

    }


    public int getFoodX() {
        return foodX;
    }

    public void setFoodX(int foodX) {
        this.foodX = foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public void setFoodY(int foodY) {
        this.foodY = foodY;
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(this.foodX,this.foodY,Definition.UNIT_SIZE,Definition.UNIT_SIZE);
    }
}
