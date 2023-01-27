public class Main {

    // 게임 화면 가로세로
    public static final int SCREEN_W = 1280;
    public static final int SCREEN_H = 800;

    // 떨어지는 노트의 속도를 뜻함
    public static final int NOTE_SPEED = 4;
    // 노트가 떨어지는 시간의 주기를 뜻함.
    public static final int SLEEP_TIME = 10;
    // 노트가 생성 되어 저지먼트 라인까지 도달하기까지의 시간을 뜻함.
    public static final int REACH_TIME = 2;

    public static void main(String[] args) {
        new JavaBeat();
    }
}
