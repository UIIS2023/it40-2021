package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import dialogs.DlgException;
import dialogs.DlgInformation;
import interfaces.SaveStrategy;

public class SaveLog implements SaveStrategy {

	private JTextPane textPane;
	
	public SaveLog(JTextPane textPane) {
		this.textPane = textPane;
	}
	
	@Override
	 public void save() {
        StyledDocument doc = textPane.getStyledDocument();
        int elementCount = doc.getLength();
        if (elementCount == 0) {
            DlgException dialogException = new DlgException("Your log is empty!");
            dialogException.setVisible(true);
            dialogException = null;
        } else {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Log");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file (.txt)", "txt");
            fileChooser.setFileFilter(filter);
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave + ".txt"))) {
                    List<String> lines = new ArrayList<>(getLines(doc));
                    Collections.reverse(lines);
                    lines.forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {}
                    });
                    DlgInformation dialogInformation = new DlgInformation("Log saved successfully!");
                    dialogInformation.setVisible(true);
                } catch (IOException e) {
                    DlgException dialogException = new DlgException("Error saving log.");
                    dialogException.setVisible(true);
                }
            }
        }
    }

    private List<String> getLines(StyledDocument doc) {
        try {
            return List.of(doc.getText(0, doc.getLength()).split("\n"));
        } catch (BadLocationException e) {
            return new ArrayList<>();
        }
    }

}