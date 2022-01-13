package v_control;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemColor;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JWindow;
import javax.swing.UIManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;


public class Vc {

	private JWindow frame;
	

	Font globalfont = new Font("Arial", Font.PLAIN, 20);
	Font menufont = new Font("Arial", Font.PLAIN, 15);
	Font customFont;

	JToggleButton tglbtnstartstop = new JToggleButton();
	JTextField txtdisplay = new JTextField();
	JButton btnclose = new JButton();
	JButton btnmove = new JButton();
	JMenu mnNewMenu = new JMenu("");
	JCheckBoxMenuItem chckbxmntmBangla = new JCheckBoxMenuItem("Bangla");
	JCheckBoxMenuItem chckbxmntmEnglish = new JCheckBoxMenuItem("English");
	ButtonGroup languagegroup = new ButtonGroup();
	JMenuItem mnoption = new JMenuItem("Settings");
	JMenuItem mncredits = new JMenuItem("Credits");
	JMenuItem mnabout = new JMenuItem("About");
	JMenuItem mninstruction = new JMenuItem("Instructions");
	JMenuItem mngotray = new JMenuItem("Jump to system tray");
	
	JMenuBar menuBar = new JMenuBar();

	static Configuration configurationen = new Configuration();
	static Configuration configurationbn = new Configuration();
	static LiveSpeechRecognizer recognize;
	static SpeechResult result;
	MyThread mythread = null;
	Timer timer = new Timer();
	CommandManagerEnglish cmdmngEng = new CommandManagerEnglish();
	CommandManagerBangla cmdmngBan = new CommandManagerBangla();

	ImageIcon iconstart = null;
	ImageIcon iconstop = null;
	ImageIcon iconoption = null;
	ImageIcon iconclose = null;
	ImageIcon iconmove = null;
	ImageIcon iconmnabout = null;
	ImageIcon iconmncredit = null;
	ImageIcon iconmnhelp = null;
	ImageIcon iconmnsettring = null;
	ImageIcon iconvc = null;
	ImageIcon iconvcsmall = null;
	
	//private final Component horizontalStrut = Box.createHorizontalStrut(20);
	
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vc window = new Vc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Vc() {
		try {
			System.setProperty("file.encoding", "UTF-8");
			Field charset = Charset.class.getDeclaredField("defaultCharset");
			charset.setAccessible(true);
			charset.set(null,null);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	private void initialize() {

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("Nikosh.ttf")).deriveFont(20f);
			ge.registerFont(customFont);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		configurationen.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configurationen.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configurationen.setGrammarPath("resource:/english/");
		configurationen.setGrammarName("hello");
		configurationen.setUseGrammar(true);

		configurationbn.setAcousticModelPath("resource:/bangla/ci_cont");
		configurationbn.setDictionaryPath("resource:/bangla/other.dic");
		//configurationbn.setLanguageModelPath("resource:/bangla/other.lm");
		configurationbn.setGrammarPath("resource:/bangla/");
		configurationbn.setGrammarName("hellobn");
		configurationbn.setUseGrammar(true);

		frame = new JWindow();
		frame.setBounds((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2), 0, 500, 30);
		frame.setFocusableWindowState(true);
		frame.setAlwaysOnTop(true);
		frame.setOpacity(0.40f);
		
		MouseListener mousefocus = new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				abcCaller();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				timer.cancel();
				frame.setOpacity(1f);
			}

		};
		

		
		
		MouseMotionListener movelistner = new MouseAdapter() {
			private int mx, my;

		    @Override
		    public void mouseMoved(MouseEvent e) {
		        mx = e.getXOnScreen();
		        my = e.getYOnScreen();
		    }
		    
		    @Override
		    public void mouseDragged(MouseEvent e) {
		        Point p = frame.getLocation();
		        p.x += e.getXOnScreen() - mx;
		        p.y += e.getYOnScreen() - my;
		        p.y=0;
		        if(p.x<0 ) {
		        	p.x=0;
		        }
		        if(p.x>Toolkit.getDefaultToolkit().getScreenSize().getWidth()-30) {
		        	p.x=(int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-30);
		        }

		        mx = e.getXOnScreen();
		        my = e.getYOnScreen();
		        frame.setLocation(p);
		    }
		};
		
		frame.addMouseListener(mousefocus);
		frame.addMouseMotionListener(movelistner);
            


		try {
			iconstart = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("play1.png")));
			iconstop = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("pause1.png")));
			iconoption = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("option1.png")));
			iconclose = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("close1.png")));
			iconmove = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("moveicon.png")));
			
			iconmnabout = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("about.png")));
			iconmncredit = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("team.png")));
			iconmnhelp = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("help.png")));
			iconmnsettring = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("tools.png")));

			iconvc = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("vc1.png")));
			iconvcsmall = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("vc2.png")));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setIconImage(iconvc.getImage());
		
		mngotray.setFont(menufont);
		mnabout.setFont(menufont);
		mncredits.setFont(menufont);
		mninstruction.setFont(menufont);
		mnoption.setFont(menufont);
		chckbxmntmBangla.setFont(menufont);
		chckbxmntmEnglish.setFont(menufont);
		
		mnabout.setIcon(iconmnabout);
		mncredits.setIcon(iconmncredit);
		mninstruction.setIcon(iconmnhelp);
		mnoption.setIcon(iconmnsettring);
		
		

		
		tglbtnstartstop.setPreferredSize(new Dimension(30, 30));
		tglbtnstartstop.addMouseListener(mousefocus);
		tglbtnstartstop.setIcon(iconstart);
		tglbtnstartstop.setFont(globalfont);
		tglbtnstartstop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tglbtnstartstop.isSelected()) {
					tglbtnstartstop.setIcon(iconstop);
					if (chckbxmntmEnglish.isSelected()) {
						mythread = new RecogEnglishThread();
						mythread.start();
					} else if (chckbxmntmBangla.isSelected()) {
						mythread = new RecogBanglaThread();
						mythread.start();
					}
				} else {
					tglbtnstartstop.setIcon(iconstart);
					mythread.stop(); //interrupt(); // 
					recognize.closeRecognitionLine();
					txtdisplay.setText("Sleeping.....");
				}
			}
		});
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		btnmove.setPreferredSize(new Dimension(30, 30));
		btnmove.setIcon(iconvcsmall);
		btnmove.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		btnmove.addMouseMotionListener(movelistner);
		btnmove.addMouseListener(mousefocus);
		btnmove.setFont(globalfont);
		
		frame.getContentPane().add(btnmove);
		frame.getContentPane().add(tglbtnstartstop);

		txtdisplay.setText("Sleeping.....");
		txtdisplay.setFont(customFont);
		txtdisplay.addMouseMotionListener(movelistner);
		txtdisplay.addMouseListener(mousefocus);
		txtdisplay.setBackground(SystemColor.inactiveCaption);
		txtdisplay.setForeground(SystemColor.desktop);
		txtdisplay.setEditable(false);
		frame.getContentPane().add(txtdisplay);

		mnNewMenu.setOpaque(true);
		mnNewMenu.addMouseListener(mousefocus);
		mnNewMenu.setPreferredSize(new Dimension(30, 30));
		mnNewMenu.setIcon(iconoption);
		mnNewMenu.setFont(globalfont);
		mnNewMenu.setBackground(SystemColor.inactiveCaption);

		mnNewMenu.add(mnoption);
		mnNewMenu.addSeparator();
		mnNewMenu.add(chckbxmntmEnglish);
		mnNewMenu.add(chckbxmntmBangla);
		mnNewMenu.addSeparator();		
		mnNewMenu.add(mngotray);
		mnNewMenu.addSeparator();
		mnNewMenu.add(mninstruction);
		mnNewMenu.add(mncredits);
		mnNewMenu.add(mnabout);
		
		mninstruction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InstructionPage instructionframe = new InstructionPage(frame.getIconImages().get(0));
				instructionframe.setVisible(true);
				instructionframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		mnoption.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Settingswindow settingframe = new Settingswindow(frame.getIconImages().get(0));
				settingframe.frame.setVisible(true);
				settingframe.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		mnabout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				About aboutframe = new About(frame.getIconImages().get(0));
				aboutframe.setVisible(true);
				aboutframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		mncredits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Credit creditframe = new Credit(frame.getIconImages().get(0));
				creditframe.setVisible(true);
				creditframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		mngotray.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!SystemTray.isSupported()){
					JOptionPane.showMessageDialog(null, "System tray is not supported !!!", "Error",JOptionPane.ERROR_MESSAGE);
			        return ;
			    }
				frame.setVisible(false);
				
				SystemTray systemTray = SystemTray.getSystemTray();
				PopupMenu trayPopupMenu = new PopupMenu();
				
			    //1t menuitem for popupmenu
				TrayIcon trayIcon = new TrayIcon(frame.getIconImages().get(0), "Voice Controller" , trayPopupMenu);
				
				MenuItem mnitmsettings = new MenuItem("Settings");
				mnitmsettings.setFont(menufont);
				mnitmsettings.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	Settingswindow settingframe = new Settingswindow(frame.getIconImages().get(0));
						settingframe.frame.setVisible(true);
						settingframe.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        }
			    });    
			    trayPopupMenu.add(mnitmsettings);
			    trayPopupMenu.addSeparator();
				
			    MenuItem mnitmrestore = new MenuItem("Restore Top Bar");
				mnitmrestore.setFont(menufont);
			    mnitmrestore.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			            //JOptionPane.showMessageDialog(null, "Action Clicked");
			            frame.setVisible(true);
			            systemTray.remove(trayIcon);
			        }
			    });     
			    trayPopupMenu.add(mnitmrestore);
			    trayPopupMenu.addSeparator();
			    
			    MenuItem mnitminstruction = new MenuItem("Instructions");
			    mnitminstruction.setFont(menufont);
			    mnitminstruction.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	InstructionPage instructionframe = new InstructionPage(frame.getIconImages().get(0));
						instructionframe.setVisible(true);
						instructionframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        }
			    });     
			    trayPopupMenu.add(mnitminstruction);
			    
			    MenuItem mnitmcredits = new MenuItem("Credits");
			    mnitmcredits.setFont(menufont);
			    mnitmcredits.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	Credit creditframe = new Credit(frame.getIconImages().get(0));
						creditframe.setVisible(true);
						creditframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        }
			    });     
			    trayPopupMenu.add(mnitmcredits);
			    
			    MenuItem mnitmabout = new MenuItem("About");
			    mnitmabout.setFont(menufont);
			    mnitmabout.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	About aboutframe = new About(frame.getIconImages().get(0));
						aboutframe.setVisible(true);
						aboutframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        }
			    });     
			    trayPopupMenu.add(mnitmabout);
			    trayPopupMenu.addSeparator();
			    
			    MenuItem mnitmexit = new MenuItem("Exit");
			    mnitmexit.setFont(menufont);
			    mnitmexit.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	System.exit(0);
			        }
			    });     
			    trayPopupMenu.add(mnitmexit);
			    
			    trayIcon.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	
	                	frame.setVisible(true);
			            systemTray.remove(trayIcon);
	                    
	                }
	            });
			    
			    trayIcon.setImageAutoSize(true);
			    try{
			        systemTray.add(trayIcon);
			    }catch(AWTException awtException){
			        awtException.printStackTrace();
			    }
				
			}
		});

		languagegroup.add(chckbxmntmBangla);
		languagegroup.add(chckbxmntmEnglish);
		chckbxmntmEnglish.setSelected(true);

		chckbxmntmBangla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnstartstop.isSelected()) {
					tglbtnstartstop.doClick();
				}
			}
		});

		chckbxmntmEnglish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnstartstop.isSelected()) {
					tglbtnstartstop.doClick();
				}
			}
		});

		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		menuBar.setOpaque(true);
		menuBar.setPreferredSize(new Dimension(30, 30));
		menuBar.setBackground(SystemColor.inactiveCaption);
		menuBar.add(mnNewMenu);
		frame.getContentPane().add(menuBar);

		btnclose.setPreferredSize(new Dimension(30, 30));
		btnclose.addMouseListener(mousefocus);
		btnclose.setIcon(iconclose);
		btnclose.setFont(globalfont);
		btnclose.setMnemonic(KeyEvent.VK_F4);
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Confirm close",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		frame.getContentPane().add(btnclose);
	}

	void recogEnglish() {
		try {
			recognize = new LiveSpeechRecognizer(configurationen);
			recognize.startRecognition(true);
			txtdisplay.setText("Listening.....");
			while ((result = recognize.getResult()) != null) {
				String command = result.getHypothesis();
				if (command.equals("<unk>")) {
					System.out.println("Can't understand. Speak Again..");
					txtdisplay.setText("Can't understand. Speak Again..");
				} else {
					cmdmngEng.setCommand(command);
					System.out.println(command);
					txtdisplay.setText(cmdmngEng.getProcess());
					cmdmngEng.executeProcess();
					//txtdisplay.setText("Listening.....");
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void recogBangla() {
		try {
			recognize = new LiveSpeechRecognizer(configurationbn);
			recognize.startRecognition(true);
			txtdisplay.setText("Listening.....");
			while ((result = recognize.getResult()) != null) {
				String command = result.getHypothesis();
				if (command.equals("<unk>")) {
					System.out.println("Can't understand. Speak Again..");
					txtdisplay.setText("Can't understand. Speak Again..");
				} else {
					cmdmngBan.setCommand(command);
					System.out.println(command);
					txtdisplay.setText(cmdmngBan.getProcess());
					cmdmngBan.executeProcess();
					//txtdisplay.setText("Listening.....");
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	abstract class MyThread extends Thread {

	}
	
	
	public synchronized void abcCaller() {
	    this.timer.cancel(); //this will cancel the current task. if there is no active task, nothing happens
	    this.timer = new Timer();
	    TimerTask action = new TimerTask() {
	        public void run() {
	        	frame.setOpacity(0.40f); //as you said in the comments: abc is a static method
	        }

	    };

	    this.timer.schedule(action, 3000); //this starts the task
	}

	class RecogEnglishThread extends MyThread {
		@Override
		public void run() {
			recogEnglish();
		}
	}

	class RecogBanglaThread extends MyThread {
		@Override
		public void run() {
			recogBangla();
		}
	}
}
