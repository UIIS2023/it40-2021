package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import interfaces.Moveable;

public abstract class Shape implements Moveable, Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean selected;
	protected Color borderColor = new Color(0,0,0);
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Shape) {
			Shape shape = (Shape)obj;
			return shape.getBorderColor().equals(this.getBorderColor());
		}
		return false;
	}

	public abstract void setBorderColor(Color borderColor) throws Exception;
	public abstract void draw(Graphics g);
	public abstract boolean contains(Point p);
	public abstract Shape clone();
	
}