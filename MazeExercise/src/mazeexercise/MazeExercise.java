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

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            List<String[]> setMaze = new ArrayList<>();                         //Variable made for getting maze setting purposes (such as coordinates of Start and End of the maze)

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

            //**
            for (int i = 0; i < 3; i++) {
                String line = Files.readAllLines(Paths.get(filePath)).get(i);
                setMaze.add(line.split(" "));
            }

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

            
            
            for (int y = 0; y < myMaze.getY(); y++) {
                List<String> rowOutput = new ArrayList<>();
                for (int x = 0; x < myMaze.getX(); x++) {
                    if (x == myMaze.getStartX() && y == myMaze.getStartY()) {
                        rowOutput.add("S");
                    } else if (x == myMaze.getEndX() && y == myMaze.getEndY()) {
                        rowOutput.add("E");
                    } else {
                        if (Character.toString(mazeSetup.get(y).get(0).charAt(x)).equals("1")) {
                            rowOutput.add("#");
                        } else {
                            rowOutput.add(" ");
                        }
                    }
                }
                columnOutput.add(rowOutput);
            }
            
            //For loop over here transforms 0's and 1's from the txt file into required walls (#) and passeges (empty spaces).
            //**
            
            
            //**
            //A while loop in which walkers explore and exploreBack methods are being called until the MazeWalker reaches his destation

            while (walker.getPositionX() != myMaze.getEndX() || walker.getPositionY() != myMaze.getEndY()) {
                if (walker.getPositionX() != 0 && walker.getPositionY() != 0 && walker.getPositionX() != myMaze.getX() - 1 && walker.getPositionY() != myMaze.getY() - 1) {
                    if (walker.explore(columnOutput.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            columnOutput.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            columnOutput.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                            columnOutput.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                        
                        // **
                        // Line below marks chosen path with letter X
                        
                        columnOutput.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                    } else {
                        // **
                        // Line below is executed only if MazeWalker surrounded by the walls and the only way to get out is to go back.
                        // It then marks current coordinates as a wrong ones by "W" letter and then it goes one posistion back.
                        
                        columnOutput.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                        walker.exploreBack(columnOutput.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                                columnOutput.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                                columnOutput.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                                columnOutput.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                    }
                }
                
                
                /*
                else if (walker.getPositionX() == 0) {
                    if (walker.explore(columnOutput.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            columnOutput.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            columnOutput.get(walker.getPositionY()).get(myMaze.getX() - 1),
                            columnOutput.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                        columnOutput.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                    } else {
                        columnOutput.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                        walker.exploreBack(columnOutput.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                                columnOutput.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                                columnOutput.get(walker.getPositionY()).get(myMaze.getX() - 1),
                                columnOutput.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                    }
                } else if (walker.getPositionY() == 0) {
                    if (walker.explore(columnOutput.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            columnOutput.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            columnOutput.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                            columnOutput.get(myMaze.getY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                        columnOutput.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                    } else {
                        columnOutput.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                        walker.exploreBack(columnOutput.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                                columnOutput.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                                columnOutput.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                                columnOutput.get(myMaze.getY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                    }
                }
                 */
                
                
            }
            
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
}
