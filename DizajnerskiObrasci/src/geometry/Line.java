package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Line extends Shape {
	
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color borderColor) throws Exception {
		setStartPoint(startPoint);
		setEndPoint(endPoint);
		setSelected(selected);
		setBorderColor(borderColor);
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	private double length() {
		return startPoint.distance(endPoint);
	}
	
	@Override
	public boolean contains(Point p) {
		double d = startPoint.distance(p) + endPoint.distance(p);
		return ((d - length()) <= 0.1);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBorderColor());
		g2.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(isSelected()) {
			g2.setColor(new Color(24, 132, 236));
			g2.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
			g2.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g2.fillRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g2.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
			g2.fillRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);
		}
	}
	
	public void moveTo(Point newStartPoint, Point newEndPoint) throws Exception {
		startPoint.moveTo(newStartPoint.getX(), newStartPoint.getY()); 
		endPoint.moveTo(newEndPoint.getX(), newEndPoint.getY());
	}
	
	@Override
	public void moveTo(short x, short y) throws Exception {
		
	}
	
	@Override
	public void setBorderColor(Color borderColor) throws Exception {
		this.borderColor = borderColor;
	}

	@Override
	public Shape clone() {
		Line clonedLine = new Line();
		try {
			clonedLine.setStartPoint(new Point());
			clonedLine.setEndPoint(new Point());
			clonedLine.getStartPoint().setX(this.getStartPoint().getX());
			clonedLine.getStartPoint().setY(this.getStartPoint().getY());
			clonedLine.getEndPoint().setX(this.getEndPoint().getX());
			clonedLine.getEndPoint().setY(this.getEndPoint().getY());
			clonedLine.setSelected(this.isSelected());
			clonedLine.setBorderColor(this.getBorderColor());
		} catch (Exception e) {}
		return clonedLine;
	}

	@Override
	public String toString() {
		return "Line [startPoint: (" + this.getStartPoint().getX() + 
				"," + this.getStartPoint().getY() + "); endPoint: (" + 
				this.getEndPoint().getX() + "," + this.getEndPoint().getY() + 
				"); borderColor: rgb(" + this.getBorderColor().getRed() + 
				"," + this.getBorderColor().getGreen() + "," + 
				this.getBorderColor().getBlue() + ")]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line tempLine = (Line)obj;
			return super.equals(tempLine) && 
					tempLine.getStartPoint().equals(this.getStartPoint()) && 
					tempLine.getEndPoint().equals(this.getEndPoint());
		}
		return false;
	}
	
}