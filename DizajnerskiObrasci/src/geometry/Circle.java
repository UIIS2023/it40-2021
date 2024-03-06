package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Circle extends CustomShape {
	
	private static final long serialVersionUID = 1L;
	private Point center;
	private short radius;
	
	public Circle() {
		
	}
	
	public Circle(Point center, short radius) throws Exception {
		setCenter(center);
		setRadius(radius);
	}
	
	public Circle(Point center, short radius, boolean selected, Color borderColor, Color innerColor) throws Exception {
		this(center, radius);
		setSelected(selected);
		setBorderColor(borderColor);
		setInnerColor(innerColor);
	}
	
	public Point getCenter() {
		return this.center;
	}
	
	public void setCenter(Point center) { 
		this.center = center;
	}
	
	public short getRadius() {
		return this.radius;
	}
	
	public void setRadius(short radius) throws Exception {
		if(radius <= 0) {
			throw new Exception("The radius of the circle must be greater than 0!");
		}
		else {
			this.radius = radius;
		}
	}
	
	@Override
	public boolean contains(Point p) {
		return (center.distance(p) <= radius);
	}
	
	@Override
	protected void fill(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getInnerColor());
		g2.fillOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		fill(g2);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBorderColor());
		g2.drawOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
		if(isSelected()) {
			g2.setColor(new Color(24, 132, 236));
			g2.drawOval(center.getX()-radius, center.getY()-radius, radius*2, radius*2);
			g2.drawRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
			g2.fillRect(center.getX() - radius - 2, center.getY() - 2, 4, 4);
			g2.drawRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
			g2.fillRect(center.getX() + radius - 2, center.getY() - 2, 4, 4);
			g2.drawRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
			g2.fillRect(center.getX() - 2, center.getY() - radius - 2, 4, 4);
			g2.drawRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
			g2.fillRect(center.getX() - 2, center.getY() + radius - 2, 4, 4);
		}
	}
	
	@Override
	public void moveTo(short newX, short newY) throws Exception {
		center.moveTo(newX, newY);
	}
	
	@Override
	public void setBorderColor(Color borderColor) throws Exception {
		this.borderColor = borderColor;
	}
	
	@Override
	public void setInnerColor(Color innerColor) throws Exception {
		this.innerColor = innerColor;
	}

	@Override
	public Shape clone() {
		Circle clonedCircle = new Circle();
		try {
			clonedCircle.setCenter(new Point());
			clonedCircle.getCenter().setX(this.getCenter().getX());
			clonedCircle.getCenter().setY(this.getCenter().getY());
			clonedCircle.setRadius(this.getRadius());
			clonedCircle.setSelected(this.isSelected());
			clonedCircle.setBorderColor(this.getBorderColor());
			clonedCircle.setInnerColor(this.getInnerColor());
		} catch (Exception e) {}
		return clonedCircle;
	}

	@Override
	public String toString() {
		return "Circle [center: (" + this.getCenter().getX() + 
				"," + this.getCenter().getY() + "); radius: " + 
				this.getRadius() + "; borderColor: rgb(" + this.getBorderColor().getRed() + 
				"," + this.getBorderColor().getGreen() + "," + 
				this.getBorderColor().getBlue() + "); innerColor: rgb(" + 
				this.getInnerColor().getRed() + "," + this.getInnerColor().getGreen() + 
				"," + this.getInnerColor().getBlue() + ")]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle tempCircle = (Circle)obj;
			return super.equals(tempCircle) && 
					tempCircle.getCenter().equals(this.getCenter()) && 
					tempCircle.getRadius() == this.getRadius();
		}
		return false;
	}
	
}