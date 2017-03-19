import java.util.HashMap;

public class FirstNoRepeating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FirstNoRepeating test = new FirstNoRepeating();
		test.add(1);
		test.add(2);
		System.out.println(test.get());
		test.add(2);
		System.out.println(test.get());
		test.add(2);
		System.out.println(test.get());
		test.add(1);
		System.out.println(test.get());
	}
	HashMap<Integer, ListNode> map;
	ListNode dummyNode;
	public FirstNoRepeating() {
		map = new HashMap<Integer, ListNode>();
		dummyNode = new ListNode(-1);
		dummyNode.next = dummyNode;
		dummyNode.prev = dummyNode;
	}
	public int get() {
		return dummyNode.prev.value;
	}
	public void add(int value) {
		if (map.containsKey(value) && map.get(value) != null) {
			ListNode temp = map.get(value);
			ListNode prev = temp.prev, next = temp.next;
			prev.next = next;
			next.prev = prev;
			temp.next = null;
			temp.prev = null;
			map.put(value, null);
		} else if (!map.containsKey(value)) {
			ListNode temp = new ListNode(value), prev= dummyNode.prev;
			prev.next = temp;
			temp.prev = prev;
			temp.next = dummyNode;
			dummyNode.prev = temp;
			map.put(value, temp);
		}
	}
}
class ListNode {
	ListNode next, prev;
	int value;
	public ListNode (int value) {
		this.value = value;
	}
}
