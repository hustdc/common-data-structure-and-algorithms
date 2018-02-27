package caiweiwei.com.cn.auto_complete;

import java.util.ArrayList;

import caiweiwei.com.cn.ternary_trie.TernaryTrie;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        TernaryTrie ternaryTrie = new TernaryTrie('a');
        ternaryTrie.put("abc");
        ternaryTrie.put("aba");
        ternaryTrie.put("abxy");
        ternaryTrie.put("abzdc");
        ternaryTrie.put("abbp");
        ternaryTrie.put("taz");
        ternaryTrie.put("tpq");
        ternaryTrie.put("tya");
        ternaryTrie.put("tzp");
        ternaryTrie.put("tcdz");
        ternaryTrie.put("oddy");
        ternaryTrie.put("oddc");
        ternaryTrie.put("odda");
        ternaryTrie.put("oddzb");
        
        ArrayList<String> resultab = ternaryTrie.get("ab");
        ArrayList<String> resultt = ternaryTrie.get("t");
        ArrayList<String> resultodd = ternaryTrie.get("odd");
        System.out.println("the auto-complet result of prefix ab is:" + "\n");
        for (String s : resultab) {
        	System.out.println(s + "\n");
        }
        
        System.out.println("the auto-complet result of prefix t is:" + "\n");
        for (String s : resultt) {
        	System.out.println(s + "\n");
        }
        
        System.out.println("the auto-complet result of prefix odd is:" + "\n");
        for (String s : resultodd) {
        	System.out.println(s + "\n");
        }
    }
}
