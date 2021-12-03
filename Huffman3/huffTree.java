package Huffman3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class huffTree {

    public TreeNode getRoot() {

        return root;

    }

    ListArrayBased list = new ListArrayBased();

    TreeNode node;
    TreeNode root;

    String s;
    String[] arr;
    String encode;
    public String encodeResult;

    public void readFile() throws IOException {

        int t = 1;

        // declaring file , filereader and bufferreader
        File f = new File("Huffman3/LetterCount.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        // while loop to make sure it doesnt read in the space between the letter and
        // the rumbers
        while ((s = br.readLine()) != null) {
            arr = s.split("\t", 2);
            String let = arr[0];
            int numbers = Integer.parseInt(arr[1]);

            huffItems item = new huffItems(let, numbers);
            item.getSymbol();

            node = new TreeNode(item);
            node.getItem();
            list.add(t++, node);

        }
        br.close();
    }

    public void treeGenerator() {

        while (list.size() > 1) {
            // initialise left node
            TreeNode l = (TreeNode) list.get(1);

            // inistialise right node
            TreeNode r = (TreeNode) list.get(2);

            // removing the first item in the list and the second (after the first is
            // removed the second becomes the first so i removed the first reference twice)
            list.remove(1);
            list.remove(1);

            // initialise and typecasted to huffitems left and right to get the item
            huffItems left = ((huffItems) l.getItem());
            huffItems right = ((huffItems) r.getItem());

            // get the sum of the frequency of left and right nodes
            huffItems freqSum = new huffItems("@", left.getFreq() + right.getFreq());

            // adding the size of the list and the parent to the list
            TreeNode p = new TreeNode(freqSum, r, l);
            list.add(list.size() + 1, p);
            list.bSort();
        }

        // get the root
        root = (TreeNode) list.get(1);
    }

    // method to decode adapted from pseudocode provided in the labs same for the
    // encode method
    public String decode(String code) {
        TreeNode curr = root;

        // for loop iterates throguh the binary and compares to 0
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            // traversing the tree
            if (c == '0')
                curr = curr.getLeft();
            else
                curr = curr.getRight();
        }
        return ((huffItems) curr.getItem()).getSymbol();
    }

    // traverses the tree in order through recursion
    // Set a global variable String encodeResult
    public String encode(TreeNode root, String code, String symbol) {

        // type casting the root to use getItem
        if (((huffItems) root.getItem()).getSymbol().equals(symbol)) {
            encodeResult = code;
            return encodeResult;
        }

        if (root.getLeft() != null)

            encode(root.getLeft(), code + "0", symbol);

        if (root.getRight() != null)
            encode(root.getRight(), code + "1", symbol);
        return encodeResult;

    }

    public String getRes() {

        return encodeResult;
    }

}
