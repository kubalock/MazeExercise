/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeexercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Grzegorz
 */
public class Reader {

    public Reader() {
        
    }
    
    public List<List<String>> readSetUp(String filePath) throws FileNotFoundException, IOException {
        List<List<String>> mazeSetup = new ArrayList<>();
        BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));

        String testLine = br.readLine();
        int counter = 0;
        while (testLine != null) {
            //**

            if (counter >= 3) {
                List<String> lines = new ArrayList<>();
                lines.add(testLine.replaceAll(" ", ""));
                mazeSetup.add(lines);
            }
            counter++;
            testLine = br.readLine();

            // Here, we tell filereader to read every single line of the txt file which are then transformed into list, which is then added to the list of the list/lines.
            //**
        }
        return mazeSetup;
    }
    
    public List<String[]> mazeSet(String filePath) throws IOException {
        List<String[]> setMaze = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String line = Files.readAllLines(Paths.get(filePath)).get(i);
            setMaze.add(line.split(" "));
        }
        return setMaze;
    }
}
