package com.xsynergy.schemacomposer;

import javax.swing.JOptionPane;

import oracle.dbtools.crest.fcp.DMDiagramNode;
import oracle.dbtools.crest.model.design.Design;
import oracle.dbtools.crest.model.design.logical.LogicalDesign;
import oracle.dbtools.crest.swingui.ApplicationView;
import oracle.dbtools.crest.swingui.logical.DPVLogicalSubView;

import oracle.ide.Context;
import oracle.ide.controller.*;
import oracle.ide.docking.*;
import oracle.ide.layout.ViewId;

/**
 * TODO Provide javadoc comment for SchemaWindowController.
 */
public final class SchemaWindowController
  implements Controller, TriggerController
{

  private String getSubViewName(Context context)
  {
    try
    {
      DMDiagramNode node = (DMDiagramNode) context.getNode();
      String subViewName = node.getComponent().getName();
    
                                                          // Get the current model
      ApplicationView view = ApplicationView.getInstance();
    
                                                          // Get the Entities for the selectect subview.
      Design design = view.getCurrentDesign();
      LogicalDesign logical = design.getLogicalDesign();
      DPVLogicalSubView subView = (DPVLogicalSubView) logical.getSubViewByName(subViewName);    
      
      if(subView != null)
        return subViewName;
    }
    catch(Exception e) {}
    
    return null;    
  }

  private boolean isSubViewSelected(Context context)
  {
    return getSubViewName(context) != null;
  }

  public boolean update(IdeAction action, Context context)
  {
    boolean enabled = isSubViewSelected(context);
    
    action.setEnabled(enabled);
    return true;
  }

  public boolean handleEvent(IdeAction action, Context context)
  {        
    String subViewName = getSubViewName(context);
    
    ViewId view =
      new ViewId(SchemaWindowFactory.TYPE_ID, SchemaWindow.DOCKABLE_NAME + subViewName);
  
    Dockable dockable = DockStation.getDockStation().findDockable(view);
    DockStation.getDockStation().setDockableVisible(dockable, true);
    return true;
  }

  @Override
  public Object getInvalidStateMessage(IdeAction ideAction, Context context)
  {
    return "0:00-Schema Composer Requires a Subview to be selected."; 
  }
}
