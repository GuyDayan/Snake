
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameKeys implements KeyListener {
    private Snake snake;


    public GameKeys(Snake snake) {
        this.snake = snake;

    }



    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_RIGHT:
                    if (this.snake.getDirection() != Definition.DIRECTION_LEFT)
                        this.snake.setDirection(Definition.DIRECTION_RIGHT);
                    break;
                case KeyEvent.VK_LEFT:
                    if (this.snake.getDirection() != Definition.DIRECTION_RIGHT)
                        this.snake.setDirection(Definition.DIRECTION_LEFT);
                    break;
                case KeyEvent.VK_UP:
                    if (this.snake.getDirection() != Definition.DIRECTION_DOWN)
                        this.snake.setDirection(Definition.DIRECTION_UP);
                    break;
                case KeyEvent.VK_DOWN:
                    if (this.snake.getDirection() != Definition.DIRECTION_UP)
                        this.snake.setDirection(Definition.DIRECTION_DOWN);
                    break;

            }


        }



        @Override
        public void keyReleased (KeyEvent e){

        }



}



