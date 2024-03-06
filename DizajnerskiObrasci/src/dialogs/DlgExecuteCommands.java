package dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

public class DlgExecuteCommands extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private boolean confirm;
	private JTextArea textAreaContent;
	
	public DlgExecuteCommands(String textContent) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds((450*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (300*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (700*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (150*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		setResizable(false);
		getContentPane().add(panel);
		panel.setBackground(new Color(25, 25, 25));
		
		ImageIcon iconDefault = new ImageIcon("res/icons/customDialogs/question.png");
		Icon icon = new ImageIcon(iconDefault.getImage().getScaledInstance((iconDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		JLabel labelIcon = new JLabel(icon);
		labelIcon.setHorizontalAlignment(SwingConstants.CENTER);
		
		textAreaContent = new JTextArea();
		textAreaContent.setForeground(new Color(214, 214, 214));
		textAreaContent.setFont(new Font("Tahoma", Font.PLAIN, (int)(14 * (Math.min((float)(540 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 540, (float)(41 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 41)))));
		textAreaContent.setText(textContent);
		textAreaContent.setWrapStyleWord(true);
	    textAreaContent.setLineWrap(true);
	    textAreaContent.setOpaque(false);
	    textAreaContent.setEditable(false);
	    textAreaContent.setFocusable(false);
		
	    JButton buttonExecute = new JButton("Execute");
		buttonExecute.setForeground(new Color(25, 25, 25));
		buttonExecute.setBackground(new Color(230, 230, 230));
		buttonExecute.setBorder(new LineBorder(new Color(230, 230, 230)));
		buttonExecute.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(79 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 79, (float)(18 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 18)))));
		buttonExecute.setFocusPainted(false);
		buttonExecute.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				buttonExecute.setContentAreaFilled(false);
				buttonExecute.setForeground(new Color(230, 230, 230));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonExecute.setContentAreaFilled(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				buttonExecute.setContentAreaFilled(true);
				buttonExecute.setForeground(new Color(25, 25, 25));
			}
		});
		buttonExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm = true;
				setVisible(false);
			}
		});
		
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.setForeground(new Color(25, 25, 25));
		buttonCancel.setFocusPainted(false);
		buttonCancel.setBorder(new LineBorder(new Color(230, 230, 230)));
		buttonCancel.setBackground(new Color(230, 230, 230));
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(79 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 79, (float)(18 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 18)))));
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				buttonCancel.setContentAreaFilled(false);
				buttonCancel.setForeground(new Color(230, 230, 230));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonCancel.setContentAreaFilled(true);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				buttonCancel.setContentAreaFilled(true);
				buttonCancel.setForeground(new Color(25, 25, 25));
			}
		});
		buttonCancel.addActionListener(new ActionListener() {
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
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textAreaContent, GroupLayout.PREFERRED_SIZE, (540*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
							.addGap((60*Toolkit.getDefaultToolkit().getScreenSize().width)/1536))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(buttonExecute, GroupLayout.PREFERRED_SIZE, (79*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(buttonCancel, GroupLayout.PREFERRED_SIZE, (79*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
							.addGap((22*Toolkit.getDefaultToolkit().getScreenSize().width)/1536))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelIcon, GroupLayout.PREFERRED_SIZE, (93*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap((38*Toolkit.getDefaultToolkit().getScreenSize().height)/864)
							.addComponent(textAreaContent, GroupLayout.PREFERRED_SIZE, (41*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(buttonCancel, GroupLayout.PREFERRED_SIZE, (18*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonExecute, GroupLayout.PREFERRED_SIZE, (18*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
	
	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean value) {
		confirm = value;
	}
	
	public void setTextContent(String text) {
		textAreaContent.setText(text);
	}
	
}