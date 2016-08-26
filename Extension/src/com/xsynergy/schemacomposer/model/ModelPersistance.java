package com.xsynergy.schemacomposer.model;

import com.xsynergy.datamodeler.Util;

import com.xsynergy.schemacomposer.generate.Generate;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import oracle.dbtools.crest.model.design.Design;
import oracle.dbtools.crest.swingui.ApplicationView;

public class ModelPersistance
  extends Properties
{
  private static String kFILE_LOCATION = Util.FS + "files" + Util.FS + "schemacomposer.xml";
  
  private static ModelPersistance properties = null;
  
  public static ModelPersistance getInstance()
  {
    return properties==null?new ModelPersistance():properties;
  }
  
  private ModelPersistance(Properties properties)
  {
    
  }

  private ModelPersistance()
  {
    super();
    
    init();
  }
  
  private void init()
  {
    
                          // Get the current model
    ApplicationView view = ApplicationView.getInstance();

                        // Get the Entities for the selectect subview.
    Design design = view.getCurrentDesign();
    
    assert(design != null) : "Cannot initalise properties without a design";
    
    String path = design.getStoragePath();
    String filelocation = path + kFILE_LOCATION;
    
    Util.log(Util.LogLevel.DEBUG, "Properties file location " + filelocation);


    FileInputStream propertiesStream = null;
    try
    {
      propertiesStream = new FileInputStream(filelocation);
    }
    catch (FileNotFoundException e)
    {
      saveProperties();               // Save propeties will save the file if it doesn't exist.
     
      try
      {
        propertiesStream = new FileInputStream(filelocation);
      }
      catch (FileNotFoundException f)
      {
        Util.log(Util.LogLevel.ERROR, "Error creating properties file", f);
        return;
      }
    }

    try
    {
      loadFromXML(propertiesStream);
    }
    catch (InvalidPropertiesFormatException e)
    {
      Util.log(Util.LogLevel.ERROR, "Error reading from properties file", e);
      return;
    }
    catch (IOException e)
    {
      Util.log(Util.LogLevel.ERROR, "Error reading from properties file", e);
      return;
    }    
  }
    
  private void saveProperties()
  {
    // Get the current model
    ApplicationView view = ApplicationView.getInstance();

    // Get the Entities for the selectect subview.
    Design design = view.getCurrentDesign();
    
    assert(design != null) : "Cannot initalise properties without a design";
       
    String path = design.getStoragePath();
    String filelocation = path + kFILE_LOCATION;
    
    File file = new File(filelocation);

    FileOutputStream outputStream = null;
    try
    {
      outputStream = new FileOutputStream(file);
      this.storeToXML(outputStream, "Created");
      outputStream.close();
    }
    catch (FileNotFoundException f)
    {
      Util.log(Util.LogLevel.ERROR, "Error creating properties file", f);
    }
    catch (IOException f)
    {
      Util.log(Util.LogLevel.ERROR, "Error saving properties file", f);
    }

  }
  
  public void save(Model model, Generate generate)
  {
    assert(model != null) : "Model cannot be null";
    assert(generate != null) : "Generate cannot be null";
        
    String name = model.getSubViewName();
    
    String xml = generate.getModelAsXMLString();
    
    String compressXML = Util.compress(xml);
    
    put(name, compressXML);
    
    saveProperties();
  }
  
  public String load(Model model)
  {
    assert(model != null) : "Model cannot be null";
        
    String name = model.getSubViewName();
    
    String compressXML = (String)get(name);
    
    String xml = null;
    
    if(compressXML != null)
      xml = Util.decompress(compressXML);
    
    return xml;
  }
  
}
