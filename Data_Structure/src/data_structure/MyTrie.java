package data_structure;

import java.util.*;

public class MyTrie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTrie trie = new MyTrie();
		trie.insert("abc");
		trie.insert("abd");
		trie.insert("abe");
		trie.insert("ac");
		System.out.println(trie.findAllMatchWithWildCard("a?b"));
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
	
	public List<String> findAllWithPrefix(String prefix) {
		List<String> result = new ArrayList<String>();
		if (prefix == null) {
			return result;
		}
		TrieNode cur = root;
		for(int i = 0; i < prefix.length(); i++) {
			TrieNode next = cur.children[prefix.charAt(i) - 'a'];
			if (next == null) {
				return result;
			}
			cur = next;
		}
		
		findAllWithPrefixHelper(result, cur, new StringBuilder(prefix));
		return result;
	}
	private void findAllWithPrefixHelper(List<String> result, TrieNode cur, StringBuilder sb) {
		if (cur.isEnd) {
			result.add(sb.toString());
		}
		for(int i = 0; i < cur.children.length; i++) {
			TrieNode next = cur.children[i];
			if (next != null) {
				sb.append((char)(i + 'a'));
				findAllWithPrefixHelper(result, next, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
	public List<String> findAllMatchWithWildCard(String word){
		List<String> result = new ArrayList<String>();
		if (word == null) {
			return result;
		}
		findAllMatchDFS(root, result, 0, word, new StringBuilder());
		return result;
	}
	private void findAllMatchDFS(TrieNode cur, List<String> result, int index, String word, StringBuilder sb) {
		if (index == word.length()) {
			if (cur.isEnd) {
				result.add(sb.toString());
			}
			return;
		}
		char temp = word.charAt(index);
		if (temp == '?') {
			for(int i = 0; i < cur.children.length; i++) {
				TrieNode next = cur.children[i];
				if (next != null) {
					sb.append((char)(i + 'a'));
					findAllMatchDFS(next, result, index + 1, word, sb);
					sb.deleteCharAt(sb.length() - 1);
				}
			}
		} else {
			TrieNode next = cur.children[temp - 'a'];
			if (next != null) {
				sb.append(temp);
				findAllMatchDFS(next, result, index + 1, word, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		
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
