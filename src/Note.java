import java.awt.Image;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Note extends Thread {
    private ImageIcon noteBasicImg = new ImageIcon(Main.class.getResource("./images/note.png"));
    private ImageIcon noteBasicSpaceImg = new ImageIcon(Main.class.getResource("./images/note2.png"));
    private Image noteBasic = noteBasicImg.getImage();
    private Image noteBasicSpace = noteBasicSpaceImg.getImage();

    private int x, y = 660 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
    private String noteType;
    private boolean proceeded = true;

    // 현재 노트 타입의 키보드 어떤 버튼인지 - 반환
    public String getNoteType() {
        return noteType;
    }

    // 현재 노트의 진행 여부
    public boolean isProceed() {
        return proceeded;
    }

    public void close() {
        proceeded = false;
    }

    public Note(String noteType) {
        if (noteType.equals("1")) {
            x = 228;
        } else if (noteType.equals("2")) {
            x = 332;
        } else if (noteType.equals("3")) {
            x = 436;
        } else if (noteType.equals("Space")) {
            x = 540;
        } else if (noteType.equals("8")) {
            x = 744;
        } else if (noteType.equals("9")) {
            x = 848;
        } else if (noteType.equals("0")) {
            x = 952;
        }
        this.noteType = noteType;
    }

    // 노트가 내려오는 그래픽을 그리기 위한 screenDraw
    public void screenDraw(Graphics2D g) {
        if (!noteType.equals("Space")) {
            g.drawImage(noteBasic, x, y, null);
        } else {
            g.drawImage(noteBasicSpace, x, y, null);
        }
    }

    // 노트가 떨어지도록 만드는 함수
    public void drop() {
        y += Main.NOTE_SPEED;
        if (y > 700) {
            System.out.println("Miss");
            close();
        }
    }

    // 스레드 실행 함수
    @Override
    public void run() {
        try {
            while (true) {
                drop();
                if (proceeded) {
                    // 떨어질때 슬립타임에 설정뒀던 시간만큼 딜레이를 주면서 떨어짐
                    // 현재 노트가 계속해서 움직이고 있다면 반복적으로 내려옴
                    Thread.sleep(Main.SLEEP_TIME);
                } else {
                    // 노트에 대한 전반적 작업 - 판정, 입력 등- 끝나서
                    // 더이상 해당 노트가 필요없어지면 스레드 종료시킴
                    // proceeded를 false로
                    interrupt();
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String judge() {
        if (y >= 693) {
            System.out.println("Good");
            close();
            return "Good";
        } else if (y >= 680) {
            System.out.println("Great");
            close();
            return "Great";
        } else if (y >= 654) {
            System.out.println("Perfect");
            close();
            return "Perfect";
        } else if (y >= 641) {
            System.out.println("Great");
            close();
            return "Great";
        } else if (y >= 628) {
            System.out.println("Good");
            close();
            return "Good";
        } else {
            System.out.println("Miss");
            close();
            return "Miss";
        }
    }

    public int getY() {
        return y;
    }
}
