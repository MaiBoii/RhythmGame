import java.awt.event.*;

public class KeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        if (JavaBeat.game == null) {
            return; // 게임이 진행중이 아닐 시 어떠한 키보드도 무력화시킴
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            JavaBeat.game.press1();
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            JavaBeat.game.press2();
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            JavaBeat.game.press3();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            JavaBeat.game.pressSpace();
        } else if (e.getKeyCode() == KeyEvent.VK_8) {
            JavaBeat.game.press8();
        } else if (e.getKeyCode() == KeyEvent.VK_9) {
            JavaBeat.game.press9();
        } else if (e.getKeyCode() == KeyEvent.VK_0) {
            JavaBeat.game.press0();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (JavaBeat.game == null) {
            return; // 게임이 진행중이 아닐 시 어떠한 키보드도 무력화시킴
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            JavaBeat.game.release1();
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            JavaBeat.game.release2();
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            JavaBeat.game.release3();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            JavaBeat.game.releaseSpace();
        } else if (e.getKeyCode() == KeyEvent.VK_8) {
            JavaBeat.game.release8();
        } else if (e.getKeyCode() == KeyEvent.VK_9) {
            JavaBeat.game.release9();
        } else if (e.getKeyCode() == KeyEvent.VK_0) {
            JavaBeat.game.release0();
        }
    }
}
