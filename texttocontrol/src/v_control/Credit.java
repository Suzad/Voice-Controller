package v_control;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Credit extends JFrame {

	public JPanel creditpanel = new JPanel();

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
					//credit frame = new credit();
					Credit frame = new Credit();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Credit() {
		initialize();
	}
	
	public Credit(Image icon) {
		setIconImage(icon);
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setResizable(false);
		setSize(450, 300);
		setTitle("Credits");
		setLocationRelativeTo(null);

		getContentPane().add(creditpanel);
		creditpanel.setLayout(null);

		JLabel lblCredit = new JLabel("Credits");
		lblCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredit.setFont(new Font("Arial", Font.PLAIN, 30));
		lblCredit.setBounds(142, 16, 119, 35);
		creditpanel.add(lblCredit);

		JTextArea creditVCText = new JTextArea();
		creditVCText.setBackground(UIManager.getColor("CheckBox.background"));
		creditVCText.setFont(new Font("Arial", Font.PLAIN, 20));
		creditVCText.setEditable(false);
		creditVCText.setText("All the credits reserved to the team Voice \nController. Special thanks to CMU Sphinx.");
		creditVCText.setBounds(15, 67, 414, 177);

		creditpanel.add(creditVCText);
	}

}
