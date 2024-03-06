package geometry;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	
	private static final long serialVersionUID = 1L;
	private short innerRadius;
	
	public Donut() {
	}
	
	public Donut(Point center, short radius, short innerRadius, boolean selected, Color borderColor, Color innerColor) throws Exception {
		super(center, radius, selected, borderColor, innerColor);
		setInnerRadius(innerRadius);
	}
	
	public short getInnerRadius() {
		return innerRadius;
	}
	
	public void setInnerRadius(short innerRadius) throws Exception {
		if(innerRadius <= 0) {
			throw new Exception("The inner radius of the donut must be greater than 0!");
		}
		else if(innerRadius >= getRadius()) {
			throw new Exception("The inner radius must be less than the outer radius!");
		}
		else {
			this.innerRadius = innerRadius;
		}
	}
	
	@Override
	public void setRadius(short radius) throws Exception {
		if(radius <= 0) {
			throw new Exception("The outer radius of the donut must be greater than 0!");
		}
		else {
			super.setRadius(radius);
		}
	}
	
	@Override
	public boolean contains(Point p) {
		return ((getCenter().distance(p) <= getRadius()+2) && (getCenter().distance(p) >= innerRadius));
	}
	
	@Override
	public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke defaultStroke = g2d.getStroke();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D outerCircle = new Ellipse2D.Double(getCenter().getX() - getRadius(), getCenter().getY() - getRadius(), 2 * getRadius(), 2 * getRadius());
        Ellipse2D innerCircle = new Ellipse2D.Double(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2 * innerRadius, 2 * innerRadius);
        Area donutArea = new Area(outerCircle);
        donutArea.subtract(new Area(innerCircle));
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(borderColor);
        g2d.draw(donutArea);
        g2d.setColor(innerColor);
        g2d.fill(donutArea);
        if (isSelected()) {
        	g2d.setStroke(new BasicStroke(1));
        	g2d.setColor(new Color(24, 132, 236));
            g2d.draw(donutArea);
            int offset = 2;
            int thickness = -(innerRadius - getRadius());
            g2d.drawRect(getCenter().getX() - offset, getCenter().getY() - innerRadius - offset, 4, 4);
            g2d.fillRect(getCenter().getX() - offset, getCenter().getY() - innerRadius - offset, 4, 4);
            g2d.drawRect(getCenter().getX() - offset, getCenter().getY() + innerRadius - offset, 4, 4);
            g2d.fillRect(getCenter().getX() - offset, getCenter().getY() + innerRadius - offset, 4, 4);
            g2d.drawRect(getCenter().getX() - innerRadius - offset, getCenter().getY() - offset, 4, 4);
            g2d.fillRect(getCenter().getX() - innerRadius - offset, getCenter().getY() - offset, 4, 4);
            g2d.drawRect(getCenter().getX() + innerRadius - offset, getCenter().getY() - offset, 4, 4);
            g2d.fillRect(getCenter().getX() + innerRadius - offset, getCenter().getY() - offset, 4, 4);
            g2d.drawRect(getCenter().getX() - offset, getCenter().getY() - thickness - innerRadius - offset, 4, 4);
            g2d.fillRect(getCenter().getX() - offset, getCenter().getY() - thickness - innerRadius - offset, 4, 4);
            g2d.drawRect(getCenter().getX() - offset, getCenter().getY() + thickness + innerRadius - offset, 4, 4);
            g2d.fillRect(getCenter().getX() - offset, getCenter().getY() + thickness + innerRadius - offset, 4, 4);
            g2d.drawRect(getCenter().getX() - thickness - innerRadius - offset, getCenter().getY() - offset, 4, 4);
            g2d.fillRect(getCenter().getX() - thickness - innerRadius - offset, getCenter().getY() - offset, 4, 4);
            g2d.drawRect(getCenter().getX() + thickness + innerRadius - offset, getCenter().getY() - offset, 4, 4);
            g2d.fillRect(getCenter().getX() + thickness + innerRadius - offset, getCenter().getY() - offset, 4, 4);
        }
        g2d.setStroke(defaultStroke);
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
		Donut clonedDonut = new Donut();
		try {
			clonedDonut.setCenter(new Point());
			clonedDonut.getCenter().setX(this.getCenter().getX());
			clonedDonut.getCenter().setY(this.getCenter().getY());
			clonedDonut.setRadius(this.getRadius());
			clonedDonut.setInnerRadius(this.getInnerRadius());
			clonedDonut.setSelected(this.isSelected());
			clonedDonut.setBorderColor(this.getBorderColor());
			clonedDonut.setInnerColor(this.getInnerColor());
		} catch (Exception e) {}
		return clonedDonut;
	}

	@Override
	public String toString() {
		return "Donut [center: (" + this.getCenter().getX() + 
				"," + this.getCenter().getY() + "); outerRadius: " + 
				this.getRadius() + "; innerRadius: " + this.getInnerRadius() + 
				"; borderColor: rgb(" + this.getBorderColor().getRed() + "," + 
				this.getBorderColor().getGreen() + "," + this.getBorderColor().getBlue() + 
				"); innerColor: rgb(" + this.getInnerColor().getRed() + "," + 
				this.getInnerColor().getGreen() + "," + this.getInnerColor().getBlue() + ")]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut tempDonut = (Donut)obj;
			return super.equals(tempDonut) && 
					tempDonut.getInnerRadius() == this.getInnerRadius();
		}
		return false;
	}
	
}