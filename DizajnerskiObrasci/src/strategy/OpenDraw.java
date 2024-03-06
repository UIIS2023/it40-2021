package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import dialogs.DlgException;
import geometry.Shape;
import interfaces.OpenStrategy;
import mvc.models.ShapesModel;
import supports.FrameSupport;
import supports.PanelDrawingSupport;
import supports.PanelSelectSupport;
import supports.PanelToolSupport;

public class OpenDraw implements OpenStrategy {

	private PanelDrawingSupport panelDrawingSupport;
	private PanelToolSupport panelToolSupport;
	private PanelSelectSupport panelSelectSupport;
	private ShapesModel shapesModel;
	private JTextPane logTextPane;
	
	public OpenDraw(FrameSupport frameSupport, ShapesModel shapesModel, JTextPane logTextPane) {
		panelDrawingSupport = frameSupport.getPanelDrawingSupport();
		panelToolSupport = frameSupport.getPanelToolSupport();
		panelSelectSupport = frameSupport.getPanelSelectSupport();
		this.shapesModel = shapesModel;
		this.logTextPane = logTextPane;
	}
	
	@Override
	public void open() {
		JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Open Draw");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Bin file (.bin)", "bin");
        fileChooser.setFileFilter(filter);
	    int userSelection = fileChooser.showOpenDialog(null);
	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToLoad = fileChooser.getSelectedFile();
	        if (!fileToLoad.getName().toLowerCase().endsWith(".bin")) {
	            fileToLoad = new File(fileToLoad.getAbsolutePath() + ".bin");
	        }
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToLoad))) {
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
	        	@SuppressWarnings("unchecked")
				List<Shape> loadedShapes = (List<Shape>) ois.readObject();
	            shapesModel.setShapes(loadedShapes);
	        } catch (IOException | ClassNotFoundException e) {
	            DlgException dialogException = new DlgException("Error loading draw.");
	            dialogException.setVisible(true);
	        }
	    }
	}
	
}