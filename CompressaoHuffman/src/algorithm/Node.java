package algorithm;

import java.util.Map;

public class Node implements Comparable<Node> {

	private char character;
	private int numberOfRepetitions;
	
	private Node rightNode;  // Direita
	private Node leftNode;	// Esquerda
	
	public Node(char character) {
		this.character = character;
	}
	
	public Node(Node leftNode, Node rightNode) {
		this.character = '+';
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	public boolean leafNode() {
		return (leftNode == null) && (rightNode == null);
	}
	
	public int frequency() {
		if(leafNode())
			return numberOfRepetitions;
		return ( rightNode.frequency() ) + ( leftNode.frequency() );
	}
	
	public void add() {
		numberOfRepetitions++;
	}
	
	public void fillCodeMap(Map<Character, String> codeMap, String work) {
		if(leafNode()) {
			codeMap.put(getCharacter(), work);
			return;
		}
		
		rightNode.fillCodeMap(codeMap, work + "1");
		leftNode.fillCodeMap(codeMap, work + "0");
	}
	
	public char getCharacter() {
		return character;
	}
	
	public Node getRightNode() {
		return rightNode;
	}
	
	public Node getLeftNode() {
		return leftNode;
	}

	@Override
	public int compareTo(Node node) {
		return frequency() - node.frequency();
	}
	
}
