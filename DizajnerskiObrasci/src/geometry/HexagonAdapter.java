package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import hexagon.Hexagon;

public class HexagonAdapter extends CustomShape {

	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	private Point center;
	
	public HexagonAdapter() {
		hexagon = new Hexagon(0, 0, 0);
	}
	
	public HexagonAdapter(Point center, short radius) throws Exception {
		this();
		setCenter(center);
		setRadius(radius);
	}
	
	public HexagonAdapter(Point center, short radius, boolean selected, Color borderColor, Color innerColor) throws Exception {
		this(center, radius);
		setSelected(selected);
		setBorderColor(borderColor);
		setInnerColor(innerColor);
	}
	
	public void setCenter(Point center) {
		hexagon.setX(center.getX());
		hexagon.setY(center.getY());
		this.center = center;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setRadius(short radius) throws Exception {
		if(radius <= 0) {
			throw new Exception("The radius of the hexagon must be greater than 0!");
		}
		else {
			hexagon.setR(radius);
		}
	}
	
	public int getRadius() {
		return hexagon.getR();
	}

	@Override
	protected void fill(Graphics g) {
		
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hexagon.paint(g);
		if (isSelected()) {
			g2.setColor(new Color(24, 132, 236));
			g2.drawRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 0 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 0 / 6)) - 2 / 2, 4, 4);
			g2.fillRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 0 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 0 / 6)) - 2 / 2, 4, 4);
			g2.drawRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 1 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 1 / 6)) - 2 / 2, 4, 4);
			g2.fillRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 1 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 1 / 6)) - 2 / 2, 4, 4);
			g2.drawRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 2 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 2 / 6)) - 2 / 2, 4, 4);
			g2.fillRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 2 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 2 / 6)) - 2 / 2, 4, 4);
			g2.drawRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 3 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 3 / 6)) - 2 / 2, 4, 4);
			g2.fillRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 3 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 3 / 6)) - 2 / 2, 4, 4);
			g2.drawRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 4 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 4 / 6)) - 2 / 2, 4, 4);
			g2.fillRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 4 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 4 / 6)) - 2 / 2, 4, 4);
			g2.drawRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 5 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 5 / 6)) - 2 / 2, 4, 4);
			g2.fillRect((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 5 / 6)) - 2 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 5 / 6)) - 2 / 2, 4, 4);
			g2.drawLine((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 0 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 0 / 6)) - 1 / 2, (int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 1 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 1 / 6)) - 1 / 2);
			g2.drawLine((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 1 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 1 / 6)) - 0 / 2, (int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 2 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 2 / 6)) - 0 / 2);
			g2.drawLine((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 2 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 2 / 6)) - 1 / 2, (int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 3 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 3 / 6)) - 1 / 2);
			g2.drawLine((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 3 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 3 / 6)) - 3 / 2, (int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 4 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 4 / 6)) - 3 / 2);
			g2.drawLine((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 4 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 4 / 6)) - 0 / 2, (int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 5 / 6)) - 0 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 5 / 6)) - 0 / 2);
			g2.drawLine((int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 5 / 6)) + 1 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 5 / 6)) - 3 / 2, (int)(hexagon.getX() + hexagon.getR() * Math.cos(2 * Math.PI * 0 / 6)) +1 / 2, (int)(hexagon.getY() + hexagon.getR() * Math.sin(2 * Math.PI * 0 / 6)) - 3 / 2);
		}
	}
	 
	@Override
	public boolean contains(Point p) {
		return hexagon.doesContain(p.getX(), p.getY());
	}
	
	@Override
	public void moveTo(short newX, short newY) throws Exception {
		if(newX < 0) {
			throw new Exception("The coordinate x must be greater than or equal to 0!");
		} else if (newX > 1111) {
			throw new Exception("The coordinate x must be less than or equal to 1111!");
		} else if(newY < 0) {
			throw new Exception("The coordinate y must be greater than or equal to 0!");
		} else if(newY > 559) {
			throw new Exception("The coordinate y must be less than or equal to 559!");
		} else {
			hexagon.setX(newX);
			hexagon.setY(newY);
		}
	}
	
	@Override
	public void setBorderColor(Color borderColor) throws Exception {
		hexagon.setBorderColor(borderColor);
		this.borderColor = hexagon.getBorderColor();
	}
	
	@Override
	public void setInnerColor(Color innerColor) throws Exception {
		hexagon.setAreaColor(innerColor);
		this.innerColor = hexagon.getAreaColor();
	}

	@Override
	public Shape clone() {
		HexagonAdapter clonedHexagonAdapter = new HexagonAdapter();
		try {
			clonedHexagonAdapter.setCenter(new Point((short)this.getCenter().getX(), (short)this.getCenter().getY()));
			clonedHexagonAdapter.setRadius((short)this.getRadius());
			clonedHexagonAdapter.setSelected(this.isSelected());
			clonedHexagonAdapter.setBorderColor(this.getBorderColor());
			clonedHexagonAdapter.setInnerColor(this.getInnerColor());
		} catch (Exception e) {}
		return clonedHexagonAdapter;
	}

	@Override
	public String toString() {
		return "Hexagon [center: (" + this.getCenter().getX() + 
				"," + this.getCenter().getY() + "); radius: " + 
				this.getRadius() + "; borderColor: rgb(" + 
				this.getBorderColor().getRed() + "," + 
				this.getBorderColor().getGreen() + "," + 
				this.getBorderColor().getBlue() + "); innerColor: rgb(" + 
				this.getInnerColor().getRed() + "," + this.getInnerColor().getGreen() + 
				"," + this.getInnerColor().getBlue() + ")]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			HexagonAdapter tempHexagon = (HexagonAdapter)obj;
			return super.equals(tempHexagon) && 
					tempHexagon.getCenter().equals(this.getCenter()) && 
					tempHexagon.getRadius() == this.getRadius();
		}
		return false;
	}
	
}