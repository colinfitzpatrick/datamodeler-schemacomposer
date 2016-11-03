package com.xsynergy.schemacomposer;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTree;

public class GUIPanel extends JPanel
{

    /**
     * Create the panel.
     */
    public GUIPanel()
    {
    	
    	JTree tree = new JTree();
    	tree.setShowsRootHandles(true);
    	tree.setEditable(true);
    	GroupLayout groupLayout = new GroupLayout(this);
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addComponent(tree, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addComponent(tree, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
    	);
    	setLayout(groupLayout);

    }
}
