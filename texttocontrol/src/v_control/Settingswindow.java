package v_control;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.nio.channels.AcceptPendingException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.Dimension;

public class Settingswindow {

	static boolean issafe = true;

	public JFrame frame = new JFrame();
	Font globalfont = new Font("Arial", Font.PLAIN, 20);
	ButtonGroup bg = new ButtonGroup();

	JCheckBox chckbxNewCheckBox = new JCheckBox("Safe Mode ( Recommanded )");
	JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Full Active Mode");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settingswindow window = new Settingswindow();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Settingswindow() {
		initialize();
	}

	public Settingswindow(Image icon) {
		frame.setIconImage(icon);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame.setSize(600, 400);
		frame.setTitle("Settings");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel spa = new JLabel();
		JLabel spa2 = new JLabel();
		spa.setText("          ");
		spa2.setText("          ");

		JPanel mainpanel = new JPanel();
		frame.getContentPane().add(mainpanel, BorderLayout.CENTER);
		frame.getContentPane().add(spa, BorderLayout.WEST);
		frame.getContentPane().add(spa2, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_3);

		JPanel btnpanel = new JPanel();
		panel.add(btnpanel);
		btnpanel.setLayout(new BoxLayout(btnpanel, BoxLayout.X_AXIS));

		Component horizontalStrut_1 = Box.createHorizontalStrut(200);
		btnpanel.add(horizontalStrut_1);

		JButton btnApply = new JButton("Apply and Close");
		btnpanel.add(btnApply);
		btnApply.setFont(globalfont);
		btnApply.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnApply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (chckbxNewCheckBox.isSelected()) {
					CommandManagerEnglish.commandlist.remove("shut down computer");
					CommandManagerEnglish.commandlist.remove("restart computer");

					CommandManagerEnglish.keyList.remove("cut it");
					CommandManagerEnglish.keyList.remove("paste here");
					CommandManagerEnglish.keyList.remove("undo it");
					CommandManagerEnglish.keyList.remove("redo it");
					CommandManagerEnglish.keyList.remove("delete it");

					CommandManagerEnglish.strokeMap.remove("back space");
					CommandManagerEnglish.strokeMap.remove("back");
					CommandManagerEnglish.strokeMap.remove("delete");

					CommandManagerBangla.commandlist.remove("কম্পিউটার বন্ধ করো");
					CommandManagerBangla.commandlist.remove("কম্পিউটার পুনরায় চালু করো");

					CommandManagerBangla.keyList.remove("ব্যাক স্পেস চাপো");
					CommandManagerBangla.keyList.remove("আনডু করো");
					CommandManagerBangla.keyList.remove("রিডু করো");
					CommandManagerBangla.keyList.remove("ডিলিট করো");
					CommandManagerBangla.keyList.remove("কাট করো");
					CommandManagerBangla.keyList.remove("পেস্ট করো");

					issafe = true;

					// safe
				} else {
					// not safe
					CommandManagerEnglish.commandlist.put("shut down computer", "shutdown -s -t 10");
					CommandManagerEnglish.commandlist.put("restart computer", "shutdown -r -t 10");

					CommandManagerEnglish.keyList.put("cut it",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_X)));
					CommandManagerEnglish.keyList.put("paste here",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_V)));
					CommandManagerEnglish.keyList.put("undo it",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_Z)));
					CommandManagerEnglish.keyList.put("redo it",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_Y)));
					CommandManagerEnglish.keyList.put("delete it",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_DELETE)));

					CommandManagerEnglish.strokeMap.put("back space", new KeyStroke(KeyEvent.VK_BACK_SPACE, false));
					CommandManagerEnglish.strokeMap.put("back", new KeyStroke(KeyEvent.VK_BACK_SPACE, false));
					CommandManagerEnglish.strokeMap.put("delete", new KeyStroke(KeyEvent.VK_DELETE, false));

					CommandManagerBangla.commandlist.put("কম্পিউটার বন্ধ করো", "shutdown -s -t 10");
					CommandManagerBangla.commandlist.put("কম্পিউটার পুনরায় চালু করো", "shutdown -r -t 10");

					CommandManagerBangla.keyList.put("ব্যাক স্পেস চাপো",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_BACK_SPACE)));
					CommandManagerBangla.keyList.put("আনডু করো",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_Z)));
					CommandManagerBangla.keyList.put("রিডু করো",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_Y)));
					CommandManagerBangla.keyList.put("ডিলিট করো",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_DELETE)));
					CommandManagerBangla.keyList.put("কাট করো",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_X)));
					CommandManagerBangla.keyList.put("পেস্ট করো",
							new ArrayList<Integer>(Arrays.asList(KeyEvent.VK_CONTROL, KeyEvent.VK_V)));

					issafe = false;
				}
				frame.dispose();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(globalfont);
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});

		Component horizontalStrut = Box.createHorizontalStrut(50);
		btnpanel.add(horizontalStrut);
		btnpanel.add(btnCancel);

		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);

		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));

		chckbxNewCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxNewCheckBox.setFont(globalfont);
		mainpanel.add(chckbxNewCheckBox);

		chckbxNewCheckBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxNewCheckBox_1.setFont(globalfont);
		mainpanel.add(chckbxNewCheckBox_1);

		bg.add(chckbxNewCheckBox);
		bg.add(chckbxNewCheckBox_1);
		if (issafe) {
			chckbxNewCheckBox.setSelected(true);
		} else {
			chckbxNewCheckBox_1.setSelected(true);
		}

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		mainpanel.add(verticalStrut_1);
		JLabel down = new JLabel();
		mainpanel.add(down);
		down.setText("In Safe Mode some sensitive commands will not work..");
		down.setFont(globalfont);

	}
}
