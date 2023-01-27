import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
    private ImageIcon gameInfo = new ImageIcon(Main.class.getResource("./images/gameInfo.png"));
    private ImageIcon judgementLineImg = new ImageIcon(Main.class.getResource("./images/judgement.png"));
    private ImageIcon NoteLineImg = new ImageIcon(Main.class.getResource("./images/Line.png"));
    private ImageIcon NoteLineImg2 = new ImageIcon(Main.class.getResource("./images/Line2.png"));
    private ImageIcon NoteSepLineImg = new ImageIcon(Main.class.getResource("./images/nodeLine.png"));
    private ImageIcon PressedNote = new ImageIcon(Main.class.getResource("./images/ClickEffect1.png"));
    private ImageIcon PressedSpaceNote = new ImageIcon(Main.class.getResource("./images/ClickEffect.png"));
    private ImageIcon judgemissImage = new ImageIcon(Main.class.getResource(("./images/miss.PNG")));
    private ImageIcon judgegoodImage = new ImageIcon(Main.class.getResource(("./images/good.PNG")));
    private ImageIcon judgegreatImage = new ImageIcon(Main.class.getResource(("./images/great.PNG")));
    private ImageIcon judgeperfectImage = new ImageIcon(Main.class.getResource(("./images/perfect.PNG")));
    private ImageIcon judgeImage = new ImageIcon(Main.class.getResource(("./images/keypad.png")));

    private Image NoteSepLine = NoteSepLineImg.getImage();
    private Image judgementLine = judgementLineImg.getImage();
    private Image GameInfoImg = gameInfo.getImage();
    private Image Note1Line = NoteLineImg.getImage();
    private Image Note2Line = NoteLineImg.getImage();
    private Image Note3Line = NoteLineImg.getImage();
    private Image NoteSpaceLine = NoteLineImg2.getImage();
    private Image Note8Line = NoteLineImg.getImage();
    private Image Note9Line = NoteLineImg.getImage();
    private Image Note0Line = NoteLineImg.getImage();
    private Image judgementEffect = judgeImage.getImage();

    private String titleName;
    private String difficulty;
    private String musicTitle;
    private Music gameMusic;

    ArrayList<Note> noteList = new ArrayList<Note>();

    public Game(String titleName, String difficulty, String musicTitle) {
        this.titleName = titleName;
        this.difficulty = difficulty;
        this.musicTitle = musicTitle;
        gameMusic = new Music(this.musicTitle, false);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(Note1Line, 228, 30, null);
        g.drawImage(Note2Line, 332, 30, null);
        g.drawImage(Note3Line, 436, 30, null);
        g.drawImage(NoteSpaceLine, 540, 30, null);
        g.drawImage(Note8Line, 744, 30, null);
        g.drawImage(Note9Line, 848, 30, null);
        g.drawImage(Note0Line, 952, 30, null);
        g.drawImage(NoteSepLine, 224, 30, null);
        g.drawImage(NoteSepLine, 328, 30, null);
        g.drawImage(NoteSepLine, 432, 30, null);
        g.drawImage(NoteSepLine, 536, 30, null);
        g.drawImage(NoteSepLine, 740, 30, null);
        g.drawImage(NoteSepLine, 844, 30, null);
        g.drawImage(NoteSepLine, 948, 30, null);
        g.drawImage(NoteSepLine, 1052, 30, null);
        g.drawImage(GameInfoImg, 0, 740, null);
        g.drawImage(judgementLine, 0, 660, null);
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (note.getY() > 700) {
                judgementEffect = judgeImage.getImage();
            }
            if (!note.isProceed()) {
                noteList.remove(i);
                i--;
            } else {
                note.screenDraw(g);
            }
        }
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(titleName, 500, 780);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.cyan);
        g.drawString("1", 270, 692);
        g.drawString("2", 374, 692);
        g.drawString("3", 478, 692);
        g.drawString("SPACE BAR", 560, 692);
        g.drawString("8", 784, 690);
        g.drawString("9", 889, 692);
        g.drawString("0", 993, 692);
        g.setColor(Color.LIGHT_GRAY);
        g.drawImage(judgementEffect, 0, 0, null);

    }

    public void press1() {
        judge("1");
        Note1Line = PressedNote.getImage();
    }

    public void release1() {
        Note1Line = NoteLineImg.getImage();
    }

    public void press2() {
        judge("2");
        Note2Line = PressedNote.getImage();
    }

    public void release2() {
        Note2Line = NoteLineImg.getImage();
    }

    public void press3() {
        judge("3");
        Note3Line = PressedNote.getImage();
    }

    public void release3() {
        Note3Line = NoteLineImg.getImage();
    }

    public void pressSpace() {
        judge("Space");
        NoteSpaceLine = PressedSpaceNote.getImage();
    }

    public void releaseSpace() {
        NoteSpaceLine = NoteLineImg2.getImage();
    }

    public void press8() {
        judge("8");
        Note8Line = PressedNote.getImage();
    }

    public void release8() {
        Note8Line = NoteLineImg.getImage();
    }

    public void press9() {
        judge("9");
        Note9Line = PressedNote.getImage();
    }

    public void release9() {
        Note9Line = NoteLineImg.getImage();
    }

    public void press0() {
        judge("0");
        Note0Line = PressedNote.getImage();
    }

    public void release0() {
        Note0Line = NoteLineImg.getImage();
    }

    @Override
    public void run() {
        dropNotes(this.titleName);
    }

    public void close() {
        gameMusic.close();
        this.interrupt();
    }

    public void dropNotes(String titleName) {
        Beat[] beats = null;
        if (titleName.equals("01_FlowerDance") && difficulty.equals("Easy")) {
            int startTime = 4460 - Main.REACH_TIME * 1000;
            int gap = 110;
            beats = new Beat[] {
                    new Beat(startTime, "1"),
                    new Beat(startTime + gap * 2, "2"),
                    new Beat(startTime + gap * 4, "2"),
                    new Beat(startTime + gap * 6, "3"),
                    new Beat(startTime + gap * 8, "2"),
                    new Beat(startTime + gap * 10, "3"),
                    new Beat(startTime + gap * 12, "2"),
                    new Beat(startTime + gap * 14, "3"),
                    new Beat(startTime + gap * 16, "3"),
                    new Beat(startTime + gap * 18, "2"),
                    new Beat(startTime + gap * 20, "3"),
                    new Beat(startTime + gap * 22, "2"),
                    new Beat(startTime + gap * 24, "2"),
                    new Beat(startTime + gap * 26, "3"),
                    new Beat(startTime + gap * 28, "2"),
                    new Beat(startTime + gap * 30, "3"),
                    new Beat(startTime + gap * 32, "8"),
                    new Beat(startTime + gap * 34, "8"),
                    new Beat(startTime + gap * 36, "9"),
                    new Beat(startTime + gap * 38, "0"),
                    new Beat(startTime + gap * 40, "8"),
                    new Beat(startTime + gap * 42, "Space"),
                    new Beat(startTime + gap * 44, "2"),
                    new Beat(startTime + gap * 46, "3"),
                    new Beat(startTime + gap * 48, "8"),
                    new Beat(startTime + gap * 50, "8"),
            };
        } else if (titleName.equals("02_inferno") && difficulty.equals("Easy")) {
            int startTime = 4460 - Main.REACH_TIME * 1000;
            int gap = 300;
            beats = new Beat[] {
                    new Beat(startTime, "1"),
                    new Beat(startTime + gap * 2, "2"),
                    new Beat(startTime + gap * 4, "2"),
                    new Beat(startTime + gap * 6, "3"),
                    new Beat(startTime + gap * 8, "2"),
                    new Beat(startTime + gap * 10, "3"),
                    new Beat(startTime + gap * 12, "2"),
                    new Beat(startTime + gap * 14, "3"),
                    new Beat(startTime + gap * 16, "3"),
                    new Beat(startTime + gap * 18, "2"),
                    new Beat(startTime + gap * 20, "3"),
                    new Beat(startTime + gap * 22, "2"),
                    new Beat(startTime + gap * 24, "2"),
                    new Beat(startTime + gap * 26, "3"),
                    new Beat(startTime + gap * 28, "2"),
                    new Beat(startTime + gap * 30, "3"),
                    new Beat(startTime + gap * 32, "8"),
                    new Beat(startTime + gap * 34, "8"),
                    new Beat(startTime + gap * 36, "9"),
                    new Beat(startTime + gap * 38, "0"),
                    new Beat(startTime + gap * 40, "8"),
                    new Beat(startTime + gap * 42, "Space"),
                    new Beat(startTime + gap * 44, "2"),
                    new Beat(startTime + gap * 46, "3"),
                    new Beat(startTime + gap * 48, "8"),
                    new Beat(startTime + gap * 50, "8"),
                    new Beat(startTime + gap * 52, "Space"),
                    new Beat(startTime + gap * 54, "8"),
                    new Beat(startTime + gap * 56, "Space"),
                    new Beat(startTime + gap * 58, "9"),
                    new Beat(startTime + gap * 60, "0"),
                    new Beat(startTime + gap * 62, "0"),
                    new Beat(startTime + gap * 64, "0"),
                    new Beat(startTime + gap * 66, "8"),
                    new Beat(startTime + gap * 68, "3"),
                    new Beat(startTime + gap * 70, "2"),
                    new Beat(startTime + gap * 72, "8"),
                    new Beat(startTime + gap * 74, "2"),
                    new Beat(startTime + gap * 76, "1"),
                    new Beat(startTime + gap * 78, "2"),
                    new Beat(startTime + gap * 80, "0"),
                    new Beat(startTime + gap * 82, "9"),
                    new Beat(startTime + gap * 84, "Space"),
                    new Beat(startTime + gap * 86, "8"),
                    new Beat(startTime + gap * 88, "2"),
                    new Beat(startTime + gap * 90, "0"),
                    new Beat(startTime + gap * 92, "2"),
                    new Beat(startTime + gap * 94, "Space"),
                    new Beat(startTime + gap * 96, "1"),
                    new Beat(startTime + gap * 98, "2"),
                    new Beat(startTime + gap * 100, "0"),
                    new Beat(startTime + gap * 102, "2"),
                    new Beat(startTime + gap * 104, "1"),
                    new Beat(startTime + gap * 106, "Space"),
                    new Beat(startTime + gap * 108, "2"),
                    new Beat(startTime + gap * 110, "0"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 142, "2"),
                    new Beat(startTime + gap * 144, "1"),
                    new Beat(startTime + gap * 146, "3"),
                    new Beat(startTime + gap * 148, "2"),
                    new Beat(startTime + gap * 150, "Space"),
                    new Beat(startTime + gap * 152, "2"),
                    new Beat(startTime + gap * 154, "1"),
                    new Beat(startTime + gap * 156, "3"),
                    new Beat(startTime + gap * 158, "2"),
                    new Beat(startTime + gap * 160, "Space"),
                    new Beat(startTime + gap * 162, "2"),
                    new Beat(startTime + gap * 164, "1"),
                    new Beat(startTime + gap * 166, "3"),
                    new Beat(startTime + gap * 168, "2"),
            };
        } else if (titleName.equals("03_pretender") && difficulty.equals("Easy")) {
            int startTime = 4460 - Main.REACH_TIME * 1000;
            int gap = 300;
            beats = new Beat[] {
                    new Beat(startTime, "1"),
                    new Beat(startTime + gap * 2, "2"),
                    new Beat(startTime + gap * 4, "2"),
                    new Beat(startTime + gap * 6, "3"),
                    new Beat(startTime + gap * 8, "2"),
                    new Beat(startTime + gap * 10, "3"),
                    new Beat(startTime + gap * 12, "2"),
                    new Beat(startTime + gap * 14, "3"),
                    new Beat(startTime + gap * 16, "3"),
                    new Beat(startTime + gap * 18, "2"),
                    new Beat(startTime + gap * 20, "3"),
                    new Beat(startTime + gap * 22, "2"),
                    new Beat(startTime + gap * 24, "2"),
                    new Beat(startTime + gap * 26, "3"),
                    new Beat(startTime + gap * 28, "2"),
                    new Beat(startTime + gap * 30, "3"),
                    new Beat(startTime + gap * 32, "8"),
                    new Beat(startTime + gap * 34, "8"),
                    new Beat(startTime + gap * 36, "9"),
                    new Beat(startTime + gap * 38, "0"),
                    new Beat(startTime + gap * 40, "8"),
                    new Beat(startTime + gap * 42, "Space"),
                    new Beat(startTime + gap * 44, "2"),
                    new Beat(startTime + gap * 46, "3"),
                    new Beat(startTime + gap * 48, "8"),
                    new Beat(startTime + gap * 50, "8"),
                    new Beat(startTime + gap * 52, "Space"),
                    new Beat(startTime + gap * 54, "8"),
                    new Beat(startTime + gap * 56, "Space"),
                    new Beat(startTime + gap * 58, "9"),
                    new Beat(startTime + gap * 60, "0"),
                    new Beat(startTime + gap * 62, "0"),
                    new Beat(startTime + gap * 64, "0"),
                    new Beat(startTime + gap * 66, "8"),
                    new Beat(startTime + gap * 68, "3"),
                    new Beat(startTime + gap * 70, "2"),
                    new Beat(startTime + gap * 72, "8"),
                    new Beat(startTime + gap * 74, "2"),
                    new Beat(startTime + gap * 76, "1"),
                    new Beat(startTime + gap * 78, "2"),
                    new Beat(startTime + gap * 80, "0"),
                    new Beat(startTime + gap * 82, "9"),
                    new Beat(startTime + gap * 84, "Space"),
                    new Beat(startTime + gap * 86, "8"),
                    new Beat(startTime + gap * 88, "2"),
                    new Beat(startTime + gap * 90, "0"),
                    new Beat(startTime + gap * 92, "2"),
                    new Beat(startTime + gap * 94, "Space"),
                    new Beat(startTime + gap * 96, "1"),
                    new Beat(startTime + gap * 98, "2"),
                    new Beat(startTime + gap * 100, "0"),
                    new Beat(startTime + gap * 102, "2"),
                    new Beat(startTime + gap * 104, "1"),
                    new Beat(startTime + gap * 106, "Space"),
                    new Beat(startTime + gap * 108, "2"),
                    new Beat(startTime + gap * 110, "0"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 142, "2"),
                    new Beat(startTime + gap * 144, "1"),
                    new Beat(startTime + gap * 146, "3"),
                    new Beat(startTime + gap * 148, "2"),
                    new Beat(startTime + gap * 150, "Space"),
                    new Beat(startTime + gap * 152, "2"),
                    new Beat(startTime + gap * 154, "1"),
                    new Beat(startTime + gap * 156, "3"),
                    new Beat(startTime + gap * 158, "2"),
                    new Beat(startTime + gap * 160, "Space"),
                    new Beat(startTime + gap * 162, "2"),
                    new Beat(startTime + gap * 164, "1"),
                    new Beat(startTime + gap * 166, "3"),
                    new Beat(startTime + gap * 168, "2"),
            };
        } else if (titleName.equals("04_undertale") && difficulty.equals("Easy")) {
            int startTime = 4460 - Main.REACH_TIME * 1000;
            int gap = 300;
            beats = new Beat[] {
                    new Beat(startTime, "1"),
                    new Beat(startTime + gap * 2, "2"),
                    new Beat(startTime + gap * 4, "2"),
                    new Beat(startTime + gap * 6, "3"),
                    new Beat(startTime + gap * 8, "2"),
                    new Beat(startTime + gap * 10, "3"),
                    new Beat(startTime + gap * 12, "2"),
                    new Beat(startTime + gap * 14, "3"),
                    new Beat(startTime + gap * 16, "3"),
                    new Beat(startTime + gap * 18, "2"),
                    new Beat(startTime + gap * 20, "3"),
                    new Beat(startTime + gap * 22, "2"),
                    new Beat(startTime + gap * 24, "2"),
                    new Beat(startTime + gap * 26, "3"),
                    new Beat(startTime + gap * 28, "2"),
                    new Beat(startTime + gap * 30, "3"),
                    new Beat(startTime + gap * 32, "8"),
                    new Beat(startTime + gap * 34, "8"),
                    new Beat(startTime + gap * 36, "9"),
                    new Beat(startTime + gap * 38, "0"),
                    new Beat(startTime + gap * 40, "8"),
                    new Beat(startTime + gap * 42, "Space"),
                    new Beat(startTime + gap * 44, "2"),
                    new Beat(startTime + gap * 46, "3"),
                    new Beat(startTime + gap * 48, "8"),
                    new Beat(startTime + gap * 50, "8"),
                    new Beat(startTime + gap * 52, "Space"),
                    new Beat(startTime + gap * 54, "8"),
                    new Beat(startTime + gap * 56, "Space"),
                    new Beat(startTime + gap * 58, "9"),
                    new Beat(startTime + gap * 60, "0"),
                    new Beat(startTime + gap * 62, "0"),
                    new Beat(startTime + gap * 64, "0"),
                    new Beat(startTime + gap * 66, "8"),
                    new Beat(startTime + gap * 68, "3"),
                    new Beat(startTime + gap * 70, "2"),
                    new Beat(startTime + gap * 72, "8"),
                    new Beat(startTime + gap * 74, "2"),
                    new Beat(startTime + gap * 76, "1"),
                    new Beat(startTime + gap * 78, "2"),
                    new Beat(startTime + gap * 80, "0"),
                    new Beat(startTime + gap * 82, "9"),
                    new Beat(startTime + gap * 84, "Space"),
                    new Beat(startTime + gap * 86, "8"),
                    new Beat(startTime + gap * 88, "2"),
                    new Beat(startTime + gap * 90, "0"),
                    new Beat(startTime + gap * 92, "2"),
                    new Beat(startTime + gap * 94, "Space"),
                    new Beat(startTime + gap * 96, "1"),
                    new Beat(startTime + gap * 98, "2"),
                    new Beat(startTime + gap * 100, "0"),
                    new Beat(startTime + gap * 102, "2"),
                    new Beat(startTime + gap * 104, "1"),
                    new Beat(startTime + gap * 106, "Space"),
                    new Beat(startTime + gap * 108, "2"),
                    new Beat(startTime + gap * 110, "0"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 142, "2"),
                    new Beat(startTime + gap * 144, "1"),
                    new Beat(startTime + gap * 146, "3"),
                    new Beat(startTime + gap * 148, "2"),
                    new Beat(startTime + gap * 150, "Space"),
                    new Beat(startTime + gap * 152, "2"),
                    new Beat(startTime + gap * 154, "1"),
                    new Beat(startTime + gap * 156, "3"),
                    new Beat(startTime + gap * 158, "2"),
                    new Beat(startTime + gap * 160, "Space"),
                    new Beat(startTime + gap * 162, "2"),
                    new Beat(startTime + gap * 164, "1"),
                    new Beat(startTime + gap * 166, "3"),
                    new Beat(startTime + gap * 168, "2"),
            };
        } else if (titleName.equals("05_GodZilla") && difficulty.equals("Easy")) {
            int startTime = 4460 - Main.REACH_TIME * 1000;
            int gap = 300;
            beats = new Beat[] {
                    new Beat(startTime, "1"),
                    new Beat(startTime + gap * 2, "2"),
                    new Beat(startTime + gap * 4, "2"),
                    new Beat(startTime + gap * 6, "3"),
                    new Beat(startTime + gap * 8, "2"),
                    new Beat(startTime + gap * 10, "3"),
                    new Beat(startTime + gap * 12, "2"),
                    new Beat(startTime + gap * 14, "3"),
                    new Beat(startTime + gap * 16, "3"),
                    new Beat(startTime + gap * 18, "2"),
                    new Beat(startTime + gap * 20, "3"),
                    new Beat(startTime + gap * 22, "2"),
                    new Beat(startTime + gap * 24, "2"),
                    new Beat(startTime + gap * 26, "3"),
                    new Beat(startTime + gap * 28, "2"),
                    new Beat(startTime + gap * 30, "3"),
                    new Beat(startTime + gap * 32, "8"),
                    new Beat(startTime + gap * 34, "8"),
                    new Beat(startTime + gap * 36, "9"),
                    new Beat(startTime + gap * 38, "0"),
                    new Beat(startTime + gap * 40, "8"),
                    new Beat(startTime + gap * 42, "Space"),
                    new Beat(startTime + gap * 44, "2"),
                    new Beat(startTime + gap * 46, "3"),
                    new Beat(startTime + gap * 48, "8"),
                    new Beat(startTime + gap * 50, "8"),
                    new Beat(startTime + gap * 52, "Space"),
                    new Beat(startTime + gap * 54, "8"),
                    new Beat(startTime + gap * 56, "Space"),
                    new Beat(startTime + gap * 58, "9"),
                    new Beat(startTime + gap * 60, "0"),
                    new Beat(startTime + gap * 62, "0"),
                    new Beat(startTime + gap * 64, "0"),
                    new Beat(startTime + gap * 66, "8"),
                    new Beat(startTime + gap * 68, "3"),
                    new Beat(startTime + gap * 70, "2"),
                    new Beat(startTime + gap * 72, "8"),
                    new Beat(startTime + gap * 74, "2"),
                    new Beat(startTime + gap * 76, "1"),
                    new Beat(startTime + gap * 78, "2"),
                    new Beat(startTime + gap * 80, "0"),
                    new Beat(startTime + gap * 82, "9"),
                    new Beat(startTime + gap * 84, "Space"),
                    new Beat(startTime + gap * 86, "8"),
                    new Beat(startTime + gap * 88, "2"),
                    new Beat(startTime + gap * 90, "0"),
                    new Beat(startTime + gap * 92, "2"),
                    new Beat(startTime + gap * 94, "Space"),
                    new Beat(startTime + gap * 96, "1"),
                    new Beat(startTime + gap * 98, "2"),
                    new Beat(startTime + gap * 100, "0"),
                    new Beat(startTime + gap * 102, "2"),
                    new Beat(startTime + gap * 104, "1"),
                    new Beat(startTime + gap * 106, "Space"),
                    new Beat(startTime + gap * 108, "2"),
                    new Beat(startTime + gap * 110, "0"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "8"),
                    new Beat(startTime + gap * 136, "9"),
                    new Beat(startTime + gap * 138, "0"),
                    new Beat(startTime + gap * 142, "2"),
                    new Beat(startTime + gap * 144, "1"),
                    new Beat(startTime + gap * 146, "3"),
                    new Beat(startTime + gap * 148, "2"),
                    new Beat(startTime + gap * 150, "Space"),
                    new Beat(startTime + gap * 152, "2"),
                    new Beat(startTime + gap * 154, "1"),
                    new Beat(startTime + gap * 156, "3"),
                    new Beat(startTime + gap * 158, "8"),
                    new Beat(startTime + gap * 160, "Space"),
                    new Beat(startTime + gap * 162, "2"),
                    new Beat(startTime + gap * 164, "0"),
                    new Beat(startTime + gap * 166, "3"),
                    new Beat(startTime + gap * 168, "8"),
                    new Beat(startTime + gap * 170, "Space"),
                    new Beat(startTime + gap * 172, "2"),
                    new Beat(startTime + gap * 174, "9"),
                    new Beat(startTime + gap * 176, "3"),
                    new Beat(startTime + gap * 178, "2"),
                    new Beat(startTime + gap * 180, "Space"),
                    new Beat(startTime + gap * 182, "2"),
                    new Beat(startTime + gap * 184, "9"),
                    new Beat(startTime + gap * 186, "3"),
                    new Beat(startTime + gap * 188, "2"),
                    new Beat(startTime + gap * 190, "Space"),
                    new Beat(startTime + gap * 192, "9"),
                    new Beat(startTime + gap * 194, "1"),
                    new Beat(startTime + gap * 196, "3"),
                    new Beat(startTime + gap * 198, "8"),
                    new Beat(startTime + gap * 202, "2"),
                    new Beat(startTime + gap * 204, "1"),
                    new Beat(startTime + gap * 206, "8"),
                    new Beat(startTime + gap * 208, "2"),
                    new Beat(startTime + gap * 200, "Space"),
                    new Beat(startTime + gap * 212, "9"),
                    new Beat(startTime + gap * 214, "1"),
                    new Beat(startTime + gap * 216, "3"),
                    new Beat(startTime + gap * 218, "0"),
                    new Beat(startTime + gap * 220, "Space"),
                    new Beat(startTime + gap * 222, "2"),
                    new Beat(startTime + gap * 224, "1"),
                    new Beat(startTime + gap * 226, "8"),
                    new Beat(startTime + gap * 228, "2"),
                    new Beat(startTime + gap * 230, "Space"),
                    new Beat(startTime + gap * 232, "2"),
                    new Beat(startTime + gap * 234, "9"),
                    new Beat(startTime + gap * 236, "3"),
                    new Beat(startTime + gap * 238, "9"),
            };
        } else if (titleName.equals("06_EventHorizon") && difficulty.equals("Easy")) {
            int startTime = 4460 - Main.REACH_TIME * 1000;
            int gap = 125;
            beats = new Beat[] {
                    new Beat(startTime, "1"),
                    new Beat(startTime + gap * 2, "2"),
                    new Beat(startTime + gap * 4, "2"),
                    new Beat(startTime + gap * 6, "Space"),
                    new Beat(startTime + gap * 8, "2"),
                    new Beat(startTime + gap * 10, "3"),
                    new Beat(startTime + gap * 12, "2"),
                    new Beat(startTime + gap * 14, "3"),
                    new Beat(startTime + gap * 16, "3"),
                    new Beat(startTime + gap * 18, "Space"),
                    new Beat(startTime + gap * 20, "3"),
                    new Beat(startTime + gap * 22, "2"),
                    new Beat(startTime + gap * 24, "2"),
                    new Beat(startTime + gap * 26, "3"),
                    new Beat(startTime + gap * 28, "2"),
                    new Beat(startTime + gap * 30, "3"),
                    new Beat(startTime + gap * 32, "8"),
                    new Beat(startTime + gap * 34, "8"),
                    new Beat(startTime + gap * 36, "9"),
                    new Beat(startTime + gap * 38, "0"),
                    new Beat(startTime + gap * 40, "8"),
                    new Beat(startTime + gap * 42, "Space"),
                    new Beat(startTime + gap * 44, "2"),
                    new Beat(startTime + gap * 46, "3"),
                    new Beat(startTime + gap * 48, "8"),
                    new Beat(startTime + gap * 50, "8"),
                    new Beat(startTime + gap * 52, "Space"),
                    new Beat(startTime + gap * 54, "8"),
                    new Beat(startTime + gap * 56, "Space"),
                    new Beat(startTime + gap * 58, "9"),
                    new Beat(startTime + gap * 60, "0"),
                    new Beat(startTime + gap * 62, "0"),
                    new Beat(startTime + gap * 64, "0"),
                    new Beat(startTime + gap * 66, "8"),
                    new Beat(startTime + gap * 68, "3"),
                    new Beat(startTime + gap * 70, "Space"),
                    new Beat(startTime + gap * 72, "8"),
                    new Beat(startTime + gap * 74, "2"),
                    new Beat(startTime + gap * 76, "1"),
                    new Beat(startTime + gap * 78, "2"),
                    new Beat(startTime + gap * 80, "0"),
                    new Beat(startTime + gap * 82, "9"),
                    new Beat(startTime + gap * 84, "Space"),
                    new Beat(startTime + gap * 86, "8"),
                    new Beat(startTime + gap * 88, "2"),
                    new Beat(startTime + gap * 90, "0"),
                    new Beat(startTime + gap * 92, "2"),
                    new Beat(startTime + gap * 94, "Space"),
                    new Beat(startTime + gap * 96, "1"),
                    new Beat(startTime + gap * 98, "2"),
                    new Beat(startTime + gap * 100, "0"),
                    new Beat(startTime + gap * 102, "2"),
                    new Beat(startTime + gap * 104, "1"),
                    new Beat(startTime + gap * 106, "Space"),
                    new Beat(startTime + gap * 108, "2"),
                    new Beat(startTime + gap * 110, "0"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 112, "2"),
                    new Beat(startTime + gap * 114, "1"),
                    new Beat(startTime + gap * 116, "3"),
                    new Beat(startTime + gap * 118, "2"),
                    new Beat(startTime + gap * 120, "Space"),
                    new Beat(startTime + gap * 122, "2"),
                    new Beat(startTime + gap * 124, "1"),
                    new Beat(startTime + gap * 126, "3"),
                    new Beat(startTime + gap * 128, "2"),
                    new Beat(startTime + gap * 130, "Space"),
                    new Beat(startTime + gap * 132, "2"),
                    new Beat(startTime + gap * 134, "1"),
                    new Beat(startTime + gap * 136, "3"),
                    new Beat(startTime + gap * 138, "2"),
                    new Beat(startTime + gap * 142, "2"),
                    new Beat(startTime + gap * 144, "1"),
                    new Beat(startTime + gap * 146, "3"),
                    new Beat(startTime + gap * 148, "2"),
                    new Beat(startTime + gap * 150, "Space"),
                    new Beat(startTime + gap * 152, "2"),
                    new Beat(startTime + gap * 154, "1"),
                    new Beat(startTime + gap * 156, "3"),
                    new Beat(startTime + gap * 158, "2"),
                    new Beat(startTime + gap * 160, "Space"),
                    new Beat(startTime + gap * 162, "2"),
                    new Beat(startTime + gap * 164, "1"),
                    new Beat(startTime + gap * 166, "3"),
                    new Beat(startTime + gap * 168, "2"),
            };
        }
        int i = 0;
        gameMusic.start();
        while (i < beats.length && !isInterrupted()) {
            boolean dropped = false;
            if (beats[i].getTime() <= gameMusic.getTime()) {
                Note note = new Note(beats[i].getNoteName());
                note.start();
                noteList.add(note);
                i++;
                dropped = true;
            }
            if (!dropped) {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }
        if (i == beats.length) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            JavaBeat.backMain();
        }
    }

    public void judge(String input) {
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (input.equals(note.getNoteType())) {
                judgeEvent(note.judge());
                break;
            }
        }
    }

    public void judgeEvent(String judge) {
        if (judge.equals("None")) {
            judgementEffect = judgeImage.getImage();
        }
        if (judge.equals("Miss")) {
            judgementEffect = judgemissImage.getImage();
        } else if (judge.equals("Good")) {
            judgementEffect = judgegoodImage.getImage();
        } else if (judge.equals("Great")) {
            judgementEffect = judgegreatImage.getImage();
        } else if (judge.equals("Perfect")) {
            judgementEffect = judgeperfectImage.getImage();
        }
    }

}