package caiweiwei.com.cn.ternary_trie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TernaryTrie {
	private final int NUM = 3;
	private char c;
	private TernaryTrie ternaryTrie[];
	public TernaryTrie(char c) {
		ternaryTrie = new TernaryTrie[NUM];
		this.c = c;
	}
	
    void put(String s) {
    	TernaryTrie curTernaryTrie = this;
    	for (int i = 0; i < s.length(); i++) {
    		char t = s.charAt(i);
    		while (true) {
    			int diff = t - curTernaryTrie.c;
    			if (diff == 0) {
    				if (curTernaryTrie.ternaryTrie[0] == null) {
    					curTernaryTrie.ternaryTrie[0] = new TernaryTrie(t);
    				} else {
    					curTernaryTrie = curTernaryTrie.ternaryTrie[0];
    				}
    				break;
    			} else if (diff < 0) {
    				if (curTernaryTrie.ternaryTrie[1] == null) {
    					curTernaryTrie.ternaryTrie[1] = new TernaryTrie(t);
    				} else {
    					curTernaryTrie = curTernaryTrie.ternaryTrie[1];
    				}
    				break;
    			} else {
    				if (curTernaryTrie.ternaryTrie[2] == null) {
    					curTernaryTrie.ternaryTrie[2] = new TernaryTrie(t);
    				} else {
    					curTernaryTrie = curTernaryTrie.ternaryTrie[2];
    				}
    				break;
    			}
    		}
    	}
    }
    
    ArrayList<String> get(String s) {
    	ArrayList<String> result = new ArrayList<String>();
    	ArrayDeque<Stack<TernaryTrie>> keyStackQueue = new ArrayDeque<Stack<TernaryTrie>>();
    	TernaryTrie curTernaryTrie = this;

    	Stack<TernaryTrie> firstStack = new Stack<TernaryTrie>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		char t = s.charAt(i);
    		if (curTernaryTrie == null) {
    			return null;
    		}
    		if (t == curTernaryTrie.c) {
    			curTernaryTrie = curTernaryTrie.ternaryTrie[0];
    			firstStack.push(curTernaryTrie);
    		} else {
    			return null;
    		}
    	}
    	
    	if (curTernaryTrie == null) {
    		result.add(s);
    		return result;
    	} else {
    		keyStackQueue.add(firstStack);
    		firstStack.push(curTernaryTrie);
    	}
    	
    	while (!keyStackQueue.isEmpty()) {
    		Stack<TernaryTrie> first = keyStackQueue.getFirst();
    		TernaryTrie tmpTernaryTrie = first.peek();
    		if (tmpTernaryTrie.ternaryTrie[1] != null) {
    			Stack<TernaryTrie> newStack = (Stack<TernaryTrie>) first.clone();
    			newStack.pop();
    			newStack.push(tmpTernaryTrie.ternaryTrie[1]);
    			keyStackQueue.addLast(newStack);
    		}
    		
            if (tmpTernaryTrie.ternaryTrie[2] != null) {
            	Stack<TernaryTrie> newStack = (Stack<TernaryTrie>) first.clone();
    			newStack.pop();
    			newStack.push(tmpTernaryTrie.ternaryTrie[2]);
    			keyStackQueue.addLast(newStack);
    		}
    		
    		if (tmpTernaryTrie.ternaryTrie[0] == null) {
    			String key = "";
    			while (!first.isEmpty()) {
    				TernaryTrie tmptmpTernaryTrie = first.pop();
    				key = tmptmpTernaryTrie.c + key;
    			}
    			result.add(key);
    			keyStackQueue.removeFirst();
    		} else {
    			first.push(tmpTernaryTrie.ternaryTrie[0]);
    		}
    	}
    	
		return null;
    }
	
}
