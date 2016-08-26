package com.xsynergy.util.tree;

import java.util.List;

/**
 * Base Object for all Tree Object.  Stores a tree structure store values of type V.
 * @param <V> The type of value this tree object should store.
 * @author Colin Fitzpatrick
 * @version 1.0
 */
public interface TreeObject<V>
{
  /** Gets the parent object of this TreeObject.  Returns null if no parent object exists. */
  TreeObject<V> getParent();

  /** Get a list of children.  Returns an empty list of no children are present. */
  List<V> getChildren();

  /**
   * Get the value of the item stored in this TreeObject
   * @return tThe store value.
   */
  V getValue();
  
    public List<TreeObject<V>> getChildrenNodes();
}
