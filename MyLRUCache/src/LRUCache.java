import java.util.HashMap;

public class LRUCache {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	HashMap<Integer, Node> map;
	Node dummyNode;
	int cap, size;
	public LRUCache (int cap) {
		map = new HashMap<Integer, Node>();
		dummyNode = new Node(-1, - 1);
		dummyNode.next = dummyNode;
		dummyNode.prev = dummyNode;
		this.cap = cap;
		size = 0;
	}
	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		}
		Node temp = map.get(key);
		reorder(temp);
		return temp.value;
	}
	public void put(int key, int value) {
		if (map.containsKey(key)) {
		    Node temp = map.get(key);
		    temp.value = value;
			reorder(temp);
		} else {
			Node temp = new Node(key, value);
			map.put(key, temp);
			Node next = dummyNode.next;
			dummyNode.next = temp;
			temp.prev = dummyNode;
			temp.next = next;
			next.prev = temp;
			size++;
			if (size > cap) {
				Node prev = dummyNode.prev;
				dummyNode.prev = prev.prev;
				prev.prev.next = dummyNode;
				prev.prev = null;
				prev.next = null;
				map.remove(prev.key);
				size--;
			}
		}
	}
	public void reorder(Node temp) {
		Node prev = temp.prev, next = temp.next;
		prev.next = next;
		next.prev = prev;
		temp.next = null;
		temp.prev = null;
		next = dummyNode.next;
		dummyNode.next = temp;
		temp.prev = dummyNode;
		temp.next = next;
		next.prev = temp;
	}
}
class Node {
	Node next, prev;
	int key, value;
	public Node(int key, int value) {
		this.key = key;
		this.value = value;
	}
}
