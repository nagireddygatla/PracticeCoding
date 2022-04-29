package Karat;

import java.util.*;

public class StudentCoursePairs {


    private static Map<List<Integer>, List<String>> studentCoursePairs(String [][] studentCourses){
        Map<Integer, List<String>> studentCourseMap = new HashMap<>();

        for(int i=0; i< studentCourses.length; i++){
            studentCourseMap.computeIfAbsent(Integer.parseInt(studentCourses[i][0]), k-> new ArrayList<>()).add(studentCourses[i][1]);
        }

        List<Map.Entry<Integer, List<String>>> entryList = new ArrayList<>(studentCourseMap.entrySet());

        Map<List<Integer>, List<String>> finalMap = new HashMap<>();
        for(int i=0; i< entryList.size()-1; i++){
            for(int j=i+1; j< entryList.size(); j++){
                List<Integer> keyList = new ArrayList<>();
                keyList.addAll(Arrays.asList(entryList.get(i).getKey(), entryList.get(j).getKey()));
                List<String> commonList = findCommonsList(entryList.get(i).getValue(), entryList.get(j).getValue());
                finalMap.put(keyList, commonList);
            }
        }
        return finalMap;

    }

    private static List<String> findCommonsList(List<String> list1, List<String> list2) {
        List<String> commonsList = new ArrayList<>();
        for(String list1Item: list1){
            if(list2.contains(list1Item)){
                commonsList.add(list1Item);
            }
        }
        return commonsList;

    }

    public static void main(String [] args){
        String [][] student_course_pairs_1 = {{"58", "Linear Algebra"},
                                                {"94", "Art History"},
                                                {"94", "Operating Systems"},
                                                {"17", "Software Design"},
                                                {"58", "Mechanics"},
                                                {"58", "Economics"},
                                                {"17", "Linear Algebra"},
                                                {"17", "Political Science"},
                                                {"94", "Economics"},
                                                {"25", "Economics"},
                                                {"58", "Software Design"}};
        Map<List<Integer>, List<String>> result = studentCoursePairs(student_course_pairs_1);

        for(Map.Entry<List<Integer>, List<String>> item: result.entrySet()){
            System.out.println("{" + item.getKey().get(0) + "," + item.getKey().get(1)+ "}" + "  "+ Arrays.toString(item.getValue().toArray()));
        }



    }

}


