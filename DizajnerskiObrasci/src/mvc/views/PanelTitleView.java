package mvc.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import mvc.controllers.PanelTitleController;

public class PanelTitleView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel labelCenter;
	private JLabel labelLeft;
	private JPanel buttonDefaultBorderColor;
	private JPanel buttonDefaultInnerColor;
	private PanelTitleController controller;
	
	public PanelTitleView() {
		setBackground(new Color(50, 50, 50));
		setSize((1536*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		
		ImageIcon iconExitDefault = new ImageIcon("res/icons/panelTitle/exit.png");
		Icon iconExit = new ImageIcon(iconExitDefault.getImage().getScaledInstance((iconExitDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconExitDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconMinimizeDefault = new ImageIcon("res/icons/panelTitle/minimize.png");
		Icon iconMinimize = new ImageIcon(iconMinimizeDefault.getImage().getScaledInstance((iconMinimizeDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconMinimizeDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		
		JButton buttonExit = new JButton(iconExit);
		JButton buttonMinimize = new JButton(iconMinimize);
		
		labelCenter = new JLabel();
		labelCenter.setHorizontalAlignment(SwingConstants.CENTER);
		labelCenter.setForeground(new Color(214, 214, 214));
		labelCenter.setFont(new Font("Tahoma", Font.BOLD, (int)(12 * (Math.min((float)(1536 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 1536, (float)(23 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 23)))));
		labelLeft = new JLabel();
		labelLeft.setForeground(new Color(214, 214, 214));
		labelLeft.setFont(new Font("Tahoma", Font.BOLD, (int)(12 * (Math.min((float)(1536 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 1536, (float)(23 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 23)))));
		
		buttonExit.setBackground(new Color(50,50,50));
		buttonExit.setBorderPainted(false);
		buttonExit.setFocusPainted(false);
		buttonExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.mouseEntered(buttonExit, "Exit");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.mouseExited(buttonExit);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				controller.mousePressed(buttonExit);
			}
		});
		buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.buttonExitAction();
			}
		});
		buttonMinimize.setBackground(new Color(50,50,50));
		buttonMinimize.setBorderPainted(false);
		buttonMinimize.setFocusPainted(false);
		buttonMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.mouseEntered(buttonMinimize, "Minimize");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.mouseExited(buttonMinimize);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				controller.mousePressed(buttonMinimize);
			}
		});
		buttonMinimize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.buttonMinimizeAction();
			}
		});
		buttonDefaultBorderColor = new JPanel();
		buttonDefaultBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.buttonDefaultColorEntered("Default Border Color");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.buttonDefaultColorExited();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.buttonDefaultBorderColorClicked();
			}
		});
		buttonDefaultInnerColor = new JPanel();
		buttonDefaultInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.buttonDefaultColorEntered("Default Inner Color");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.buttonDefaultColorExited();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.buttonDefaultInnerColorClicked();
			}
		});
		
		GroupLayout g1_titleBarPanel = new GroupLayout(this);
		g1_titleBarPanel.setHorizontalGroup(
			g1_titleBarPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(g1_titleBarPanel.createSequentialGroup()
					.addComponent(buttonDefaultBorderColor, GroupLayout.PREFERRED_SIZE, (33*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addGap((2*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
					.addComponent(buttonDefaultInnerColor, GroupLayout.PREFERRED_SIZE, (33*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addGap((10*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
					.addComponent(labelLeft, GroupLayout.PREFERRED_SIZE, (144*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addGap((345*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
					.addComponent(labelCenter, GroupLayout.PREFERRED_SIZE, (394*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, (522*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, Short.MAX_VALUE)
					.addComponent(buttonMinimize)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonExit))
		);
		g1_titleBarPanel.setVerticalGroup(
			g1_titleBarPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(g1_titleBarPanel.createParallelGroup(Alignment.BASELINE)
					.addComponent(labelLeft, GroupLayout.PREFERRED_SIZE, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addComponent(buttonExit, GroupLayout.PREFERRED_SIZE, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addComponent(buttonMinimize, GroupLayout.PREFERRED_SIZE, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addComponent(labelCenter, GroupLayout.PREFERRED_SIZE, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addComponent(buttonDefaultBorderColor, GroupLayout.PREFERRED_SIZE, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addComponent(buttonDefaultInnerColor, GroupLayout.PREFERRED_SIZE, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(g1_titleBarPanel);
	}
	
	public void setController(PanelTitleController ptc) {
		controller = ptc;
	}
	
	public JLabel getLabelCenter() {
		return labelCenter;
	}
	
	public JLabel getLabelLeft() {
		return labelLeft;
	}
	
	public JPanel getButtonDefaultBorderColor() {
		return buttonDefaultBorderColor;
	}
	
	public JPanel getButtonDefaultInnerColor() {
		return buttonDefaultInnerColor;
	}

}