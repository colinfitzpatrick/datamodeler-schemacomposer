package com.xsynergy.util.tree;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class TreeTest
{
  Tree<String> tree;
  TreeNode<String> node1;
  TreeNode<String> node2;

  private static int counter = 0;

  @SuppressWarnings("unchecked")
  public TreeTest()
  {    
    tree = Tree.<String>getInstance("root");
    
    node1 = new TreeNode<String>("node1", tree);
    node2 = new TreeNode<String>("node2", tree);
    
    tree.add(node1);
    tree.add(node2);
    
  }

  public static void main(String[] args)
  {
    String[] args2 =
    {
      TreeTest.class.getName()
    };
    JUnitCore.main(args2);
  }

  /**
   * @see Tree#processTree(TreeProcessor)
   */
  @Test
  public void testProcessTree()
  {    
    tree.processChildren(new TreeProcessor<String>(){
      @Override
      public void process(TreeObject<String> node, boolean firstNode)
      {
        assertTrue("Node should not be null", node != null);
        assertTrue("Node should have a value", node.getValue() != null);
        TreeTest.counter++;
      }
    });
    
    assertTrue("Not all nodes were traversed", tree.children.size() == counter);

    TreeTest.counter = 0;
  }

  /**
   * @see com.xsynergy.util.TreeNode<V>#getChildren()
   */
  @Test
  public void testGetChildren()
  {
    assertTrue("Tree does not have the correct number of children", tree.children.size() == 2);
    assertTrue("Tree's first child is incorrect", tree.children.get(0).getValue().equals("node1"));
    assertTrue("Tree's second child is incorrect", tree.children.get(1).getValue().equals("node2"));    
  }

  /**
   * @see com.xsynergy.util.TreeNode<V>#getParent()
   */
  @Test
  public void testGetParent()
  {
    assertTrue("Parent of tree should be null", tree.getParent() == null);
    assertTrue("Node 1's parent should be root", node1.getParent().getValue().equals(tree.getValue()));
  }

  /**
   * @see com.xsynergy.util.TreeNode<V>#getValue()
   */
  @Test
  public void testGetValue()
  {
    assertTrue("Tree's value should be root", tree.getValue().equals("root"));
    assertTrue("Node's value should be root", node1.getValue().equals("node1"));
    assertTrue("Node's value should be root", node2.getValue().equals("node2"));
    
    assertFalse("Node's value should be root", node1.getValue().equals("node2"));
    assertFalse("Node's value should be root", node2.getValue().equals("node1"));    
  }

  /**
   * @see com.xsynergy.util.TreeNode<V>#setValue(V)
   */
  @Test
  public void testSetValue()
  {
    node1.setValue("newNode1");
    
    assertTrue("Tree's value should be root", node1.getValue().equals("newNode1"));
  }

  /**
   * @see com.xsynergy.util.TreeNode<V>#processChildren(TreeProcessor)
   */
  @Test
  public void testProcessChildren()
  {
    tree.processChildren(new TreeProcessor<String>(){
      @Override
      public void process(TreeObject<String> node, boolean firstNode)
      {
        assertTrue("Node should not be null", node != null);
        assertTrue("Node should have a value", node.getValue() != null);
        TreeTest.counter++;
      }
    });
    
    assertTrue("Not all nodes were traversed", tree.children.size() == TreeTest.counter);

    TreeTest.counter = 0;
  }
}
