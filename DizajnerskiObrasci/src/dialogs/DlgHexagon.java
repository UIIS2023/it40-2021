package dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import geometry.HexagonAdapter;
import geometry.Point;

public class DlgHexagon extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldRadius;
	private JPanel buttonBorderColor;
	private JPanel buttonInnerColor;
	private Color newBorderColor;
	private Color newInnerColor;
	private boolean confirm;
	private HexagonAdapter newHexagon;
	private HexagonAdapter selectedHexagon;
	private HexagonAdapter modifiedHexagon;
	
	public DlgHexagon(Color defaultBorderColor, Color defaultInnerColor) {
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setBounds((433*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (668*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (60*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		getContentPane().add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(50, 50, 50));
		panel.setLayout(null);
		this.newBorderColor = defaultBorderColor;
		this.newInnerColor = defaultInnerColor;
		
		JLabel labelCenter = new JLabel("center");
		labelCenter.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(10 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 10, (float)(7 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 7)))));
		labelCenter.setHorizontalAlignment(SwingConstants.CENTER);
		labelCenter.setForeground(new Color(214, 214, 214));
		labelCenter.setBounds((10*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (7*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (104*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (13*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelCenter);
		
		JLabel labelX = new JLabel("X:");
		labelX.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 0.1, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelX.setHorizontalAlignment(SwingConstants.RIGHT);
		labelX.setForeground(new Color(214, 214, 214));
		labelX.setBounds((0*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (23*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (47*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelX);
		
		textFieldX = new JTextField();
		textFieldX.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldX.setForeground(new Color(214, 214, 214));
		textFieldX.setBackground(new Color(25, 25, 25));
		textFieldX.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(26 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 26, (float)(29 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 29)))));
		textFieldX.setBounds((26*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		textFieldX.setBorder(null);
		panel.add(textFieldX);
		
		JLabel labelY = new JLabel("Y:");
		labelY.setHorizontalAlignment(SwingConstants.RIGHT);
		labelY.setForeground(new Color(214, 214, 214));
		labelY.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(60 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 60, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelY.setBounds((60*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (23*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (47*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelY);
		
		textFieldY = new JTextField();
		textFieldY.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldY.setForeground(new Color(214, 214, 214));
		textFieldY.setColumns(10);
		textFieldY.setBorder(null);
		textFieldY.setBackground(new Color(25, 25, 25));
		textFieldY.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(84 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 84, (float)(29 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 29)))));
		textFieldY.setBounds((84*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(textFieldY);
		
		JLabel labelRadius = new JLabel("radius:");
		labelRadius.setHorizontalAlignment(SwingConstants.CENTER);
		labelRadius.setForeground(new Color(214, 214, 214));
		labelRadius.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(148 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 148, (float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 0.1)))));
		labelRadius.setBounds((148*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (0*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (60*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelRadius);
		
		textFieldRadius = new JTextField();
		textFieldRadius.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRadius.setForeground(new Color(214, 214, 214));
		textFieldRadius.setColumns(10);
		textFieldRadius.setBorder(null);
		textFieldRadius.setBackground(new Color(25, 25, 25));
		textFieldRadius.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(195 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 195, (float)(22 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 22)))));
		textFieldRadius.setBounds((195*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (22*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(textFieldRadius);
		
		JLabel labelColors = new JLabel("colors");
		labelColors.setHorizontalAlignment(SwingConstants.CENTER);
		labelColors.setForeground(new Color(214, 214, 214));
		labelColors.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(266 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 266, (float)(7 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 7)))));
		labelColors.setBounds((266*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (7*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (159*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (13*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelColors);
		
		JLabel labelBorder = new JLabel("border:");
		labelBorder.setHorizontalAlignment(SwingConstants.RIGHT);
		labelBorder.setForeground(new Color(214, 214, 214));
		labelBorder.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(259 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 259, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelBorder.setBounds((259*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelBorder);
		
		buttonBorderColor = new JPanel();
		buttonBorderColor.setBackground(defaultBorderColor);
		buttonBorderColor.setBounds((310*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color tempColor = JColorChooser.showDialog(null, "Choose the border color of the hexagon", defaultBorderColor);
				if(tempColor != null) {
					newBorderColor = tempColor;
				}
				buttonBorderColor.setBackground(newBorderColor);
			}
		});
		panel.add(buttonBorderColor);
		
		JLabel labelInner = new JLabel("inner:");
		labelInner.setHorizontalAlignment(SwingConstants.RIGHT);
		labelInner.setForeground(new Color(214, 214, 214));
		labelInner.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(343 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 343, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelInner.setBounds((343*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelInner);
		
		buttonInnerColor = new JPanel();
		buttonInnerColor.setBackground(defaultInnerColor);
		buttonInnerColor.setBounds((393*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color tempColor = JColorChooser.showDialog(null, "Choose the inner color of the hexagon", defaultInnerColor);
				if(tempColor != null) {
					newInnerColor = tempColor;
				}
				buttonInnerColor.setBackground(newInnerColor);
			}
		});
		panel.add(buttonInnerColor);
		
		JButton buttonOk = new JButton("Ok");
		buttonOk.setForeground(new Color(214, 214, 214));
		buttonOk.setContentAreaFilled(false);
		buttonOk.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(521 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 521, (float)(21 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 21)))));
		buttonOk.setBounds((521*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (63*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonOk.setBorder(new LineBorder(new Color(214, 214, 214), 1, true));
		buttonOk.setFocusPainted(false);
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textFieldX.getText().isEmpty() || textFieldY.getText().isEmpty() || textFieldRadius.getText().isEmpty()) {
						throw new Exception("Please fill in all fields!");
					}
					else {
						short radius = Short.parseShort(textFieldRadius.getText());
						Point center = new Point(Short.parseShort(textFieldX.getText()), Short.parseShort(textFieldY.getText()));
						if (selectedHexagon == null) {
							newHexagon = new HexagonAdapter(center, radius, false, newBorderColor, newInnerColor);
						} else {
							modifiedHexagon = new HexagonAdapter(center, radius, selectedHexagon.isSelected(), newBorderColor, newInnerColor);
						}
						confirm = true;
						setVisible(false);
						dispose();
					}
				} catch(NumberFormatException ex) {
					DlgException dialogException = new DlgException("The input type is not in the correct format!");
					dialogException.setVisible(true);
					dialogException = null;
				} catch(Exception ex) {
					DlgException dialogException = new DlgException(ex.getMessage());
					dialogException.setVisible(true);
					dialogException = null;
				}
			}
		});
		panel.add(buttonOk);
		
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.setForeground(new Color(214, 214, 214));
		buttonCancel.setContentAreaFilled(false);
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(594 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 594, (float)(21 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 21)))));
		buttonCancel.setBounds((594*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (63*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonCancel.setBorder(new LineBorder(new Color(214, 214, 214), 1, true));
		buttonCancel.setFocusPainted(false);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		panel.add(buttonCancel);
	}
	
	public boolean isConfirm() {
		return confirm;
	}
	
	public HexagonAdapter getNewHexagon() {
		return newHexagon;
	}
	
	public HexagonAdapter getModifiedHexagon() {
		return modifiedHexagon;
	}
	
	public void createNewHexagon(MouseEvent e) {
		textFieldX.setEditable(false);
		textFieldY.setEditable(false);
		textFieldX.setText(Integer.toString(e.getX()));
		textFieldY.setText(Integer.toString(e.getY()));
	}
	
	public void setSelectedHexagon(HexagonAdapter selectedHexagon) {
		this.selectedHexagon = selectedHexagon;
		textFieldX.setText(Short.toString((short)selectedHexagon.getCenter().getX()));
		textFieldY.setText(Short.toString((short)selectedHexagon.getCenter().getY()));
		textFieldRadius.setText(Short.toString((short)selectedHexagon.getRadius()));
		newBorderColor = selectedHexagon.getBorderColor();
		newInnerColor = selectedHexagon.getInnerColor();
		buttonBorderColor.setBackground(newBorderColor);
		buttonInnerColor.setBackground(newInnerColor);
	}
	
}