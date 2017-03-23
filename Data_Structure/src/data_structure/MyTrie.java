package data_structure;

public class MyTrie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTrie trie = new MyTrie();
		System.out.println(trie.search("abc"));
		System.out.println(trie.insert("abc"));
		System.out.println(trie.search("abc"));
		System.out.println(trie.insert("abd"));
		System.out.println(trie.search("abd"));
		System.out.println(trie.delete("abc"));
		System.out.println(trie.search("abc"));
		System.out.println(trie.search("abd"));
	}
	TrieNode root;
	
	public MyTrie() {
		root = new TrieNode();
	}
	
	public boolean search(String word) {
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++) {
			if (cur.children[word.charAt(i) - 'a'] != null) {
				cur = cur.children[word.charAt(i) - 'a'];
			} else {
				return false;
			}
		}
		return cur.isEnd;
	}
	
	public boolean insert(String word) {
		if (search(word)) {
			return false;
		}
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++) {
			TrieNode next = cur.children[word.charAt(i) - 'a'];
			if (next == null) {
				next = new TrieNode();
				cur.children[word.charAt(i) - 'a'] = next;
			}
			cur = next;
			cur.counter++;
		}
		cur.isEnd = true;
		return true;
	}
	
	public boolean delete(String word) {
		if (!search(word)) {
			return false;
		}
		TrieNode cur = root;
		for(int i = 0; i < word.length(); i++) {
			TrieNode next = cur.children[word.charAt(i) - 'a'];
			next.counter--;
			if (next.counter == 0) {
				cur.children[word.charAt(i) - 'a'] = null;
				return true;
			}
			cur = next;
		}
		cur.isEnd = false;
		return true;
	}
}
class TrieNode {
	int counter;
	TrieNode[] children;
	boolean isEnd;
	public TrieNode() {
		counter = 0;
		children = new TrieNode[26];
		isEnd = false;
	}
}
