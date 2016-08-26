package com.xsynergy.schemacomposer;

import java.awt.Component;

import javax.swing.JButton;

import javax.swing.JComponent;

import oracle.ide.Context;
import oracle.ide.docking.*;
import oracle.ide.layout.ViewId;
import oracle.ide.test.Bundle;

/**
 * TODO Provide javadoc comment for SchemaWindow.
 */
public final class SchemaWindow
  extends DockableWindow
{
  private String subviewName = "";

  private JComponent ui;

  public static final String DOCKABLE_NAME = "SCHEMAWINDOW_VIEW-";

  public SchemaWindow(String subviewName)
  {
    assert(subviewName != null) : "Cannot have a null subview name";
    this.subviewName = subviewName;
  }

  @Override
  public Component getGUI() {
      if (ui == null) {
          ui = new Panel(subviewName);
      }
      
      return ui;
  }

  @Override
  public String getTitleName()
  {
    return subviewName + " [Schema Composer]";
  }

}
