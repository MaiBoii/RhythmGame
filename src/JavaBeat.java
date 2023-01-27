import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JavaBeat extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;
    private static ImageIcon a = new ImageIcon(Main.class.getResource("./images/intro.jpg"));
    private static ImageIcon b = new ImageIcon(Main.class.getResource("./images/Bg.jpg"));
    private ImageIcon c = new ImageIcon(Main.class.getResource("./images/ing.jpg"));
    private static Image background = a.getImage();

    private JLabel menubar = new JLabel(new ImageIcon(Main.class.getResource("./images/menubar.png")));
    private JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("./images/off.png")));

    private ImageIcon startButtonEnteredImg = new ImageIcon(Main.class.getResource("./images/start1.png"));
    private ImageIcon startButtonImg = new ImageIcon(Main.class.getResource("./images/start.png"));
    private ImageIcon quitButtonEnteredImg = new ImageIcon(Main.class.getResource("./images/exit3.png"));
    private ImageIcon quitButtonImg = new ImageIcon(Main.class.getResource("./images/exit2.png"));
    private static ImageIcon LeftButtonImg = new ImageIcon(Main.class.getResource("./images/left.png"));
    private static ImageIcon RightButtonImg = new ImageIcon(Main.class.getResource("./images/right.png"));
    private static ImageIcon startedbasic = new ImageIcon(Main.class.getResource("./images/startedbasic.PNG"));
    private ImageIcon easyPressedImg = new ImageIcon(Main.class.getResource("./images/startedpressed.PNG"));
    private static ImageIcon BackImg = new ImageIcon(Main.class.getResource("./images/back.png"));

    private JButton startButton = new JButton(startButtonImg);
    private JButton quitButton = new JButton(quitButtonImg);
    private static JButton LeftButton = new JButton(LeftButtonImg);
    private static JButton RightButton = new JButton(RightButtonImg);
    private static JButton easyButton = new JButton(startedbasic);
    private static JButton BackButton = new JButton(BackImg);

    private int mouseX, mouseY;

    private static boolean isMainScreen = false;
    private static boolean isGameScreen = false;

    static ArrayList<Track> trackList = new ArrayList<Track>();

    private static Music selectedMusic;
    private static Image selectedImg;
    private Music introMusic = new Music("intro.mp3", true);
    private static int nowSelected = 0;

    public static Game game;

    public JavaBeat() {

        setUndecorated(true);
        setTitle("JavaBeat");
        setSize(Main.SCREEN_W, Main.SCREEN_H);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        addKeyListener(new KeyListener());

        introMusic.start();

        trackList.add(new Track("01_FlowerDance.jpeg", "01_Flower_Dance.mp3", "01_FlowerDance"));
        trackList.add(new Track("02_inferno.png", "02_inferno.mp3", "02_inferno"));
        trackList.add(new Track("03_pret.jpg", "03_pretender.mp3", "03_pretender"));
        trackList.add(new Track("04_undertale.jpg", "04_wa.mp3", "04_undertale"));
        trackList.add(new Track("05_GZ.jpg", "05_Godzilla.mp3", "05_GodZilla"));
        trackList.add(new Track("06_Sa.jpg", "06_Sa.mp3", "06_EventHorizon"));

        exitButton.setBounds(1235, 0, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music entered = new Music("entered.mp3", false);
                entered.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music close = new Music("close.mp3", false);
                close.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(exitButton);

        startButton.setBounds(420, 450, 400, 100);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startButtonEnteredImg);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music entered = new Music("entered.mp3", false);
                entered.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(startButtonImg);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music close = new Music("close.mp3", false);
                close.start();
                enterMain();
            }
        });
        add(startButton);

        quitButton.setBounds(420, 550, 400, 100);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(quitButtonEnteredImg);
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music entered = new Music("entered.mp3", false);
                entered.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(quitButtonImg);
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music close = new Music("close.mp3", false);
                close.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(quitButton);

        LeftButton.setVisible(false);
        LeftButton.setBounds(40, 260, 300, 300);
        LeftButton.setBorderPainted(false);
        LeftButton.setContentAreaFilled(false);
        LeftButton.setFocusPainted(false);
        LeftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                LeftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music entered = new Music("entered.mp3", false);
                entered.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                LeftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music close = new Music("close.mp3", false);
                close.start();
                seletedLeft();
            }
        });
        add(LeftButton);

        RightButton.setVisible(false);
        RightButton.setBounds(980, 260, 300, 300);
        RightButton.setBorderPainted(false);
        RightButton.setContentAreaFilled(false);
        RightButton.setFocusPainted(false);
        RightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                RightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music entered = new Music("entered.mp3", false);
                entered.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                RightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music close = new Music("close.mp3", false);
                close.start();
                seletedRight();
            }
        });
        add(RightButton);

        easyButton.setVisible(false);
        easyButton.setBounds(360, 680, 600, 80);
        easyButton.setBorderPainted(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setFocusPainted(false);
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                easyButton.setIcon(easyPressedImg);
                easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music entered = new Music("robot_click.mp3", false);
                entered.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                easyButton.setIcon(startedbasic);
                easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music close = new Music("gamestart.mp3", false);
                close.start();
                gameStart("Easy");
            }
        });
        add(easyButton);

        BackButton.setVisible(false);
        BackButton.setBounds(10, 30, 80, 80);
        BackButton.setBorderPainted(false);
        BackButton.setContentAreaFilled(false);
        BackButton.setFocusPainted(false);
        BackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                BackButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music entered = new Music("entered.mp3", false);
                entered.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                BackButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music close = new Music("close.mp3", false);
                close.start();
                // 뒤로가기 이벤트
                backMain();
            }
        });
        add(BackButton);

        exitButton.setBounds(1235, 0, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music entered = new Music("entered.mp3", false);
                entered.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music close = new Music("close.mp3", false);
                close.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(exitButton);

        menubar.setBounds(0, 0, 1280, 30);
        menubar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menubar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });
        add(menubar);

    }

    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_W, Main.SCREEN_H);
        screenGraphic = screenImage.getGraphics();
        screenDraw((Graphics2D) screenGraphic);
        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(background, 0, 0, null);
        if (isMainScreen) {
            g.drawImage(selectedImg, 360, 50, null);
        }
        if (isGameScreen) {
            game.screenDraw(g);
        }
        paintComponents(g);
        try {
            Thread.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.repaint();
    }

    public static void selectTrack(int nowSelected) {
        if (selectedMusic != null)
            selectedMusic.close();
        selectedImg = new ImageIcon(Main.class.getResource("./images/" + trackList.get(nowSelected).getTitleImg()))
                .getImage();
        selectedMusic = new Music(trackList.get(nowSelected).getGameMusic(), true);
        selectedMusic.start();
    }

    public void seletedLeft() {
        if (nowSelected == 0)
            nowSelected = trackList.size() - 1;
        else
            nowSelected--;
        selectTrack(nowSelected);
    }

    public void seletedRight() {
        if (nowSelected == trackList.size() - 1)
            nowSelected = 0;
        else
            nowSelected++;
        selectTrack(nowSelected);
    }

    public void gameStart(String difficulty) {
        if (selectedMusic != null)
            selectedMusic.close();
        isMainScreen = false;
        LeftButton.setVisible(false);
        RightButton.setVisible(false);
        easyButton.setVisible(false);
        BackButton.setVisible(true);
        background = c.getImage();
        isGameScreen = true;
        game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
                trackList.get(nowSelected).getGameMusic());
        game.start();
        setFocusable(true);
    }

    public static void backMain() {
        isMainScreen = true;
        LeftButton.setVisible(true);
        RightButton.setVisible(true);
        easyButton.setVisible(true);
        background = b.getImage();
        BackButton.setVisible(false);
        selectTrack(nowSelected);
        isGameScreen = false;
        game.close();
    }

    public void enterMain() {
        background = b.getImage();
        isMainScreen = true;
        startButton.setVisible(false);
        quitButton.setVisible(false);
        LeftButton.setVisible(true);
        RightButton.setVisible(true);
        easyButton.setVisible(true);
        introMusic.close();
        selectTrack(0);
    }
}