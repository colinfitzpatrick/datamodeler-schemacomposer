package com.xsynergy.util.tree;

import com.xsynergy.jdeveloper.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * A node/leaf of the tree.
 * @param <V> The type of value stored in the node.
 * @version 1.0
 * @author Colin Fizpatrick.
 */
public class TreeNode<V>
  implements TreeObject<V>
{
  /** List of Children */
  protected ArrayList<TreeObject<V>> children;
  
  /** The parent node of this node */
  private TreeObject<V> parent = null;
  
  /** The stored value */
  private V value = null;

  /**
   * Intentionally protected default constructor.
   */
  protected TreeNode(V value)
  {      
      children = new ArrayList<TreeObject<V>>();
      
      assert(value != null) : "Cannot have a null value";
    
      this.value = value;
  }

  /**
   * Create a new TreeNode
   * @param value The value to store in the tree node.
   * @param parent The parent of the tree node.  Cannot be null for a TreeNode.
   */
  public TreeNode(V value, TreeObject<V> parent)
  {
    this(value);
    
    this.parent = parent;
  }

  /**
   * Get the list of children for this node.
   * @return The list of children.  If no children, and empty list is returned.
   */
  @Override
  public List<V> getChildren()
  {
    ArrayList<V> list = new ArrayList<V>();
    
    for(TreeObject<V> child : children )
      list.add(child.getValue());
    
    return list;
  }
  
  /**
   * Get the parent of this item.
   * @return The parent node.
   */
  @Override
  public TreeObject<V> getParent()
  {
    return parent;
  }

  /**
   * Get the value stored in this leaf node.
   * @return The value stored in this leaf node.
   */
  @Override
  public V getValue()
  {
    return value;
  }
  
  /**
   * Changes the value stored in this leaf node.
   * @param value The value to store.
   */
  public void setValue(V value)
  {
    Util.log(Util.LogLevel.DEBUG, this.value + " changed to " + value);
    this.value = value;
  }
  
  /**
   * Process all children of this node.
   * @param process The TreeProcessor to use.
   */
  public void processChildren(TreeProcessor<V> process)
  {
    for (TreeObject<V> child : children)
    {
      process.process(child, false); 
    }
  }
  
  /**
   * Adds a New child.
   * @param add The child to add.
   */
  public void add(TreeObject<V> add)
  {
    Util.log(Util.LogLevel.DEBUG, this.value + " adding child " + add.getValue());

    children.add(add);
  }

    public List<TreeObject<V>> getChildrenNodes() {
        return children;
    }
}
