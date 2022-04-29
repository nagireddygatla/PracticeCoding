package Karat;

import java.util.*;

public class UserResources {

    private static Map<String,List<Integer>> getMinMax(String [][] userResources){
        Map<String, List<Integer>> map = new HashMap<>();


        for(int i=0; i < userResources.length; i++){
            String user = userResources[i][1];
            int timestamp = Integer.parseInt(userResources[i][0]);
            if(map.containsKey(user)){
                List<Integer> list = map.get(user);
                int minTime = Math.min(timestamp, list.get(0));
                int maxTime = Math.max(timestamp, list.get(1));
                list.set(0,minTime);
                list.set(1,maxTime);
                //list = new ArrayList<>(Arrays.asList(minTime, maxTime));
                map.put(user, list);
            }
            else{
                List<Integer> list = new ArrayList<>();
                int minTime = Math.min(timestamp, Integer.MAX_VALUE);
                int maxTime = Math.max(timestamp, Integer.MIN_VALUE);
                list.add(minTime);
                list.add(maxTime);
                map.put(user, list);
            }
        }
        return map;
    }

    private static List<String> maxResources(String [][] resources){
        Map<String, List<Integer>> map = new HashMap<>();

        for(int i=0; i < resources.length; i++) {
            String resource = resources[i][2];
            int timestamp = Integer.parseInt(resources[i][0]);
            map.computeIfAbsent(resource, k->new ArrayList<>()).add(timestamp);
        }

        int maxResourcesCount = 0;
        String maxResourceName="";
        for(Map.Entry<String, List<Integer>> mapItem: map.entrySet()){
            String resource = mapItem.getKey();
            List<Integer> timesList = mapItem.getValue();
            Collections.sort(timesList);
            for(int i =0; i< timesList.size()-1; i++){
                int end =0;
                if(timesList.get(i+1) - timesList.get(i) <= 300) {
                    end = i+1;
                    while (end < timesList.size() && timesList.get(end) - timesList.get(i) <= 300) {
                        end++;
                    }
                }
                else{
                    end = i;
                }
                int resourceCount = end-i;

                if(resourceCount>maxResourcesCount){
                    maxResourcesCount = resourceCount;
                    maxResourceName = resource;
                    System.out.println(timesList.subList(i,end));
                }
            }
        }

        List<String> result = new ArrayList<>();
        result.add(maxResourceName);
        result.add(String.valueOf(maxResourcesCount));

        return result;

    }

    public static void main(String [] args) {
        String[][] logs = {
                {"58523", "user_1", "resource_1"},
                {"62314", "user_2", "resource_2"},
                {"54001", "user_1", "resource_3"},
                {"200", "user_6", "resource_5"},
                {"215", "user_6", "resource_4"},
                {"54060", "user_2", "resource_3"},
                {"53760", "user_3", "resource_3"},
                {"58522", "user_4", "resource_1"},
                {"53651", "user_5", "resource_3"},
                {"2", "user_6", "resource_1"},
                {"100", "user_6", "resource_6"},
                {"400", "user_7", "resource_2"},
                {"100", "user_8", "resource_2"},
                {"54359", "user_1", "resource_3"},
        };

        String[][] logs2 = {
                { "300", "user_1", "resource_3" },
                {"599", "user_1", "resource_3"},
                {"900", "user_1", "resource_3"},
                {"1199", "user_1", "resource_3"},
                {"1200", "user_1", "resource_3"},
                {"1201", "user_1", "resource_3"},
                {"1202", "user_1", "resource_3"}};

        Map<String, List<Integer>> map = getMinMax(logs);

        /*for(Map.Entry<String, List<Integer>> mapItem: map.entrySet()){
            System.out.println(mapItem.getKey() + " " + mapItem.getValue());
        }*/
        System.out.println(Arrays.deepToString(maxResources(logs).toArray()));


    }
}
