package Karat;/*

We are working on a security system for a badged-access room in our company's building.

Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:

1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)

2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)

Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.

EMPTY
badge_records_1 = [
MISSING ENTER for Martha
  ["Martha",   "exit"],
  ["Paul",     "enter"],
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "enter"],
  ["Paul",     "enter"],
  ["Curtis",   "exit"],
  ["Curtis",   "enter"],
  ["Paul",     "exit"],
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "exit"],
  ["Paul",     "enter"],
MISSING EXIT for Paul
  ["Paul",     "enter"],
MISSING EXIT for Paul
  ["Martha",   "exit"],
]
EMPTY

Expected output: ["Curtis", "Paul"], ["Martha", "Curtis"]

Additional test cases:

badge_records_2 = [
  ["Paul", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
]

Expected output: ["Paul"], []

badge_records_3 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
]

Expected output: [], ["Paul"]

badge_records_4 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
  ["Paul", "enter"],
  ["Martha", "enter"],
  ["Martha", "exit"],
]

Expected output: ["Paul"], ["Paul"]

badge_records_5 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
]

Expected output: [], []

badge_records_6 = [
  ["Paul", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
]

Expected output: ["Paul"], ["Paul"]

EMPTY
badge_records_7 = [
  ["Paul", "enter"], {paul: enter}
  ["Paul", "exit"],  {paul: }
MISSING ENTER
  ["Paul", "exit"],
  ["Paul", "enter"],
MISSING EXIT
]
EMPTY

Expected output: enter but missing exit ["Paul"], exit but mising enter ["Paul"]


n: length of the badge records array

*/

import java.util.*;

public class SecuritySystemBadges {

    /*
    Map(name, times) -> Tree Set.
    if set size >= 3
    iterate through set -> if alternate element diff < 100. then take start element  -> iterate till greater than 100 -> add to list. else increment by 1.
     */

    private static Map<String, List<Integer>> oneHourUsers(String [][] records){

        Map<String, TreeSet<Integer>> map = new HashMap<>();
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for(int i=0; i< records.length;i++){
            map.computeIfAbsent(records[i][0], k-> new TreeSet<>()).add(Integer.parseInt(records[i][1]));
        }
        for(Map.Entry<String, TreeSet<Integer>> mapItem: map.entrySet()){
            String name = mapItem.getKey();
            TreeSet<Integer> times = mapItem.getValue();
            List<Integer> listTimes = new ArrayList<>(times);

            if(listTimes.size() >= 3){
                int i = 0;
                while(i < listTimes.size()-2){
                    //int start = i;
                    if(listTimes.get(i+2) -listTimes.get(i) <= 100){
                        int start = i+2;
                        while(start<listTimes.size() && listTimes.get(start) - listTimes.get(i) <= 100){
                            start = start +1;
                        }
                        List<Integer> subList = listTimes.subList(i, start);
                        if(!resultMap.containsKey(name))
                        resultMap.put(name, subList);
                        i = start;
                    }
                    else{
                        i = i + 1;
                    }
                }
            }
        }
        return resultMap;
    }

    private List<List<String>> getAnamolies(String [][] records){
        /*
        <--Logic for this problem-->
    missingEnterSet, missingExitSet
    map(name, enter/exit)
    if(map not present){
    enter -> add to map
    exit -> missing enter -> add to missing enter set
    }
    else{
    enter -> missing exit -> add to missing exit set
    exit -> remove from map.
    }
    after iteration.
    if map has enter -> add to missing exit.
     */
       if(records==null || records.length==0)return null;

       Map<String, String> map = new HashMap<>();
       Set<String> missingEnter = new HashSet<>();
       Set<String> missingExit = new HashSet<>();
       List<List<String>> result = new ArrayList<>();

       for(int i=0; i< records.length; i++){
           String name = records[i][0];
           String entryExit = records[i][1];

           if(!map.containsKey(name)){
               if(entryExit.equalsIgnoreCase("enter"))
                   map.put(name, entryExit);
               else
                   missingEnter.add(name);
           }
           else{
               if(entryExit.equalsIgnoreCase("enter"))
                   missingExit.add(name);
               else
                   map.remove(name);
           }
       }

       for(Map.Entry<String, String> mapItem: map.entrySet())
           if(mapItem.getValue().equalsIgnoreCase("enter"))
               missingExit.add(mapItem.getKey());
       result.add(new ArrayList<>(missingEnter));
       result.add(new ArrayList<>(missingExit));

       return result;
    }


    public static void main(String[] argv) {
        String badgeRecords1[][] = new String[][] {
                // MISSING ENTER for Martha
                {"Martha",   "exit"},
                {"Paul",     "enter"},
                {"Martha",   "enter"},
                {"Martha",   "exit"},
                {"Jennifer", "enter"},
                {"Paul",     "enter"},
                // MISSING ENTER FOR CURTIS
                {"Curtis",   "exit"},
                {"Curtis",   "enter"},
                // MISSING EXIT for Curtis
                {"Paul",     "exit"},
                {"Martha",   "enter"},
                {"Martha",   "exit"},
                {"Jennifer", "exit"},
                {"Paul",     "enter"},
                {"Paul",     "enter"},
                // MISSING EXIT for Paul
                {"Martha",   "exit"},
        };

        SecuritySystemBadges obj = new SecuritySystemBadges();
        String badgeRecords2[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
        };

        String badgeRecords3[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
        };

        String badgeRecords4[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Martha", "enter"},
                {"Martha", "exit"},
        };

        String badgeRecords5[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "exit"},
        };

        String badgeRecords6[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
        };

        String badgeRecords7[][] = new String[][] {
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
        };

        List<String [][]> result = Arrays.asList(badgeRecords1,badgeRecords2, badgeRecords3, badgeRecords4, badgeRecords5, badgeRecords6, badgeRecords7);

        /*for(String[][] item: result){
            List<List<String>> res = obj.getAnamolies(item); // missing exit ["Curtis", "Paul"], missing entry ["Martha", "Curtis"]
            for(List<String> var: res){
                System.out.print(Arrays.toString(var.toArray()) + " ");
            }
            System.out.println();
        }*/

        String [][] badgetimes = {
                {"Paul", "1355"},
                {"Jennifer", "1910"},
                {"John", "830"},
                {"Paul", "1315"},
                {"John", "1615"},
                {"John", "1640"},
                {"John", "835"},
                {"Paul", "1405"},
                {"John", "855"},
                {"John", "930"},
                {"John", "915"},
                {"John", "730"},
                {"Jennifer", "1335"},
                {"Jennifer", "730"},
                {"John", "1630"}};
        Map<String, List<Integer>> map = oneHourUsers(badgetimes);

        for(Map.Entry<String, List<Integer>> mapItem: map.entrySet()){
            System.out.println(mapItem.getKey() + " : " + Arrays.toString(mapItem.getValue().toArray()));
        }




    }
}
