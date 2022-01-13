package v_control;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemColor;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Component;
import javax.swing.JTextArea;

public class About extends JFrame {

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
					About frame = new About();
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
	public About() {
		initialize();
	}
	
	public About(Image icon) {
		setIconImage(icon);
		initialize();
	}
	
	private void initialize() {
		setSize(850, 500);
		setTitle("About");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlabout = new JPanel();
		contentPane.add(pnlabout, BorderLayout.NORTH);
		pnlabout.setLayout(new BoxLayout(pnlabout, BoxLayout.Y_AXIS));
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setFont(new Font("Arial", Font.PLAIN, 30));
		pnlabout.add(lblAbout);
		
		//Box verticalBox = (Box)Box.createVerticalStrut(20);
		pnlabout.add(Box.createVerticalStrut(20));
		
		JLabel uselesslabel = new JLabel("        ");
		contentPane.add(uselesslabel, BorderLayout.WEST);
		
		JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 20));
		textArea.setText("Voice controller is a desktop application used for controlling computer using voice.\n\nWhy will you use it?\n=>It gives you the multitasking ability\n=>It facilitates you to use Bangla and English language to operate computer\n=>It is very handy for children and physically disabled\n=>It facilitates the use of computer for those who knows Bangla but not English\n\nOur Motivation?\nThe general mass people were our  motivation.The modern era is of computer. \nBut in our country many people can't use computer because they don't know \nhow to operate it. This concept gave us the idea that what if computer can hear  \nour voice and work accordingly.\n\nOur future Planning with this software:\r\nWe do agree that our software is not in the level of professional use. But, we are \ntrying to continuously upgrade it. So, we have a plan to publish the upgraded \nversion of the software on a periodic manner. \r\n\r\nRelease date: 01-01-2020\r\nVersion: VC@1\r\nContact us: suzadmohammad@gmail.com");
		textArea.setBackground(SystemColor.menu);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(null);
		contentPane.add(scrollPane, BoxLayout.Y_AXIS);
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				scrollPane.getViewport().setViewPosition(new Point(0,0));
			}
		});
	}

}
