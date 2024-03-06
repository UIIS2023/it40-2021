package mvc.models;

import java.awt.Color;

public class ColorModel {

	private Color defaultBorderColor;
	private Color defaultInnerColor;

	public ColorModel() {
		defaultBorderColor = new Color(0,0,0);
		defaultInnerColor = new Color(0,0,0);
	}
	
	public Color getDefaultBorderColor() {
		return defaultBorderColor;
	}

	public void setDefaultBorderColor(Color newDefaultBorderColor) {
		defaultBorderColor = newDefaultBorderColor;
	}

	public Color getDefaultInnerColor() {
		return defaultInnerColor;
	}

	public void setDefaultInnerColor(Color newDefaultInnerColor) {
		defaultInnerColor = newDefaultInnerColor;
	}
	
}