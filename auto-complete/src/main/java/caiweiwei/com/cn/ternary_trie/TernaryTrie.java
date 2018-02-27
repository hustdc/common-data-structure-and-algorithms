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
	
    public void put(String s) {
    	TernaryTrie curTernaryTrie = this;
    	TernaryTrie lastTernaryTrie = null;
    	
    	for (int i = 0; i < s.length(); i++) {
    		char t = s.charAt(i);
    		if (curTernaryTrie == null) {
    			lastTernaryTrie.ternaryTrie[0] = new TernaryTrie(t);
    			lastTernaryTrie = lastTernaryTrie.ternaryTrie[0];
    			curTernaryTrie = null;
    			continue;
    		}
    		
    		while (true) {
    			int diff = t - curTernaryTrie.c;
    			if (diff == 0) {
    				if (curTernaryTrie.ternaryTrie[0] == null) {
    					lastTernaryTrie = curTernaryTrie;
    					curTernaryTrie = null;
    				} else {
    					lastTernaryTrie = curTernaryTrie;
    					curTernaryTrie = curTernaryTrie.ternaryTrie[0];
    				}
    				
    				break;
    			} else if (diff < 0) {
    				if (curTernaryTrie.ternaryTrie[1] == null) {
    					curTernaryTrie.ternaryTrie[1] = new TernaryTrie(t);
    					lastTernaryTrie = curTernaryTrie.ternaryTrie[1];
    					curTernaryTrie = null;
    					break;
    				} else {
    					curTernaryTrie = curTernaryTrie.ternaryTrie[1];
    				}
    				
    			} else {
    				if (curTernaryTrie.ternaryTrie[2] == null) {
    					curTernaryTrie.ternaryTrie[2] = new TernaryTrie(t);
    					lastTernaryTrie = curTernaryTrie.ternaryTrie[2];
    					curTernaryTrie = null;
    					break;
    				} else {
    					curTernaryTrie = curTernaryTrie.ternaryTrie[2];
    				}
    			}
    		}
    	}
    }
    
    public ArrayList<String> get(String s) {
    	ArrayList<String> result = new ArrayList<String>();
    	ArrayDeque<Stack<TernaryTrie>> keyStackQueue = new ArrayDeque<Stack<TernaryTrie>>();
    	TernaryTrie curTernaryTrie = this;

    	Stack<TernaryTrie> firstStack = new Stack<TernaryTrie>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		char t = s.charAt(i);
    		if (curTernaryTrie == null) {
    			return null;
    		}
    		while (true) {
    			if (t == curTernaryTrie.c) {
        			firstStack.push(curTernaryTrie);
        			curTernaryTrie = curTernaryTrie.ternaryTrie[0];
        			break;
        		} else if (t < curTernaryTrie.c){
        			curTernaryTrie = curTernaryTrie.ternaryTrie[1];
        			if (curTernaryTrie == null) {
        				break;
        			}
        		} else {
        			curTernaryTrie = curTernaryTrie.ternaryTrie[2];
        			if (curTernaryTrie == null) {
        				break;
        			}
        		}
    		}
    	}
    	
    	if (curTernaryTrie == null) {
    		result.add(s);
    		return result;
    	} else {
    		firstStack.push(curTernaryTrie);
    		keyStackQueue.add(firstStack);
    	}
    	
    	while (!keyStackQueue.isEmpty()) {
    		Stack<TernaryTrie> first = keyStackQueue.getFirst();
    		TernaryTrie tmpTernaryTrie = first.peek();
    		if (tmpTernaryTrie.ternaryTrie[1] != null) {
    			Stack<TernaryTrie> newStackOne = (Stack<TernaryTrie>) first.clone();
    			newStackOne.pop();
    			newStackOne.push(tmpTernaryTrie.ternaryTrie[1]);
    			keyStackQueue.addLast(newStackOne);
    		}
    		
            if (tmpTernaryTrie.ternaryTrie[2] != null) {
            	Stack<TernaryTrie> newStackTwo = (Stack<TernaryTrie>) first.clone();
            	newStackTwo.pop();
            	newStackTwo.push(tmpTernaryTrie.ternaryTrie[2]);
    			keyStackQueue.addLast(newStackTwo);
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
    	
		return result;
    }
	
}
