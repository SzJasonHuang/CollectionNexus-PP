package ui;

import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));


        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run(){
                new CollectionNexusJFrame();              
            }

        });


    }

}
