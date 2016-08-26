package com.xsynergy.util.tree;

/**
 * An interface to simplying walking the tree.  The process method is invoked for every node option.
 * @param <V> The value stored in the Tree node.
 * @author Colin Fitzpatrick
 * @version 1.0
 */
public interface TreeProcessor<V>
{
  /**
   * The process method is invoked for every node option.
   * @param node The node being processed.
   */
  public void process(TreeObject<V> node, boolean firstNode);
}
