/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeexercise;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Grzegorz
 */
public class MazeExercise {

    public static void main(String[] args) throws IOException {

        //**
        final JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only txt files", "txt");
        chooser.setFileFilter(filter);
        chooser.showSaveDialog(null);
        String filePath = chooser.getSelectedFile().toString();

        //Here, we create a new class allowing us to choose file from our PC with specified extension.
        //**
        List<List<String>> mazeSetup = new ArrayList<>();                       //This variable is made for the maze construction purposes
        List<String[]> setMaze = new ArrayList<>();                         //Variable made for getting maze setting purposes (such as coordinates of Start and End of the maze)

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            Reader reader = new Reader();
            mazeSetup = reader.readSetUp(filePath);
            setMaze = reader.mazeSet(filePath);
        }

        //**
        // Small loop here is made to read the maze settings - its width, height, coordinates of start and end of the maze
        //**
        Maze myMaze = new Maze(Integer.parseInt(setMaze.get(0)[0]),
                Integer.parseInt(setMaze.get(0)[1]),
                Integer.parseInt(setMaze.get(1)[0]), Integer.parseInt(setMaze.get(1)[1]),
                Integer.parseInt(setMaze.get(2)[0]), Integer.parseInt(setMaze.get(2)[1]));

        // A new Maze object is created with attributes taken from the txt file here
        // **
        MazeWalker walker = new MazeWalker(myMaze.getStartX(), myMaze.getStartY());

        // A new MazeWalker is created here which is going to perform various operation of movement inside the maze
        // **
        List<List<String>> columnOutput = new ArrayList<>();                // A variable created for the output purposes

        MazeOutput output = new MazeOutput();
        columnOutput = output.addRowAndColumns(myMaze.getX(), myMaze.getY(), myMaze.getStartX(), myMaze.getStartY(),
                myMaze.getEndX(), myMaze.getEndY(), mazeSetup);
        
        output.printOutput(myMaze, walker, columnOutput);
        
        // **
        // After the MazeWalker reached his destination, we set both start and end point back to "S" and "E"
        columnOutput.get(myMaze.getEndY()).set(myMaze.getEndX(), "E");
        columnOutput.get(myMaze.getStartX()).set(myMaze.getStartX(), "S");

        //**
        //Loop below prints clears out ways the MazeWalker marked as a wrong ones by W letter which allows to see clrearly which way it went at the end
        for (List<String> line : columnOutput) {
            for (String letter : line) {
                if (letter == "W") {
                    letter = " ";
                }
                System.out.print(letter);
            }
            System.out.println("");

        }
    }
}
