import java.util.*;
class Solution {
    Queue<String> keyQ;
    public int solution(String[][] relation) {
        List<Set<String>> keyList = new ArrayList<>();
        int columnCnt = relation[0].length;
        int keyCnt = 0;
        keyQ = new LinkedList<>();
            
        // 키 개수가 1개일 때 ~ 컬럼 개수 만큼
        for (int size = 1; size <= columnCnt; size++) {
            // 1. 키 개수만큼 고르기 (조합)
            dfs(columnCnt, size, "", 0);
            
            // 2. 튜플 중복 확인 : 유일성
            while (!keyQ.isEmpty()) {
                HashSet<String> keySet = new HashSet<>();
                String nowKey = keyQ.poll(); // 현재 키가 되는 컬럼의 인덱스
                String[] keyarr = nowKey.split(" ");
                boolean chk = true;
                //System.out.println(nowKey);
                
                // 튜플 조회
                for (int i = 0; i < relation.length; i++) {
                    String value = "";
                    // 해당되는 인덱스의 값을 추가
                    for (int j = 0; j < keyarr.length; j++) {
                        int idx = Integer.parseInt(keyarr[j]);
                        value += relation[i][idx] + " ";
                    }
                    value = value.substring(0, value.length()-1);
                    //System.out.println(value);
                    
                    // 중복 확인
                    if (!keySet.contains(value)) keySet.add(value);
                    else {
                        chk = false;
                        break;
                    }
                }
                
                // 중복 (키가 될 수 없음)
                if (!chk) continue;
                else {
                    // 부분 집합에 포함되는지 확인 : 최소성
                    Set<String> tmp = new HashSet<>();
                    for (String idx : keyarr) tmp.add(idx);
                    
                    boolean chk2 = true;
                    for (Set<String> k : keyList) {
                        if (tmp.containsAll(k)) {
                            chk2 = false;
                            break;
                        }
                    }
                    
                    if (chk2) {
                        keyList.add(tmp);
                        keyCnt++;
                    } 
                }
                //System.out.println();
            }
        }
        return keyCnt;
    }
    
    // 키 조합이 될 수 있는 인덱스 고르기
    private void dfs(int columnCnt, int size, String selected, int idx) {
        // 종료 조건 : 키 개수
        if (selected.length() != 0) {
            String[] keyarr = selected.split(" ");
            if (keyarr.length == size) {
                keyQ.add(selected);
                return;
            }
        }
        
        for (int i = idx; i < columnCnt; i++) {
            if (selected.length() == 0) dfs(columnCnt, size, String.valueOf(i), i+1);
            else {
                dfs(columnCnt, size, selected + " " + i, i+1);
            }
        }
    }
}