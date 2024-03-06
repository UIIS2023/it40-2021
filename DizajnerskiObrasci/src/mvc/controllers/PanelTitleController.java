package mvc.controllers;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import mvc.models.ColorModel;
import mvc.models.TextModel;
import mvc.views.FrameView;
import mvc.views.PanelTitleView;
import supports.FrameSupport;

public class PanelTitleController implements PropertyChangeListener {

	private FrameSupport frameSupport;
	private TextModel textModel;
	private ColorModel colorModel;
	private PanelTitleView view;
	
	public PanelTitleController(FrameSupport frameSupport, TextModel textModel, ColorModel colorModel, FrameView frameView) {
		view = frameView.getPanelTitleView();
		this.frameSupport = frameSupport;
		this.textModel = textModel;
		this.colorModel = colorModel;
		this.textModel.addPropertyChangeListener(this);
		this.textModel.setCenterText(null);
		view.getButtonDefaultBorderColor().setBackground(this.colorModel.getDefaultBorderColor());
		view.getButtonDefaultInnerColor().setBackground(this.colorModel.getDefaultInnerColor());
	}
	
	public void mouseEntered(JButton activeButton, String newState) {
		if (newState.equals("Exit")) {
			activeButton.setBackground(new Color(178,34,34));
		} else {
			activeButton.setBackground(new Color(25,25,25));
		}
		activeButton.setContentAreaFilled(true);
		textModel.setCenterText(newState);
	}
	
	public void mouseExited(JButton activeButton) {
		activeButton.setBackground(new Color(50,50,50));
		textModel.setCenterText(null);
	}
	
	public void mousePressed(JButton activeButton) {
		activeButton.setContentAreaFilled(false);
	}
	
	public void buttonExitAction() {
		frameSupport.executeExit();
	}
	
	public void buttonMinimizeAction() {
		frameSupport.executeMinimize();
	}
	
	public void buttonDefaultColorEntered(String newState) {
		textModel.setCenterText(newState);
	}
	
	public void buttonDefaultColorExited() {
		textModel.setCenterText(null);
	}
	
	public void buttonDefaultBorderColorClicked() {
		Color tempColor = JColorChooser.showDialog(null, "Choose the default border color", this.colorModel.getDefaultBorderColor());
		if(tempColor != null) {
			this.colorModel.setDefaultBorderColor(tempColor);
		}
		view.getButtonDefaultBorderColor().setBackground(this.colorModel.getDefaultBorderColor());
	}
	
	public void buttonDefaultInnerColorClicked() {
		Color tempColor = JColorChooser.showDialog(null, "Choose the default inner color", this.colorModel.getDefaultInnerColor());
		if(tempColor != null) {
			this.colorModel.setDefaultInnerColor(tempColor);
		}
		view.getButtonDefaultInnerColor().setBackground(this.colorModel.getDefaultInnerColor());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("centerTextChanged".equals(evt.getPropertyName())) {
			if (evt.getNewValue() != null) {
				view.getLabelCenter().setText((String)evt.getNewValue());
			} else {
				if (textModel.getFixedCenterText() != null) {
					view.getLabelCenter().setText(textModel.getFixedCenterText());
				} else {
					view.getLabelCenter().setText(null);
				}
			}
		} else if ("fixedCenterTextChanged".equals(evt.getPropertyName())) {
			if (evt.getNewValue() != null) {
				view.getLabelCenter().setText((String)evt.getNewValue());
			} else {
				view.getLabelCenter().setText(null);
			}
		} else if ("leftTextChanged".equals(evt.getPropertyName())) {
			if (evt.getNewValue() != null) {
				view.getLabelLeft().setText((String)evt.getNewValue());
			} else {
				view.getLabelLeft().setText(null);
			}
		}
	}
	
}