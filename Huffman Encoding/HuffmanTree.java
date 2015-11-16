import java.util.HashMap;

public class HuffmanTree implements Comparable<HuffmanTree>{

	private HuffmanTreeNode node; //= new HuffmanTreeNode();

	public HuffmanTree(){ }

	/* Constructor
	 * @param node: takes a node that the tree represents
	 */
	public HuffmanTree(HuffmanTreeNode node){
		this.node = node;
	}

	public HuffmanTreeNode getNode(){
		return this.node;
	}

	public int compareTo(HuffmanTree tree){
		if (this.getNode().getFrequency()<tree.getNode().getFrequency()){
			return -1;
		} else if (this.getNode().getFrequency()==tree.getNode().getFrequency()){
			return 0;
		} else {
			return 1;
		}
	}

	/* Merges two HuffmanTrees to create a parent
	 * @param leftSiblingTree: a HuffmanTree to combine with
	 */
	public void mergeWithLeftSibling(HuffmanTree leftSiblingTree){
		int combfreq = node.getFrequency() + leftSiblingTree.getNode().getFrequency();
		HuffmanTreeNode parent = new HuffmanTreeNode(combfreq, leftSiblingTree, this);
		node = parent;
	}
	
	public void traverse(HashMap<Character, String> map){
		this.getNode().traverse("", map);
	}


}
