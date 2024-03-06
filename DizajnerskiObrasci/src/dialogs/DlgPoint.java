package dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
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
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class DlgPoint extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JPanel buttonBorderColor;
	private Color newBorderColor;
	private boolean confirm;
	private Point newPoint;
	private Point modifiedPoint;
	private Point selectedPoint;
	
	public DlgPoint(Color defaultBorderColor) {
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setBounds((537*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (461*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (48*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		getContentPane().add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(50, 50, 50));
		panel.setLayout(null);
		this.newBorderColor = defaultBorderColor;
		
		JLabel labelX = new JLabel("X:");
		labelX.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 0.1, (float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 0.1)))));
		labelX.setHorizontalAlignment(SwingConstants.RIGHT);
		labelX.setForeground(new Color(214, 214, 214));
		labelX.setBounds((0*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (0*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (23*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (51*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelX);
		
		textFieldX = new JTextField();
		textFieldX.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldX.setForeground(new Color(214, 214, 214));
		textFieldX.setBackground(new Color(25, 25, 25));
		textFieldX.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(26 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 26, (float)(16 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 16)))));
		textFieldX.setBounds((26*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (16*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		textFieldX.setBorder(null);
		panel.add(textFieldX);
		textFieldX.setColumns(10);
		
		JLabel labelY = new JLabel("Y:");
		labelY.setHorizontalAlignment(SwingConstants.RIGHT);
		labelY.setForeground(new Color(214, 214, 214));
		labelY.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(55 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 55, (float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 0.1)))));
		labelY.setBounds((55*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (0*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (23*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (51*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelY);
		
		textFieldY = new JTextField();
		textFieldY.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldY.setForeground(new Color(214, 214, 214));
		textFieldY.setColumns(10);
		textFieldY.setBorder(null);
		textFieldY.setBackground(new Color(25, 25, 25));
		textFieldY.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(80 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 80, (float)(16 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 16)))));
		textFieldY.setBounds((80*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (16*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(textFieldY);
		
		JLabel labelBorderColor = new JLabel("color:");
		labelBorderColor.setHorizontalAlignment(SwingConstants.RIGHT);
		labelBorderColor.setForeground(new Color(214, 214, 214));
		labelBorderColor.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(135 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 135, (float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 0.1)))));
		labelBorderColor.setBounds((135*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (0*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (51*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelBorderColor);
		
		buttonBorderColor = new JPanel();
		buttonBorderColor.setBackground(defaultBorderColor);
		buttonBorderColor.setBounds((186*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (16*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color tempColor = JColorChooser.showDialog(null, "Choose the color of the point", defaultBorderColor);
				if(tempColor != null) {
					newBorderColor = tempColor;
				}
				buttonBorderColor.setBackground(newBorderColor);
			}
		});
		panel.add(buttonBorderColor);
		
		JButton buttonOk = new JButton("Ok");
		buttonOk.setForeground(new Color(214, 214, 214));
		buttonOk.setContentAreaFilled(false);
		buttonOk.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(315 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 315, (float)(16 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 16)))));
		buttonOk.setBounds((315*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (16*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (63*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonOk.setBorder(new LineBorder(new Color(214, 214, 214), 1, true));
		buttonOk.setFocusPainted(false);
		 
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (selectedPoint == null) {
						newPoint = new Point(Short.parseShort(textFieldX.getText()), Short.parseShort(textFieldY.getText()), false, newBorderColor);
					} else {
						if(textFieldX.getText().isEmpty() || textFieldY.getText().isEmpty()) {
							throw new Exception("Please fill in all fields!");
						} else {
							modifiedPoint = new Point(Short.parseShort(textFieldX.getText()), Short.parseShort(textFieldY.getText()), selectedPoint.isSelected(), newBorderColor);
						}
					}
					confirm = true;
					setVisible(false);
					dispose();
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
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(388 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 388, (float)(16 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 16)))));
		buttonCancel.setBounds((388*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (16*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (63*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
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
	
	public Point getNewPoint() {
		return newPoint;
	}
	
	public Point getModifiedPoint() {
		return modifiedPoint;
	}
	
	public void createNewPoint(MouseEvent e) {
		textFieldX.setEditable(false);
		textFieldY.setEditable(false);
		textFieldX.setText(Integer.toString(e.getX()));
		textFieldY.setText(Integer.toString(e.getY()));
	}
	
	public void setSelectedPoint(Point selectedPoint) {
		this.selectedPoint = selectedPoint;
		textFieldX.setText(Short.toString(selectedPoint.getX()));
		textFieldY.setText(Short.toString(selectedPoint.getY()));
		newBorderColor = selectedPoint.getBorderColor();
		buttonBorderColor.setBackground(newBorderColor);
	}
	
}