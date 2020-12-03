package ua.edu.ucu.tries;

import ua.edu.ucu.additional_structures.Queue;

import java.util.ArrayList;
import java.util.List;

public class RWayTrie implements Trie {

    private static final int R = 256; // radix
    private Node root = new Node(); // root of trie

    private static class Node {
        private Object val;
        private final Node[] next = new Node[R];
    }

    private Node put(Node x, String key, int val, int d) {
        // Change value associated with key if in subtrie rooted at x.
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    @Override
    public void add(Tuple t) {
        this.root = put(root, t.term, t.weight, 0);
    }

    @Override
    public boolean contains(String word) {
        Node containsWord = get(root, word, 0);
        return containsWord != null && containsWord.val != null;
    }

    @Override
    public boolean delete(String key) {
        if (contains(key)) {
            root = delete(root, key, 0);
            return true;
        }
        return false;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.val != null) {
            return x;
        }

        for (char c = 0; c < R; c++)
            if (x.next[c] != null) {
                return x;
            }
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pre) {
        Queue q = new Queue();
        collect(get(root, pre, 0), pre, q);

//        String[] strings = new String[q.size()];
        List<String> outList = new ArrayList<>();

        int size = q.size();
        for (int i = 0; i < size; i++) {
            outList.add((String) q.dequeue());
        }
        return outList;
    }

    private void collect(Node x, String pre, Queue q) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.enqueue(pre);
        }

        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    private Node get(Node x, String key, int d) {
        // Return node associated with key in the subtrie rooted at x.
        if (x == null) {
            return null;
        } else if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d + 1);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        int cnt = 0;
        if (x.val != null) {
            cnt++;
        }
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt;
    }

}
