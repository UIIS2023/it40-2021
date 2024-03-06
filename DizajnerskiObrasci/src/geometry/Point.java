package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

public class Point extends Shape {
	
	private static final long serialVersionUID = 1L;
	private short x;
	private short y;
	
	public Point() {	
		
	}
	
	public Point(short x, short y) throws Exception {
		setX(x);
		setY(y);
	}
	
	public Point(short x, short y, boolean selected, Color borderColor) throws Exception {
		this(x, y);
		setSelected(selected);
		setBorderColor(borderColor);
	}
	
	public short getX() {
		return x;
	}
	
	public void setX(short x) throws Exception {
		if(x < 0) {
			throw new Exception("The coordinate x must be greater than or equal to 0!");
		} else if (x > (1098*Toolkit.getDefaultToolkit().getScreenSize().width)/1536) {
			throw new Exception(String.format("The coordinate x must be less than or equal to %d!", (1098*Toolkit.getDefaultToolkit().getScreenSize().width)/1536));
		} else {
			this.x = x;
		}
	}
	
	public short getY() {
		return y;
	}
	
	public void setY(short y) throws Exception {
		if(y < 0) {
			throw new Exception("The coordinate y must be greater than or equal to 0!");
		} else if (y > (600*Toolkit.getDefaultToolkit().getScreenSize().height)/864) {
			throw new Exception(String.format("The coordinate y must be less than or equal to %d!", (600*Toolkit.getDefaultToolkit().getScreenSize().height)/864));
		} else {
			this.y = y;
		}
	}
	
	public double distance(Point p) {
		double distance = Math.sqrt(Math.pow(p.x-x, 2) + Math.pow(p.y-y, 2));
		return distance;
	}
	
	@Override
	public boolean contains(Point p) {
		return (distance(p) <= 2);
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBorderColor());
		g2.drawLine(x - 2, y, x + 2, y);
		g2.drawLine(x, y - 2, x, y + 2);	
		if(isSelected()) {
			g2.setColor(new Color(24, 132, 136));
			g2.drawRect(x-2, y-2, 4, 4);
			g2.fillRect(x-2, y-2, 4, 4);
		}
	}
	
	@Override
	public void moveTo(short newX, short newY) throws Exception {
		setX(newX);
		setY(newY);
	}
	
	@Override
	public void setBorderColor(Color borderColor) throws Exception {
		this.borderColor = borderColor;
	}

	@Override
	public Shape clone() {
		Point clonedPoint = new Point();
		try {
			clonedPoint.setX(this.getX());
			clonedPoint.setY(this.getY());
			clonedPoint.setSelected(this.isSelected());
			clonedPoint.setBorderColor(this.getBorderColor());
		} catch (Exception e) {}
		return clonedPoint;
	}

	@Override
	public String toString() {
		return "Point [coordinates: (" + this.getX() + 
				"," + this.getY() + "); borderColor: rgb(" + 
				this.getBorderColor().getRed() + "," + 
				this.getBorderColor().getGreen() + "," + 
				this.getBorderColor().getBlue() + ")]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point tempPoint = (Point)obj;
			return super.equals(tempPoint) && 
					tempPoint.getX() == this.getX() && 
					tempPoint.getY() == this.getY();
		}
		return false;
	}
	
}