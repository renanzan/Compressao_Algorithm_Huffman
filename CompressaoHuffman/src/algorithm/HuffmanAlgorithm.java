package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class HuffmanAlgorithm {

	private Node root;
	
	public String coding(String text) {
		char[] charArray = text.toCharArray();
		root = createTree(countFrequency(charArray));
		Map<Character, String> map = initializeMap();
		
		StringBuilder data = new StringBuilder();
		for(char character : charArray)
			data.append(map.get(character));
		
		return data.toString();
	}
	
	public String decoding(String text) {
		Node assist = root;
		
		StringBuilder data = new StringBuilder();
		for(char character : text.toCharArray()) {
			if(character == '1')
				assist = assist.getRightNode();
			else
				assist = assist.getLeftNode();
		
			if(assist.leafNode()) {
				data.append(assist.getCharacter());
				assist = root;
			}
		}
		
		return data.toString();
	}
	
	private Node createTree(PriorityQueue<Node> nodes) {
		while(true) {
			/** Método guloso **/
			Node node1 = nodes.poll();
			Node node2 = nodes.poll();
			
			Node parent = new Node(node1, node2);
			
			if(nodes.isEmpty())
				return parent;
			
			nodes.add(parent);
		}
	}
	
	private PriorityQueue<Node> countFrequency(char[] charArray) {
		HashMap<Character, Node> counterCharacter = new HashMap<>();
		
		for(char character : charArray) {
			if(!counterCharacter.containsKey(character))
				counterCharacter.put(character, new Node(character));
			counterCharacter.get(character).add();
		}
		
		return new PriorityQueue<>(counterCharacter.values());
	}
	
	private Map<Character, String> initializeMap() {
		Map<Character, String> hashMap = new TreeMap<>();
		root.fillCodeMap(hashMap, "");
		
		return hashMap;
	}
	
}
