package mvc.views;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import mvc.controllers.PanelSelectController;

public class PanelSelectView extends JPanel {

	private static final long serialVersionUID = 1L;
	private PanelSelectController controller;
	private JButton buttonModify;
	
	public PanelSelectView() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(new Color(50, 50, 50));
		
		ImageIcon iconDeleteDefault = new ImageIcon("res/icons/panelSelect/delete.png");
		Icon iconDelete = new ImageIcon(iconDeleteDefault.getImage().getScaledInstance((iconDeleteDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconDeleteDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		buttonModify = new JButton();
		JButton buttonDelete = new JButton(iconDelete);
		
		buttonModify.setBackground(new Color(50,50,50));
		buttonModify.setBorderPainted(false);
		buttonModify.setFocusPainted(false);
		buttonModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.mouseEntered(buttonModify, "Modify Shape");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.mouseExited(buttonModify);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				controller.mousePressed(buttonModify);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(buttonModify);
			}
		});
		buttonModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.buttonModifyAction();
			}
		});
		
		buttonDelete.setBackground(new Color(50,50,50));
		buttonDelete.setBorderPainted(false);
		buttonDelete.setFocusPainted(false);
		buttonDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.mouseEntered(buttonDelete, "Delete Shapes");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.mouseExited(buttonDelete);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				controller.mousePressed(buttonDelete);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(buttonDelete);
			}
		});
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.buttonDeleteAction();
			}
		});
		
		GroupLayout gl_panelSelect = new GroupLayout(this);
		gl_panelSelect.setHorizontalGroup(
			gl_panelSelect.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelect.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSelect.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonModify, GroupLayout.PREFERRED_SIZE, (51*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, (51*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelSelect.setVerticalGroup(
			gl_panelSelect.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSelect.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonModify, GroupLayout.PREFERRED_SIZE, (51*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, (8*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Short.MAX_VALUE)
					.addComponent(buttonDelete, GroupLayout.PREFERRED_SIZE, (51*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(gl_panelSelect);
	}
	
	public void setController(PanelSelectController psc) {
		controller = psc;
	}
	
	public JButton getButtonModify() {
		return buttonModify;
	}

}