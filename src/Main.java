import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        try {

            File myinput = new File(args[0]);
            Scanner myReader = new Scanner(myinput);
            String data1 = myReader.nextLine();
            BST _bst = new BST(Integer.parseInt(data1));
            AVLT _avl = new AVLT(Integer.parseInt(data1));
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] x = data.split(" ");

                switch (x[0]){
                    case "ADDNODE":
                        _bst.addElement(Integer.parseInt(x[1]));
                        _avl.addElement(Integer.parseInt(x[1]));
                        break;
                    case "DELETE":
                        _bst.deleteItem(Integer.parseInt(x[1]));
                        _avl.deleteItem(Integer.parseInt(x[1]));
                        break;
                    case "SEND":
                        _bst.sendMessage(Integer.parseInt(x[1]),Integer.parseInt(x[2]));
                        _avl.sendMessage(Integer.parseInt(x[1]),Integer.parseInt(x[2]));
                        break;
                }

            }
            File myOutput_avl = new File(args[1]+"_avl.txt");
            if (myOutput_avl.createNewFile()) {
                System.out.println("File created: " + myOutput_avl.getName());
            } else {
                System.out.println("File already exists.");
            }
            File myOutput_bst = new File(args[1]+"_bst.txt");
            if (myOutput_bst.createNewFile()) {
                System.out.println("File created: " + myOutput_avl.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter bst = new FileWriter(myOutput_bst.getName());
            bst.write(_bst.getOutput());
            bst.close();
            FileWriter avl = new FileWriter(myOutput_avl.getName());
            avl.write(_avl.getOutput_AVL());
            avl.close();
            System.out.println(_avl.getOutput_AVL());



        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}