package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private final Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        for (String string: strings) {
            String[] words = string.split("\\s+");
            for (String word: words) {
                if (word.length() > 2 && !trie.contains(word)) {
                    trie.add(new Tuple(word, word.length()));
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException();
        }
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        int kLen;
        if (k == 2) {
            kLen = k + 1;
        } else {
            kLen = k;
        }

        List<String> outList = new ArrayList<>();
        Iterable<String> words = trie.wordsWithPrefix(pref);

        for (String word: words) {
            if (word.length() < (pref.length() + kLen)) {
                outList.add(word);
            }
        }
        return outList;
    }

    public int size() {
        return trie.size();
    }
}
