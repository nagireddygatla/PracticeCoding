package Karat;

import java.util.HashMap;
import java.util.Map;

public class CompleteHand {

    private static boolean completeHand(String tiles){

        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i< tiles.length(); i++){
            char c = tiles.charAt(i);
            map.put(c, (map.getOrDefault(c,0)+1)%3);
        }
        boolean foundPair = false;
        for(Map.Entry<Character, Integer> mapItem: map.entrySet()){
            int value = mapItem.getValue();
            if(value==0){
                continue;
            }
            else if(value==2){
                if(foundPair)
                    return false;
                foundPair = true;
            }
            else{
                return false;
            }
        }
        return foundPair;
    }


    private static boolean completeHandFollowup(String tiles){
        int seriesCount = 0;
        int twoCount = 0;
        int threeCount = 0;
        int [] tilesCount = new int[10];
        for(int i=0; i< tiles.length(); i++){
            int val = Integer.parseInt(String.valueOf(tiles.charAt(i)));
            tilesCount[val]++;
        }

        for(int i=0; i<10; i++){
            if(tilesCount[i]==0)
                continue;
            if(tilesCount[i]==1){
                seriesCount++;
                if(seriesCount==3)
                    seriesCount=0;
            }
            else if(tilesCount[i]==2){
                if(twoCount==1)
                    return false;
                if(seriesCount>0)
                    return false;
                twoCount++;
            }
            else if(tilesCount[i]==3){
                if(seriesCount>0)
                    return false;
                threeCount++;
            }
            else{
                int rem3 = tilesCount[i]%3;
                int rem2 = rem3%2;
                if(rem3>0){
                    if(rem2==0)
                        twoCount++;
                    else
                        seriesCount++;
                }

            }
        }

        return seriesCount==0 && twoCount==1 && threeCount>=0;
    }


    public static void main(String [] args){

        System.out.println("Complete Hand");
        // True. 111 33 555
        System.out.println(completeHand("11133555"));
        // False. There are three triples, 111 333 555 but no pair.
        System.out.println(completeHand("111333555"));
        // True. 000 00 111. Your pair and a triplet can be of the same value// There is also no limit to how many of each tile there is.
        System.out.println(completeHand("00000111"));
        // True. Tiles are not guaranteed to be in order
        System.out.println(completeHand("13233121"));
        // False. There cannot be more than one pair
        System.out.println(completeHand("11223344555"));
        // True. You can have many of one tile
        System.out.println(completeHand("99999999"));
        // False.
        System.out.println(completeHand("999999999"));
        // False.
        System.out.println(completeHand("9"));
         // True.
        System.out.println(completeHand("99"));
       // False.
        System.out.println(completeHand("000022"));
        // False. There cannot be any tiles left over.
        System.out.println(completeHand("221"));
        // False. There cannot be any tiles left over.
        System.out.println(completeHand("889"));
        System.out.println("Complete Hand Followup");

        System.out.println("1: " +completeHandFollowup("11333456"));
        System.out.println("2: " +completeHandFollowup("1133456"));
        System.out.println("3: " +completeHandFollowup("1133345"));
        System.out.println("4: " +completeHandFollowup("123456777"));
        System.out.println("5: " +completeHandFollowup("12345677788"));
        System.out.println("6: " +completeHandFollowup("81237456778"));
        System.out.println("7: " +completeHandFollowup("12333456777"));
        System.out.println("8: " +completeHandFollowup("11342225"));

    }
}
