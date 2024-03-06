package mvc.controllers;

import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import mvc.views.FrameView;
import mvc.views.PanelLogView;
import supports.FrameSupport;

public class PanelLogController implements PropertyChangeListener {

	private PanelLogView view;
	
	public PanelLogController(FrameSupport frameSupport, FrameView frameView) {
		view = frameView.getPanelLogView();
		frameSupport.getPanelLogSupport().addPropertyChangeListener(this);
		frameSupport.getPanelToolSupport().sendLogTextPane(view.getTextPane());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("addTextToLog".equals(evt.getPropertyName())) {
			StyledDocument doc = view.getTextPane().getStyledDocument();
		    SimpleAttributeSet style = new SimpleAttributeSet();
		    StyleConstants.setFontSize(style, (int)(15 * (Math.min((float)(426 * Toolkit.getDefaultToolkit().getScreenSize().width) / 1536 / 426, (float)(276 * Toolkit.getDefaultToolkit().getScreenSize().height) / 864 / 276))));
		    String logText = (String)evt.getNewValue() + "\n";
		    try {
		    	doc.insertString(0, logText, style);
		    	view.getTextPane().setCaretPosition(0);
		    	
		    } catch (BadLocationException e) {}
		}
	}
	
}