package dialogs;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import mvc.controllers.PanelToolController;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

public class DlgFileOptions extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JButton[] buttons;
	private String valueProperty;
	private String[] values = {"Save Draw", "Save Log", "Open Draw", "Open Log"};
	
	public DlgFileOptions(PanelToolController panelToolController) {
		buttons = new JButton[4];
		valueProperty = "value";
		setModal(false);
		setResizable(false);
		setUndecorated(true);
		setBounds((67*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (288*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (68*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		getContentPane().add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(50, 50, 50));
		setVisible(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowDeactivated(WindowEvent e) {
		        setVisible(false);
		    }
		});
		
		ImageIcon iconSaveDrawDefault = new ImageIcon("res/icons/customDialogs/dialogFile/saveDraw.png");
		Icon iconSaveDraw = new ImageIcon(iconSaveDrawDefault.getImage().getScaledInstance((iconSaveDrawDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconSaveDrawDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconSaveLogDefault = new ImageIcon("res/icons/customDialogs/dialogFile/saveLog.png");
		Icon iconSaveLog = new ImageIcon(iconSaveLogDefault.getImage().getScaledInstance((iconSaveLogDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconSaveLogDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconOpenDrawDefault = new ImageIcon("res/icons/customDialogs/dialogFile/openDraw.png");
		Icon iconOpenDraw = new ImageIcon(iconOpenDrawDefault.getImage().getScaledInstance((iconOpenDrawDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconOpenDrawDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconOpenLogDefault = new ImageIcon("res/icons/customDialogs/dialogFile/openLog.png");
		Icon iconOpenLog = new ImageIcon(iconOpenLogDefault.getImage().getScaledInstance((iconOpenLogDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconOpenLogDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		
		JButton buttonSaveDraw = new JButton(iconSaveDraw);
		buttons[0] = buttonSaveDraw;
		JButton buttonSaveLog = new JButton(iconSaveLog);
		buttons[1] = buttonSaveLog;
		JButton buttonOpenDraw = new JButton(iconOpenDraw);
		buttons[2] = buttonOpenDraw;
		JButton buttonOpenLog = new JButton(iconOpenLog);
		buttons[3] = buttonOpenLog;
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].putClientProperty(valueProperty, values[i]);
			buttons[i].setBackground(new Color(50, 50, 50));
			buttons[i].setBorderPainted(false);
			buttons[i].setFocusPainted(false);
			final int buttonIndex = i;
			buttons[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					panelToolController.buttonFileOptionsEntered(buttons[buttonIndex], valueProperty);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					panelToolController.buttonFileOptionsExited(buttons[buttonIndex]);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					panelToolController.buttonsMousePressed(buttons[buttonIndex]);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					panelToolController.buttonFileOptionsClicked(buttons[buttonIndex]);
				}
			});
		}
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelToolController.buttonSaveDrawAction();
			}
		});
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelToolController.buttonSaveLogAction();
			}
		});
		buttons[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelToolController.buttonOpenDrawAction();
			}
		});
		buttons[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelToolController.buttonOpenLogAction();
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap((20*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
					.addComponent(buttonSaveDraw, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addGap((20*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
					.addComponent(buttonSaveLog, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addGap((20*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
					.addComponent(buttonOpenDraw, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addGap((20*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
					.addComponent(buttonOpenLog, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap((10*Toolkit.getDefaultToolkit().getScreenSize().height)/864)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonOpenLog, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonOpenDraw, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonSaveLog, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonSaveDraw, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
}