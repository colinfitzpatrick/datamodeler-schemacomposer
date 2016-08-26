package com.xsynergy.schemacomposer.model;

import com.xsynergy.util.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import oracle.dbtools.crest.model.design.logical.Attribute;
import oracle.dbtools.crest.model.design.logical.Entity;
import oracle.dbtools.crest.model.design.logical.Relation;

public abstract class ModelNode
  implements ModelObject
{
  /** The underlying modeler object */
  Entity entity;
  
  /** The Relationship with it's parent node */ 
  Relation parentRelation;
  
  /** The tree structure this is part of */
  TreeNode<ModelNode> node;
  
  /** The list of attributes **/
  ArrayList<ModelAttribute> attributes; 

  /** The the parent node **/ 
  TreeNode<ModelNode> parent;
  
  /** Is this node selected  **/
  boolean selected = false;
  
  /**
   * Crreates a new ModelNode.
   * @param entity The Data Modeler Entity this is based.
   * @param rolename The Role name of the relations between this object and it's parent.
   * @param parent The parent Tree Item.
   */
  protected ModelNode(Entity entity, Relation relation, TreeNode<ModelNode> parent)
  {
    assert(entity != null) : "Cannot have a null Entity";
    
    this.parentRelation = relation;
    this.parent = parent;
    this.entity = entity;

    node = new TreeNode<ModelNode>(this, parent);

    attributes = new ArrayList<ModelAttribute>();
    addAttributes();    
  }
  
  private void addAttributes()
  {
    for(Object object : entity.getAttributes())
    {
      Attribute attribute = (Attribute)object;
      attributes.add(new ModelAttribute(attribute) );
    }
  }
  
  /**
   * Retrives the name of this entity.
   * @return The name.
   */
  public String getName()
  {    
    return entity.getName(); 
  }

  /**
   * The GUID of this item.  This will not change if the entity name changes.
   * @return The GUID.
   */
  public String getID()
  {    
    return entity.getObjectID();
  }


  /**
   * Adds a new child.
   * @param modelNode The Child to add.
   */
  public void add(ModelNode modelNode)  
  {
    node.add(modelNode.getTreeNode());
  }
  
  /**
   * The list of attributes.  
   * @return The list of attributes, or an empty list if no attributes. 
   */
  public List<ModelAttribute> getAttributes()
  {
    return attributes;
  }
    
  /**
   * Get the TreeNode for this object.
   * @return The TreeNode
   */
  protected TreeNode<ModelNode> getTreeNode()
  {
    return node;
  }

    /**
     * List the possible child for this node.
     * @param subview The current subview
     * @return A list of possible Children.
     */
    @SuppressWarnings("unchecked")
    public List<ModelNode> getChildren(ModelSubview subview)
  {
      ArrayList<ModelNode> children = new ArrayList<ModelNode>(); 
      
      for (Relation relation :  (List<Relation>)entity.getRelations())
      {
          Entity child = (relation.getEntity(0).equals(this.entity) == true ? relation.getEntity(1) :  relation.getEntity(0) );
         
          boolean isSuperType = child.hasHierarchicalChildren();
         
          if(child != null && subview.contains(child.getName()))
          {
            if(parent == null || (child.getName() != parent.getValue().getName()) ) //TODO Do this based on the relationship instead.
            {
              ModelNode node = null;
              
              if(isSuperType)
                  node = new TypeNode(child, relation, this.getTreeNode());
              else
                  node = new EntityNode(child, relation, this.getTreeNode());
                        
              add(node);    
                            
              children.add(node);
            }
          }  
      }
                                                                                    //TODO create a ModelRelationship class
      for(Entity child : (List<Entity>)entity.getHierarchicalChildren()) {
          
          boolean isSuperType = child.hasHierarchicalChildren();
          
          ModelNode node = null;
          
          if(isSuperType)
              node = new TypeNode(child, null, parent);
          else
              node = new EntityNode(child, null, parent);
          
          add(node);
          
          children.add(node);
      }
      
      return children;
  }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getComment() {
        return entity.getComment();
    }

    public String toString() {
        return getName();
    }
    
    public String getParentForeignKeyNameAsString() {
        
        if(parentRelation != null)
        {
            Attribute attributes[] = parentRelation.getFKAttributes(entity);

            if(attributes.length > 0)
              return attributes[0].getName();
        }
        
        return "unknown";
    }
    
    
    
    public Cardinality getCardinality() {
        
        if(this.parent == null || this.parentRelation == null)
            return null;
        
        if(parentRelation.isOneToOne())
            return Cardinality.MANDATORY;
        
        String source = parentRelation.getSourceCardinalityString();
        String target = parentRelation.getTargetCardinalityString();
        
        if(source.equals("1") && target.equals("*")) {
            return Cardinality.ONE_TO_MANY;
        }
        
        if(source.equals("0") && target.equals("*")) {
            return Cardinality.ZERO_TO_MANY;
        }        
    
        return Cardinality.OPTIONAL;
    }

    /**
     * The Cardinality of a node, with it's parent.
     */
  public enum Cardinality
  {
    ONE_TO_MANY("1","unbounded"),
    ZERO_TO_MANY("0", "unbounded"),
    MANDATORY("1","1"),
    OPTIONAL("0","1");
    
    private String source;
    private String target;
    
    private Cardinality(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
    }
}
