package com.xsynergy.schemacomposer.model;

import com.xsynergy.datamodeler.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.jar.Attributes;

import oracle.dbtools.crest.model.design.logical.Attribute;
import oracle.dbtools.crest.model.design.logical.Entity;
import oracle.dbtools.crest.model.design.logical.Relation;


public class EntityNode
  implements SchemaNode
{
  Entity entity = null;
  boolean selected = false;
  boolean expanded = true;

  SchemaNode parent = null;

  Model model = null;

  String rolename = null;

  public EntityNode(Entity entity, Model model)
  {
    this(entity, model, null, "");
  }

  public EntityNode(Entity entity, Model model, SchemaNode parent, String rolename)
  {
    assert(entity != null) : "Cannot Initalilse EntityNode with a null entity";
    assert(model != null) : "Cannot Initalilse EntityNode with a null model";
    
    this.entity = entity;
    this.model = model;
    this.parent = parent;
    this.rolename = rolename;
  }
  
  @Override
  public String getName()
  {
    return entity.getName();    
  }

  @Override
  public SchemaNode[] getChildren()
  {
    ArrayList<SchemaNode> children = new ArrayList<SchemaNode>(); 

    @SuppressWarnings("unchecked")
    List<Relation> relationship = entity.getRelations();
    
    for (Iterator<Relation> iter = relationship.iterator(); iter.hasNext(); )
    {
        Relation rel = iter.next();
            
        Entity child = (rel.getEntity(0).equals(this.entity) == true ? rel.getEntity(1) :  rel.getEntity(0) );
       
        boolean isSuperType = child.hasHierarchicalChildren();
       
        if(child != null && model.contains(child.getName()))
        {
          if(parent == null || (child.getName() != parent.getName()) )
          {
            // Set Roles Name
            Attribute attributes[] = rel.getFKAttributes(entity);
            
            String rolename = null;
            
            if(attributes.length > 0)
              rolename = attributes[0].getName();
            
            SchemaNode node = null;
            
            node = new EntityNode(child, model, this, rolename);
            
            children.add(node);

          }
        }  
    }
    
    return children.toArray(new SchemaNode[children.size()]);
  }

  @Override
  public SchemaAttribute[] getAttributes()
  {
    ArrayList<SchemaAttribute> children = new ArrayList<SchemaAttribute>(); 

    
    @SuppressWarnings("unchecked")
    List<Attribute> attributes = entity.getAttributes();
    
    for (Iterator<Attribute> iter = attributes.iterator(); iter.hasNext(); )
    {
      Attribute attribute = iter.next();
    
      assert(attribute != null) : "Null Attribute returned from iterator - weird";
      
      SchemaAttribute schemaAttribute = new SchemaAttribute(attribute);
      children.add(schemaAttribute);
      
    }
    
    return children.toArray(new SchemaAttribute[children.size()]);
  }

  @Override
  public SchemaNode[] getExclusiveChildren()
  {
    // TODO Implement this method
    return new SchemaNode[0];
  }

  @Override
  public SchemaAttribute[] getRelationshipAttributesWithParent()
  {
    // TODO Implement this method
    return new SchemaAttribute[0];
  }

  @Override
  public String getRelationshipWithParent()
  {
   
    Relation rel = entity.getRelationTo(((EntityNode)parent).getEntity());
  
    return rel.getTargetCardinalityString();
    
  }

  @Override
  public boolean isMandatory()
  {
    Relation rel = ((EntityNode)parent).getEntity().getRelationTo(entity);
   
    String cardinality = rel.getSourceCardinalityString();
  
    return cardinality.equals("1");
  }


  @Override
  public SchemaNode getParent()
  {
    return parent;
  }

  @Override
  public void setParent(SchemaNode parent)
  {
    // TODO Implement this method
  }

  @Override
  public String getParentForeignKeyNameAsString()
  {
    return rolename;
  }
  
  /**
   * Get the comments for this Entity.
   * @return The entities comments.
   */
  public String getComment()
  {
      return entity.getComment().replace("\n", " ").replace("\r", " ");
  }

  protected Entity getEntity()
  {
    return entity;
  }
  
  @Override
  public SchemaNode.Type getType()
  {
    if(entity.hasHierarchicalChildren())
    {
      return SchemaNode.Type.HIERARCHICAL;
    }
    else
    {
      return SchemaNode.Type.RELATIONAL;
    }
  }
}
