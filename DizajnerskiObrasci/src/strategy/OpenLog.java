package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import commands.AddShapeCmd;
import commands.BringToBackCmd;
import commands.BringToFrontCmd;
import commands.DeselectShapeCmd;
import commands.DeselectShapesCmd;
import commands.ModifyCircleCmd;
import commands.ModifyDonutCmd;
import commands.ModifyHexagonCmd;
import commands.ModifyLineCmd;
import commands.ModifyPointCmd;
import commands.ModifyRectangleCmd;
import commands.RemoveSelectedShapesCmd;
import commands.SelectShapeCmd;
import commands.ToBackCmd;
import commands.ToFrontCmd;
import dialogs.DlgException;
import dialogs.DlgExecuteCommands;
import interfaces.OpenStrategy;
import mvc.models.ShapesModel;
import supports.FrameSupport;
import supports.PanelDrawingSupport;
import supports.PanelLogSupport;
import supports.PanelSelectSupport;
import supports.PanelToolSupport;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class OpenLog implements OpenStrategy {

	private FrameSupport frameSupport;
	private PanelDrawingSupport panelDrawingSupport;
	private PanelToolSupport panelToolSupport;
	private PanelSelectSupport panelSelectSupport;
	private PanelLogSupport panelLogSupport;
	private ShapesModel shapesModel;
	private JTextPane logTextPane;
	private String regexPoint;
	private Pattern patternPoint;
	private String regexLine;
	private Pattern patternLine;
	private String regexRectangle;
	private Pattern patternRectangle;
	private String regexCircle;
	private Pattern patternCircle;
	private String regexDonut;
	private Pattern patternDonut;
	private String regexHexagon;
	private Pattern patternHexagon;
	private String regexAdded;
	private Pattern patternAdded;
	private String regexModified;
	private Pattern patternModified;
	private String regexSelected;
	private Pattern patternSelected;
	private String regexDeselected;
	private Pattern patternDeselected;
	private String regexBroughtToBack;
	private Pattern patternBroughtToBack;
	private String regexBroughtToFront;
	private Pattern patternBroughtToFront;
	private String regexToBack;
	private Pattern patternToBack;
	private String regexToFront;
	private Pattern patternToFront;
	private String regexDeselectedShapes;
	private Pattern patternDeselectedShapes;
	private String regexDeletedSelectedShapes;
	private Pattern patternDeletedSelectedShapes;
	private String regexUndo;
	private Pattern patternUndo;
	private String regexRedo;
	private Pattern patternRedo;
	
    public OpenLog(FrameSupport frameSupport, ShapesModel shapesModel, JTextPane logTextPane) {
    	this.frameSupport = frameSupport;
    	panelDrawingSupport = frameSupport.getPanelDrawingSupport();
    	panelToolSupport = frameSupport.getPanelToolSupport();
    	panelSelectSupport = frameSupport.getPanelSelectSupport();
    	panelLogSupport = frameSupport.getPanelLogSupport();
    	this.shapesModel = shapesModel;
    	this.logTextPane = logTextPane;
    	regexPoint = "Point \\[coordinates: \\((\\d+),(\\d+)\\); borderColor: rgb\\((\\d+),(\\d+),(\\d+)\\)\\]";
    	patternPoint = Pattern.compile(regexPoint);
    	regexLine = "Line \\[startPoint: \\((\\d+),(\\d+)\\); endPoint: \\((\\d+),(\\d+)\\); borderColor: rgb\\((\\d+),(\\d+),(\\d+)\\)\\]";
    	patternLine = Pattern.compile(regexLine);
    	regexRectangle = "Rectangle \\[upperLeftPoint: \\((\\d+),(\\d+)\\); width: (\\d+); height: (\\d+); borderColor: rgb\\((\\d+),(\\d+),(\\d+)\\); innerColor: rgb\\((\\d+),(\\d+),(\\d+)\\)\\]";
    	patternRectangle = Pattern.compile(regexRectangle);
    	regexCircle = "Circle \\[center: \\((\\d+),(\\d+)\\); radius: (\\d+); borderColor: rgb\\((\\d+),(\\d+),(\\d+)\\); innerColor: rgb\\((\\d+),(\\d+),(\\d+)\\)\\]";
    	patternCircle = Pattern.compile(regexCircle);
    	regexDonut = "Donut \\[center: \\((\\d+),(\\d+)\\); outerRadius: (\\d+); innerRadius: (\\d+); borderColor: rgb\\((\\d+),(\\d+),(\\d+)\\); innerColor: rgb\\((\\d+),(\\d+),(\\d+)\\)\\]";
    	patternDonut = Pattern.compile(regexDonut);
    	regexHexagon = "Hexagon \\[center: \\((\\d+),(\\d+)\\); radius: (\\d+); borderColor: rgb\\((\\d+),(\\d+),(\\d+)\\); innerColor: rgb\\((\\d+),(\\d+),(\\d+)\\)\\]";
    	patternHexagon = Pattern.compile(regexHexagon);
    	regexAdded = "Added";
    	patternAdded = Pattern.compile(regexAdded);
    	regexModified = "Modified";
    	patternModified = Pattern.compile(regexModified);
    	regexSelected = "Selected";
    	patternSelected = Pattern.compile(regexSelected);
    	regexDeselected = "Deselected";
    	patternDeselected = Pattern.compile(regexDeselected);
    	regexBroughtToBack = "Brought To Back";
    	patternBroughtToBack = Pattern.compile(regexBroughtToBack);
    	regexBroughtToFront = "Brought To Front";
    	patternBroughtToFront = Pattern.compile(regexBroughtToFront);
    	regexToBack = "To Back";
    	patternToBack = Pattern.compile(regexToBack);
    	regexToFront = "To Front";
    	patternToFront = Pattern.compile(regexToFront);
    	regexDeselectedShapes = "Deselected Shapes";
    	patternDeselectedShapes = Pattern.compile(regexDeselectedShapes);
    	regexDeletedSelectedShapes = "Deleted Selected Shapes";
    	patternDeletedSelectedShapes = Pattern.compile(regexDeletedSelectedShapes);
    	regexUndo = "Undo";
    	patternUndo = Pattern.compile(regexUndo);
    	regexRedo = "Redo";
    	patternRedo = Pattern.compile(regexRedo);
    }
    
	@Override
	public void open() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Log");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file (.txt)", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
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
            if (selectedFile.getName().toLowerCase().endsWith(".txt")) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    String line = "";
                    DlgExecuteCommands dialogExecuteCommands = new DlgExecuteCommands(line);
                    while ((line = reader.readLine()) != null) {
                    	dialogExecuteCommands.setTextContent(line);
                    	dialogExecuteCommands.setVisible(true);
                    	if (dialogExecuteCommands.isConfirm()) {
                    		processLine(line);
                    		dialogExecuteCommands.setConfirm(false);
                    	} else {
                    		reader.close();
                    		return;
                    	}
                    }
                    dialogExecuteCommands.dispose();
                    reader.close();
                } catch (Exception e) {
                    DlgException dialogException = new DlgException("Error loading log.");
    	            dialogException.setVisible(true);
                }
            } else {
            	DlgException dialogException = new DlgException("Please choose correct file.");
	            dialogException.setVisible(true);
            }
        }
	}
	
	private void processLine(String line) {
		Matcher matcherUndo = patternUndo.matcher(line);
		if (matcherUndo.find()) {
			panelToolSupport.popFromUndoStack();
			return;
		}
		
		Matcher matcherRedo = patternRedo.matcher(line);
		if (matcherRedo.find()) {
			panelToolSupport.popFromRedoStack();
			return;
		}
		
		Matcher matcherDeselectedShapes = patternDeselectedShapes.matcher(line);
		if (matcherDeselectedShapes.find()) {
			DeselectShapesCmd deselectShapesCommand = new DeselectShapesCmd(frameSupport, shapesModel);
	        deselectShapesCommand.execute();
	        panelToolSupport.pushToUndoStack(deselectShapesCommand);
	        panelToolSupport.pushToRedoStack(null);
	        panelLogSupport.addTextToLog(deselectShapesCommand.toString());
	        return;
		}
		
		Matcher matcherDeletedSelectedShapes = patternDeletedSelectedShapes.matcher(line);
		if (matcherDeletedSelectedShapes.find()) {
			RemoveSelectedShapesCmd removeSelectedShapesCommand = new RemoveSelectedShapesCmd(frameSupport, shapesModel);
			removeSelectedShapesCommand.execute();
			panelToolSupport.pushToUndoStack(removeSelectedShapesCommand);
			panelToolSupport.pushToRedoStack(null);
			panelLogSupport.addTextToLog(removeSelectedShapesCommand.toString());
			return;
		}
		
		Matcher matcherBroughtToBack = patternBroughtToBack.matcher(line);
		if (matcherBroughtToBack.find()) {
    		int indexOfSelectedShape = shapesModel.getShapes().indexOf(shapesModel.getSelectedShapes().getFirst());
    		BringToBackCmd bringToBackCommand = new BringToBackCmd(frameSupport, shapesModel, indexOfSelectedShape);
			bringToBackCommand.execute();
			panelToolSupport.pushToUndoStack(bringToBackCommand);
			panelToolSupport.pushToRedoStack(null);
			panelLogSupport.addTextToLog(bringToBackCommand.toString());
			return;
    	}
		
		Matcher matcherBroughtToFront = patternBroughtToFront.matcher(line);
		if (matcherBroughtToFront.find()) {
			int indexOfSelectedShape = shapesModel.getShapes().indexOf(shapesModel.getSelectedShapes().getFirst());
			BringToFrontCmd bringToFrontCommand = new BringToFrontCmd(frameSupport, shapesModel, indexOfSelectedShape);
			bringToFrontCommand.execute();
			panelToolSupport.pushToUndoStack(bringToFrontCommand);
			panelToolSupport.pushToRedoStack(null);
			panelLogSupport.addTextToLog(bringToFrontCommand.toString());
			return;
    	}
		
		Matcher matcherToBack = patternToBack.matcher(line);
		if (matcherToBack.find()) {
			int indexOfSelectedShape = shapesModel.getShapes().indexOf(shapesModel.getSelectedShapes().getFirst());
			ToBackCmd toBackCommand = new ToBackCmd(frameSupport, shapesModel, indexOfSelectedShape);
			toBackCommand.execute();
			panelToolSupport.pushToUndoStack(toBackCommand);
			panelToolSupport.pushToRedoStack(null);
			panelLogSupport.addTextToLog(toBackCommand.toString());
			return;
    	}
		
		Matcher matcherToFront = patternToFront.matcher(line);
		if (matcherToFront.find()) {
			int indexOfSelectedShape = shapesModel.getShapes().indexOf(shapesModel.getSelectedShapes().getFirst());
			ToFrontCmd toFrontCommand = new ToFrontCmd(frameSupport, shapesModel, indexOfSelectedShape);
			toFrontCommand.execute();
			panelToolSupport.pushToUndoStack(toFrontCommand);
			panelToolSupport.pushToRedoStack(null);
			panelLogSupport.addTextToLog(toFrontCommand.toString());
			return;
    	}

		Matcher matcherAdded = patternAdded.matcher(line);
		Matcher matcherModified = patternModified.matcher(line);
		Matcher matcherSelected = patternSelected.matcher(line);
		Matcher matcherDeselected = patternDeselected.matcher(line);
		
		Matcher matcherPoint = patternPoint.matcher(line);
        if (matcherPoint.find()) {
        	short x = Short.parseShort(matcherPoint.group(1));
    		short y = Short.parseShort(matcherPoint.group(2));
    		int borderColorRed = Integer.parseInt(matcherPoint.group(3));
    		int borderColorGreen = Integer.parseInt(matcherPoint.group(4));
    		int borderColorBlue = Integer.parseInt(matcherPoint.group(5));
			try {
				Shape point = new Point(x, y, false, new Color(borderColorRed, borderColorGreen, borderColorBlue));
				if (matcherAdded.find()) {
					addShape(point);
	        	} else if (matcherModified.find()) {
					point.setSelected(true);
					ModifyPointCmd modifyPointCommand = new ModifyPointCmd(shapesModel, (Point)point);
					modifyPointCommand.execute();
					panelToolSupport.pushToUndoStack(modifyPointCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyPointCommand.toString());
	        	} else if (matcherSelected.find()) {
	        		SelectShapeCmd selectShapeCommand = new SelectShapeCmd(frameSupport, shapesModel, findShape(point));
			        selectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(selectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(selectShapeCommand.toString());
	        	} else if (matcherDeselected.find()) {
	        		point.setSelected(true);
	        		DeselectShapeCmd deselectShapeCommand = new DeselectShapeCmd(frameSupport, shapesModel, findShape(point));
			        deselectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(deselectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(deselectShapeCommand.toString());
	        	}
				return;
			} catch (Exception e) {}
        }
        
        Matcher matcherLine = patternLine.matcher(line);
        if (matcherLine.find()) {
        	short startPointX = Short.parseShort(matcherLine.group(1));
        	short startPointY = Short.parseShort(matcherLine.group(2));
        	short endPointX = Short.parseShort(matcherLine.group(3));
        	short endPointY = Short.parseShort(matcherLine.group(4));
        	int borderColorRed = Integer.parseInt(matcherLine.group(5));
        	int borderColorGreen = Integer.parseInt(matcherLine.group(6));
        	int borderColorBlue = Integer.parseInt(matcherLine.group(7));
        	try {
        		Shape lineShape = new Line(new Point(startPointX, startPointY), new Point(endPointX, endPointY), false, new Color(borderColorRed, borderColorGreen, borderColorBlue));
        		if (matcherAdded.find()) {
        			addShape(lineShape);
            	} else if (matcherModified.find()) {
            		lineShape.setSelected(true);
            		ModifyLineCmd modifyLineCommand = new ModifyLineCmd(shapesModel, (Line)lineShape);
					modifyLineCommand.execute();
					panelToolSupport.pushToUndoStack(modifyLineCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyLineCommand.toString());
            	} else if (matcherSelected.find()) {
	        		SelectShapeCmd selectShapeCommand = new SelectShapeCmd(frameSupport, shapesModel, findShape(lineShape));
			        selectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(selectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(selectShapeCommand.toString());
	        	} else if (matcherDeselected.find()) {
	        		lineShape.setSelected(true);
	        		DeselectShapeCmd deselectShapeCommand = new DeselectShapeCmd(frameSupport, shapesModel, findShape(lineShape));
			        deselectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(deselectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(deselectShapeCommand.toString());
	        	}
        		return;
        	} catch (Exception e) {}
        }
        
        Matcher matcherRectangle = patternRectangle.matcher(line);
        if (matcherRectangle.find()) {
        	short upperLeftPointX = Short.parseShort(matcherRectangle.group(1));
        	short upperLeftPointY = Short.parseShort(matcherRectangle.group(2));
        	short width = Short.parseShort(matcherRectangle.group(3));
        	short height = Short.parseShort(matcherRectangle.group(4));
        	int borderColorR = Integer.parseInt(matcherRectangle.group(5));
        	int borderColorG = Integer.parseInt(matcherRectangle.group(6));
        	int borderColorB = Integer.parseInt(matcherRectangle.group(7));
        	int innerColorR = Integer.parseInt(matcherRectangle.group(8));
        	int innerColorG = Integer.parseInt(matcherRectangle.group(9));
        	int innerColorB = Integer.parseInt(matcherRectangle.group(10));
        	try {
        		Shape rectangle = new Rectangle(new Point(upperLeftPointX, upperLeftPointY), width, height, false, new Color(borderColorR, borderColorG, borderColorB), new Color(innerColorR, innerColorG, innerColorB));
        		if (matcherAdded.find()) {
            		addShape(rectangle);
            	} else if (matcherModified.find()) {
            		rectangle.setSelected(true);
            		ModifyRectangleCmd modifyRectangleCommand = new ModifyRectangleCmd(shapesModel, (Rectangle)rectangle);
					modifyRectangleCommand.execute();
					panelToolSupport.pushToUndoStack(modifyRectangleCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyRectangleCommand.toString());
            	} else if (matcherSelected.find()) {
	        		SelectShapeCmd selectShapeCommand = new SelectShapeCmd(frameSupport, shapesModel, findShape(rectangle));
			        selectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(selectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(selectShapeCommand.toString());
	        	} else if (matcherDeselected.find()) {
	        		rectangle.setSelected(true);
	        		DeselectShapeCmd deselectShapeCommand = new DeselectShapeCmd(frameSupport, shapesModel, findShape(rectangle));
			        deselectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(deselectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(deselectShapeCommand.toString());
	        	}
        		return;
        	} catch (Exception e) {}
        }
           
        Matcher matcherCircle = patternCircle.matcher(line);
        if (matcherCircle.find()) {
        	short centerX = Short.parseShort(matcherCircle.group(1));
        	short centerY = Short.parseShort(matcherCircle.group(2));
        	short radius = Short.parseShort(matcherCircle.group(3));
        	int borderColorR = Integer.parseInt(matcherCircle.group(4));
        	int borderColorG = Integer.parseInt(matcherCircle.group(5));
        	int borderColorB = Integer.parseInt(matcherCircle.group(6));
        	int innerColorR = Integer.parseInt(matcherCircle.group(7));
        	int innerColorG = Integer.parseInt(matcherCircle.group(8));
        	int innerColorB = Integer.parseInt(matcherCircle.group(9));
        	try {
        		Shape circle = new Circle(new Point(centerX, centerY), radius, false, new Color(borderColorR, borderColorG, borderColorB), new Color(innerColorR, innerColorG, innerColorB));
        		if (matcherAdded.find()) {
            		addShape(circle);
            	} else if (matcherModified.find()) {
            		circle.setSelected(true);
            		ModifyCircleCmd modifyCircleCommand = new ModifyCircleCmd(shapesModel, (Circle)circle);
					modifyCircleCommand.execute();
					panelToolSupport.pushToUndoStack(modifyCircleCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyCircleCommand.toString());
            	} else if (matcherSelected.find()) {
	        		SelectShapeCmd selectShapeCommand = new SelectShapeCmd(frameSupport, shapesModel, findShape(circle));
			        selectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(selectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(selectShapeCommand.toString());
	        	} else if (matcherDeselected.find()) {
	        		circle.setSelected(true);
	        		DeselectShapeCmd deselectShapeCommand = new DeselectShapeCmd(frameSupport, shapesModel, findShape(circle));
			        deselectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(deselectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(deselectShapeCommand.toString());
	        	}
            	return;
        	} catch (Exception e) {}
        }
        
        Matcher matcherDonut = patternDonut.matcher(line);
        if (matcherDonut.find()) {
        	short centerX = Short.parseShort(matcherDonut.group(1));
        	short centerY = Short.parseShort(matcherDonut.group(2));
        	short outerRadius = Short.parseShort(matcherDonut.group(3));
        	short innerRadius = Short.parseShort(matcherDonut.group(4));
        	int borderColorR = Integer.parseInt(matcherDonut.group(5));
        	int borderColorG = Integer.parseInt(matcherDonut.group(6));
        	int borderColorB = Integer.parseInt(matcherDonut.group(7));
        	int innerColorR = Integer.parseInt(matcherDonut.group(8));
        	int innerColorG = Integer.parseInt(matcherDonut.group(9));
        	int innerColorB = Integer.parseInt(matcherDonut.group(10));
        	try {
        		Shape donut = new Donut(new Point(centerX, centerY), outerRadius, innerRadius, false, new Color(borderColorR, borderColorG, borderColorB), new Color(innerColorR, innerColorG, innerColorB));
        		if (matcherAdded.find()) {
            		addShape(donut);
            	} else if (matcherModified.find()) {
            		donut.setSelected(true);
            		ModifyDonutCmd modifyDonutCommand = new ModifyDonutCmd(shapesModel, (Donut)donut);
					modifyDonutCommand.execute();
					panelToolSupport.pushToUndoStack(modifyDonutCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyDonutCommand.toString());
            	} else if (matcherSelected.find()) {
	        		SelectShapeCmd selectShapeCommand = new SelectShapeCmd(frameSupport, shapesModel, findShape(donut));
			        selectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(selectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(selectShapeCommand.toString());
	        	} else if (matcherDeselected.find()) {
	        		donut.setSelected(true);
	        		DeselectShapeCmd deselectShapeCommand = new DeselectShapeCmd(frameSupport, shapesModel, findShape(donut));
			        deselectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(deselectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(deselectShapeCommand.toString());
	        	}
            	return;
        	} catch (Exception e) {}
        }
        
        Matcher matcherHexagon = patternHexagon.matcher(line);
        if (matcherHexagon.find()) {
        	short centerX = Short.parseShort(matcherHexagon.group(1));
        	short centerY = Short.parseShort(matcherHexagon.group(2));
        	short radius = Short.parseShort(matcherHexagon.group(3));
        	int borderColorR = Integer.parseInt(matcherHexagon.group(4));
        	int borderColorG = Integer.parseInt(matcherHexagon.group(5));
        	int borderColorB = Integer.parseInt(matcherHexagon.group(6));
        	int innerColorR = Integer.parseInt(matcherHexagon.group(7));
        	int innerColorG = Integer.parseInt(matcherHexagon.group(8));
        	int innerColorB = Integer.parseInt(matcherHexagon.group(9));
        	try {
        		Shape hexagon = new HexagonAdapter(new Point(centerX, centerY), radius, false, new Color(borderColorR, borderColorG, borderColorB), new Color(innerColorR, innerColorG, innerColorB));
        		if (matcherAdded.find()) {
            		addShape(hexagon);
            	} else if (matcherModified.find()) {
            		hexagon.setSelected(true);
            		ModifyHexagonCmd modifyHexagonCommand = new ModifyHexagonCmd(shapesModel, (HexagonAdapter)hexagon);
					modifyHexagonCommand.execute();
					panelToolSupport.pushToUndoStack(modifyHexagonCommand);
					panelToolSupport.pushToRedoStack(null);
					panelLogSupport.addTextToLog(modifyHexagonCommand.toString());
            	} else if (matcherSelected.find()) {
	        		SelectShapeCmd selectShapeCommand = new SelectShapeCmd(frameSupport, shapesModel, findShape(hexagon));
			        selectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(selectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(selectShapeCommand.toString());
	        	} else if (matcherDeselected.find()) {
	        		hexagon.setSelected(true);
	        		DeselectShapeCmd deselectShapeCommand = new DeselectShapeCmd(frameSupport, shapesModel, findShape(hexagon));
			        deselectShapeCommand.execute();
			        panelToolSupport.pushToUndoStack(deselectShapeCommand);
			        panelToolSupport.pushToRedoStack(null);
			        panelLogSupport.addTextToLog(deselectShapeCommand.toString());
	        	}
            	return;
        	} catch (Exception e) {}
        }
	}
	
	private void addShape(Shape shape) {
		AddShapeCmd addShapeCommand = new AddShapeCmd(shapesModel, shape);
		addShapeCommand.execute();
		panelToolSupport.pushToUndoStack(addShapeCommand);
		panelToolSupport.pushToRedoStack(null);
		panelLogSupport.addTextToLog(addShapeCommand.toString());
	}
	
	private Shape findShape(Shape shape) {
		if (shape instanceof Point) {
			Point point = (Point) shape;
			if (point.isSelected()) {
				for (Shape tempShape : shapesModel.getSelectedShapes()) {
					if (tempShape instanceof Point) {
						if (point.equals((Point)tempShape)) {
							return (Point)tempShape;
						}
					}
				}
			} else {
				for (Shape tempShape : shapesModel.getShapes()) {
					if (tempShape instanceof Point) {
						if (point.equals((Point)tempShape)) {
							return (Point)tempShape;
						}
					}
				}
			}
		} else if (shape instanceof Line) {
			Line line = (Line) shape;
			if (line.isSelected()) {
				for (Shape tempShape : shapesModel.getSelectedShapes()) {
					if (tempShape instanceof Line) {
						if (line.equals((Line)tempShape)) {
							return (Line)tempShape;
						}
					}
				}
			} else {
				for (Shape tempShape : shapesModel.getShapes()) {
					if (tempShape instanceof Line) {
						if (line.equals((Line)tempShape)) {
							return (Line)tempShape;
						}
					}
				}
			}
		} else if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;
			if (rectangle.isSelected()) {
				for (Shape tempShape : shapesModel.getSelectedShapes()) {
					if (tempShape instanceof Rectangle) {
						if (rectangle.equals((Rectangle)tempShape)) {
							return (Rectangle)tempShape;
						}
					}
				}
			} else {
				for (Shape tempShape : shapesModel.getShapes()) {
					if (tempShape instanceof Rectangle) {
						if (rectangle.equals((Rectangle)tempShape)) {
							return (Rectangle)tempShape;
						}
					}
				}
			}
		} else if (shape instanceof HexagonAdapter) {
			HexagonAdapter hexagon = (HexagonAdapter) shape;
			if (hexagon.isSelected()) {
				for (Shape tempShape : shapesModel.getSelectedShapes()) {
					if (tempShape instanceof HexagonAdapter) {
						if (hexagon.equals((HexagonAdapter)tempShape)) {
							return (HexagonAdapter)tempShape;
						}
					}
				}
			} else {
				for (Shape tempShape : shapesModel.getShapes()) {
					if (tempShape instanceof HexagonAdapter) {
						if (hexagon.equals((HexagonAdapter)tempShape)) {
							return (HexagonAdapter)tempShape;
						}
					}
				}
			}
		} else if (shape instanceof Donut) {
			Donut donut = (Donut) shape;
			if (donut.isSelected()) {
				for (Shape tempShape : shapesModel.getSelectedShapes()) {
					if (tempShape instanceof Donut) {
						if (donut.equals((Donut)tempShape)) {
							return (Donut)tempShape;
						}
					}
				}
			} else {
				for (Shape tempShape : shapesModel.getShapes()) {
					if (tempShape instanceof Donut) {
						if (donut.equals((Donut)tempShape)) {
							return (Donut)tempShape;
						}
					}
				}
			}
		} else if (shape instanceof Circle) {
			Circle circle = (Circle) shape;
			if (circle.isSelected()) {
				for (Shape tempShape : shapesModel.getSelectedShapes()) {
					if (tempShape instanceof Circle) {
						if (circle.equals((Circle)tempShape)) {
							return (Circle)tempShape;
						}
					}
				}
			} else {
				for (Shape tempShape : shapesModel.getShapes()) {
					if (tempShape instanceof Circle) {
						if (circle.equals((Circle)tempShape)) {
							return (Circle)tempShape;
						}
					}
				}
			}
		}
		return null;
	}

}