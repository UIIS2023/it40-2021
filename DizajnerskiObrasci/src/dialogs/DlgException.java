package dialogs;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextArea;

public class DlgException extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	
	public DlgException(String textContent) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds((515*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (250*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (500*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (150*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		setResizable(false);
		getContentPane().add(panel);
		panel.setBackground(new Color(25, 25, 25));
		
		ImageIcon iconDefault = new ImageIcon("res/icons/customDialogs/warning.png");
		Icon icon = new ImageIcon(iconDefault.getImage().getScaledInstance((iconDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		JLabel labelIcon = new JLabel(icon);
		labelIcon.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextArea textAreaContent = new JTextArea();
		textAreaContent.setForeground(new Color(214, 214, 214));
		textAreaContent.setFont(new Font("Tahoma", Font.PLAIN, (int)(14 * (Math.min((float)(283 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 283, (float)(41 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 41)))));
		textAreaContent.setText(textContent);
		textAreaContent.setWrapStyleWord(true);
	    textAreaContent.setLineWrap(true);
	    textAreaContent.setOpaque(false);
	    textAreaContent.setEditable(false);
	    textAreaContent.setFocusable(false);
		
		JButton buttonOk = new JButton("Ok");
		buttonOk.setForeground(new Color(25, 25, 25));
		buttonOk.setBackground(new Color(230, 230, 230));
		buttonOk.setBorder(new LineBorder(new Color(230, 230, 230)));
		buttonOk.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(79 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 79, (float)(18 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 18)))));
		buttonOk.setFocusPainted(false);
		buttonOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				buttonOk.setContentAreaFilled(false);
				buttonOk.setForeground(new Color(230, 230, 230));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonOk.setContentAreaFilled(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				buttonOk.setContentAreaFilled(true);
				buttonOk.setForeground(new Color(25, 25, 25));
			}
		});
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelIcon, GroupLayout.PREFERRED_SIZE, (80*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, (307*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, Short.MAX_VALUE)
							.addComponent(buttonOk, GroupLayout.PREFERRED_SIZE, (79*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textAreaContent, GroupLayout.PREFERRED_SIZE, (283*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(buttonOk, GroupLayout.PREFERRED_SIZE, (18*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelIcon, GroupLayout.PREFERRED_SIZE, (93*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap((38*Toolkit.getDefaultToolkit().getScreenSize().height)/864)
							.addComponent(textAreaContent, GroupLayout.PREFERRED_SIZE, (41*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
}