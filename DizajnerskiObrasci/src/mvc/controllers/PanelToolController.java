package mvc.controllers;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Stack;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import commands.BringToBackCmd;
import commands.BringToFrontCmd;
import commands.ToBackCmd;
import commands.ToFrontCmd;
import dialogs.DlgConfirm;
import dialogs.DlgException;
import dialogs.DlgFileOptions;
import interfaces.Command;
import mvc.models.ShapesModel;
import mvc.models.TextModel;
import mvc.views.FrameView;
import mvc.views.PanelToolView;
import strategy.OpenDraw;
import strategy.OpenLog;
import strategy.OpenManager;
import strategy.SaveDraw;
import strategy.SaveLog;
import strategy.SaveManager;
import supports.FrameSupport;
import supports.PanelDrawingSupport;
import supports.PanelLogSupport;
import supports.PanelSelectSupport;
import supports.PanelToolSupport;

public class PanelToolController implements PropertyChangeListener {

	private FrameSupport frameSupport;
	private PanelLogSupport panelLogSupport;
	private PanelToolSupport panelToolSupport;
	private PanelSelectSupport panelSelectSupport;
	private PanelDrawingSupport panelDrawingSupport;
	private TextModel textModel;
	private ShapesModel shapesModel;
	private PanelToolView view;
	private Icon iconUndo;
	private Icon iconUndoDisabled;
	private Icon iconRedo;
	private Icon iconRedoDisabled;
	private Icon iconToFront;
	private Icon iconToBack;
	private Icon iconBringToFront;
	private Icon iconBringToBack;
	private Icon iconToFrontDisabled;
	private Icon iconToBackDisabled;
	private Icon iconBringToFrontDisabled;
	private Icon iconBringToBackDisabled;
	private Stack<Command> undoStack;
	private Stack<Command> redoStack;
	private DlgFileOptions dialogFileOptions;
	private JTextPane logTextPane;
	
	public PanelToolController(FrameSupport frameSupport, TextModel textModel, ShapesModel shapesModel, FrameView frameView) {
		view = frameView.getPanelToolView();
		this.frameSupport = frameSupport;
		this.panelLogSupport = frameSupport.getPanelLogSupport();
		this.panelToolSupport = frameSupport.getPanelToolSupport();
		this.panelDrawingSupport = frameSupport.getPanelDrawingSupport();
		this.panelSelectSupport = frameSupport.getPanelSelectSupport();
		this.textModel = textModel;
		this.shapesModel = shapesModel;
		panelToolSupport.addPropertyChangeListener(this);
		
		ImageIcon iconUndoDefault = new ImageIcon("res/icons/panelTool/undo.png");
		iconUndo = new ImageIcon(iconUndoDefault.getImage().getScaledInstance((iconUndoDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconUndoDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconRedoDefault = new ImageIcon("res/icons/panelTool/redo.png");
		iconRedo = new ImageIcon(iconRedoDefault.getImage().getScaledInstance((iconRedoDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconRedoDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconUndoDisabledDefault= new ImageIcon("res/icons/panelTool/undoDisabled.png");
		iconUndoDisabled = new ImageIcon(iconUndoDisabledDefault.getImage().getScaledInstance((iconUndoDisabledDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconUndoDisabledDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconRedoDisabledDefault = new ImageIcon("res/icons/panelTool/redoDisabled.png");
		iconRedoDisabled = new ImageIcon(iconRedoDisabledDefault.getImage().getScaledInstance((iconRedoDisabledDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconRedoDisabledDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconToFrontDefault = new ImageIcon("res/icons/panelTool/zAxis/toFront.png");
		iconToFront = new ImageIcon(iconToFrontDefault.getImage().getScaledInstance((iconToFrontDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconToFrontDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconToBackDefault = new ImageIcon("res/icons/panelTool/zAxis/toBack.png");
		iconToBack = new ImageIcon(iconToBackDefault.getImage().getScaledInstance((iconToBackDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconToBackDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconBringToFrontDefault = new ImageIcon("res/icons/panelTool/zAxis/bringToFront.png");
		iconBringToFront = new ImageIcon(iconBringToFrontDefault.getImage().getScaledInstance((iconBringToFrontDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconBringToFrontDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconBringToBackDefault = new ImageIcon("res/icons/panelTool/zAxis/bringToBack.png");
		iconBringToBack = new ImageIcon(iconBringToBackDefault.getImage().getScaledInstance((iconBringToBackDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconBringToBackDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconToFrontDisabledDefault = new ImageIcon("res/icons/panelTool/zAxis/toFrontDisabled.png");
		iconToFrontDisabled = new ImageIcon(iconToFrontDisabledDefault.getImage().getScaledInstance((iconToFrontDisabledDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconToFrontDisabledDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconToBackDisabledDefault = new ImageIcon("res/icons/panelTool/zAxis/toBackDisabled.png");
		iconToBackDisabled = new ImageIcon(iconToBackDisabledDefault.getImage().getScaledInstance((iconToBackDisabledDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconToBackDisabledDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconBringToFrontDisabledDefault = new ImageIcon("res/icons/panelTool/zAxis/bringToFrontDisabled.png");
		iconBringToFrontDisabled = new ImageIcon(iconBringToFrontDisabledDefault.getImage().getScaledInstance((iconBringToFrontDisabledDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconBringToFrontDisabledDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		ImageIcon iconBringToBackDisabledDefault = new ImageIcon("res/icons/panelTool/zAxis/bringToBackDisabled.png");
		iconBringToBackDisabled = new ImageIcon(iconBringToBackDisabledDefault.getImage().getScaledInstance((iconBringToBackDisabledDefault.getIconWidth()*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, (iconBringToBackDisabledDefault.getIconHeight()*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Image.SCALE_SMOOTH));
		
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
		dialogFileOptions = new DlgFileOptions(this);
		panelToolSupport.pushToUndoStack(null);
		panelToolSupport.pushToRedoStack(null);
		disableAllButtonsZAxis();
		
		this.panelDrawingSupport.propertiesOfPanelTool(view.getValueProperty(), view.getButtonsGeometryValues());
		this.panelSelectSupport.propertiesOfPanelTool(view.getEnabledProperty());
	}
	
	public void buttonMouseEntered(JButton activeButton, String valueProperty) {
		if ((Boolean)activeButton.getClientProperty(view.getEnabledProperty())) {
			activeButton.setContentAreaFilled(true);
			activeButton.setBackground(new Color(25,25,25));
		}
		if (activeButton.getClientProperty(valueProperty) != null) {
			textModel.setCenterText((String)activeButton.getClientProperty(valueProperty));
		}
	}
	
	public void buttonFileOptionsEntered(JButton activeButton, String valueProperty) {
		activeButton.setContentAreaFilled(true);
		activeButton.setBackground(new Color(25,25,25));
		if (activeButton.getClientProperty(valueProperty) != null) {
			textModel.setCenterText((String)activeButton.getClientProperty(valueProperty));
		}
	}
	
	public void buttonsGeometryMouseExited(JButton activeButton, String selectedProperty) {
		if (!(Boolean)activeButton.getClientProperty(selectedProperty) && (Boolean)activeButton.getClientProperty(view.getEnabledProperty())) {
			activeButton.setBackground(new Color(50,50,50));
			textModel.setCenterText(null);
		}
	}
	
	public void buttonsMouseExited(JButton activeButton) {
		if ((Boolean)activeButton.getClientProperty(view.getEnabledProperty())) {
			activeButton.setBackground(new Color(50,50,50));
		}
		textModel.setCenterText(null);
	}
	
	public void buttonFileOptionsExited(JButton activeButton) {
		activeButton.setBackground(new Color(50,50,50));
		textModel.setCenterText(null);
	}
	
	public void buttonsMousePressed(JButton activeButton) {
		activeButton.setContentAreaFilled(false);
	}
	
	public void buttonsMouseClicked(JButton activeButton) {
		if ((Boolean)activeButton.getClientProperty(view.getEnabledProperty())) {
			activeButton.setContentAreaFilled(true);
			activeButton.setBackground(new Color(25,25,25));
		}
	}
	
	public void buttonFileOptionsClicked(JButton activeButton) {
		activeButton.setContentAreaFilled(true);
		activeButton.setBackground(new Color(25,25,25));
	}
	
	public void buttonFileAction(JButton activeButton) {
		activeButton.setContentAreaFilled(false);
		if (dialogFileOptions.isVisible()) {
			dialogFileOptions.setVisible(false);
		} else {
			dialogFileOptions.setVisible(true);
		}
	}
	
	public void buttonsUndoRedoAction(JButton selectedButton, String valueProperty) {
		if ((Boolean)selectedButton.getClientProperty(view.getEnabledProperty())) {
			String buttonValue = (String)selectedButton.getClientProperty(valueProperty);
			if(view.getButtonsUndoRedoValues()[0].equals(buttonValue)) {
				panelToolSupport.popFromUndoStack();
			} else if(view.getButtonsUndoRedoValues()[1].equals(buttonValue)) {
				panelToolSupport.popFromRedoStack();
			}
		}
	}
	
	public void buttonsGeometryAction(JButton selectedButton, String valueProperty, String selectedProperty) {
		if((boolean)selectedButton.getClientProperty(selectedProperty)) {
			selectedButton.setBackground(new Color(50,50,50));
			selectedButton.putClientProperty(selectedProperty, false);
			textModel.setFixedCenterText(null);
			String selectedButtonValue = (String)selectedButton.getClientProperty(valueProperty);
			if(view.getButtonsGeometryValues()[1].equals(selectedButtonValue)) {
				panelDrawingSupport.startPointOfLineChanged(null);
			}
			panelDrawingSupport.selectedButtonChanged(null);
		}
		else {
			for(int i = 0; i < view.getButtonsGeometry().length; i++) {
				view.getButtonsGeometry()[i].putClientProperty(selectedProperty, false);
				view.getButtonsGeometry()[i].setBackground(new Color(50,50,50));
			}
			selectedButton.setContentAreaFilled(true);
			selectedButton.setBackground(new Color(25,25,25));
			selectedButton.putClientProperty(selectedProperty, true);
			String selectedButtonValue = (String)selectedButton.getClientProperty(valueProperty);
			textModel.setFixedCenterText(selectedButtonValue);
			if(view.getButtonsGeometryValues()[1].equals(selectedButtonValue)) {
				panelDrawingSupport.startPointOfLineChanged(null);
			}
			panelDrawingSupport.selectedButtonChanged(selectedButton);
		}
	}
	
	public void buttonDeleteAllAction(JButton activeButton) {
		activeButton.setContentAreaFilled(false);
		if(shapesModel.getShapes().isEmpty()) {
			DlgException dialogException = new DlgException("You have no drawn shapes in the drawing!");
			dialogException.setVisible(true);
			dialogException = null;
		}
		else {
			DlgConfirm dialogConfirm = new DlgConfirm("Are you sure you want to delete all shapes from the drawing?");
			dialogConfirm.setVisible(true);
			if(dialogConfirm.isConfirm()) {
				if (!shapesModel.getSelectedShapes().isEmpty()) {
					panelSelectSupport.panelVisibilityChanged(false);
					shapesModel.getSelectedShapes().clear();
				}
				shapesModel.getShapes().clear();
				logTextPane.setText(null);
				panelDrawingSupport.startPointOfLineChanged(null);
				panelSelectSupport.selectedShapesChanged();
				panelToolSupport.selectedShapesChanged();
				shapesModel.shapesChanged();
				panelToolSupport.pushToUndoStack(null);
				panelToolSupport.pushToRedoStack(null);
			}
			dialogConfirm = null;
		}
	}
	
	public void buttonsZAxisAction(JButton selectedButton, String valueProperty) {
		if ((Boolean)selectedButton.getClientProperty(view.getEnabledProperty())) {
			String buttonValue = (String)selectedButton.getClientProperty(valueProperty);
			int indexOfSelectedShape = shapesModel.getShapes().indexOf(shapesModel.getSelectedShapes().getFirst());
			if(view.getButtonsZAxisValues()[0].equals(buttonValue)) {
				ToFrontCmd toFrontCommand = new ToFrontCmd(frameSupport, shapesModel, indexOfSelectedShape);
				toFrontCommand.execute();
				panelToolSupport.pushToUndoStack(toFrontCommand);
				panelToolSupport.pushToRedoStack(null);
				panelLogSupport.addTextToLog(toFrontCommand.toString());
			} else if(view.getButtonsZAxisValues()[1].equals(buttonValue)) {
				ToBackCmd toBackCommand = new ToBackCmd(frameSupport, shapesModel, indexOfSelectedShape);
				toBackCommand.execute();
				panelToolSupport.pushToUndoStack(toBackCommand);
				panelToolSupport.pushToRedoStack(null);
				panelLogSupport.addTextToLog(toBackCommand.toString());
			} else if(view.getButtonsZAxisValues()[2].equals(buttonValue)) {
				BringToFrontCmd bringToFrontCommand = new BringToFrontCmd(frameSupport, shapesModel, indexOfSelectedShape);
				bringToFrontCommand.execute();
				panelToolSupport.pushToUndoStack(bringToFrontCommand);
				panelToolSupport.pushToRedoStack(null);
				panelLogSupport.addTextToLog(bringToFrontCommand.toString());
			} else if(view.getButtonsZAxisValues()[3].equals(buttonValue)) {
				BringToBackCmd bringToBackCommand = new BringToBackCmd(frameSupport, shapesModel, indexOfSelectedShape);
				bringToBackCommand.execute();
				panelToolSupport.pushToUndoStack(bringToBackCommand);
				panelToolSupport.pushToRedoStack(null);
				panelLogSupport.addTextToLog(bringToBackCommand.toString());
			}
		}
	}
	
	public void buttonSaveDrawAction() {
		SaveManager saveManager = new SaveManager(new SaveDraw(shapesModel));
		saveManager.save();
	}
	
	public void buttonSaveLogAction() {
		SaveManager saveManager = new SaveManager(new SaveLog(logTextPane));
		saveManager.save();
	}
	
	public void buttonOpenDrawAction() {
		OpenManager openManager = new OpenManager(new OpenDraw(frameSupport, shapesModel, logTextPane));
		openManager.open();
	}
	
	public void buttonOpenLogAction() {
		OpenManager openManager = new OpenManager(new OpenLog(frameSupport, shapesModel, logTextPane));
		openManager.open();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("selectedShapesChanged".equals(evt.getPropertyName())) {
			if (shapesModel.getSelectedShapes().isEmpty() || shapesModel.getSelectedShapes().size() > 1) {
				disableAllButtonsZAxis();
			} else if (shapesModel.getSelectedShapes().getFirst() == shapesModel.getShapes().getFirst()) {
				disableAllButtonsZAxis();
				if (shapesModel.getShapes().size() > 1) {
					view.getButtonsZAxis()[0].putClientProperty(view.getEnabledProperty(), true);
					view.getButtonsZAxis()[0].setIcon(iconToFront);
					view.getButtonsZAxis()[2].putClientProperty(view.getEnabledProperty(), true);
					view.getButtonsZAxis()[2].setIcon(iconBringToFront);
				}
			} else if (shapesModel.getSelectedShapes().getFirst() == shapesModel.getShapes().getLast()) {
				disableAllButtonsZAxis();
				view.getButtonsZAxis()[1].putClientProperty(view.getEnabledProperty(), true);
				view.getButtonsZAxis()[1].setIcon(iconToBack);
				view.getButtonsZAxis()[3].putClientProperty(view.getEnabledProperty(), true);
				view.getButtonsZAxis()[3].setIcon(iconBringToBack);
			}
			else {
				enableAllButtonsZAxis();
			}
		} else if ("pushToUndoStack".equals(evt.getPropertyName())) {
			if (evt.getNewValue() == null) {
				view.getButtonsUndoRedo()[0].putClientProperty(view.getEnabledProperty(), false);
				view.getButtonsUndoRedo()[0].setIcon(iconUndoDisabled);
				undoStack.removeAllElements();
			} else {
				if (undoStack.isEmpty()) {
					view.getButtonsUndoRedo()[0].putClientProperty(view.getEnabledProperty(), true);
					view.getButtonsUndoRedo()[0].setIcon(iconUndo);
				}
				undoStack.push((Command)evt.getNewValue());
			}
		} else if ("popFromUndoStack".equals(evt.getPropertyName())) {
			Command tempCommand = undoStack.pop();
			if (undoStack.isEmpty()) {
				view.getButtonsUndoRedo()[0].putClientProperty(view.getEnabledProperty(), false);
				view.getButtonsUndoRedo()[0].setIcon(iconUndoDisabled);
			}
			tempCommand.unexecute();
			panelToolSupport.pushToRedoStack(tempCommand);
			panelLogSupport.addTextToLog(String.format("Undo - " + tempCommand.toString()));
		} else if ("pushToRedoStack".equals(evt.getPropertyName())) {
			if (evt.getNewValue() == null) {
				view.getButtonsUndoRedo()[1].putClientProperty(view.getEnabledProperty(), false);
				view.getButtonsUndoRedo()[1].setIcon(iconRedoDisabled);
				redoStack.removeAllElements();
			} else {
				if (redoStack.isEmpty()) {
					view.getButtonsUndoRedo()[1].putClientProperty(view.getEnabledProperty(), true);
					view.getButtonsUndoRedo()[1].setIcon(iconRedo);
				}
				redoStack.push((Command)evt.getNewValue());
			}
		} else if ("popFromRedoStack".equals(evt.getPropertyName())) {
			Command tempCommand = redoStack.pop();
			if (redoStack.isEmpty()) {
				view.getButtonsUndoRedo()[1].putClientProperty(view.getEnabledProperty(), false);
				view.getButtonsUndoRedo()[1].setIcon(iconRedoDisabled);
			}
			tempCommand.execute();
			panelToolSupport.pushToUndoStack(tempCommand);
			panelLogSupport.addTextToLog(String.format("Redo - " + tempCommand.toString()));
		} else if ("sendLogTextPane".equals(evt.getPropertyName())) {
			logTextPane = (JTextPane)evt.getNewValue();
		}
	}
	
	private void disableAllButtonsZAxis() {
		for(int i = 0; i < view.getButtonsZAxis().length; i++) {
			view.getButtonsZAxis()[i].putClientProperty(view.getEnabledProperty(), false);
		}
		view.getButtonsZAxis()[0].setIcon(iconToFrontDisabled);
		view.getButtonsZAxis()[1].setIcon(iconToBackDisabled);
		view.getButtonsZAxis()[2].setIcon(iconBringToFrontDisabled);
		view.getButtonsZAxis()[3].setIcon(iconBringToBackDisabled);
	}
	
	private void enableAllButtonsZAxis() {
		for(int i = 0; i < view.getButtonsZAxis().length; i++) {
			view.getButtonsZAxis()[i].putClientProperty(view.getEnabledProperty(), true);
		}
		view.getButtonsZAxis()[0].setIcon(iconToFront);
		view.getButtonsZAxis()[1].setIcon(iconToBack);
		view.getButtonsZAxis()[2].setIcon(iconBringToFront);
		view.getButtonsZAxis()[3].setIcon(iconBringToBack);
	}
	
}