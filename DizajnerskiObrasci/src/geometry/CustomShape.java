package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class CustomShape extends Shape {
	
	private static final long serialVersionUID = 1L;
	protected Color innerColor = new Color(0,0,0);
	
	public Color getInnerColor() {
		return innerColor;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CustomShape) {
			CustomShape shape = (CustomShape)obj;
			return super.equals(shape) &&
					shape.getInnerColor().equals(this.getInnerColor());
		}
		return false;
	}

	public abstract void setInnerColor(Color innerColor) throws Exception;
	protected abstract void fill(Graphics g);
	
}