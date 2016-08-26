package com.xsynergy.util.tree;


/**
 * The Tree class, stores the root node.
 * @param <V> The value stored in the leaf nodes of this tree.
 * @author Colin Fitzpatrick
 * @version 1.0
 */
public class Tree<V>
  extends TreeNode<V>
{
  /**
   * Create tree, storing a value of Type V having root node of root.
   * @param <V> The value type to store
   * @param value The value to store in the Tree.
   * @return The instance of the selected tree.
   */
  public static <V> Tree getInstance(V value)
  {
    return new Tree<V>(value);
  }

  /**
   * Create a new TreeNode.
   * @param value The value to store in the tree node.
   * @param parent The parent of the tree node.  Cannot be null for a TreeNode.
   */
  public Tree(V value)
  {
    super(value);

    assert(value != null) : "Cannot have a null value";    
  }

  /**
   * Process all nodes in the tree.
   * @param process The processer to invoke for every node.
   */
  public void processTree(TreeProcessor<V> process)
  {
    processTree(process, ProcessMethod.DEPTH_FIRST);
  }
  
  /**
   * Process all nodes in the tree.
   * @param process The processer to invoke for every node.
   * @param method The processing method to use.
   */
  public void processTree(TreeProcessor<V> process, ProcessMethod method)
  {
    switch(method)
    {
      case DEPTH_FIRST:
        process.process(this, true);
        depthFirstProcess(this, process);
        break;
      
      default:
        assert(false) : "Choosen Proces Method not implemented";
    }
  }

  /**
   * Do a depth first search of the tree from node root.
   * @param root The root node to process from.
   * @param process The processing method to use.
   */
  private void depthFirstProcess(TreeObject<V> root, TreeProcessor<V> process)
  {            
    boolean first = true;

    for (TreeObject<V> child : root.getChildrenNodes() )
    {
      process.process(child, first);
      depthFirstProcess(child, process);
      first = false;
    }     
  }

  /**
   * Available Processing methods.
   */
  public enum ProcessMethod
  {
    DEPTH_FIRST
  }
}
