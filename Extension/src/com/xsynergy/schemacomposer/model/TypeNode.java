package com.xsynergy.schemacomposer.model;

import com.xsynergy.util.tree.TreeNode;

import oracle.dbtools.crest.model.design.logical.Attribute;
import oracle.dbtools.crest.model.design.logical.Entity;
import oracle.dbtools.crest.model.design.logical.Relation;

public class TypeNode
  extends ModelNode
{
  public TypeNode(Entity entity, Relation relation, TreeNode<ModelNode> parent)
  {
    super(entity, relation, parent);
  }

}
