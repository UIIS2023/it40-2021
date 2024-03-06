package mvc.views;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrameView extends JFrame {

	private static final long serialVersionUID = 1L;
	private PanelDrawingView panelDrawingView;
	private PanelSelectView panelSelectView;
	private PanelToolView panelToolView;
	private PanelTitleView panelTitleView;
	private PanelLogView panelLogView;
	
	public FrameView() {
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		getContentPane().setBackground(new Color(25, 25, 25));
		setUndecorated(true);
		
		panelDrawingView = new PanelDrawingView();
		panelSelectView = new PanelSelectView();
		panelToolView = new PanelToolView();
		panelTitleView = new PanelTitleView();
		panelLogView = new PanelLogView();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelTitleView, GroupLayout.DEFAULT_SIZE, (1536*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelToolView, GroupLayout.PREFERRED_SIZE, (67*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE)
					.addGap((164*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelLogView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelDrawingView, GroupLayout.DEFAULT_SIZE, (1098*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, (126*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, Short.MAX_VALUE)
					.addComponent(panelSelectView, GroupLayout.PREFERRED_SIZE, (74*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelTitleView, GroupLayout.PREFERRED_SIZE, (23*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap((86*Toolkit.getDefaultToolkit().getScreenSize().height)/864)
								.addComponent(panelDrawingView, GroupLayout.PREFERRED_SIZE, (600*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, (60*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Short.MAX_VALUE)
								.addComponent(panelLogView, GroupLayout.PREFERRED_SIZE, (95*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap((340*Toolkit.getDefaultToolkit().getScreenSize().height)/864)
								.addComponent(panelSelectView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))
						.addComponent(panelToolView, GroupLayout.PREFERRED_SIZE, (841*Toolkit.getDefaultToolkit().getScreenSize().height)/864, GroupLayout.PREFERRED_SIZE)))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public PanelDrawingView getPanelDrawingView() {
		return panelDrawingView;
	}
	
	public PanelSelectView getPanelSelectView() {
		return panelSelectView;
	}
	
	public PanelToolView getPanelToolView() {
		return panelToolView;
	}
	
	public PanelTitleView getPanelTitleView() {
		return panelTitleView;
	}
	
	public PanelLogView getPanelLogView() {
		return panelLogView;
	}
	
}