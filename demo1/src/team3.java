import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class PA2{

	//PA #2 TODO: Converts a vector of bits (bool) back into readable text using the supplied Huffman map
	//Uses the supplied Huffman Map to convert the vector of bools (bits) back into text.
	//To solve this problem, I converted the Huffman Map into a Huffman Tree and used 
	//tree traversals to convert the bits back into text.
	public static String decodeBits(List<Boolean> bits, Map<Character, String> huffmanMap) {
		StringBuilder result = new StringBuilder();
		HuffmanTree<Character> tree_from_file = huffmanTreeFromMap(huffmanMap);
		HuffmanNode<Character> true_root = tree_from_file.getRoot();
		
		//while we have nodes to process
		for (int bits_counter = 0; bits_counter < bits.size(); )
		{
			HuffmanInternalNode<Character> root = (HuffmanInternalNode<Character>)true_root;
			HuffmanNode<Character> current = root;
			
			while(root != null && root.isLeaf() == false)
			{
				boolean next_bit = bits.get(bits_counter);
				if(next_bit == true)
				{
					current = root.getRightChild();
				}
				else
				{
					current = root.getLeftChild();
				}
				
				//update root and grab the next bit
				if (current.isLeaf() == false)
				{
					root = (HuffmanInternalNode<Character>)current;
					bits_counter ++;
				}
				else
				{					
					bits_counter ++;
					break;
				}
			}
			
			//at this point, current must be a leaf node. Add the text to the StringBuilder
			HuffmanLeafNode<Character> value_node = (HuffmanLeafNode<Character>)current;
			if(value_node != null)
			{
				result.append(value_node.getValue());
			}
		}
		return result.toString();		
	}

	//PA #2 TODO: Using the supplied Huffman map compression, converts the supplied text into a series of bits (boolean values)
	public static List<Boolean> toBinary(List<String> text, Map<Character, String> huffmanMap) {
		List<Boolean> result = new ArrayList<>();
		for(String line: text)
		{
			for(char ch: line.toCharArray())
			{
				for (char number : huffmanMap.get(ch).toCharArray())
				{
					if (number == '1')
					{
						result.add(true);
					}
					else
					{
						result.add(false);
					}
				}
			}
		}
		return result;
	}

}
