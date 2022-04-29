package Karat;
import java.util.*;
public class MeetingCalendar {

        public static void main(String[] args) {
            int[][] p1Meetings = {{1230, 1300}, {845, 900}, {1300, 1500}};
            int[][] p2Meetings = {{0,844},{930, 1200}, {1515, 1546}, {1600, 2400}};
            int[][] p3Meetings = {{845, 915}, {1515, 1545}, {1235, 1245}};
            int[][] p4Meetings = {{1, 5}, {844, 900}, {1515, 1600}};

            List<int[][]> schedules1 = Arrays.asList(p1Meetings, p2Meetings, p3Meetings);
            List<int[][]> schedules2 = Arrays.asList(p1Meetings, p3Meetings);
            List<int[][]> schedules3 = Arrays.asList(p2Meetings, p4Meetings);
            List<List<Integer>> result = findSchedules(schedules1);
            System.out.println("Availablility =>");
            System.out.print("[");
            for (int i = 0; i < result.size(); i++) {
                List<Integer> a = result.get(i);
                System.out.print("[" + a.get(0) + "," + a.get(1) + "]");
            }
            System.out.print("]");
        }

        private static List<List<Integer>> findSchedules(List<int[][]> schedules) {
            List<List<Integer>> combinedSchedules = new ArrayList<>();

            for(int [][] listItem:schedules){
                int [][] meeting = listItem;
                for(int i=0; i< meeting.length; i++){
                    List<Integer> schedule = new ArrayList<>();
                    schedule.add(meeting[i][0]);
                    schedule.add(meeting[i][1]);
                    combinedSchedules.add(schedule);
                }
            }
            Collections.sort(combinedSchedules,  (a,b) -> a.get(0)-b.get(0));
            List<List<Integer>> result = new ArrayList<>();

            int start = combinedSchedules.get(0).get(0);
            if(start!=0){
                List<Integer> startList = new ArrayList<>();
                startList.add(0);
                startList.add(start);
                result.add(startList);
            }

            int end = combinedSchedules.get(combinedSchedules.size()-1).get(1);
            if(end!=2400){
                List<Integer> endList = new ArrayList<>();
                endList.add(end);
                endList.add(2400);
                result.add(endList);
            }

            int ending = combinedSchedules.get(0).get(1);

            for(int i=1; i< combinedSchedules.size(); i++){
                List<Integer> currentSchedule = combinedSchedules.get(i);
                if(currentSchedule.get(0)>ending){
                    List<Integer> gaps = new ArrayList<>();
                    gaps.add(ending);
                    gaps.add(currentSchedule.get(0));
                    result.add(gaps);
                    ending = currentSchedule.get(1);
                }
                else if(currentSchedule.get(1) > ending)
                    ending = currentSchedule.get(1);
            }

            Collections.sort(result, (a,b) -> a.get(0) - b.get(0));

            return result;

        }
    }


