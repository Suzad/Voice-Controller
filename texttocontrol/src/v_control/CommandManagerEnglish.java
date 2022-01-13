package v_control;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class CommandManagerEnglish {

	private String command;
	private ArrayList<String> words;
	private String mainCommand;
	private Robot robot;

	public static Map<String, Method> methodMap = new HashMap<String, Method>();

	public CommandManagerEnglish() {
		try {
			robot = new Robot();
			methodMap.put("double click", this.getClass().getDeclaredMethod("doubleClick"));
			methodMap.put("right click", this.getClass().getDeclaredMethod("rightClick"));
			methodMap.put("click here", this.getClass().getDeclaredMethod("leftClick"));
			methodMap.put("take screenshot", this.getClass().getDeclaredMethod("captureScreen"));
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static Dictionary<String, String> getword = new Hashtable<String, String>();
	{
		getword.put("type", "Typing");
		getword.put("press", "Pressing");
		getword.put("open", "Opening");
		getword.put("go", "Going");
		getword.put("select", "Selecting");
		getword.put("restart", "Restarting");
		getword.put("shut", "Shutting");
		getword.put("close", "Closing");
		getword.put("change", "Changing");
		getword.put("enter", "Enterring");
		getword.put("minimize", "Minimizing");
		getword.put("delete", "Deleting");
		getword.put("copy", "Coping");
		getword.put("cut", "Cutting");
		getword.put("paste", "Pasting");
		getword.put("save", "Saving");
		getword.put("undo", "Undoing");
		getword.put("redo", "Redoing");
		getword.put("click", "Clicking");
		getword.put("take", "Taking");
	}

	public static Dictionary<String, String> commandlist = new Hashtable<String, String>();
	{
		//commandlist.put("shut down computer", "shutdown -s -t 10");
		//commandlist.put("restart computer", "shutdown -r -t 10");
		commandlist.put("open control panel", "cmd /c start control");
		commandlist.put("open file explorer", "cmd /c start explorer");
		commandlist.put("open chrome", "cmd /c start chrome.exe");
		commandlist.put("open firefox", "cmd /c start firefox.exe");
		commandlist.put("open notepad", "notepad.exe");
		commandlist.put("open settings", "cmd /c start ms-settings:");
		commandlist.put("open browser", "rundll32 url.dll,FileProtocolHandler http://www.google.com");
		commandlist.put("open command prompt", "cmd /c start cmd.exe");
		commandlist.put("open task manager", "cmd /c start taskmgr");
	}

	public static Map<String, ArrayList<Integer>> keyList = new HashMap<String, ArrayList<Integer>>() {
		public static final long serialVersionUID = 1L;
		{
			//put("cut it", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_X)));
			//put("paste here", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_V)));
			//put("undo it", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_Z)));
			//put("redo it", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_Y)));
			//put("delete it", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_DELETE)));
			put("close window", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_F4)));
			put("minimize", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_SPACE, KeyEvent.VK_N)));
			put("select all", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_A)));
			put("copy it", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_C)));
			put("save it", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_S)));
			put("enter here", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_ENTER)));
		}

	};

	public static Map<String, KeyStroke> strokeMap = new HashMap<String, KeyStroke>() {
		public static final long serialVersionUID = 1L;
		{
			//put("back space", new KeyStroke(KeyEvent.VK_BACK_SPACE, false));
			//put("back", new KeyStroke(KeyEvent.VK_BACK_SPACE, false));
			//put("delete", new KeyStroke(KeyEvent.VK_DELETE, false));
			put("up", new KeyStroke(KeyEvent.VK_UP, false));
			put("down", new KeyStroke(KeyEvent.VK_DOWN, false));
			put("right", new KeyStroke(KeyEvent.VK_RIGHT, false));
			put("left", new KeyStroke(KeyEvent.VK_LEFT, false));
			put("escape", new KeyStroke(KeyEvent.VK_ESCAPE, false));
			
			put("caps lock", new KeyStroke(KeyEvent.VK_CAPS_LOCK, false));
			put("start menu", new KeyStroke(KeyEvent.VK_WINDOWS, false));
			put("start", new KeyStroke(KeyEvent.VK_WINDOWS, false));
			put("windows", new KeyStroke(KeyEvent.VK_WINDOWS, false));
			put("shift", new KeyStroke(KeyEvent.VK_SHIFT, false));
			put("control", new KeyStroke(KeyEvent.VK_CONTROL, false));
			put("\n", new KeyStroke(KeyEvent.VK_ENTER, false));
			put("enter", new KeyStroke(KeyEvent.VK_ENTER, false));
			
			put("\t", new KeyStroke(KeyEvent.VK_TAB, false));
			put("tab", new KeyStroke(KeyEvent.VK_TAB, false));
			put("\r", new KeyStroke(KeyEvent.VK_HOME, false));
			put(" ", new KeyStroke(KeyEvent.VK_SPACE, false));
			put("space", new KeyStroke(KeyEvent.VK_SPACE, false));
			put("!", new KeyStroke(KeyEvent.VK_1, true));
			put("\"", new KeyStroke(KeyEvent.VK_QUOTE, true));
			put("#", new KeyStroke(KeyEvent.VK_3, true));
			put("$", new KeyStroke(KeyEvent.VK_4, true));
			put("%", new KeyStroke(KeyEvent.VK_5, true));
			put("&", new KeyStroke(KeyEvent.VK_7, true));
			put("\'", new KeyStroke(KeyEvent.VK_QUOTE, false));
			put("(", new KeyStroke(KeyEvent.VK_9, true));
			put(")", new KeyStroke(KeyEvent.VK_0, true));
			put("*", new KeyStroke(KeyEvent.VK_8, true));
			put("+", new KeyStroke(KeyEvent.VK_EQUALS, true));
			put("plus", new KeyStroke(KeyEvent.VK_EQUALS, true));
			put(",", new KeyStroke(KeyEvent.VK_COMMA, false));
			put("-", new KeyStroke(KeyEvent.VK_MINUS, false));
			put("minus", new KeyStroke(KeyEvent.VK_MINUS, false));
			put(".", new KeyStroke(KeyEvent.VK_PERIOD, false));
			put("dot", new KeyStroke(KeyEvent.VK_PERIOD, false));
			put("/", new KeyStroke(KeyEvent.VK_SLASH, false));
			put("divide", new KeyStroke(KeyEvent.VK_DIVIDE, false));
			put("multiply", new KeyStroke(KeyEvent.VK_MULTIPLY, false));
			for (int i = (int) '0'; i <= (int) '9'; i++) {
				put(Character.toString((char) i), new KeyStroke(i, false));
			}
			put(":", new KeyStroke(KeyEvent.VK_SEMICOLON, true));
			put(";", new KeyStroke(KeyEvent.VK_SEMICOLON, false));
			put("<", new KeyStroke(KeyEvent.VK_COMMA, true));
			put("=", new KeyStroke(KeyEvent.VK_EQUALS, false));
			put(">", new KeyStroke(KeyEvent.VK_PERIOD, true));
			put("?", new KeyStroke(KeyEvent.VK_SLASH, true));
			put("@", new KeyStroke(KeyEvent.VK_2, true));
			for (int i = (int) 'A'; i <= (int) 'Z'; i++) {
				put(Character.toString((char) i), new KeyStroke(i, true));
			}
			put("[", new KeyStroke(KeyEvent.VK_OPEN_BRACKET, false));
			put("\\", new KeyStroke(KeyEvent.VK_BACK_SLASH, false));
			put("]", new KeyStroke(KeyEvent.VK_CLOSE_BRACKET, false));
			put("^", new KeyStroke(KeyEvent.VK_6, true));
			put("_", new KeyStroke(KeyEvent.VK_MINUS, true));
			put("`", new KeyStroke(KeyEvent.VK_BACK_QUOTE, false));
			for (int i = (int) 'A'; i <= (int) 'Z'; i++) {
				put(Character.toString((char) (i + ((int) 'a' - (int) 'A'))), new KeyStroke(i, false));
				put("capital " + Character.toString((char) (i + ((int) 'a' - (int) 'A'))), new KeyStroke(i, true));
			}
			put("{", new KeyStroke(KeyEvent.VK_OPEN_BRACKET, true));
			put("|", new KeyStroke(KeyEvent.VK_BACK_SLASH, true));
			put("}", new KeyStroke(KeyEvent.VK_CLOSE_BRACKET, true));
			put("~", new KeyStroke(KeyEvent.VK_BACK_QUOTE, true));
			put("one", new KeyStroke(KeyEvent.VK_1, false));
			put("two", new KeyStroke(KeyEvent.VK_2, false));
			put("three", new KeyStroke(KeyEvent.VK_3, false));
			put("four", new KeyStroke(KeyEvent.VK_4, false));
			put("five", new KeyStroke(KeyEvent.VK_5, false));
			put("six", new KeyStroke(KeyEvent.VK_6, false));
			put("seven", new KeyStroke(KeyEvent.VK_7, false));
			put("eight", new KeyStroke(KeyEvent.VK_8, false));
			put("nine", new KeyStroke(KeyEvent.VK_9, false));
			put("zero", new KeyStroke(KeyEvent.VK_0, false));
		}
	};

	private void typeKey(String key) {
		strokeMap.get(key).type();
	}

	private void doubleClick() {
		leftClick();
		robot.delay(50);
		leftClick();
	}

	private void rightClick() {
		robot.mousePress(InputEvent.BUTTON3_MASK);
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON3_MASK);
		robot.delay(100);
	}

	private void leftClick() {
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(100);
	}

	private void pressButton(ArrayList<Integer> buttons) {
		if (buttons.size() == 1) {
			robot.keyPress(buttons.get(0));
			robot.keyRelease(buttons.get(0));
			return;
		}
		int temp = buttons.get(0);
		robot.keyPress(temp);
		ArrayList<Integer> buttonstemp = (ArrayList<Integer>) buttons.clone();
		buttonstemp.remove(0);
		pressButton(buttonstemp);
		robot.keyRelease(temp);
	}

	private void captureScreen() throws Exception {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
		fileChooser.setSelectedFile(new File("capture.png"));
		new Thread() {
			public void run() {
				try {
					int a = fileChooser.showSaveDialog(null);
					if (a == JFileChooser.APPROVE_OPTION) {
						String fileName = fileChooser.getSelectedFile().getAbsolutePath();
						if (!fileName.endsWith(".png"))
							fileName += ".png";
						if (new File(fileName).exists()) {
							JOptionPane.showMessageDialog(null, "Please give different file name...", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							File file = new File(fileName);
							ImageIO.write(image, "png", file);
							// System.out.println("A screenshot is captured to: " + file.getAbsolutePath());
						}
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void setCommand(String command) {
		this.command = command;
		words = new ArrayList<>(Arrays.asList(command.split(" ")));
		ArrayList<String> temp = (ArrayList<String>) words.clone();
		temp.remove(0);
		mainCommand = String.join(" ", temp);
	}

	public String getCommand() {
		return command;
	}

	public String getProcess() {
		if(commandlist.get(command) == null && keyList.get(command) == null && strokeMap.get(mainCommand) == null && methodMap.get(command) == null) {
			return "Can't understand. Speak Again..";
		} else if (getword.get(words.get(0)) != null) {
			words.set(0, getword.get(words.get(0)));
			return String.join(" ", words);
		} else {
			return "Executing " + String.join(" ", words);
		}
	}

	public void executeProcess()
			throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (commandlist.get(command) != null) {
			Runtime.getRuntime().exec(commandlist.get(command));
		} else if (keyList.get(command) != null) {
			pressButton(keyList.get(command));
		} else if (strokeMap.get(mainCommand) != null) {
			typeKey(mainCommand);
		} else if (methodMap.get(command) != null) {
			
			methodMap.get(command).invoke(this, null);
		} else {
			System.out.println("Invalid command");
		}
	}

//	public static void main(String[] args)
//			throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//
//		}
//		CommandManagerEnglish a = new CommandManagerEnglish();
//		a.setCommand("take screenshot");
//		System.out.println(a.getCommand());
//		System.out.println(a.getProcess());
//		a.executeProcess();
//		System.out.println("Work done");
//	}

}
