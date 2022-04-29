package Karat;

import java.util.*;
import java.util.Queue;

public class ParentChildPairs {

    public static void main(String [] args){
        int [][] parentChildPairs = {{1, 3},{2, 3},{3, 6},{5, 6},
                                {5, 7},{4, 5},{4, 8},{4, 9},{9, 11}};
        List<List<Integer>> zeroOneParents = findParents(parentChildPairs);

        for(List<Integer> subList: zeroOneParents){
            System.out.println(Arrays.toString(subList.toArray()));
        }

        System.out.println(findCommonAncestor(parentChildPairs, 6, 11));

        int [][] parentChildPairs1 = {{2, 3}, {3, 15}, {3, 6}, {5, 6}, {5, 7}, {4, 5},
                                    {4, 8}, {4, 9}, {9, 11}, {14, 4}};
        Map<Integer, List<Integer>> mapGrouping = childParentsGrouping(parentChildPairs1);
        List<Integer> list = getAllParentsList(mapGrouping,15);
        System.out.println(list.get(list.size()-1));

    }

    private static List<List<Integer>> findParents(int[][] parentChildPairs) {

        List<List<Integer>> finalList = new ArrayList<>();
        Map<Integer, List<Integer>> childParents = childParentsGrouping(parentChildPairs);

        List<Integer> zeroParents = new ArrayList<>();
        List<Integer> oneParents = new ArrayList<>();
        for(Map.Entry<Integer, List<Integer>> mapElem: childParents.entrySet()){
            if(mapElem.getValue().size()==0)
                zeroParents.add(mapElem.getKey());
            if(mapElem.getValue().size()==1)
                oneParents.add(mapElem.getKey());
        }
        finalList.add(zeroParents);
        finalList.add(oneParents);
        return finalList;
    }

    private static Map<Integer, List<Integer>> childParentsGrouping(int [][] parentChildPairs){
        Map<Integer, List<Integer>> childParents = new HashMap<>();

        for(int i=0; i< parentChildPairs.length; i++){
            childParents.computeIfAbsent(parentChildPairs[i][1], k-> new ArrayList<>()).add(parentChildPairs[i][0]);
            childParents.computeIfAbsent(parentChildPairs[i][0], k-> new ArrayList<>());
        }
        return childParents;
    }

    private static int findCommonAncestor(int [][] parentChildPairs, int node1, int node2){
        Map<Integer, List<Integer>> childParents = childParentsGrouping(parentChildPairs);
        List<Integer> list1 = getAllParentsList(childParents, node1);
        List<Integer> list2 = getAllParentsList(childParents, node2);
        List<Integer> bigList = list1.size() > list2.size()?list1:list2;
        List<Integer> smallList = list1.size() > list2.size()?list2:list1;
        Set<Integer> set = new HashSet<>(smallList);
        for(int num:bigList){
            if(set.contains(num)){
                //Here they could ask to return all common ancestors instead of first found ancestors.
                return num;
            }
        }
        return -1;
    }

    private static List<Integer> getAllParentsList(Map<Integer, List<Integer>> childParents, int node) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> returnList = new ArrayList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            int curr = queue.remove();
            returnList.add(curr);
            List<Integer> list = childParents.get(curr);
            for(int i=0; i<list.size(); i++){
                queue.offer(list.get(i));
            }
        }
        return returnList;
    }

}
