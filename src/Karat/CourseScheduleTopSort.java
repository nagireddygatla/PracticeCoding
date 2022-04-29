package Karat;

import java.util.*;
import java.util.Stack;


public class CourseScheduleTopSort {

    public static void main(String[] args) {
        String[][] prereqs_courses1 = {
                {"Foundations of Computer Science", "Operating Systems"},
                {"Data Structures", "Algorithms"},
                {"Computer Networks", "Computer Architecture"},
                {"Algorithms", "Foundations of Computer Science"},
                {"Computer Architecture", "Data Structures"},
                {"Software Design", "Computer Networks"}
        };
        String[][] prereqs_courses2 = {
                {"Data Structures", "Algorithms"},
                {"Algorithms", "Foundations of Computer Science"},
                {"Foundations of Computer Science", "Logic"}
        };

        String[][] prereqs_courses3 = {
                {"Data Structures", "Algorithms"},
        };
        System.out.println(findMid(prereqs_courses1));
        System.out.println(findMid(prereqs_courses2));
        System.out.println(findMid(prereqs_courses3));

    }

    //I solve by Kahn's Algorithm
    private static String findMid(String[][] prereqs_courses1) {
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, String> prereqCourseMap = new HashMap<>();

        for(String[] preReq: prereqs_courses1){
            indegree.put(preReq[1], indegree.getOrDefault(preReq[1],0)+1 );
            indegree.put(preReq[0], indegree.getOrDefault(preReq[0],0));
            //This is (preReq, course) map which is required for traversal
            prereqCourseMap.put(preReq[0],preReq[1]);
        }

        String startCourse = null;
        for(String key:indegree.keySet())
            if(indegree.get(key)==0)
                startCourse = key;

        List<String> list = new ArrayList<>();
        while(startCourse!=null){
            list.add(startCourse);
            startCourse = prereqCourseMap.get(startCourse);
        }
        int mid = 0;
        if(list.size()%2==0)
            mid = list.size()/2-1;
        else
            mid = list.size()/2;


        return list.get(mid);

    }


}
