package Huffman3;

public class huffItems {

    String sym;
    int freq;

    public huffItems(String s, int fr) {

        this.sym = s;
        this.freq = fr;
    }

    public int getFreq() {

        return freq;

    }

    public String getSymbol() {
        return sym;
    }

}
