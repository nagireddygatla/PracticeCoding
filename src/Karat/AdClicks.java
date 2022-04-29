package Karat;

import java.util.*;

public class AdClicks {


    private static Map<String, List<Integer>> adClickPurchases(String [] purchasedUsers, String [][] adClicks, String [][] userIps){

        Set<String> userIds = new HashSet<>();
        for(int i=0; i< purchasedUsers.length; i++){
            userIds.add(purchasedUsers[i]);
        }

        //This may involved str.split(",") for all the inputs.
        Set<String> purchasedIps = new HashSet<>();
        for(int i=0; i< userIps.length; i++){
            if(userIds.contains(userIps[i][0])){
                purchasedIps.add(userIps[i][1]);
            }
        }

        Map<String, List<Integer>> result = new HashMap<>();

        for(int i =0; i< adClicks.length; i++){
            String Ad = adClicks[i][2];
            if(!result.containsKey(adClicks[i][2])){

                List<Integer> list = new ArrayList<>();
                if(purchasedIps.contains(adClicks[i][0]))
                    list.add(1);
                else
                    list.add(0);
                list.add(1);
                result.put(Ad,list);
            }
            else{
                List<Integer> list = result.get(adClicks[i][2]);

                if(purchasedIps.contains(adClicks[i][0]))
                    list.set(0,list.get(0)+1);
                else
                    list.set(0,list.get(0));
                list.set(1,list.get(1)+1);
            }
        }
        return result;

    }

    public static void main(String [] args){
        String [] completed_purchase_user_ids = {
        "3123122444","234111110", "8321125440", "99911063"};
        String [][] adclicks = {

                {"122.121.0.1","2016-11-03 11:41:19","Buy wool coats for your pets"},
                {"96.3.199.11","2016-10-15 20:18:31","2017 Pet Mittens"},
                {"122.121.0.250","2016-11-01 06:13:13","The Best Hollywood Coats"},
                {"82.1.106.8","2016-11-12 23:05:14","Buy wool coats for your pets"},
                {"92.130.6.144","2017-01-01 03:18:55","Buy wool coats for your pets"},
                {"122.121.0.155","2017-01-01 03:18:55","Buy wool coats for your pets"},
                {"92.130.6.145","2017-01-01 03:18:55","2017 Pet Mittens"}};
        String [][] userIps = {
                {"2339985511","122.121.0.155"},
                {"234111110","122.121.0.1"},
                {"3123122444","92.130.6.145"},
                {"39471289472","2001:0db8:ac10:fe01:0000:0000:0000:0000"},
                {"8321125440","82.1.106.8"},
                {"99911063","92.130.6.144"}};

        Map<String, List<Integer>> result = adClickPurchases(completed_purchase_user_ids, adclicks, userIps);

        for(Map.Entry<String, List<Integer>> mapItem:result.entrySet())
            System.out.println(mapItem.getValue().get(0) + " of " + mapItem.getValue().get(1) + " " + mapItem.getKey());

    }
}
