package com.xsynergy.schemacomposer.model;

import oracle.dbtools.crest.model.design.logical.Attribute;

public class ModelAttribute
    implements ModelObject
  {
    /**The underlying Logical Model object
     */
    private Attribute attribute;

    /** Determines if the node is selected */
    private boolean selected;

    public ModelAttribute(Attribute attribute) {
        assert(attribute != null);
        
        this.attribute = attribute;
    }

    /**
     * The name of the attribute.
     * @return The attribute name.
     */
    @Override
    public String getName()
    {
      return attribute.getName();
    }

    /**
     * Get the DM ID of the object.
     * @return The ID name.
     */
    @Override
    public String getID()
    {
      return attribute.getObjectID();
    }

    /**
     * Get the size of the item.
     * @return The size.
     */
    public String getSize()
    {
      return attribute.getSize();
    }

    /**
     * Determines of the attribute is mandatory
     * @return
     */
    public boolean isMandatory()
    {
      return attribute.isMandatory();
    }

    /**
     * Determine if the attribute is a foreign key, or part of a foreign key.
     * @return
     */
    public boolean isForeignKey()
    {
      return attribute.isFKAttribute();
    }

    /**
     * Determine if the attribute is a primary key, or part of a primary key.
     * @return
     */
    public boolean isPrimaryKey()
    {
      return attribute.isPKElement();
    }

    /**
     * Get the data type name.
     * @return
     */
    public String getDataType()
    {
      return attribute.getDataType().getName();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public String getComment() {
        return attribute.getComment();
    }
}
