import java.util.*;
import java.util.Map.Entry;
import java.io.*;
public class Encoder {

	String file;

	/*
	 * @param String filename
	 * @return map of the characters in the text file and the frequency
	 */

	public static void main(String args[]) {
		Encoder.huffmanCoder("TheWizard.txt", "NewWizard.txt");

	}

	public Map<Character, Integer> toMap(String filename) throws IOException{

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String currentLine = reader.readLine();
		Map<Character, Integer> list = new HashMap<Character, Integer>();

		while(currentLine != null){
			char[] charArray = currentLine.toCharArray();
			for(int i = 0; i < charArray.length; i++){
				if(list.containsKey(charArray[i])){
					list.put(charArray[i], list.get(charArray[i])+1);
				} else {
					list.put(charArray[i], 1);
				}
			}
			currentLine = reader.readLine();
		}
		reader.close();
		return list;
	}

	/*Turns a Map of characters, integers and turns it into an array of HuffmanTree
	 * @param  map: a 
	 */
	public ArrayList<HuffmanTree> makeArray(Map<Character,Integer> map){
		Object[] keyArray = map.keySet().toArray();
		ArrayList<HuffmanTree> list = new ArrayList<HuffmanTree>();
		for(int i = 0; i < map.size(); i++){
			map.get(i);
			HuffmanTreeNode node = new HuffmanTreeNode((Character)keyArray[i],(map.get(keyArray[i]).intValue()));
			HuffmanTree tree = new HuffmanTree(node);
			list.add(tree);
		}
		Collections.sort(list);
		return list;
	}

	/*
	 * Builds a Huffman tree from data
	 * @param list:an arrayList containing HuffmanTrees
	 * @return HuffmanTree: a HuffmanTree that stores all that data
	 */
	public HuffmanTree buildTree(ArrayList<HuffmanTree> list){
		//if(list.size() > 1){
		while(list.size() > 1/*2*/){
			list.get(0).mergeWithLeftSibling(list.get(1));
			//list.add(0,(list.get(0).getNode().getParent()));
			list.remove(1);
			//list.remove(2);
			Collections.sort(list);
		}
		//list.remove(1);
		return list.get(0);
		//} else {
		//  return list.get(0);
		// }
	}

	/*
	 * Makes the binary encoding for all the characters
	 * @param tree: a full HuffmanTree with children in proper spots
	 * @return a hashmap that with characters mapped to their binary value
	 */
	public HashMap<Character, String> makeCodeMap(HuffmanTree tree) {
		if (tree.getNode().getLeft() == null && tree.getNode().getRight() == null){
			return null;
		} else {
			HashMap<Character, String> newMap = new HashMap<Character, String>();
			tree.traverse(newMap);
			return newMap;
		}
	}

	/*
	 * Turns a .txt file into a compressed binary file
	 * @param filename: the name of the file that will be compressed
	 * @param output: the name of a new file being created
	 * @
	 */
	public void compressString(String filename, String output) throws IOException {
		Encoder code = new Encoder();
		Map<Character, Integer> map = code. toMap(filename);
		ArrayList<HuffmanTree> array = code.makeArray(map);
		HuffmanTree tree = code.buildTree(array);
		HashMap<Character, String> newMap = code.makeCodeMap(tree);  

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String currentLine = reader.readLine();
		PrintWriter writer = new PrintWriter(output); 
		String s = "";

		while(currentLine != null){
			char[] charArray = currentLine.toCharArray();
			for(int i = 0; i < charArray.length; i++){
				s+= newMap.get(charArray[i]);
			}
			writer.println(s);
			s = "";
			currentLine = reader.readLine();
		}
		writer.close();
		reader.close();
	}

	public int charNumber(String filename){
		BufferedReader reader;
		String currentLine= null;
		String codeLine = "";
		int count=0;
		try{
			reader = new BufferedReader(new FileReader(filename));
			while (currentLine != null) {
				count+=codeLine.length();
				codeLine = "";
				currentLine = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return count;
	}

	public void writeToFile(String line, PrintWriter writer){

	}

	public void printCode(HashMap<Character, String> map, PrintWriter writer){
		for (Character key : map.keySet()) {
			writer.println("The character "+ key + " is represented by "+map.get(key));
		}  

	}

	public static void huffmanCoder(String inputFileName, String outputFileName){
		Encoder e = new Encoder();
		Map<Character, Integer> map = null;
		String currentLine;
		String codeLine = "";
		int orgLength=0;
		int newLength = 0;
		BufferedReader reader;
		PrintWriter writer;
		try {
			orgLength = e.charNumber(inputFileName);
			map = e.toMap(inputFileName);
			ArrayList<HuffmanTree> array = e.makeArray(map);
			HuffmanTree tree = e.buildTree(array);
			HashMap<Character, String> newMap = e.makeCodeMap(tree);
			reader = new BufferedReader(new FileReader(inputFileName));
			writer = new PrintWriter(outputFileName);
			e.printCode(newMap,writer);
			currentLine = reader.readLine();
			while (currentLine != null) {
				char[] charArray = currentLine.toCharArray();
				for(int i = 0; i < charArray.length; i++){
					codeLine+= newMap.get(charArray[i]);
				}
				writer.println(codeLine);
				newLength+= codeLine.length();
				codeLine = "";
				currentLine = reader.readLine();
			}
			writer.println("You have saved " + (newLength*2-orgLength*2^8)+ " bits of data.");

			reader.close();
			writer.close();
			System.out.println("Finished");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}