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

public class CommandManagerBangla {

	private String command;
	private Robot robot;

	private Map<String, Method> methodMap = new HashMap<String, Method>();
	public static String[] digit = { "শূণ্য", "এক", "দুই", "তিন", "চার", "পাঁচ", "ছয়", "সাত", "আট", "নয়" };
	public static String[] letter = { "এ", "বি", "সি", "ডি", "ই", "এফ", "জি", "এইচ", "আই", "জে", "কে", "এল", "এম", "এন", "ও",
			"পি", "কিউ", "আর", "এস", "টি", "ইউ", "ভি", "ডাবলু", "এক্স", "ওয়াই", "জেড" };

	public CommandManagerBangla() {
		try {
			robot = new Robot();
			methodMap.put("ডবল ক্লিক করো", this.getClass().getDeclaredMethod("doubleClick"));
			methodMap.put("রাইট ক্লিক করো", this.getClass().getDeclaredMethod("rightClick"));
			methodMap.put("ক্লিক করো", this.getClass().getDeclaredMethod("leftClick"));
			methodMap.put("স্ক্রিনসট নাও", this.getClass().getDeclaredMethod("captureScreen"));
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public static Dictionary<String, String> commandlist = new Hashtable<String, String>();
	{
		// commandlist.put("কম্পিউটার বন্ধ করো", "shutdown -s -t 10");
		// commandlist.put("কম্পিউটার পুনরায় চালু করো", "shutdown -r -t 10");
		commandlist.put("কন্ট্রোল প্যানেল খোলো", "cmd /c start control");
		commandlist.put("ফাইল এক্সপ্লোরার খোলো", "cmd /c start explorer");
		commandlist.put("গুগল ক্রোম খোলো", "cmd /c start chrome.exe");
		commandlist.put("ফায়ারফক্স খোলো", "cmd /c start firefox.exe");
		commandlist.put("নোটপ্যাড খোলো", "notepad.exe");
		commandlist.put("সেটিংস খোলো", "cmd /c start ms-settings:");
		commandlist.put("ব্রাউজার খোলো", "rundll32 url.dll,FileProtocolHandler http://www.google.com");
		commandlist.put("কমান্ড প্রোমট খোলো", "cmd /c start cmd.exe");
		commandlist.put("টাস্ক ম্যানেজার খোলো", "cmd /c start taskmgr");
	}

	public static Map<String, ArrayList<Integer>> keyList = new HashMap<String, ArrayList<Integer>>() {
		private static final long serialVersionUID = 1L;
		{
			// put("ব্যাক স্পেস চাপো", new
			// ArrayList<Integer>(Arrays.asList(KeyEvent.VK_BACK_SPACE)));
			// put("আনডু করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL,
			// KeyEvent.VK_Z)));
			// put("রিডু করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL,
			// KeyEvent.VK_Y)));
			// put("ডিলিট করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_DELETE)));
			// put("কাট করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL,
			// KeyEvent.VK_X)));
			// put("পেস্ট করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL,
			// KeyEvent.VK_V)));
			put("বন্ধ করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_F4)));
			put("মিনিমাইজ করো",
					new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_ALT, KeyEvent.VK_SPACE, KeyEvent.VK_N)));
			put("সব সিলেক্ট করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_A)));
			put("কপি করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_C)));
			put("সেভ করো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_S)));
			put("ইন্টার চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_ENTER)));
			put("পেছনে যাও", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_BACK_SPACE)));
			put("ডানে যাও", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_RIGHT)));
			put("বামে যাও", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_LEFT)));
			put("উপরে যাও", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_UP)));
			put("নিচে যাও", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_DOWN)));
			put("স্টার্ট মেনু খোলো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_WINDOWS)));
			put("এস্কেপ চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_ESCAPE)));
			put("ট্যাব চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_TAB)));
			put("ক্যাপস লক চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CAPS_LOCK)));
			put("স্পেস চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_SPACE)));
			put("সিফট চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_SHIFT)));
			put("কন্ট্রোল চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL)));
			put("যোগ চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS)));
			put("বিয়োগ চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_MINUS)));
			put("গুন চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_MULTIPLY)));
			put("ভাগ চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_DIVIDE)));
			put("দশমিক চাপো", new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_PERIOD)));
			for (int i = 48; i < 58; i++) {
				put(digit[i - 48] + " চাপো", new ArrayList<Integer>(Arrays.asList(i)));
			}
			for (int i = 65; i < 91; i++) {
				put(letter[i - 65] + " চাপো", new ArrayList<Integer>(Arrays.asList(i)));
				put("বড় হাতের " + letter[i - 65] + " চাপো",
						new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_SHIFT, i)));
			}
		}
	};

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
	}

	public String getCommand() {
		return command;
	}

	public String getProcess() {
		String lastword = command.substring(command.lastIndexOf(" ") + 1);
		if(commandlist.get(command) == null && keyList.get(command) == null && methodMap.get(command) == null) {
			lastword = "Can't understand. Speak Again..";
		} else if (lastword.equals("চাপো")) {
			lastword = command.substring(0, command.lastIndexOf(" ")) + " চাপা হচ্ছে";
		} else if (lastword.equals("খোলো")) {
			lastword = command.substring(0, command.lastIndexOf(" ")) + " খোলা হচ্ছে";
		} else if (lastword.equals("করো")) {
			lastword = command.substring(0, command.lastIndexOf(" ")) + " করা হচ্ছে";
		} else if (lastword.equals("নাও")) {
			lastword = command.substring(0, command.lastIndexOf(" ")) + " নেওয়া হচ্ছে";
		} else if (lastword.equals("যাও")) {
			lastword = command.substring(0, command.lastIndexOf(" ")) + " যাওয়া হচ্ছে";
		} else {
			lastword = command;
		}
		return lastword;
	}

	public void executeProcess()
			throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (commandlist.get(command) != null) {
			Runtime.getRuntime().exec(commandlist.get(command));
		} else if (keyList.get(command) != null) {
			pressButton(keyList.get(command));
		} else if (methodMap.get(command) != null) {
			methodMap.get(command).invoke(this, null);
		} else {
			System.out.println("Invalid command");
		}
	}

	// public static void main(String[] args) throws IllegalAccessException,
	// IllegalArgumentException,
	// InvocationTargetException, IOException, InterruptedException {
	//
	// try {
	// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	// } catch (Exception e) {
	//
	// }
	// CommandManagerBangla a = new CommandManagerBangla();
	// // for (int i = 48; i < 58; i++) {
	// // a.setCommand(a.digit[i - 48] + " চাপো");
	// // System.out.println(a.getCommand());
	// // System.out.println(a.getProcess());
	// // a.executeProcess();
	// // }
	// // for (int i = 65; i < 91; i++) {
	// // //a.setCommand(a.letter[i - 65] + " চাপো");
	// // //a.setCommand("বড় হাতের " + a.letter[i - 65] + " চাপো");
	// // System.out.println(a.getCommand());
	// // System.out.println(a.getProcess());
	// // a.executeProcess();
	// // }
	// a.setCommand("বড় হাতের এ চাপো");
	// System.out.println(a.getCommand());
	// System.out.println(a.getProcess());
	// a.executeProcess();
	// System.out.println("Work done");
	// }

}
