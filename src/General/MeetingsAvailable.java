// I am writing an App that helps people to meet up.  One of the features is to
// take the calendars of 2 people and return a list of times they can meet.  The
// calendars consist of the person's AVAILBLE TIME to meet.  
// Write a method, taking 2 calendars, return all the
// times they can meet.
// function availableTimes[] getAvailableTimes(cal1, cal2)

// int [][] arr = new int[2][2]
// [[1200, 1450] [1600, 2000]]

// [[100,200], [400,1000]...]
// [[100,200], [300, 700]]
// answer:
// [[100, 200], [400, 700]]
//      i       i       i=10
// [[10,150], [151,1000]...]
// [[100,200], [300, 700]]
//    j        j
// [[100, 150], [151, 200], [300,700]]

/*
 * Click `Run` to execute the snippet below!
 */
package General;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class MeetingsAvailable {

    public static List<List<Integer>> getAvailableTimes(int [][] cal1, int [][] cal2){

        List<List<Integer>> result = new ArrayList<>();
        int i=0; int j=0;

        while(i < cal1.length && j < cal2.length){
            // [[10,150], [151,1000]...]
            // [[100,200], [300, 700]]
            //[110,500]
            //[60,100]
            //// [[100, 150], [151, 200], [300,700]]

            if(!(cal1[i][1] <  cal2[j][0] || cal2[j][1] < cal1[i][0])){
                List<Integer> subList = new ArrayList<>();
                subList.add(Math.max(cal1[i][0], cal2[j][0]));
                subList.add(Math.min(cal1[i][1], cal2[j][1]));
                result.add(subList);
            }

            if(cal1[i][1] > cal2[j][1]){
                j++;
            }

            else if (cal1[i][1] < cal2[j][1]) {
                i++;
            }
            else{
                i++;
                j++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        /*int [][] cal1 = {{10,150}, {151,1000}};
        int [][] cal2 = {{100,200}, {300, 700}};
        */
        int [][] cal1 = {{10,200}, {450,600}};
        int [][] cal2 = {{50,150}, {401, 600}};
        List<List<Integer>> result = getAvailableTimes(cal1, cal2);

        for (List<Integer> item : result) {
            System.out.print(Arrays.toString(item.toArray()) + " ");
        }
    }
}
