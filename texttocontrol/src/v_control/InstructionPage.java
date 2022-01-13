package v_control;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.math3.geometry.partitioning.Embedding;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JButton;

public class InstructionPage extends JFrame {

	String engcmdlist = "/command_list/English.txt";
	String bancmdlist = "/command_list/Bangla.txt";

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructionPage frame = new InstructionPage();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public InstructionPage() {
		initialize();
	}

	public InstructionPage(Image icon) {
		setIconImage(icon);
		initialize();
	}

	private void initialize() {
		setSize(800, 500);
		setTitle("Instruction");
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(10);
		panel.add(verticalStrut);

		JLabel lblNewLabel = new JLabel("Instruction");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel);

		Component verticalStrut_1 = Box.createVerticalStrut(15);
		panel.add(verticalStrut_1);

		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		textArea.setText(
				"There is a \"on/off\" button which sends the software in sleeping mode or \r\nactive listening mode. When the button shows play symbol it is in sleeping\r\nmode and listening mode is denoted by pause symbol. \r\n\r\nFor the facilitation of user there is a screen which shows the current mode \r\nof the software or what it's doing.\r\n\r\nBeside that screen, there is a option button and exit button. \r\nClicking the exit button will close the application.\r\n\r\nIn options we have settings section where user can customize a few settings. \r\n"
				+ "While in the following section, he can choose the language in which he wants\r\nto operate the computer. He can go through our instruction, credit\r\nand about sections if he wishes. "
				+ "Jump to the system tray will take\r\nthe software to the system tray.One can use options from system tray as well."
				+ "\r\n\r\nFrom settings user can choose Safe Mode or Full Active Mode of the software.\r\nIn Safe Mode some sensitive commands like \"shut down computer\" ,\"delete it\" \r\netc are made inactive.In Full Active Mode all the commands will be active.\r\nWe highly recommend to use Safe Mode..\r\n");
		textArea.setFont(new Font("Arial", Font.PLAIN, 20));
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.menu);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(null);
		contentPane.add(scrollPane, BoxLayout.Y_AXIS);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				scrollPane.getViewport().setViewPosition(new Point(0, 0));
			}
		});

		JLabel label = new JLabel("       ");
		contentPane.add(label, BorderLayout.WEST);

		JLabel label_1 = new JLabel("       ");
		contentPane.add(label_1, BorderLayout.EAST);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_2, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("       ");
		panel_1.add(lblNewLabel_1, BorderLayout.WEST);

		JLabel lblNewLabel_2 = new JLabel("       ");
		panel_1.add(lblNewLabel_2, BorderLayout.EAST);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_3, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("English Command List");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Tabledemo action1 = new Tabledemo(getIconImage(), "English Command", engcmdlist);
				action1.frame.setVisible(true);
				action1.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		panel_2.add(btnNewButton, BorderLayout.WEST);

		JButton btnNewButton_1 = new JButton("Bangla Command List");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Tabledemo action2 = new Tabledemo(getIconImage(), "Bangla Command", bancmdlist);
				action2.frame.setVisible(true);
				action2.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		panel_2.add(btnNewButton_1, BorderLayout.EAST);
	}

}
