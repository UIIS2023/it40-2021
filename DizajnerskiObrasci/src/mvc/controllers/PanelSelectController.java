package mvc.controllers;

import mvc.models.TextModel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import commands.ModifyCircleCmd;
import commands.ModifyDonutCmd;
import commands.ModifyHexagonCmd;
import commands.ModifyLineCmd;
import commands.ModifyPointCmd;
import commands.ModifyRectangleCmd;
import commands.RemoveSelectedShapesCmd;
import dialogs.DlgCircle;
import dialogs.DlgConfirm;
import dialogs.DlgDonut;
import dialogs.DlgHexagon;
import dialogs.DlgLine;
import dialogs.DlgPoint;
import dialogs.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import mvc.models.ColorModel;
import mvc.models.ShapesModel;
import mvc.views.FrameView;
import mvc.views.PanelSelectView;
import supports.FrameSupport;
import supports.PanelLogSupport;
import supports.PanelSelectSupport;
import supports.PanelToolSupport;

public class PanelSelectController implements PropertyChangeListener {

	private FrameSupport frameSupport;
	private PanelLogSupport panelLogSupport;
	private PanelToolSupport panelToolSupport;
	private PanelSelectSupport panelSelectSupport;
	private ColorModel colorModel;
	private TextModel textModel;
	private ShapesModel shapesModel;
	private PanelSelectView view;
	private String enabledProperty;
	private Icon iconModify;
	private Icon iconModifyDisabled;
	
	public PanelSelectController(FrameSupport frameSupport, ColorModel colorModel, TextModel textModel, ShapesModel shapesModel, FrameView frameView) {
		this.frameSupport = frameSupport;
		this.panelLogSupport = frameSupport.getPanelLogSupport();
		this.panelToolSupport = frameSupport.getPanelToolSupport();
		this.panelSelectSupport = frameSupport.getPanelSelectSupport();
		this.panelSelectSupport.addPropertyChangeListener(this);
		this.panelToolSupport.addPropertyChangeListener(this);
		this.colorModel = colorModel;
		this.textModel = textModel;
		this.shapesModel = shapesModel;
		view = frameView.getPanelSelectView();
		view.setVisible(false);
		ImageIcon iconModifyDefault = new ImageIcon("res/icons/panelSelect/modify.png");
		iconModify = new ImageIcon(iconModifyDefault.getImage().getScaledInstance((iconModifyDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconModifyDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconModifyDisabledDefault = new ImageIcon("res/icons/panelSelect/modifyDisabled.png");
		iconModifyDisabled = new ImageIcon(iconModifyDisabledDefault.getImage().getScaledInstance((iconModifyDisabledDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconModifyDisabledDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
	}
	
	public void mouseEntered(JButton activeButton, String newState) {
		if ((activeButton.getClientProperty(enabledProperty) == null) || (Boolean)activeButton.getClientProperty(enabledProperty)) {
			activeButton.setContentAreaFilled(true);
			activeButton.setBackground(new Color(25,25,25));
		}
		textModel.setCenterText(newState);
	}
	
	public void mouseExited(JButton activeButton) {
		if ((activeButton.getClientProperty(enabledProperty) == null) || (Boolean)activeButton.getClientProperty(enabledProperty)) {
			activeButton.setBackground(new Color(50,50,50));
		}
		textModel.setCenterText(null);
	}
	
	public void mousePressed(JButton activeButton) {
		activeButton.setContentAreaFilled(false);
	}
	
	public void mouseClicked(JButton activeButton) {
		activeButton.setContentAreaFilled(true);
	}
	
	public void buttonModifyAction() {
		if ((Boolean)view.getButtonModify().getClientProperty(enabledProperty)) {
			if(shapesModel.getSelectedShapes().getFirst() instanceof Point) {
				Point selectedPoint = (Point)shapesModel.getSelectedShapes().getFirst();
				DlgPoint dialogPoint = new DlgPoint(colorModel.getDefaultBorderColor());
				textModel.setCenterText("Modify Point");
				dialogPoint.setSelectedPoint(selectedPoint);
				dialogPoint.setVisible(true);
				textModel.setCenterText(null);
				if(dialogPoint.isConfirm()) {
					ModifyPointCmd modifyPointCommand = new ModifyPointCmd(shapesModel, dialogPoint.getModifiedPoint());
					modifyPointCommand.execute();
					panelToolSupport.pushToUndoStack(modifyPointCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyPointCommand.toString());
				}
				dialogPoint = null;
				selectedPoint = null;
			}
			else if(shapesModel.getSelectedShapes().getFirst() instanceof Line) {
				Line selectedLine = (Line)shapesModel.getSelectedShapes().getFirst();
				DlgLine dialogLine = new DlgLine(colorModel.getDefaultBorderColor());
				textModel.setCenterText("Modify Line");
				dialogLine.setSelectedLine(selectedLine);
				dialogLine.setVisible(true);
				textModel.setCenterText(null);
				if(dialogLine.isConfirm()) {
					ModifyLineCmd modifyLineCommand = new ModifyLineCmd(shapesModel, dialogLine.getModifiedLine());
					modifyLineCommand.execute();
					panelToolSupport.pushToUndoStack(modifyLineCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyLineCommand.toString());
				}
				dialogLine = null;
				selectedLine = null;
			}
			else if(shapesModel.getSelectedShapes().getFirst() instanceof Rectangle) {
				Rectangle selectedRectangle = (Rectangle)shapesModel.getSelectedShapes().getFirst();
				DlgRectangle dialogRectangle = new DlgRectangle(colorModel.getDefaultBorderColor(), colorModel.getDefaultInnerColor());
				textModel.setCenterText("Modify Rectangle");
				dialogRectangle.setSelectedRectangle(selectedRectangle);
				dialogRectangle.setVisible(true);
				textModel.setCenterText(null);
				if(dialogRectangle.isConfirm()) {
					ModifyRectangleCmd modifyRectangleCommand = new ModifyRectangleCmd(shapesModel, dialogRectangle.getModifiedRectangle());
					modifyRectangleCommand.execute();
					panelToolSupport.pushToUndoStack(modifyRectangleCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyRectangleCommand.toString());
				}
				dialogRectangle = null;
				selectedRectangle = null;
			}
			else if(shapesModel.getSelectedShapes().getFirst() instanceof Donut) {
				Donut selectedDonut = (Donut)shapesModel.getSelectedShapes().getFirst();
				DlgDonut dialogDonut = new DlgDonut(colorModel.getDefaultBorderColor(), colorModel.getDefaultInnerColor());
				textModel.setCenterText("Modify Donut");
				dialogDonut.setSelectedDonut(selectedDonut);
				dialogDonut.setVisible(true);
				textModel.setCenterText(null);
				if(dialogDonut.isConfirm()) {
					ModifyDonutCmd modifyDonutCommand = new ModifyDonutCmd(shapesModel, dialogDonut.getModifiedDonut());
					modifyDonutCommand.execute();
					panelToolSupport.pushToUndoStack(modifyDonutCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyDonutCommand.toString());
				}
				dialogDonut = null;
				selectedDonut = null;	
			} else if(shapesModel.getSelectedShapes().getFirst() instanceof HexagonAdapter) {
				HexagonAdapter selectedHexagon = (HexagonAdapter)shapesModel.getSelectedShapes().getFirst();
				DlgHexagon dialogHexagon = new DlgHexagon(colorModel.getDefaultBorderColor(), colorModel.getDefaultInnerColor());
				textModel.setCenterText("Modify Hexagon");
				dialogHexagon.setSelectedHexagon(selectedHexagon);
				dialogHexagon.setVisible(true);
				textModel.setCenterText(null);
				if(dialogHexagon.isConfirm()) {
					ModifyHexagonCmd modifyHexagonCommand = new ModifyHexagonCmd(shapesModel, dialogHexagon.getModifiedHexagon());
					modifyHexagonCommand.execute();
					panelToolSupport.pushToUndoStack(modifyHexagonCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyHexagonCommand.toString());
				}
				dialogHexagon = null;
				selectedHexagon = null;	
			}
			else {
				Circle selectedCircle = (Circle)shapesModel.getSelectedShapes().getFirst();
				DlgCircle dialogCircle = new DlgCircle(colorModel.getDefaultBorderColor(), colorModel.getDefaultInnerColor());
				textModel.setCenterText("Modify Circle");
				dialogCircle.setSelectedCircle(selectedCircle);
				dialogCircle.setVisible(true);
				textModel.setCenterText(null);
				if(dialogCircle.isConfirm()) {
					ModifyCircleCmd modifyCircleCommand = new ModifyCircleCmd(shapesModel, dialogCircle.getModifiedCircle());
					modifyCircleCommand.execute();
					panelToolSupport.pushToUndoStack(modifyCircleCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyCircleCommand.toString());
				}
				dialogCircle = null;
				selectedCircle = null;
			}
		}
	}
	
	public void buttonDeleteAction() {
		DlgConfirm dialogConfirm = new DlgConfirm("Are you sure you want to delete selected shape/s from the drawing?");
		dialogConfirm.setVisible(true);
		if(dialogConfirm.isConfirm()) {
			RemoveSelectedShapesCmd removeSelectedShapesCommand = new RemoveSelectedShapesCmd(frameSupport, shapesModel);
			removeSelectedShapesCommand.execute();
			panelToolSupport.pushToUndoStack(removeSelectedShapesCommand);
			panelToolSupport.pushToRedoStack(null);
			panelLogSupport.addTextToLog(removeSelectedShapesCommand.toString());
		}
		dialogConfirm = null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("selectedShapesChanged".equals(evt.getPropertyName())) {
			if (shapesModel.getSelectedShapes().size() > 1) {
				view.getButtonModify().setIcon(iconModifyDisabled);
				view.getButtonModify().putClientProperty(enabledProperty, false);
			} else {
				view.getButtonModify().setIcon(iconModify);
				view.getButtonModify().putClientProperty(enabledProperty, true);
			}
		} else if ("panelVisibilityChanged".equals(evt.getPropertyName())) {
			if (evt.getNewValue() != null) {
				view.setVisible((Boolean)evt.getNewValue());
			}
		} else if ("propertiesOfPanelTool".equals(evt.getPropertyName())) {
			if (evt.getNewValue() != null) {
				enabledProperty = (String)evt.getNewValue();
			}
		}
	}
	
}