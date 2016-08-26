package com.xsynergy.schemacomposer;

import java.util.HashMap;

import oracle.ide.Context;
import oracle.ide.docking.*;
import oracle.ide.layout.ViewId;
import oracle.ide.IdeConstants;

/**
 * TODO Provide javadoc comment for SchemaWindowFactory.
 */
public final class SchemaWindowFactory
  implements DockableFactory
{
  public static final String TYPE_ID = "SCHEMAWINDOW_TYPE";

  //private SchemaWindow dockable = null;
  private HashMap<String, SchemaWindow> dockables = new HashMap<String, SchemaWindow>();

  private synchronized SchemaWindow getDockable(String name)
  {
    if(dockables.containsKey(name))
    {
      return dockables.get(name);
    }
    
    SchemaWindow dockable = new SchemaWindow(name);
    dockables.put(name, dockable);

    DockingParam dockingParam = new DockingParam();
    dockingParam.setPosition(IdeConstants.NORTH);
    
    DockStation.getDockStation().dock(dockable, dockingParam);    

    return dockable;
  }

  public void install()
  {
  
  }

  public Dockable getDockable(ViewId viewId)
  {
    String name = viewId.getName();
    
    if (name.startsWith(SchemaWindow.DOCKABLE_NAME))
    {
      String subViewName = name.substring(SchemaWindow.DOCKABLE_NAME.length());
      return getDockable(subViewName);
    }

    return null;
  }
}
