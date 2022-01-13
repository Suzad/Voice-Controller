package v_control;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;

public class Tabledemo {
	Font banglafont;
	public JFrame frame = new JFrame();
	JLabel lblNewLabel = new JLabel("English Command");
	String filePath = "/command_list/English.txt";
	private JTable table;

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
					Tabledemo window = new Tabledemo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tabledemo() {
		initialize();
	}

	public Tabledemo(Image icon) {
		frame.setIconImage(icon);
		initialize();
	}

	public Tabledemo(Image icon, String title, String filepath) {
		frame.setIconImage(icon);
		lblNewLabel.setText(title);
		this.filePath = filepath;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			banglafont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("Nikosh.ttf"))
					.deriveFont(20f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ge.registerFont(banglafont);

		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 20));
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel.add(verticalStrut_2);

		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.menu);
		scrollPane.getViewport().setBackground(SystemColor.menu);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setBackground(SystemColor.menu);
		if (lblNewLabel.getText().equals("Bangla Command")) {
			table.setFont(banglafont);
			table.getTableHeader().setFont(banglafont);
		} else {
			table.setFont(new Font("Arial", Font.PLAIN, 20));
			table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		}

		int height = table.getRowHeight();
		table.setRowHeight(height + 20);
		table.setEnabled(false);
		JTableHeader header = table.getTableHeader();
		table.getTableHeader().setBackground(Color.BLACK);
		header.setForeground(Color.BLACK);
		((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		// Font headerfont = header.getFont();
		// header.setFont("Arial",Font.BOLD,20);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("          ");
		frame.getContentPane().add(lblNewLabel_1, BorderLayout.WEST);

		JLabel lblNewLabel_3 = new JLabel("          ");
		frame.getContentPane().add(lblNewLabel_3, BorderLayout.EAST);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_3);

		JLabel lblNewLabel_2 = new JLabel("       * These commands will not work in safe mode");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		panel_1.add(lblNewLabel_2);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_1);
		frame.setSize(850, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InputStream is = getClass().getResourceAsStream(filePath) ;
		//File file = new File(this.getClass().getResourceAsStream(filePath));

		try {

			InputStreamReader r = new InputStreamReader(is,"UTF-8");
			BufferedReader br = new BufferedReader(r);

			//BufferedReader br = new BufferedReader(new FileReader(file));
			String firstLine = br.readLine().trim();

			String[] columnsName = firstLine.split(",");
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(columnsName);

			Object[] tableLines = br.lines().toArray();

			for (int i = 0; i < tableLines.length; i++) {
				String line = tableLines[i].toString().trim();
				String[] dataRow = line.split("/");

				model.addRow(dataRow);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
