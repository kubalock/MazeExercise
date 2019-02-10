/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeexercise;

/**
 *
 * @author Grzegorz
 */
public class MazeWalker {

    private int positionX;
    private int positionY;

    
    // MazeWalker constructor
    public MazeWalker(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
    
    
    // **
    // An explore method over here tries to perform movement methods one by one until one of them returns true
    
    public boolean explore(String rightLetter, String downLetter, String leftLetter, String upLetter, int mazeX, int mazeY) {
        if (goRight(rightLetter, mazeX, mazeY) == true) {
            return true;
        }
        if (goDown(downLetter, mazeX, mazeY) == true) {
            return true;
        }
        if (goLeft(leftLetter, mazeX, mazeY) == true) {
            return true;
        }
        if (goUp(upLetter, mazeX, mazeY) == true) {
            return true;
        }
        return false;
    }
    
    // **
    // Method below tries to perfor siilar task as the one above. Methods inside it, however, are looking for the X letter in the maze,
    // which tells the way of going back to the MazeWalker

    public boolean exploreBack(String rightLetter, String downLetter, String leftLetter, String upLetter, int mazeX, int mazeY) {
        if (goBackRight(rightLetter, mazeX, mazeY) == true) {
            return true;
        }
        if (goBackDown(downLetter, mazeX, mazeY) == true) {
            return true;
        }
        if (goBackLeft(leftLetter, mazeX, mazeY) == true) {
            return true;
        }
        if (goBackUp(upLetter, mazeX, mazeY) == true) {
            return true;
        }
        return false;
    }

    public boolean goBackRight(String letter, int mazeX, int mazeY) {
        if ("E".equals(letter) || "X".equals(letter) || "S".equals(letter)) {
            this.setPositionX(this.getPositionX() + 1);
            if (this.getPositionX() < 0) {
                this.setPositionX(mazeX-1);
            } else if (this.getPositionX() > mazeX) {
                this.setPositionX(0);
            }
            return true;
        }
        return false;
    }

    public boolean goRight(String letter, int mazeX, int mazeY) {
        if (" ".equals(letter) || "E".equals(letter)) {
            this.setPositionX(this.getPositionX() + 1);
            if (this.getPositionX() < 0) {
                this.setPositionX(mazeX-1);
            } else if (this.getPositionX() > mazeX) {
                this.setPositionX(0);
            }
            return true;
        }
        return false;
    }

    public boolean goBackDown(String letter, int mazeX, int mazeY) {
        if ("X".equals(letter) || "E".equals(letter) || "S".equals(letter)) {
            this.setPositionY(this.getPositionY() + 1);
            if (this.getPositionY() < 0) {
                this.setPositionY(mazeY-1);
            } else if (this.getPositionY() > mazeY) {
                this.setPositionY(0);
            }
            return true;
        }
        return false;
    }

    public boolean goDown(String letter, int mazeX, int mazeY) {
        if (" ".equals(letter) || "E".equals(letter)) {
            this.setPositionY(this.getPositionY() + 1);
            if (this.getPositionY() < 0) {
                this.setPositionY(mazeY-1);
            } else if (this.getPositionY() > mazeY) {
                this.setPositionY(0);
            }
            return true;
        }
        return false;
    }

    public boolean goLeft(String letter, int mazeX, int mazeY) {
        if (" ".equals(letter) || "E".equals(letter)) {
            this.setPositionX(this.getPositionX() - 1);
            if (this.getPositionX() < 0) {
                this.setPositionX(mazeX-1);
            } else if (this.getPositionX() > mazeX) {
                this.setPositionX(0);
            }
            return true;
        }
        return false;
    }

    public boolean goBackLeft(String letter, int mazeX, int mazeY) {
        if ("X".equals(letter) || "E".equals(letter) || "S".equals(letter)) {
            this.setPositionX(this.getPositionX() - 1);
            if (this.getPositionX() < 0) {
                this.setPositionX(mazeX-1);
            } else if (this.getPositionX() > mazeX) {
                this.setPositionX(0);
            }
            return true;
        }
        return false;
    }

    public boolean goUp(String letter, int mazeX, int mazeY) {
        if (" ".equals(letter) || "E".equals(letter)) {
            this.setPositionY(this.getPositionY() - 1);
            if (this.getPositionY() < 0) {
                this.setPositionY(mazeY-1);
            } else if (this.getPositionY() > mazeY) {
                this.setPositionY(0);
            }
            return true;
        }
        return false;
    }

    public boolean goBackUp(String letter, int mazeX, int mazeY) {
        if ("X".equals(letter) || "E".equals(letter) || "S".equals(letter)) {
            this.setPositionY(this.getPositionY() - 1);
            return true;
        }
        return false;
    }

    public void setPositionX(int x) {
        this.positionX = x;
    }

    public void setPositionY(int y) {
        this.positionY = y;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
