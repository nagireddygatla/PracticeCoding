package General;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fetch {

    int pageNum;
    //int nextResult;
    List<Integer> list = new ArrayList<>();

    public List<Integer> fetchWrapper(int numResults){


        List<Integer> result = new ArrayList<>();
        //nextResult = nextResult + numResults;
        if(numResults <= list.size()){
            List<Integer> currentList = list;
            list = addToResult(numResults, result, currentList);
            return result;
        }
        else{

            int finalResult = numResults - list.size();
            int numCalls = finalResult/10 + (finalResult % 10 > 0?1:0);

            if(list.size() > 0){
                result.addAll(list);
                numResults = numResults - list.size();

            }

            for(int i=0; i< numCalls; i++){

                Pair<List<Integer>, Integer> fetchResult = fetchPage(pageNum);
                if(fetchResult==null)return result;
                pageNum = fetchResult.getValue();
                List<Integer> currentList = fetchResult.getKey();

                if(numResults >= 10){
                    result.addAll(currentList);
                    numResults = numResults - 10;
                }
                else{
                    list = addToResult(numResults, result, currentList);
                    break;
                }
            }
        }
        return result;
    }

    public List<Integer> addToResult(int numResults, List<Integer> result, List<Integer> currentList){
        int i=0;
        while(numResults>0){
            result.add(currentList.get(i));
            i++;
            numResults--;
        }
        List<Integer> tempList = new ArrayList<>();
        while(i<currentList.size()){
            tempList.add(currentList.get(i));
            i++;
        }
        return tempList;
    }

    int [] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
    int index = 0;
    public Pair<List<Integer>, Integer> fetchPage(int pageNumber){
        pageNumber = pageNumber +1;
        List<Integer> result = new ArrayList<>();
        if(index < array.length){
            for(int i = index; i < index + 10; i++){
                result.add(array[i]);
            }
        }
        else
            return null;

        index = index + 10;
        return new Pair<>(result, pageNumber);
    }


    public static void main(String [] args){

        Fetch obj = new Fetch();
        System.out.println(Arrays.toString(obj.fetchWrapper(5).toArray()));
        System.out.println(Arrays.toString(obj.fetchWrapper(5).toArray()));
        System.out.println(Arrays.toString(obj.fetchWrapper(5).toArray()));
        System.out.println(Arrays.toString(obj.fetchWrapper(3).toArray()));
        System.out.println(Arrays.toString(obj.fetchWrapper(2).toArray()));
        System.out.println(Arrays.toString(obj.fetchWrapper(1).toArray()));
        System.out.println(Arrays.toString(obj.fetchWrapper(5).toArray()));
        System.out.println(Arrays.toString(obj.fetchWrapper(10).toArray()));
    }


}
