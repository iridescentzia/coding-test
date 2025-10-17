import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] result = new int[id_list.length];
        
        Set<String> reportSet = new HashSet<>(Arrays.asList(report));
        Map<String, Integer> reportCnt = new HashMap<>();
        Map<String, List<String>> userReportedList = new HashMap<>();
        
        for(String re : reportSet) {
            String[] part = re.split(" ");
            String from = part[0];
            String to = part[1];
            
            reportCnt.put(to, reportCnt.getOrDefault(to,0) + 1);
            
            if(!userReportedList.containsKey(from)) {
                userReportedList.put(from, new ArrayList<>());
            }
            
            userReportedList.get(from).add(to);
        }
        
        for(int i = 0; i < id_list.length; i++) {
            String reporter = id_list[i];
            
            for(String reportee : userReportedList.getOrDefault(reporter, new ArrayList<>())) {
                if(reportCnt.get(reportee) >= k) {
                    result[i] += 1;
                }
            }
        }
        
        return result;
    }
}