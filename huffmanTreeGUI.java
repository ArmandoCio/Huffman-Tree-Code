import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import Huffman3.huffTree;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;



public class huffmanTreeGUI extends JFrame implements ActionListener {

    huffTree h3 = new huffTree();



    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();

    JButton button1 = new JButton("Encode");
    JButton button2 = new JButton("Decode");

    JTextArea text1 = new JTextArea(1,10);
    JTextArea text2 = new JTextArea(1,10);

    

  

    huffmanTreeGUI() throws IOException {


        Container container = getContentPane();

        button1.addActionListener(this);
        button2.addActionListener(this);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(text1);
        panel1.add(text2);
        container.add(panel1);
  



        setSize(700, 700);
        setVisible(true);

        h3.readFile();
        h3.treeGenerator();

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button2){

            text2.setText(h3.decode(text1.getText()));


        }
        else{

            h3.encode(h3.getRoot(), "", text1.getText());
            text2.setText(h3.encode(h3.getRoot(), "", text1.getText()));;
            

        }
    }
    public static void main(String[] args) throws IOException {
        huffmanTreeGUI gui = new huffmanTreeGUI();


   
    }


}