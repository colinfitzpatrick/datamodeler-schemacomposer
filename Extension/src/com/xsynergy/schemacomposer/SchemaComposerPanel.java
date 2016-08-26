package com.xsynergy.schemacomposer;

import javax.swing.JTextField;

import oracle.ide.panels.DefaultTraversablePanel;
import oracle.ide.panels.TraversableContext;
import oracle.ide.panels.TraversalException;

import oracle.javatools.ui.layout.FieldLayoutBuilder;

/**
 * Schema Composer preference page implementation.
 */
public final class SchemaComposerPanel
  extends DefaultTraversablePanel
{
  private final JTextField namespace = new JTextField();
  
  public SchemaComposerPanel()
  {
    layoutControls();
    // TODO lay out the Schema Composer preferences page
  }

  private void layoutControls() {
    
    try
    {
      
      FieldLayoutBuilder b = new FieldLayoutBuilder(this);
      b.add(b.field().label().withText("&Namespace:").component(namespace));
    }
    catch(Throwable e)
    {
      e.toString();
    }
  }

  @Override
  public void onEntry(TraversableContext context)
  {
    final Preferences prefs = getPreferences(context);
 
    namespace.setText(prefs.getNamespace() );
    
    super.onEntry(context);    
  }

  @Override
  public void onExit(TraversableContext context)
    throws TraversalException
  {
    final Preferences prefs = getPreferences(context);
  
    prefs.setNamespace(namespace.getText().trim());
    
    super.onExit(context);   
  }
  
  
  private static Preferences getPreferences(TraversableContext tc)
  {
  // If you've implemented CoolFeaturePrefs according to the typica
  // implementation pattern given above, this is how you attach the
  // adapter class to the defensive copy of the preferences being
  // edited by the Tools->Preferences dialog.
    return Preferences.getInstance(tc.getPropertyStorage());
  }
  

  
}
