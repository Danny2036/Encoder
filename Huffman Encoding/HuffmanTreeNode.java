import java.util.HashMap;

public class HuffmanTreeNode{ 
 protected Character letter;
 protected HuffmanTreeNode left;
 protected HuffmanTreeNode right;
 protected HuffmanTreeNode parent;
 protected int freq;
 
 /*
  * Constructor for non leaf node
  * @param x: the frequency of this node
  * @param left: the left child to this node
  * @param right: the right child to this node
  */
 public HuffmanTreeNode(int x, HuffmanTree left, HuffmanTree right){
  this.freq = x;
  this.left = left.getNode();
  this.right = right.getNode();
  this.left.setParent(this);
  this.right.setParent(this);
 }
 
 /*Constructor for leaf node
  * @param x: the character this node represents
  * @param z: the frequency of this character in the text
  */
 public HuffmanTreeNode(Character x, int z){
  this.letter = x;
  this.freq = z;
 }

 public void setLetter(Character x){
  this.letter = x;
 }

 public Character getCharacter(){
  return this.letter;
 }

 public void setLeft(HuffmanTreeNode x){
  this.left = x;
 }

 public void setRight(HuffmanTreeNode x){
  this.right = x;
 }

 public void setParent(HuffmanTreeNode x){
  this.parent = x;
 }

 public void setFrequency(int x){
  this.freq = x;
 }

 public HuffmanTreeNode getRight(){
  return this.right;
 }

 public HuffmanTreeNode getLeft(){
  return this.left;
 }

 public HuffmanTreeNode getParent(){
  return this.parent;
 }

 public int getFrequency(){
  return this.freq;
 }

	/*Goes through a Huffman tree and assigns binary integer values to a HashMap
	 * @param code: an empty string to be amend with binary values
	 * @param map: empty HashMap where assigned characters and their values are stored
	 */
	public void traverse(String code, HashMap<Character, String> map){
		if (this.getLeft() == null && this.getRight() == null){
			map.put(this.getCharacter(), code);
		}

		if (this.getLeft() != null){
			this.getLeft().traverse(code + "0", map);
		}

		if (this.getRight() != null){
			this.getRight().traverse(code + "1", map);
		} 
	}
 
 /*
 public int compareTo(HuffmanTreeNode node){
  if (this.getFrequency()<node.getFrequency()){
   return -1;
  } else if (this.getFrequency()==node.getFrequency()){
   return 0;
  } else {
   return 1;
  }
 }*/
}

