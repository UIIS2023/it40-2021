package mvc.views;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import mvc.controllers.PanelToolController;

public class PanelToolView extends JPanel {

	private static final long serialVersionUID = 1L;
	private PanelToolController controller;
	private JButton[] buttonsUndoRedo;
	private JButton[] buttonsGeometry;
	private JButton[] buttonsZAxis;
	private String valueProperty;
	private String selectedProperty;
	private String enabledProperty;
	private String[] buttonsUndoRedoValues = {"Undo", "Redo"};
	private String[] buttonsGeometryValues = {"Point", "Line", "Rectangle", "Circle", "Donut", "Hexagon", "Select Shapes"};
	private String[] buttonsZAxisValues = {"To Front", "To Back", "Bring To Front", "Bring To Back"};
	
	public PanelToolView() {
		buttonsUndoRedo = new JButton[2];
		buttonsGeometry = new JButton[7];
		buttonsZAxis = new JButton[4];
		valueProperty = "value";
		selectedProperty = "selected";
		enabledProperty = "enabled";
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setBackground(new Color(50, 50, 50));
		setSize((74*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (811*Toolkit.getDefaultToolkit().getScreenSize().height)/864);
		
		ImageIcon iconFileDefault = new ImageIcon("res/icons/panelTool/file.png");
		Icon iconFile = new ImageIcon(iconFileDefault.getImage().getScaledInstance((iconFileDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconFileDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconPointDefault = new ImageIcon("res/icons/panelTool/geometry/point.png");
		Icon iconPoint = new ImageIcon(iconPointDefault.getImage().getScaledInstance((iconPointDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconPointDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconLineDefault = new ImageIcon("res/icons/panelTool/geometry/line.png");
		Icon iconLine = new ImageIcon(iconLineDefault.getImage().getScaledInstance((iconLineDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconLineDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconRectangleDefault = new ImageIcon("res/icons/panelTool/geometry/rectangle.png");
		Icon iconRectangle = new ImageIcon(iconRectangleDefault.getImage().getScaledInstance((iconRectangleDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconRectangleDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconCircleDefault = new ImageIcon("res/icons/panelTool/geometry/circle.png");
		Icon iconCircle = new ImageIcon(iconCircleDefault.getImage().getScaledInstance((iconCircleDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconCircleDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconDonutDefault = new ImageIcon("res/icons/panelTool/geometry/donut.png");
		Icon iconDonut = new ImageIcon(iconDonutDefault.getImage().getScaledInstance((iconDonutDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconDonutDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconHexagonDefault = new ImageIcon("res/icons/panelTool/geometry/hexagon.png");
		Icon iconHexagon = new ImageIcon(iconHexagonDefault.getImage().getScaledInstance((iconHexagonDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconHexagonDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconSelectDefault = new ImageIcon("res/icons/panelTool/select.png");
		Icon iconSelect = new ImageIcon(iconSelectDefault.getImage().getScaledInstance((iconSelectDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconSelectDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconDeleteAllDefault = new ImageIcon("res/icons/panelTool/deleteAll.png");
		Icon iconDeleteAll = new ImageIcon(iconDeleteAllDefault.getImage().getScaledInstance((iconDeleteAllDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconDeleteAllDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		
		JButton buttonFile = new JButton(iconFile);
		JPanel panelBreak_1 = new JPanel();
		JButton buttonUndo = new JButton();
		buttonsUndoRedo[0] = buttonUndo;
		JButton buttonRedo = new JButton();
		buttonsUndoRedo[1] = buttonRedo;
		JPanel panelBreak_2 = new JPanel();
		JButton buttonPoint = new JButton(iconPoint);
		buttonsGeometry[0] = buttonPoint;
		JButton buttonLine = new JButton(iconLine);
		buttonsGeometry[1] = buttonLine;
		JButton buttonRectangle = new JButton(iconRectangle);
		buttonsGeometry[2] = buttonRectangle;
		JButton buttonCircle = new JButton(iconCircle);
		buttonsGeometry[3] = buttonCircle;
		JButton buttonDonut = new JButton(iconDonut);
		buttonsGeometry[4] = buttonDonut;
		JButton buttonHexagon = new JButton(iconHexagon);
		buttonsGeometry[5] = buttonHexagon;
		JPanel panelBreak_3 = new JPanel();
		JButton buttonSelect = new JButton(iconSelect);
		buttonsGeometry[6] = buttonSelect;
		JButton buttonDeleteAll = new JButton(iconDeleteAll);
		buttonDeleteAll.putClientProperty(valueProperty, "Delete All Shapes");
		JPanel panelBreak_4 = new JPanel();
		JButton buttonToFront = new JButton();
		buttonsZAxis[0] = buttonToFront;
		JButton buttonToBack = new JButton();
		buttonsZAxis[1] = buttonToBack;
		JButton buttonBringToFront = new JButton();
		buttonsZAxis[2] = buttonBringToFront;
		JButton buttonBringToBack = new JButton();
		buttonsZAxis[3] = buttonBringToBack;
		
		buttonFile.setBackground(new Color(50,50,50));
		buttonFile.setBorderPainted(false);
		buttonFile.setFocusPainted(false);
		buttonFile.putClientProperty(valueProperty, "File");
		buttonFile.putClientProperty(enabledProperty, true);
		buttonFile.putClientProperty(selectedProperty, false);
		buttonFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.buttonMouseEntered(buttonFile, valueProperty);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.buttonsMouseExited(buttonFile);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				controller.buttonsMousePressed(buttonFile);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.buttonsMouseClicked(buttonFile);
			}
		});
		buttonFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.buttonFileAction(buttonFile);
			}
		});
		for(int i = 0; i < buttonsUndoRedo.length; i++) {
			buttonsUndoRedo[i].putClientProperty(valueProperty, buttonsUndoRedoValues[i]);
			buttonsUndoRedo[i].putClientProperty(enabledProperty, false);
			buttonsUndoRedo[i].setBackground(new Color(50,50,50));
			buttonsUndoRedo[i].setBorderPainted(false);
			buttonsUndoRedo[i].setFocusPainted(false);
			final int buttonIndex = i;
			buttonsUndoRedo[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					controller.buttonMouseEntered(buttonsUndoRedo[buttonIndex], valueProperty);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					controller.buttonsMouseExited(buttonsUndoRedo[buttonIndex]);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					controller.buttonsMousePressed(buttonsUndoRedo[buttonIndex]);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					controller.buttonsMouseClicked(buttonsUndoRedo[buttonIndex]);
				}
			});
			buttonsUndoRedo[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.buttonsUndoRedoAction(buttonsUndoRedo[buttonIndex], valueProperty);
				}
			});
		}
		
		for(int i = 0; i < buttonsGeometry.length; i++) {
			buttonsGeometry[i].putClientProperty(valueProperty, buttonsGeometryValues[i]);
			buttonsGeometry[i].putClientProperty(selectedProperty, false);
			buttonsGeometry[i].putClientProperty(enabledProperty, true);
			buttonsGeometry[i].setBackground(new Color(50,50,50));
			buttonsGeometry[i].setBorderPainted(false);
			buttonsGeometry[i].setFocusPainted(false);
			final int buttonIndex = i;
			buttonsGeometry[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					controller.buttonMouseEntered(buttonsGeometry[buttonIndex], valueProperty);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					controller.buttonsGeometryMouseExited(buttonsGeometry[buttonIndex], selectedProperty);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					controller.buttonsMousePressed(buttonsGeometry[buttonIndex]);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					controller.buttonsMouseClicked(buttonsGeometry[buttonIndex]);
				}
			});
			buttonsGeometry[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.buttonsGeometryAction(buttonsGeometry[buttonIndex], valueProperty, selectedProperty);
				}
			});
		}
		buttonDeleteAll.setBackground(new Color(50,50,50));
		buttonDeleteAll.setBorderPainted(false);
		buttonDeleteAll.setFocusPainted(false);
		buttonDeleteAll.putClientProperty(enabledProperty, true);
		buttonDeleteAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.buttonMouseEntered(buttonDeleteAll, valueProperty);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.buttonsMouseExited(buttonDeleteAll);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				controller.buttonsMousePressed(buttonDeleteAll);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.buttonsMouseClicked(buttonDeleteAll);
			}
		});
		buttonDeleteAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.buttonDeleteAllAction(buttonDeleteAll);
			}
		});
		for(int i = 0; i < buttonsZAxis.length; i++) {
			buttonsZAxis[i].putClientProperty(valueProperty, buttonsZAxisValues[i]);
			buttonsZAxis[i].setBackground(new Color(50,50,50));
			buttonsZAxis[i].setBorderPainted(false);
			buttonsZAxis[i].setFocusPainted(false);
			final int buttonIndex = i;
			buttonsZAxis[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					controller.buttonMouseEntered(buttonsZAxis[buttonIndex], valueProperty);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					controller.buttonsMouseExited(buttonsZAxis[buttonIndex]);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					controller.buttonsMousePressed(buttonsZAxis[buttonIndex]);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					controller.buttonsMouseClicked(buttonsZAxis[buttonIndex]);
				}
			});
			buttonsZAxis[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.buttonsZAxisAction(buttonsZAxis[buttonIndex], valueProperty);
				}
			});
		}
		
		panelBreak_1.setBackground(new Color(25,25,25));
		panelBreak_2.setBackground(new Color(25,25,25));
		panelBreak_3.setBackground(new Color(25,25,25));
		panelBreak_4.setBackground(new Color(25,25,25));
		
		GroupLayout gl_panelToolBar = new GroupLayout(this);
		gl_panelToolBar.setHorizontalGroup(
			gl_panelToolBar.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelToolBar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelToolBar.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonFile, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBreak_1, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonUndo, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonRedo, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBreak_2, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPoint, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonLine, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonRectangle, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonCircle, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonDonut, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonHexagon, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBreak_3, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonSelect, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonDeleteAll, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBreak_4, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonToFront, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonToBack, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonBringToFront, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonBringToBack, GroupLayout.PREFERRED_SIZE, (42*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelToolBar.setVerticalGroup(
			gl_panelToolBar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelToolBar.createSequentialGroup()
					.addGap((10*Toolkit.getDefaultToolkit().getScreenSize().height)/864)
					.addComponent(buttonFile, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBreak_1, GroupLayout.PREFERRED_SIZE, (3*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonUndo, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonRedo, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBreak_2, GroupLayout.PREFERRED_SIZE, (3*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonPoint, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonLine, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonRectangle, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonCircle, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonDonut, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonHexagon, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBreak_3, GroupLayout.PREFERRED_SIZE, (3*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonSelect, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonDeleteAll, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBreak_4, GroupLayout.PREFERRED_SIZE, (3*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonToFront, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonToBack, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonBringToFront, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonBringToBack, GroupLayout.PREFERRED_SIZE, (46*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED))
		);
		setLayout(gl_panelToolBar);
	}
	
	public void setController(PanelToolController ptc) {
		controller = ptc;
	}
	
	public String getValueProperty() {
		return valueProperty;
	}
	
	public String getSelectedProperty() {
		return selectedProperty;
	}
	
	public String getEnabledProperty() {
		return enabledProperty;
	}
	
	public JButton[] getButtonsUndoRedo() {
		return buttonsUndoRedo;
	}
	
	public String[] getButtonsGeometryValues() {
		return buttonsGeometryValues;
	}
	
	public String[] getButtonsZAxisValues() {
		return buttonsZAxisValues;
	}
	
	public String[] getButtonsUndoRedoValues() {
		return buttonsUndoRedoValues;
	}
	
	public JButton[] getButtonsGeometry() {
		return buttonsGeometry;
	}
	
	public JButton[] getButtonsZAxis() {
		return buttonsZAxis;
	}
	
}