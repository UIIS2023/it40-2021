package dialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import geometry.Line;
import geometry.Point;
import javax.swing.JButton;
import javax.swing.JColorChooser;

public class DlgLine extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JTextField textFieldStartPointX;
	private JTextField textFieldStartPointY;
	private JTextField textFieldEndPointX;
	private JTextField textFieldEndPointY;
	private JPanel buttonBorderColor;
	private Color newBorderColor;
	private boolean confirm;
	private Line newLine;
	private Line selectedLine;
	private Line modifiedLine;
	
	public DlgLine(Color defaultBorderColor) {
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setBounds((460*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (613*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (60*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		getContentPane().add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(50, 50, 50));
		panel.setLayout(null);
		this.newBorderColor = defaultBorderColor;
		
		JLabel labelStartPoint = new JLabel("start point");
		labelStartPoint.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(15 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 15, (float)(7 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 7)))));
		labelStartPoint.setHorizontalAlignment(SwingConstants.CENTER);
		labelStartPoint.setForeground(new Color(214, 214, 214));
		labelStartPoint.setBounds((15*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (7*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (104*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (13*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelStartPoint);
		
		JLabel labelStartPointX = new JLabel("X:");
		labelStartPointX.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 0.1, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelStartPointX.setHorizontalAlignment(SwingConstants.RIGHT);
		labelStartPointX.setForeground(new Color(214, 214, 214));
		labelStartPointX.setBounds((0*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (23*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (47*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelStartPointX);
		
		textFieldStartPointX = new JTextField();
		textFieldStartPointX.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldStartPointX.setForeground(new Color(214, 214, 214));
		textFieldStartPointX.setBackground(new Color(25, 25, 25));
		textFieldStartPointX.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(26 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 26, (float)(29 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 29)))));
		textFieldStartPointX.setBounds((26*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		textFieldStartPointX.setBorder(null);
		panel.add(textFieldStartPointX);
		textFieldStartPointX.setColumns(10);
		
		JLabel labelStartPointY = new JLabel("Y:");
		labelStartPointY.setHorizontalAlignment(SwingConstants.RIGHT);
		labelStartPointY.setForeground(new Color(214, 214, 214));
		labelStartPointY.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(60 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 60, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelStartPointY.setBounds((60*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (23*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (47*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelStartPointY);
		
		textFieldStartPointY = new JTextField();
		textFieldStartPointY.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldStartPointY.setForeground(new Color(214, 214, 214));
		textFieldStartPointY.setColumns(10);
		textFieldStartPointY.setBorder(null);
		textFieldStartPointY.setBackground(new Color(25, 25, 25));
		textFieldStartPointY.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(84 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 84, (float)(29 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 29)))));
		textFieldStartPointY.setBounds((84*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(textFieldStartPointY);
		
		JLabel labelEndPoint = new JLabel("end point");
		labelEndPoint.setHorizontalAlignment(SwingConstants.CENTER);
		labelEndPoint.setForeground(new Color(214, 214, 214));
		labelEndPoint.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(159 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 159, (float)(7 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 7)))));
		labelEndPoint.setBounds((159*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (7*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (104*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (13*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelEndPoint);
		
		JLabel labelEndPointX = new JLabel("X:");
		labelEndPointX.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEndPointX.setForeground(new Color(214, 214, 214));
		labelEndPointX.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(143 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 143, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelEndPointX.setBounds((143*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (23*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (47*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelEndPointX);
		
		textFieldEndPointX = new JTextField();
		textFieldEndPointX.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEndPointX.setForeground(new Color(214, 214, 214));
		textFieldEndPointX.setColumns(10);
		textFieldEndPointX.setBorder(null);
		textFieldEndPointX.setBackground(new Color(25, 25, 25));
		textFieldEndPointX.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(169 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 169, (float)(29 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 29)))));
		textFieldEndPointX.setBounds((169*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(textFieldEndPointX);
		
		JLabel labelEndPointY = new JLabel("Y:");
		labelEndPointY.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEndPointY.setForeground(new Color(214, 214, 214));
		labelEndPointY.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(203 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 203, (float)(14 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 14)))));
		labelEndPointY.setBounds((203*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (14*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (23*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (47*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelEndPointY);
		
		textFieldEndPointY = new JTextField();
		textFieldEndPointY.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEndPointY.setForeground(new Color(214, 214, 214));
		textFieldEndPointY.setColumns(10);
		textFieldEndPointY.setBorder(null);
		textFieldEndPointY.setBackground(new Color(25, 25, 25));
		textFieldEndPointY.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * (Math.min((float)(227 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 227, (float)(29 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 29)))));
		textFieldEndPointY.setBounds((227*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (29*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(textFieldEndPointY);
		
		JLabel labelBorderColor = new JLabel("color:");
		labelBorderColor.setHorizontalAlignment(SwingConstants.RIGHT);
		labelBorderColor.setForeground(new Color(214, 214, 214));
		labelBorderColor.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * (Math.min((float)(282 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 282, (float)(0.1 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 0.1)))));
		labelBorderColor.setBounds((282*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (0*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (48*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (60*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		panel.add(labelBorderColor);
		
		buttonBorderColor = new JPanel();
		buttonBorderColor.setBackground(defaultBorderColor);
		buttonBorderColor.setBounds((333*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (22*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (30*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (19*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color tempColor = JColorChooser.showDialog(null, "Choose the color of the line", defaultBorderColor);
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
		buttonOk.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(465 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 465, (float)(21 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 21)))));
		buttonOk.setBounds((465*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (63*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		buttonOk.setBorder(new LineBorder(new Color(214, 214, 214), 1, true));
		buttonOk.setFocusPainted(false);
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Point startPoint = new Point(Short.parseShort(textFieldStartPointX.getText()), Short.parseShort(textFieldStartPointY.getText()));
					Point endPoint = new Point(Short.parseShort(textFieldEndPointX.getText()), Short.parseShort(textFieldEndPointY.getText()));
					if (selectedLine == null) {
						newLine = new Line(startPoint, endPoint, false, newBorderColor);
					} else {
						if (textFieldStartPointX.getText().isEmpty() || textFieldStartPointY.getText().isEmpty() || textFieldEndPointX.getText().isEmpty() || textFieldEndPointY.getText().isEmpty()) {
							throw new Exception("Please fill in all fields!");
						}
						else {
							modifiedLine = new Line(startPoint, endPoint, selectedLine.isSelected(), newBorderColor);
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
		buttonCancel.setFont(new Font("Tahoma", Font.BOLD, (int)(9 * (Math.min((float)(539 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 539, (float)(21 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 21)))));
		buttonCancel.setBounds((539*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864, (63*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (21*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
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
	
	public Line getNewLine() {
		return newLine;
	}
	
	public Line getModifiedLine() {
		return modifiedLine;
	}
	
	public void createNewLine(Point startPoint, MouseEvent e) {
		textFieldStartPointX.setEditable(false);
		textFieldStartPointY.setEditable(false);
		textFieldEndPointX.setEditable(false);
		textFieldEndPointY.setEditable(false);
		textFieldStartPointX.setText(Integer.toString(startPoint.getX()));
		textFieldStartPointY.setText(Integer.toString(startPoint.getY()));
		textFieldEndPointX.setText(Integer.toString(e.getX()));
		textFieldEndPointY.setText(Integer.toString(e.getY()));
	}
	
	public void setSelectedLine(Line selectedLine) {
		this.selectedLine = selectedLine;
		textFieldStartPointX.setText(Short.toString(selectedLine.getStartPoint().getX()));
		textFieldStartPointY.setText(Short.toString(selectedLine.getStartPoint().getY()));
		textFieldEndPointX.setText(Short.toString(selectedLine.getEndPoint().getX()));
		textFieldEndPointY.setText(Short.toString(selectedLine.getEndPoint().getY()));
		newBorderColor = selectedLine.getBorderColor();
		buttonBorderColor.setBackground(newBorderColor);
	}
	
}