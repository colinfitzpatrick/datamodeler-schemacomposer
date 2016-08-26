package com.xsynergy.schemacomposer.model;

import oracle.dbtools.crest.model.design.logical.Attribute;

public class SchemaAttribute
{
  Attribute attribute;
  
  boolean selected = false;
  
  public SchemaAttribute(Attribute attribute)
  {
    this(attribute, true);
  }
  
  public SchemaAttribute(Attribute attribute, boolean selected)
  {
    assert(attribute != null) : "Cannot have a null attribute";
    
    this.attribute  = attribute;
    this.selected = selected;
  }
  
  public String getName()
  {
    return attribute.getName();
  }
  
  public String getUniqueIdentifierAsString()
  {
    return attribute.getEntity().getName() + attribute.getName();
  }
  
  public Datatype getDataType()
  {    
    return new Datatype(attribute.getDataType());
  }
  
  public boolean isMandatory()
  {
    return attribute.isMandatory();
  }
  
  public boolean isForeignKey()
  {
    return attribute.isFKAttribute();
  }  

  public boolean isPrimaryKey()
  {
    return attribute.isPKElement();
  }

  public boolean isSelected()
  {
    return selected;
  }
  
  public void setSelected(boolean selected)
  {
    this.selected = selected;
  }

  public String getComments()
  {
    return attribute.getComment();
  }

  public final class Datatype
  {
      private oracle.dbtools.crest.model.design.datatypes.Datatype type = null;
      
      protected Datatype(oracle.dbtools.crest.model.design.datatypes.Datatype type)
      {
        assert(type != null) : "Cannot have a null dataytype";
        this.type = type;
      }
      
      public String getDatatypeAsString()
      {
        return type.getName();
      }
      
      public String getSize()
      {
        return type.getSize();
      }
  }
}
