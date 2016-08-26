package com.xsynergy.schemacomposer.model;

import java.util.ArrayList;
import java.util.List;

import oracle.dbtools.crest.model.design.Design;
import oracle.dbtools.crest.model.design.logical.Entity;
import oracle.dbtools.crest.model.design.logical.LogicalDesign;
import oracle.dbtools.crest.swingui.ApplicationView;
import oracle.dbtools.crest.swingui.DesignPartView;
import oracle.dbtools.crest.swingui.logical.DPVLogicalSubView;

public class ModelSubview
  implements ModelObject
{
  public static List<ModelSubview> getAllSubviews()
  {
    ArrayList<ModelSubview> subviews = new ArrayList<ModelSubview>(); 
    
    ApplicationView view = ApplicationView.getInstance();
    Design design = view.getCurrentDesign();
    
    if(design == null)
    {
      return subviews;
    }
    
    LogicalDesign logical = design.getLogicalDesign();   
    
    if(logical  == null)
    {
      return subviews;
    }  
    
    List<DesignPartView> designSubviews = logical.getAllSubviews();
      
    for (DesignPartView subview : designSubviews)
    {
        subviews.add(new ModelSubview((DPVLogicalSubView)subview));      
    }
    
    return subviews;
  }
  
  public static ModelSubview getSubviewByName(String name) {
      assert(name != null && name.length() > 0) : "Must provide a valid subview name";
      
      for(ModelSubview subview : ModelSubview.getAllSubviews()) {
          if(subview.getName().equals(name)) {
              return subview;
          }
      }
      
      return null;
  }

  DPVLogicalSubView subview;
  
  private ModelSubview(DPVLogicalSubView designSubview)
  {
    assert(designSubview != null) : "You must set the datamodeler object for this subview";
    this.subview = designSubview;
  }
  
  @Override
  public String getName()
  {
    return this.subview.getName();
  }

  @Override
  public String getID()
  {
    return this.subview.getUIClassID();
  }
  
  public List<ModelNode> getNodes()
  {
    ArrayList<ModelNode> nodes = new ArrayList<ModelNode>();
    
    for(Object object : subview.getEntities())
    {
      Entity entity = (Entity)object;
      nodes.add(Model.get(entity, null, null) );
    }
   
    return nodes; 
  }
  
  public boolean contains(String entityName)
  {
      List<ModelNode> nodes = this.getNodes();
      
      for(ModelNode node : nodes) {
          if(node.getName().equals(entityName))
            return true;
      }
      
      return false;
  }

    @Override
    public String getComment() {
        return subview.getComment();
    }
}
