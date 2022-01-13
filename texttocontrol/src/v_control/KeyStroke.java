package v_control;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class KeyStroke {

	private Robot robot;

	private int keyCode;
	private boolean isShifted;

	public int getKeyCode() {
		return keyCode;
	}

	public boolean isShifted() {
		return isShifted;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public void setShifted(boolean isShifted) {
		this.isShifted = isShifted;
	}

	public KeyStroke(int keyCode, boolean isShifted) {
		this.keyCode = keyCode;
		this.isShifted = isShifted;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void type() {
		try {
			if (isShifted) {
				robot.keyPress(KeyEvent.VK_SHIFT);
			}
			robot.delay(50);
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
			if (isShifted) {
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}

		} catch (IllegalArgumentException ex) {

		}
	}
}
