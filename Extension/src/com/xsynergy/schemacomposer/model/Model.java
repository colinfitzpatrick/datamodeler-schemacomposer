package com.xsynergy.schemacomposer.model;

import com.xsynergy.datamodeler.Util;
import com.xsynergy.schemacomposer.generate.Generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.dbtools.crest.model.design.Design;
import oracle.dbtools.crest.model.design.logical.Entity;
import oracle.dbtools.crest.model.design.logical.LogicalDesign;
import oracle.dbtools.crest.swingui.ApplicationView;
import oracle.dbtools.crest.swingui.DesignPartView;
import oracle.dbtools.crest.swingui.logical.DPVLogicalSubView;

import oracle.javatools.data.PropertyStorage;

public class Model
{
  SchemaNode root = null;
  
  String subViewName = null;
  
  HashMap<String, SchemaNode> modelNodes = new HashMap<String, SchemaNode>();
  
  HashMap<String, SchemaNode> entities = new HashMap<String, SchemaNode>();
  HashMap<String, SchemaNode> entityPath = new HashMap<String, SchemaNode>();

  HashMap<String, SchemaNode> attributes = new HashMap<String, SchemaNode>();
    
  public Model(String subViewName)
  {
    this.subViewName = subViewName;
    init(subViewName);
  }
  
  public void setRoot(SchemaNode root)
  {
    assert(root != null) : "Cannot have a null root";
    this.root = root;
    generateHeirarachy();
  }

  public SchemaNode getRoot()
  {
    assert(root != null) : "Instance not initalised with root node.";

    return root;
  }

  public void init(String subViewName)
  {
    assert(subViewName != null && subViewName.length() > 0 ) : "Must Have a valid subview name";
    
    modelNodes = null;
    modelNodes = new HashMap<String, SchemaNode>();
    
    SchemaNode nodes[] = getNodesForSubview(subViewName);
    
    for(SchemaNode node : nodes)
    {
      assert(node != null) : "getNodesForSubview must have returns a null node";
      modelNodes.put(node.getName(), node);
    }
  }

  public void generateHeirarachy()
  {
    assert(root != null) : "Instance not initalised with root node.";
    
    entities.clear();
    entityPath.clear();
    
    generateHeirarachy(root, 0);
  
  }

  private void generateHeirarachy(SchemaNode root, int depth)
  {
    for (SchemaNode node : root.getChildren())
    {
      if(!entities.containsKey(node.getName()) )
      {
        entities.put(node.getName(), node);

        generateHeirarachy(node, depth++);
      }
    }                
  }
  
  public boolean contains(String name)
  {
    assert(name != null && name.length() > 0) : "Must search for a valid name";
    
    return this.modelNodes.containsKey(name);
  }
  
  public SchemaNode[] getNodes()
  {
    assert(modelNodes != null) : "Instance not initalised";
    return modelNodes.values().toArray(new SchemaNode[modelNodes.size()]);
  }
  
  public SchemaNode findNodeByname(String name)
  {
    assert(name != null && name.length() > 0) : "Must search for a valid name";
    
    return this.modelNodes.get(name);
  }
  
  public static String[] getSubViews()
  {
    ArrayList<String> subViewNames = new ArrayList<String>(); 
    
    ApplicationView view = ApplicationView.getInstance();
    Design design = view.getCurrentDesign();
    
    if(design == null)
    {
      return new String[0];
    }
    
    LogicalDesign logical = design.getLogicalDesign();   
   
    if(logical  == null)
    {
      return new String[0];
    }  
    
    List<DesignPartView> subviews = logical.getAllSubviews();
      
    for (DesignPartView subview : subviews)
    {
        subViewNames.add(subview.getName());      
    }
    
    return subViewNames.toArray(new String[subViewNames.size()]);
  }
  
  private SchemaNode[] getNodesForSubview(String subViewName)
  {
    ArrayList<SchemaNode> nodes = new ArrayList<SchemaNode>();
    
    ApplicationView view = ApplicationView.getInstance();
    assert(view != null) : "There is no current view instance";

    Design design = view.getCurrentDesign();

    if(design == null)
    {
      return new SchemaNode[0];
    }
    
    LogicalDesign logical = design.getLogicalDesign();   
    
    if(logical  == null)
    {
      return new SchemaNode[0];
    }  
    
    DPVLogicalSubView subView = (DPVLogicalSubView) logical.getSubViewByName(subViewName);
    
    @SuppressWarnings("unchecked")
    List<Entity> entities = subView.getEntities();  
    
    for(Entity searchEntity : entities)
    {
      nodes.add(new EntityNode(searchEntity, this, null, "") ); 
      
    }

    return nodes.toArray(new SchemaNode[nodes.size()]);
   
  }  
  
  // TODO return the generator from a factory based in the model
  
  
  public static void PersistModel(Model model, Generate generator)
  {
    // Get the current model
    ApplicationView view = ApplicationView.getInstance();

    
    
    String modelXML = generator.getModelAsXMLString();
    Util.log(Util.LogLevel.DEBUG, modelXML);
    
    String compressedModel = Util.compress(modelXML);
    Util.log(Util.LogLevel.DEBUG, compressedModel);
    
    
    
  }

//  public static void LoadModel(Model model, Generate generator)
//  {
//
//    if(compressedModel != null)
//    {
//      String modelxml = Util.decompress(compressedModel);
//      Util.log(Util.LogLevel.DEBUG, modelxml);
//    }
//  }

  public String getSubViewName()
  {
    return this.subViewName;
  }
}
