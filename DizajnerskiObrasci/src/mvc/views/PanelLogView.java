package mvc.views;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;

public class PanelLogView extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextPane textPane;
    private JScrollPane scrollPane;

    public PanelLogView() {
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        setBackground(new Color(50, 50, 50));

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFocusable(false);
        textPane.setBackground(new Color(245, 245, 245));
        scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addGap((5*Toolkit.getDefaultToolkit().getScreenSize().width)/1536)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, (426*Toolkit.getDefaultToolkit().getScreenSize().width)/1536, Short.MAX_VALUE)
        			.addGap((5*Toolkit.getDefaultToolkit().getScreenSize().width)/1536))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap((5*Toolkit.getDefaultToolkit().getScreenSize().height)/864)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, (276*Toolkit.getDefaultToolkit().getScreenSize().height)/864, Short.MAX_VALUE)
        			.addGap((5*Toolkit.getDefaultToolkit().getScreenSize().height)/864))
        );
        setLayout(groupLayout);
    }

    public JTextPane getTextPane() {
        return textPane;
    }
    
    public JScrollPane getScrollPane() {
    	return scrollPane;
    }
}