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
import geometry.Point;
import geometry.Rectangle;

public class DlgRectangle extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;
	private JPanel buttonBorderColor;
	private JPanel buttonInnerColor;
	private Color newBorderColor;
	private Color newInnerColor;
	private boolean confirm;
	private Rectangle newRectangle;
	private Rectangle selectedRectangle;
	private Rectangle modifiedRectangle;
	
	public DlgRectangle(Color defaultBorderColor, Color defaultInnerColor) {
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setBounds((390*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (755*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (60*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		getContentPane().add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(50, 50, 50));
		panel.setLayout(null);
		this.newBorderColor = defaultBorderColor;
		this.newInnerColor = defaultInnerColor;
		
		JLabel labelUpperLeftPoint = new JLabel("upper left point");
		labelUpperLeftPoint.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(10 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 10, (float)(7 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 7)))));
		labelUpperLeftPoint.setHorizontalAlignment(SwingConstants.CENTER);
		labelUpperLeftPoint.setForeground(new Color(214, 214, 214));
		labelUpperLeftPoint.setBounds((10*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (7*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (104*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (13*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelUpperLeftPoint);
		
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
		textFieldX.setColumns(10);
		
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
		
		JLabel labelWidth = new JLabel("width:");
		labelWidth.setHorizontalAlignment(SwingConstants.CENTER);
		labelWidth.setForeground(new Color(214, 214, 214));
		labelWidth.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(150 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 150, (float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 0.1)))));
		labelWidth.setBounds((150*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (0*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (60*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelWidth);
		
		textFieldWidth = new JTextField();
		textFieldWidth.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldWidth.setForeground(new Color(214, 214, 214));
		textFieldWidth.setColumns(10);
		textFieldWidth.setBorder(null);
		textFieldWidth.setBackground(new Color(25, 25, 25));
		textFieldWidth.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(194 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 194, (float)(22 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 22)))));
		textFieldWidth.setBounds((194*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (22*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(textFieldWidth);
		
		JLabel labelHeight = new JLabel("height:");
		labelHeight.setHorizontalAlignment(SwingConstants.CENTER);
		labelHeight.setForeground(new Color(214, 214, 214));
		labelHeight.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(234 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 234, (float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 0.1)))));
		labelHeight.setBounds((234*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (0*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (60*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelHeight);
		
		textFieldHeight = new JTextField();
		textFieldHeight.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldHeight.setForeground(new Color(214, 214, 214));
		textFieldHeight.setColumns(10);
		textFieldHeight.setBorder(null);
		textFieldHeight.setBackground(new Color(25, 25, 25));
		textFieldHeight.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(280 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 280, (float)(22 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 22)))));
		textFieldHeight.setBounds((280*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (22*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(textFieldHeight);
		
		JLabel labelColors = new JLabel("colors");
		labelColors.setHorizontalAlignment(SwingConstants.CENTER);
		labelColors.setForeground(new Color(214, 214, 214));
		labelColors.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(352 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 352, (float)(7 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 7)))));
		labelColors.setBounds((352*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (7*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (159*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (13*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelColors);
		
		JLabel labelBorder = new JLabel("border:");
		labelBorder.setHorizontalAlignment(SwingConstants.RIGHT);
		labelBorder.setForeground(new Color(214, 214, 214));
		labelBorder.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(345 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 345, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelBorder.setBounds((345*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelBorder);
		
		buttonBorderColor = new JPanel();
		buttonBorderColor.setBackground(defaultBorderColor);
		buttonBorderColor.setBounds((396*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color tempColor = JColorChooser.showDialog(null, "Choose the border color of the rectangle", defaultBorderColor);
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
		labelInner.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(429 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 429, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelInner.setBounds((429*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelInner);
		
		buttonInnerColor = new JPanel();
		buttonInnerColor.setBackground(defaultInnerColor);
		buttonInnerColor.setBounds((479*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color tempColor = JColorChooser.showDialog(null, "Choose the inner color of the rectangle", defaultInnerColor);
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
		buttonOk.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(609 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 609, (float)(21 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 21)))));
		buttonOk.setBounds((609*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (63*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonOk.setBorder(new LineBorder(new Color(214, 214, 214), 1, true));
		buttonOk.setFocusPainted(false);
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textFieldX.getText().isEmpty() || textFieldY.getText().isEmpty() || textFieldWidth.getText().isEmpty() || textFieldHeight.getText().isEmpty()) {
						throw new Exception("Please fill in all fields!");
					}
					else {
						short width = Short.parseShort(textFieldWidth.getText());
						short height = Short.parseShort(textFieldHeight.getText());
						Point upperLeftPoint = new Point(Short.parseShort(textFieldX.getText()), Short.parseShort(textFieldY.getText()));
						if (selectedRectangle == null) {
							newRectangle = new Rectangle(upperLeftPoint, width, height, false, newBorderColor, newInnerColor);
						} else {
							modifiedRectangle = new Rectangle(upperLeftPoint, width, height, selectedRectangle.isSelected(), newBorderColor, newInnerColor);
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
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(682 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 682, (float)(21 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 21)))));
		buttonCancel.setBounds((682*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (63*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
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
	
	public Rectangle getNewRectangle() {
		return newRectangle;
	}
	
	public Rectangle getModifiedRectangle() {
		return modifiedRectangle;
	}
	
	public void createNewRectangle(MouseEvent e) {
		textFieldX.setEditable(false);
		textFieldY.setEditable(false);
		textFieldX.setText(Integer.toString(e.getX()));
		textFieldY.setText(Integer.toString(e.getY()));
	}
	
	public void setSelectedRectangle(Rectangle selectedRectangle) {
		this.selectedRectangle = selectedRectangle;
		textFieldX.setText(Short.toString(selectedRectangle.getUpperLeftPoint().getX()));
		textFieldY.setText(Short.toString(selectedRectangle.getUpperLeftPoint().getY()));
		textFieldWidth.setText(Short.toString(selectedRectangle.getWidth()));
		textFieldHeight.setText(Short.toString(selectedRectangle.getHeight()));
		newBorderColor = selectedRectangle.getBorderColor();
		newInnerColor = selectedRectangle.getInnerColor();
		buttonBorderColor.setBackground(newBorderColor);
		buttonInnerColor.setBackground(newInnerColor);
	}
	
}