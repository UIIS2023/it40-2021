package mvc.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import geometry.Shape;
import mvc.controllers.PanelDrawingController;

public class PanelDrawingView extends JPanel {

	private static final long serialVersionUID = 1L;
	private PanelDrawingController controller;
	private List<Shape> shapesFromModel;

	public PanelDrawingView() {
		setBackground(new Color(255, 255, 255));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				controller.mouseEntered();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				controller.mouseExited();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				controller.mouseMoved(e);
			}
		});
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(shapesFromModel != null) {
			Iterator<Shape> iterator = shapesFromModel.iterator();
			while (iterator.hasNext()) {
				iterator.next().draw(g);
			}
		}
	}
	
	public void setController(PanelDrawingController pdc) {
		controller = pdc;
	}
	
	public void setShapesFromModel(List<Shape> shapesFromModel) {
		this.shapesFromModel = shapesFromModel;
	}
	
}