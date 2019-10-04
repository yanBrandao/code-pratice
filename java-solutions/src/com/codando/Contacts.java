package com.codando;

import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    int count = 1;
    boolean isLeaf;

    public TrieNode() {}

    public TrieNode(char c){
        this.c = c;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);

            TrieNode t;
            if(children.containsKey(c)){
                children.get(c).count++;
                t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            //set leaf node
            if(i==word.length()-1)
                t.isLeaf = true;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);

        if(t != null && t.isLeaf)
            return true;
        else
            return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(searchNode(prefix) == null)
            return false;
        else
            return true;
    }

    public int howMuchNodesHavePrefix(String prefix) {
        TrieNode node = searchNode(prefix);
        if(node == null)
            return 0;
        else
            return node.count;
    }

    public TrieNode searchNode(String str){
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }

        return t;
    }
}

public class Contacts {

    static List<Integer> contacts(String[][] queries) {
        Trie contacts = new Trie();
        int finds = 0;
        ArrayList<String> queriesFind = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int findCounts = 0;
        for (int i = 0; i < queries.length; i++){
            if(queries[i][0].equals("add"))
                contacts.insert(queries[i][1]);
            else {
                queriesFind.add(queries[i][1]);
                result.add(contacts.howMuchNodesHavePrefix(queriesFind.get(findCounts)));
                finds++;
                findCounts++;
            }
        }
        return  result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contacts.txt"));

        int queriesRows = 100000;
        File file = new File("C:\\Users\\yan.diniz\\Desktop\\input04.txt");
        String[][] queries = new String[queriesRows][2];

        Scanner sc = new Scanner(file);
        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = sc.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        List<Integer> result = contacts(queries);

        for (int resultItr = 0; resultItr < result.size(); resultItr++) {
            bufferedWriter.write(String.valueOf(result.get(resultItr)));

            if (resultItr != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
