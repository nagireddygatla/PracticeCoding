package General;

/*
 * Click `Run` to execute the snippet below!
 */

/*
* Drop Box Questions:
*
* Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
Output: [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]


https://leetcode.com/discuss/interview-question/417262/dropbox-phone-screen-permissions-in-a-file-system
https://leetcode.com/discuss/interview-question/1088754/Dropbox-or-Phone-Screen-or-March-2021

Game of Life
duplicate files in a path



https://leetcode.com/discuss/interview-question/860501/Text-Editor-Implementation
https://leetcode.com/discuss/interview-question/366628/Dropbox-or-OA-2019-or-Auto-complete-feature

Words in a puzzle

https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
* */

import java.io.*;


// NASA is working on making a panorama (or a very large) image of the universe and has selected Dropbox for storing its images. Since the universe is very large, NASA has decided to split the universe into a two-dimensional grid of sectors. The Hubble telescope will occasionally take a photo of a sector, and send it to us.



// Implement a data structure that affords the following interface. You can assume that the # of rows and columns will be at most 1K (i.e. 1K x 1K grid of sectors.)


public class DropBoxQuestion {
    public DropBoxQuestion(int numRows, int numCols) {
        // initializes the data structure. numRows x numCols is the sector layout.
        // numRows, numCols can be as large as 1K each.
    }

    File f = new File("test.png");


    public void update(int row, int col, Image image) {
        // The Hubble will occasionally call this (via some radio wave communication)
        // to report new imagery for the sector at a particular row and col

        File f = new Image(row, col).getFile();
        image.write(f);


    }

    public Image fetch(int row, int col) {
        // NASA will occasionally call this to check the view of a particular sector.
        // 1 MB
        // 1K x 1K grid of sectors
        // 1000000 = 1M
        Image image = new Image(row, col);

        File currFile = image.getFile();

        Image imageRes = image.read(currFile);

        return null;
    }
}

class Image{

    int size;
    File f;

    public Image(int row, int col){

        //f = new File(row*4 + col);
    }

    public byte[] getBytes(){return null;}
    public void write(File f){};
    public static Image read(File f){return null;}

    public File getFile(){
        return f;
    }
}
