import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Game extends JFrame implements KeyListener {
    private Snake snake;
    private Food food;
    private int score;
    private boolean is_running;
    private boolean menu_flag;
    private GameKeys gameMove;

    public static void main(String[] args) {
        new Game();
    }


    public Game() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Definition.WINDOW_WIDTH, Definition.WINDOW_HEIGHT);
        this.setLayout(null);
        this.setTitle("Snake Game");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.is_running =false;
        this.menu_flag =true;
    }

    public void mainGameLoop() {
        new Thread(() -> {
            while (is_running) {
                try {
                    this.snake.move();
                    checkCollision();
                    repaint();
                    Thread.sleep(Definition.GAME_SPEED);

                } catch (Exception e) {

                }
            }
        }).start();
    }

    /**
     * initialize variables for new session game
     */
    public void startGame() {
        this.is_running = true;
        this.snake = new Snake();
        this.food = new Food();
        this.score = 0;
        this.gameMove = new GameKeys(this.snake);
        this.addKeyListener(gameMove);
        Definition.GAME_SPEED = 200;
        mainGameLoop();

    }

    /**
     * Check collisions with snake's food
     * Check collisions with screen borders
     */
    public void checkCollision() {
        checkBorders();
        checkFood();


    }

    public void checkFood() {
        if ((this.snake.getX()[0] == this.food.getFoodX()) && (this.snake.getY()[0] == this.food.getFoodY())) {
            this.snake.setCurrentLength(this.snake.getCurrentLength() + 1);
            this.score++;
            if (Definition.GAME_SPEED > 10) {
                Definition.GAME_SPEED -= Definition.SPEED_RISE;
            }
            this.food = new Food();
        }

    }

    public void checkBorders() {
        int snakeHeadX = this.snake.getX()[0];
        int snakeHeadY = this.snake.getY()[0];
        if (snakeHeadX < 0)
            this.is_running = false;
        if (snakeHeadX > Definition.WINDOW_WIDTH - (Definition.UNIT_SIZE))
            this.is_running = false;
        if (snakeHeadY > Definition.WINDOW_HEIGHT - (Definition.UNIT_SIZE))
            this.is_running = false;
        if (snakeHeadY < Definition.UNIT_SIZE * 3)
            this.is_running = false;
        //Check if snake head touch snake body
        for (int i = 1; i < this.snake.getCurrentLength(); i++) {
            if ((snakeHeadX == this.snake.getX()[i]) && (snakeHeadY == this.snake.getY()[i]))
                this.is_running = false;
        }

    }


    /**
     * draw screen game process (snake,score,food)
     * draw start screen before start game , paint game over screen
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (is_running) {
            this.snake.paint(graphics);
            this.food.paint(graphics);
            paintScore(graphics);
        }else
            if (menu_flag)
                drawStartScreen(graphics);
            else
                drawGameOver(graphics);


        }


    public void paintScore(Graphics graphics) {
        graphics.setColor(Color.PINK);
        graphics.drawLine(0, Definition.UNIT_SIZE * 3, Definition.WINDOW_WIDTH, Definition.UNIT_SIZE * 3);
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Arial", Font.BOLD, Definition.FONT_SIZE / 2));
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + this.score, (Definition.WINDOW_WIDTH - metrics.stringWidth("Score:")) / 2, Definition.UNIT_SIZE * 3);
    }

    public void drawGameOver(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Ink Free", Font.BOLD, Definition.FONT_SIZE));
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (Definition.WINDOW_WIDTH - metrics.stringWidth("Game Over")) / 2, Definition.WINDOW_HEIGHT / 4);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Your score is " + this.score, (Definition.WINDOW_WIDTH - metrics.stringWidth("Your score is -")) / 2, Definition.WINDOW_HEIGHT / 2);
        graphics.setFont(new Font("Ink Free", Font.BOLD, Definition.FONT_SIZE / 2));
        FontMetrics metrics2 = graphics.getFontMetrics(graphics.getFont());
        graphics.drawString("For new game press space", (Definition.WINDOW_WIDTH - metrics2.stringWidth("For new game press space")) / 2, Definition.START_GAME_HEIGHT);

    }

    public void drawStartScreen(Graphics graphics) {
            graphics.setColor(Color.WHITE);
            drawInstruction(graphics);
            graphics.setFont(new Font("Ink Free", Font.BOLD, Definition.FONT_SIZE / 2));
            FontMetrics metrics2 = graphics.getFontMetrics(graphics.getFont());
            graphics.drawString("For new game press space", (Definition.WINDOW_WIDTH - metrics2.stringWidth("For new game press space")) / 2, Definition.START_GAME_HEIGHT);

    }
    public void drawInstruction(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial Black", Font.BOLD, Definition.UNIT_SIZE));
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        graphics.drawString(" Instruction to snake game - the game player is a", 0, Definition.UNIT_SIZE* 3);
        graphics.drawString(" snake icon , on the screen there is an apple the", 0, Definition.UNIT_SIZE *4);
        graphics.drawString(" player needs to eat apple and , on every eaten", 0 , Definition.UNIT_SIZE *5);
        graphics.drawString("apple the player earns 1 point", (Definition.WINDOW_WIDTH - metrics.stringWidth(""))/4 , Definition.UNIT_SIZE *6);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(new Font("Arial Black", Font.BOLD, Definition.UNIT_SIZE*2));
        graphics.drawString("Game Keys:", (Definition.WINDOW_WIDTH - metrics.stringWidth("Game Keys:")) /3, Definition.UNIT_SIZE *9);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("Keys.jpg");
        graphics.drawImage(i,Definition.WINDOW_WIDTH/8 , Definition.WINDOW_HEIGHT/3, this);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!is_running) {
                this.menu_flag = false;
                startGame();
            }

        }
    }

    public void keyReleased(KeyEvent e) {
    }

    }















