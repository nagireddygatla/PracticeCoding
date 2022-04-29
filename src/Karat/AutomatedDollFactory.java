package Karat;

import java.util.*;


public class AutomatedDollFactory {

    private static List<String> properDolls(String [][] parts, String [] allParts){
        Map<String, Set<String>> dollParts = new HashMap<>();

        Set<String> partsSet = new HashSet<>();

        for(int i=0; i< allParts.length; i++){
            partsSet.add(allParts[i]);
        }

        for(int i=0; i< parts.length; i++){
            dollParts.computeIfAbsent(parts[i][0], k-> new HashSet<>()).add(parts[i][1]);
        }
        List<String> result = new ArrayList<>();

        for(Map.Entry<String, Set<String>> mapItem: dollParts.entrySet()){

            Set<String> allDollParts = new HashSet<>(partsSet);
            Set<String> currentDoll = mapItem.getValue();
            for(String currentDollPart: currentDoll){
                allDollParts.remove(currentDollPart);
            }
            if(allDollParts.size()==0)
                result.add(mapItem.getKey());
        }
        return result;

    }

    private static Map<String, List<String>> sourceDestination(String [][] paths){

        Map<String, Integer> indegree = new HashMap<>();
        Map<String, List<String>> pathMap = new HashMap<>();

        for(int i=0; i< paths.length; i++){
            indegree.put(paths[i][1], indegree.getOrDefault(paths[i][1],0)+1);
            indegree.put(paths[i][0], indegree.getOrDefault(paths[i][0],0));
            pathMap.computeIfAbsent(paths[i][0], k-> new ArrayList<>()).add(paths[i][1]);
        }

        List<String> zeroIndegree = new ArrayList<>();
        for(Map.Entry<String, Integer> mapItem: indegree.entrySet()){
            if(mapItem.getValue()==0)
                zeroIndegree.add(mapItem.getKey());
        }

        Map<String, List<String>> result = new HashMap<>();
        for(int i=0; i< zeroIndegree.size(); i++){
            Queue<String> queue = new LinkedList<>();
            String startElement = zeroIndegree.get(i);
            queue.offer(startElement);
            Set<String> childs = new HashSet<>();
            while(!queue.isEmpty()){
                String curr = queue.remove();
                List<String> list = pathMap.get(curr);

                if(list!=null) {
                    for (int j = 0; j < list.size(); j++) {
                        queue.offer(list.get(j));
                    }
                }
                else{
                    childs.add(curr);
                }

            }
            result.put(startElement, new ArrayList<>(childs));
        }
        return result;
    }

    public static void main(String [] args){

        String [] allParts = {"head","neck","torso","left_arm","right_arm","left_leg","right_leg"};
        String [][] dollsParts = {{"sally", "left_leg"}, {"sally", "right_leg"}, {"user1145h", "left_leg"}, {"sally", "torso"},
                {"sally", "left_arm"}, {"sally", "right_arm"}, {"sally", "neck"}, {"sally", "head"}};

        System.out.println(properDolls(dollsParts,allParts));

        String [][] paths = {
{"A", "B"},
{"A", "C"},
{"B", "K"},
{"C", "K"},
{"E", "L"},
{"F", "G"},
{"J", "M"},
{"E", "F"},
{"G", "H"},
{"G", "I"},
{"C", "G"}
};
        Map<String, List<String>> result = sourceDestination(paths);
        for(Map.Entry<String, List<String>> mapItem: result.entrySet()){
            System.out.println(mapItem.getKey() + ":" + mapItem.getValue());
        }

    }

}
