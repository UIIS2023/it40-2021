package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Rectangle extends CustomShape {
	
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private short width;
	private short height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeftPoint, short width, short height, boolean selected, Color borderColor, Color innerColor) throws Exception {
		setUpperLeftPoint(upperLeftPoint);
		setWidth(width);
		setHeight(height);
		setSelected(selected);
		setBorderColor(borderColor);
		setInnerColor(innerColor);
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	
	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}
	
	public short getWidth() {
		return width;
	}
	
	public void setWidth(short width) throws Exception {
		if(width <= 0) {
			throw new Exception("The width of the rectangle must be greater than 0!");
		}
		else {
			this.width = width;
		}
	}
	
	public short getHeight() {
		return height;
	}
	
	public void setHeight(short height) throws Exception {
		if(height <= 0) {
			throw new Exception("The height of the rectangle must be greater than 0!");
		}
		else {
			this.height = height;
		}
	}
	
	@Override
	public boolean contains(Point p) {
		return (p.getX() > upperLeftPoint.getX() && p.getX() < (upperLeftPoint.getX() + width) && p.getY() > upperLeftPoint.getY() && p.getY() < (upperLeftPoint.getY() + height));
	}
	
	@Override
	public void fill(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getInnerColor());
		g2.fillRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		fill(g2);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBorderColor());
		g2.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		if(isSelected()) {
			g2.setColor(new Color(24, 132, 236));
			g2.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
			g2.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g2.fillRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() - 2, 4, 4);
			g2.drawRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g2.fillRect(upperLeftPoint.getX() + width - 2, upperLeftPoint.getY() - 2, 4, 4);
			g2.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g2.fillRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g2.drawRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g2.fillRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + height - 2, 4, 4);	
			g2.drawRect(upperLeftPoint.getX() + (width/2) - 2, upperLeftPoint.getY() - 2, 4, 4);
			g2.fillRect(upperLeftPoint.getX() + (width/2) - 2, upperLeftPoint.getY() - 2, 4, 4);
			g2.drawRect(upperLeftPoint.getX() + (width/2) - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g2.fillRect(upperLeftPoint.getX() + (width/2) - 2, upperLeftPoint.getY() + height - 2, 4, 4);
			g2.drawRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + (height/2) - 2, 4, 4);
			g2.fillRect(upperLeftPoint.getX() - 2, upperLeftPoint.getY() + (height/2) - 2, 4, 4);
			g2.drawRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + (height/2) - 2, 4, 4);
			g2.fillRect(upperLeftPoint.getX() + width  - 2, upperLeftPoint.getY() + (height/2) - 2, 4, 4);
		}
	}
	
	@Override
	public void moveTo(short newX, short newY) throws Exception {
		upperLeftPoint.moveTo(newX, newY);
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
		Rectangle clonedRectangle = new Rectangle();
		try {
			clonedRectangle.setUpperLeftPoint(new Point());
			clonedRectangle.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
			clonedRectangle.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
			clonedRectangle.setWidth(this.getWidth());
			clonedRectangle.setHeight(this.getHeight());
			clonedRectangle.setSelected(this.isSelected());
			clonedRectangle.setBorderColor(this.getBorderColor());
			clonedRectangle.setInnerColor(this.getInnerColor());
		} catch (Exception e) {}
		return clonedRectangle;
	}

	@Override
	public String toString() {
		return "Rectangle [upperLeftPoint: (" + this.getUpperLeftPoint().getX() + 
				"," + this.getUpperLeftPoint().getY() + "); width: " + 
				this.getWidth() + "; height: " + this.getHeight() + 
				"; borderColor: rgb(" + this.getBorderColor().getRed() + 
				"," + this.getBorderColor().getGreen() + "," + 
				this.getBorderColor().getBlue() + "); innerColor: rgb(" + 
				this.getInnerColor().getRed() + "," + this.getInnerColor().getGreen() + 
				"," + this.getInnerColor().getBlue() + ")]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle tempRectangle = (Rectangle)obj;
			return super.equals(tempRectangle) && 
					tempRectangle.getUpperLeftPoint().equals(this.getUpperLeftPoint()) && 
					tempRectangle.getWidth() == this.getWidth() && 
					tempRectangle.getHeight() == this.getHeight();
		}
		return false;
	}
	
}