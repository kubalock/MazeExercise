/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeexercise;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Grzegorz
 */
public class MazeOutput {

    public List<List<String>> addRowAndColumns(int mazeX, int mazeY, int startX, int startY, int endX, int endY, List<List<String>> mazeSetup) {
        List<List<String>> output = new ArrayList<>();

        for (int y = 0; y < mazeY; y++) {
            List<String> rowOutput = new ArrayList<>();
            for (int x = 0; x < mazeX; x++) {
                if (x == startX && y == startY) {
                    rowOutput.add("S");
                } else if (x == endX && y == endY) {
                    rowOutput.add("E");
                } else {
                    if (Character.toString(mazeSetup.get(y).get(0).charAt(x)).equals("1")) {
                        rowOutput.add("#");
                    } else {
                        rowOutput.add(" ");
                    }
                }
            }
            output.add(rowOutput);
        }
        setColumnOutput(output);
        return output;
    }

    public void printOutput(Maze myMaze, MazeWalker walker, List<List<String>> output) {

        //For loop over here transforms 0's and 1's from the txt file into required walls (#) and passeges (empty spaces).
        //**
        //**
        //A while loop in which walkers explore and exploreBack methods are being called until the MazeWalker reaches his destation
        while (walker.getPositionX() != myMaze.getEndX() || walker.getPositionY() != myMaze.getEndY()) {
            if (walker.getPositionX() != 0 && walker.getPositionY() != 0 && walker.getPositionX() != myMaze.getX() - 1 && walker.getPositionY() != myMaze.getY() - 1) {
                if (walker.explore(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                        output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                        output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {

                    // **
                    // Line below marks chosen path with letter X
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    // **
                    // Line below is executed only if MazeWalker surrounded by the walls and the only way to get out is to go back.
                    // It then marks current coordinates as a wrong ones by "W" letter and then it goes one posistion back.

                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.exploreBack(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                            output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            } else if (walker.getPositionX() == 0 && walker.getPositionY() == 0) {
                if (walker.explore(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                        output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                        output.get(myMaze.getY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.exploreBack(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                            output.get(myMaze.getY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            } else if (walker.getPositionX() == myMaze.getX() - 1 && walker.getPositionY() == 0) {
                if (walker.explore(output.get(walker.getPositionY()).get(0),
                        output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                        output.get(myMaze.getY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.exploreBack(output.get(walker.getPositionY()).get(0),
                            output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                            output.get(myMaze.getY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            } else if (walker.getPositionY() == myMaze.getY() - 1 && walker.getPositionX() == myMaze.getX() - 1) {
                if (walker.explore(output.get(walker.getPositionY()).get(0),
                        output.get(0).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                        output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.exploreBack(output.get(walker.getPositionY()).get(0),
                            output.get(0).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                            output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            } else if (walker.getPositionY() == myMaze.getY() - 1 && walker.getPositionX() == 0) {
                if (walker.explore(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                        output.get(0).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                        output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.exploreBack(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            output.get(0).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                            output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            } else if (walker.getPositionX() == 0) {
                if (walker.explore(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                        output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                        output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.exploreBack(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                            output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            } else if (walker.getPositionX() == myMaze.getX() - 1) {
                if (walker.explore(output.get(walker.getPositionY()).get(0),
                        output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                        output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.exploreBack(output.get(walker.getPositionY()).get(0),
                            output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(walker.getPositionX() - 1),
                            output.get(walker.getPositionY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            } else if (walker.getPositionY() == 0) {
                if (walker.explore(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                        output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                        output.get(myMaze.getY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.explore(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                            output.get(myMaze.getY() - 1).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            } else if (walker.getPositionY() == myMaze.getY() - 1) {
                if (walker.explore(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                        output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                        output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                        output.get(0).get(walker.getPositionX()), myMaze.getX(), myMaze.getY()) == true) {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "X");
                } else {
                    output.get(walker.getPositionY()).set(walker.getPositionX(), "W");
                    walker.explore(output.get(walker.getPositionY()).get(walker.getPositionX() + 1),
                            output.get(walker.getPositionY() + 1).get(walker.getPositionX()),
                            output.get(walker.getPositionY()).get(myMaze.getX() - 1),
                            output.get(0).get(walker.getPositionX()), myMaze.getX(), myMaze.getY());
                }
            }
        }
    }

    public List<List<String>> getColumnOutput() {
        return columnOutput;
    }

    public void setColumnOutput(List<List<String>> columnOutput) {
        this.columnOutput = columnOutput;
    }

    private List<List<String>> columnOutput;

    public MazeOutput() {

    }
}
