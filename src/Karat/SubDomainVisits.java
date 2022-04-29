package Karat;

import javafx.util.Pair;

import java.util.*;

public class SubDomainVisits {

    public static List<String> subdomainVisits(String[] cpdomains) {

        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i< cpdomains.length; i++){
            String [] cpinfo = cpdomains[i].split("\\s+");
            String [] domainName = cpinfo[1].split("\\.");
            int count = Integer.parseInt(cpinfo[0]);
            String curr = "";
            for(int j=domainName.length-1; j>=0; j--){
                if(j!=domainName.length-1)
                    curr = domainName[j] + "." + curr;
                else
                    curr = domainName[j];
                map.put(curr, map.getOrDefault(curr,0) + count);
            }
        }
        List<String> list = new ArrayList<>();
        for(Map.Entry<String, Integer> mapItem: map.entrySet()){
            list.add(mapItem.getValue() + " " + mapItem.getKey());
        }

        System.out.println(Arrays.toString(list.toArray()));
        return list;
    }

    public static List<String> contiguousHistory(String [] user0, String [] user1){
        int m = user0.length;
        int n = user1.length;
        int [][] dp = new int[m][n];
        int max = 0;
        int x = 0;
        int y = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j< n; j++){
                if(user0[i].equalsIgnoreCase(user1[j])){
                    if(i==0 || j==0)
                        dp[i][j] =1;
                    else
                        dp[i][j] = dp[i-1][j-1] +1;
                    if(dp[i][j] > max){
                        max = dp[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
        }
        List<String> result = new ArrayList<>();
        while(x>=0 && y>=0 && max>0){
            result.add(user0[x]);
            x--;
            max--;
            y--;
        }
        Collections.reverse(result);
        return result;
    }


    public static void main(String [] args){
        String [] subDomains  = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        subdomainVisits(subDomains);
        String [] user0 = {"/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"};
        String [] user1 = {"/start", "/pink", "/register", "/orange", "/red", "a"};
        String [] user2 = {"a", "/one", "/two"};
        String [] user3 = {"/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen"};
        String [] user4 = {"/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red", "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow"};
        String [] user5 = {"a"};
        String [] user6 = {"/pink","/orange","/six","/plum","/seven","/tan","/red", "/amber"};
        System.out.println(Arrays.deepToString(contiguousHistory(user0,user1).toArray()));
        System.out.println(Arrays.deepToString(contiguousHistory(user0,user2).toArray()));
        System.out.println(Arrays.deepToString(contiguousHistory(user0,user0).toArray()));
        System.out.println(Arrays.deepToString(contiguousHistory(user2,user1).toArray()));
        System.out.println(Arrays.deepToString(contiguousHistory(user5,user2).toArray()));
        System.out.println(Arrays.deepToString(contiguousHistory(user3,user4).toArray()));
        System.out.println(Arrays.deepToString(contiguousHistory(user4,user3).toArray()));
        System.out.println(Arrays.deepToString(contiguousHistory(user3,user6).toArray()));
    }
}
