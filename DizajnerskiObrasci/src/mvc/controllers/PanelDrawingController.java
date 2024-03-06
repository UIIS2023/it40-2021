package mvc.controllers;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.JButton;
import commands.AddShapeCmd;
import commands.DeselectShapeCmd;
import commands.DeselectShapesCmd;
import commands.SelectShapeCmd;
import dialogs.DlgLine;
import dialogs.DlgPoint;
import dialogs.DlgRectangle;
import dialogs.DlgCircle;
import dialogs.DlgDonut;
import dialogs.DlgHexagon;
import geometry.Point;
import geometry.Shape;
import mvc.models.TextModel;
import mvc.models.ColorModel;
import mvc.models.ShapesModel;
import mvc.views.FrameView;
import mvc.views.PanelDrawingView;
import supports.FrameSupport;
import supports.PanelDrawingSupport;
import supports.PanelLogSupport;
import supports.PanelToolSupport;

public class PanelDrawingController implements PropertyChangeListener {

	private FrameSupport frameSupport;
	private PanelLogSupport panelLogSupport;
	private PanelDrawingSupport panelDrawingSupport;
	private PanelToolSupport panelToolSupport;
	private ColorModel colorModel;
	private TextModel textModel;
	private ShapesModel shapesModel;
	private PanelDrawingView view;
	private JButton selectedButton;
	private String valueProperty;
	private String[] buttonsGeometryValues;
	private String textLine;
	private Point startPointOfLine;
	
	public PanelDrawingController(FrameSupport frameSupport, ColorModel colorModel, TextModel textModel, ShapesModel shapesModel, FrameView frameView) {
		view = frameView.getPanelDrawingView();
		this.frameSupport = frameSupport;
		this.panelLogSupport = frameSupport.getPanelLogSupport();
		this.panelDrawingSupport = frameSupport.getPanelDrawingSupport();
		this.panelToolSupport = frameSupport.getPanelToolSupport();
		this.colorModel = colorModel;
		this.textModel = textModel;
		this.shapesModel = shapesModel;
		this.shapesModel.addPropertyChangeListener(this);
		this.panelDrawingSupport.addPropertyChangeListener(this);
		view.setShapesFromModel(this.shapesModel.getShapes());
	}
	
	public void mouseEntered() {
		view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		if (selectedButton != null) {
			String selectedButtonValue = (String)selectedButton.getClientProperty(valueProperty);
			if (buttonsGeometryValues[0].equals(selectedButtonValue)) {
				textModel.setCenterText("Select Point");
			} else if (buttonsGeometryValues[1].equals(selectedButtonValue)) {
				if (startPointOfLine == null) {
					textLine = "Select Start Point";
				} else {
					textLine = "Select End Point";
				}
				textModel.setCenterText(textLine);
			} else if (buttonsGeometryValues[2].equals(selectedButtonValue)) {
				textModel.setCenterText("Select Upper Left Point");
			} else if (buttonsGeometryValues[3].equals(selectedButtonValue) || buttonsGeometryValues[4].equals(selectedButtonValue) || buttonsGeometryValues[5].equals(selectedButtonValue)) {
				textModel.setCenterText("Select Center");
			} else if (buttonsGeometryValues[6].equals(selectedButtonValue)) {
				view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}
	
	public void mouseExited() {
		textModel.setLeftText(null);
		textModel.setCenterText(null);
	}
	
	public void mouseMoved(MouseEvent e) {
		textModel.setLeftText(String.format("X:  " + e.getX() + "      Y:  " + e.getY()));
	}
	
	public void mouseClicked(MouseEvent e) {
		if (selectedButton != null) {
			String selectedButtonValue = (String)selectedButton.getClientProperty(valueProperty);
			if (buttonsGeometryValues[0].equals(selectedButtonValue)) {
				DlgPoint dialogPoint = new DlgPoint(colorModel.getDefaultBorderColor());
				textModel.setCenterText("Create Point");
				dialogPoint.createNewPoint(e);
				dialogPoint.setVisible(true);
				textModel.setCenterText(null);
				if(dialogPoint.isConfirm()) {
					AddShapeCmd addPointCommand = new AddShapeCmd(shapesModel, dialogPoint.getNewPoint());
					addPointCommand.execute();
					panelToolSupport.pushToUndoStack(addPointCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(addPointCommand.toString());
				}
				dialogPoint = null;
			} else if (buttonsGeometryValues[1].equals(selectedButtonValue)) {
				if(startPointOfLine == null) {
					try {
						startPointOfLine = new Point((short)e.getX(), (short)e.getY());
					} catch (Exception e1) {}
					textModel.setCenterText("Select End Point");
				}
				else {
					DlgLine dialogLine = new DlgLine(colorModel.getDefaultBorderColor());
					textModel.setCenterText("Create Line");
					dialogLine.createNewLine(startPointOfLine, e);
					dialogLine.setVisible(true);
					textModel.setCenterText(null);
					if(dialogLine.isConfirm()) {
						AddShapeCmd addLineCommand = new AddShapeCmd(shapesModel, dialogLine.getNewLine());
						addLineCommand.execute();
						panelToolSupport.pushToUndoStack(addLineCommand);
						panelToolSupport.pushToRedoStack(null);
						panelLogSupport.addTextToLog(addLineCommand.toString());
					}
					startPointOfLine = null;
					dialogLine = null;
				}
			} else if (buttonsGeometryValues[2].equals(selectedButtonValue)) {
				DlgRectangle dialogRectangle = new DlgRectangle(colorModel.getDefaultBorderColor(), colorModel.getDefaultInnerColor());
				textModel.setCenterText("Create Rectangle");
				dialogRectangle.createNewRectangle(e);
				dialogRectangle.setVisible(true);
				textModel.setCenterText(null);
				if(dialogRectangle.isConfirm()) {
					AddShapeCmd addRectangleCommand = new AddShapeCmd(shapesModel, dialogRectangle.getNewRectangle());
					addRectangleCommand.execute();
					panelToolSupport.pushToUndoStack(addRectangleCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(addRectangleCommand.toString());
				}
				dialogRectangle = null;
			} else if (buttonsGeometryValues[3].equals(selectedButtonValue)) {
				DlgCircle dialogCircle = new DlgCircle(colorModel.getDefaultBorderColor(), colorModel.getDefaultInnerColor());
				textModel.setCenterText("Create Circle");
				dialogCircle.createNewCircle(e);
				dialogCircle.setVisible(true);
				textModel.setCenterText(null);
				if(dialogCircle.isConfirm()) {
					AddShapeCmd addCircleCommand = new AddShapeCmd(shapesModel, dialogCircle.getNewCircle());
					addCircleCommand.execute();
					panelToolSupport.pushToUndoStack(addCircleCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(addCircleCommand.toString());
				}
				dialogCircle = null;		
			} else if (buttonsGeometryValues[4].equals(selectedButtonValue)) {
				DlgDonut dialogDonut = new DlgDonut(colorModel.getDefaultBorderColor(), colorModel.getDefaultInnerColor());
				textModel.setCenterText("Create Donut");
				dialogDonut.createNewDonut(e);
				dialogDonut.setVisible(true);
				textModel.setCenterText(null);
				if(dialogDonut.isConfirm()) {
					AddShapeCmd addDonutCommand = new AddShapeCmd(shapesModel, dialogDonut.getNewDonut());
					addDonutCommand.execute();
					panelToolSupport.pushToUndoStack(addDonutCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(addDonutCommand.toString());
				}
				dialogDonut = null;
			} else if (buttonsGeometryValues[5].equals(selectedButtonValue)) {
				DlgHexagon dialogHexagon = new DlgHexagon(colorModel.getDefaultBorderColor(), colorModel.getDefaultInnerColor());
				textModel.setCenterText("Create Hexagon");
				dialogHexagon.createNewHexagon(e);
				dialogHexagon.setVisible(true);
				textModel.setCenterText(null);
				if(dialogHexagon.isConfirm()) {
					AddShapeCmd addHexagonCommand = new AddShapeCmd(shapesModel, dialogHexagon.getNewHexagon());
					addHexagonCommand.execute();
					panelToolSupport.pushToUndoStack(addHexagonCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(addHexagonCommand.toString());
				}
				dialogHexagon = null;		
			} else if (buttonsGeometryValues[6].equals(selectedButtonValue)) {
				Shape tempShape = null;
				Shape tempSelectedShape = null;
				Point clickedPoint = null;
				try {
					clickedPoint = new Point((short)e.getX(), (short)e.getY());
				} catch (Exception e1) {}
				
				ListIterator<Shape> iteratorShapes = shapesModel.getShapes().listIterator(shapesModel.getShapes().size());
				while (iteratorShapes.hasPrevious()) {
				    tempShape = iteratorShapes.previous();
				    if (tempShape.contains(clickedPoint)) {
				    	Iterator<Shape> iteratorSelectedShapes = shapesModel.getSelectedShapes().iterator();
						while(iteratorSelectedShapes.hasNext()) {
							tempSelectedShape = iteratorSelectedShapes.next();
							if (tempShape==tempSelectedShape) {
								DeselectShapeCmd deselectShapeCommand = new DeselectShapeCmd(frameSupport, shapesModel, tempShape);
						        deselectShapeCommand.execute();
						        panelToolSupport.pushToUndoStack(deselectShapeCommand);
						        panelToolSupport.pushToRedoStack(null);
						        panelLogSupport.addTextToLog(deselectShapeCommand.toString());
						        return;
							}
						}
				        SelectShapeCmd selectShapeCommand = new SelectShapeCmd(frameSupport, shapesModel, tempShape);
				        selectShapeCommand.execute();
				        panelToolSupport.pushToUndoStack(selectShapeCommand);
				        panelToolSupport.pushToRedoStack(null);
				        panelLogSupport.addTextToLog(selectShapeCommand.toString());
				        return;
				    }
				}
				
				if(!shapesModel.getSelectedShapes().isEmpty()) {
					DeselectShapesCmd deselectShapesCommand = new DeselectShapesCmd(frameSupport, shapesModel);
			        deselectShapesCommand.execute();
			        panelToolSupport.pushToUndoStack(deselectShapesCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(deselectShapesCommand.toString());
				}
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("shapesChanged".equals(evt.getPropertyName())) {
			view.repaint();
		} else if ("selectedButtonChanged".equals(evt.getPropertyName())) {
			if (evt.getNewValue() != null) {
				selectedButton = (JButton)evt.getNewValue();
			} else {
				selectedButton = null;
			}
		} else if ("propertiesOfPanelTool".equals(evt.getPropertyName())) {
			if (evt.getNewValue() != null) {
				Object[] tempValues = (Object[])evt.getNewValue();
				valueProperty = (String)tempValues[0];
				buttonsGeometryValues = (String[])tempValues[1];
			}
		} else if ("startPointOfLineChanged".equals(evt.getPropertyName())) {
			if (evt.getNewValue() == null) {
				startPointOfLine = null;
			} else {
				startPointOfLine = (Point)evt.getNewValue();
			}
		}
	}
	
}