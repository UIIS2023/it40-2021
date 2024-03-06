package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import dialogs.DlgException;
import dialogs.DlgInformation;
import geometry.Shape;
import interfaces.SaveStrategy;
import mvc.models.ShapesModel;

public class SaveDraw implements SaveStrategy {

	private ShapesModel shapesModel;
	
	public SaveDraw(ShapesModel shapesModel) {
		this.shapesModel = shapesModel;
	}
	
	@Override
	public void save() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Draw");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Bin file (.bin)", "bin");
        fileChooser.setFileFilter(filter);
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".bin")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".bin");
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToSave.getAbsolutePath()))) {
                for (Shape shape : shapesModel.getSelectedShapes()) {
                	shape.setSelected(false);
                }
            	oos.writeObject(shapesModel.getShapes());
                DlgInformation dialogInformation = new DlgInformation("Draw saved successfully!");
                dialogInformation.setVisible(true);
                for (Shape shape : shapesModel.getSelectedShapes()) {
                	shape.setSelected(true);
                }
            } catch (IOException e) {
            	DlgException dialogException = new DlgException("Error saving draw.");
                dialogException.setVisible(true);
            }
        }
	}

}