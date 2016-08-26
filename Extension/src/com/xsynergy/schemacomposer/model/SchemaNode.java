package com.xsynergy.schemacomposer.model;

public interface SchemaNode
{   
    public String getName();
  
    public SchemaNode[] getChildren();
    
    public SchemaNode getParent();  
    public void setParent(SchemaNode parent);  
  
    public SchemaNode[] getExclusiveChildren();
    
    public SchemaAttribute[] getAttributes();
 
    public SchemaAttribute[] getRelationshipAttributesWithParent();
            
    public String getRelationshipWithParent();
    
    public String getParentForeignKeyNameAsString();
    
    public Type getType();

    public boolean isMandatory();

    public enum Type
    {
      RELATIONAL,
      HIERARCHICAL 
    }
    
}
