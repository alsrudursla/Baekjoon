import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        // HashMap 에 차량 번호에 따른 시간 계산해서 넣어주기
        // 최종 해시맵 한 개, in out 기록용 한 개
        
        HashMap<String, Integer> total = new HashMap<>();
        HashMap<String, String> in_records = new HashMap<>();
        
        for (int i = 0; i < records.length; i++) {
            String[] now = records[i].split(" ");
            
            // 1. IN 일 때
            if (now[2].equals("IN")) {
                in_records.put(now[1], now[0]); // 차량 번호, 들어온 시각
                System.out.println("입차 : " + now[1] + " " + now[0]);
            } else { // 2. OUT 일 때
                String in_record = in_records.get(now[1]);
                String out_record = now[0];
                System.out.println("출차 : " + now[1] + " " + now[0]);
                
                // 시간 비교하기 : out - in
                int in_hour = Integer.valueOf(in_record.split(":")[0]);
                int out_hour = Integer.valueOf(out_record.split(":")[0]);
                int in_miniute = Integer.valueOf(in_record.split(":")[1]);
                int out_miniute = Integer.valueOf(out_record.split(":")[1]);
                
                // 분으로 바꿔서 계산
                int in_time = in_hour * 60 + in_miniute;
                int out_time = out_hour * 60 + out_miniute;
                int time_diff = out_time - in_time;
                System.out.println("시간 차 : " + time_diff);
                
                // 총 시간 해시맵에 더해주기
                total.put(now[1], total.getOrDefault(now[1], 0) + time_diff);
                
                // 입차 내역 지우기
                in_records.remove(now[1]);
            }
        }
        
        // 남아있는 입차 기록은 23:59 에 출차로 계산해주기
        for (Map.Entry<String, String> rest : in_records.entrySet()) {
            String car_number = rest.getKey();
            String in_record = rest.getValue();
            System.out.println("남아있는 입차 : " + car_number + " " + in_record);
            
            int in_hour = Integer.valueOf(in_record.split(":")[0]);
            int in_miniute = Integer.valueOf(in_record.split(":")[1]);
            
            int in_time = in_hour * 60 + in_miniute;
            int out_time = 23 * 60 + 59;
            int time_diff = out_time - in_time;
            System.out.println("시간 차 : " + time_diff);
            
            total.put(car_number, total.getOrDefault(car_number, 0) + time_diff);
        }
        
        // 총 시간 들어있는 기록으로 주차 요금 계산하기
        HashMap<String, Integer> money = new HashMap<>();
        for (Map.Entry<String, Integer> car : total.entrySet()) {
            String car_number = car.getKey();
            int time = car.getValue();
            
            if (time <= fees[0]) { // 기본 시간 이하라면 기본 요금 청구
                money.put(car_number, fees[1]);
                System.out.println("차량 번호 : " + car_number + " 시간 : " + time + " 요금 : " + fees[1]);
            } else { // 기본 요금 + 초과한 시간에 대해 단위 시간마다 단위 요금 부과
                int plus = (int) Math.ceil((double) (time - fees[0]) / fees[2]);
                int total_money = fees[1] + plus * fees[3];
                money.put(car_number, total_money);
                System.out.println("차량 번호 : " + car_number + " 시간 : " + time + " 요금 : " + total_money);
            }
        }
        
        // 키 값 정렬
        List<String> sorted_car = new ArrayList<>(money.keySet());
        Collections.sort(sorted_car);
        
        int[] answer = new int[sorted_car.size()];
        for (int i = 0; i < sorted_car.size(); i++) {
            String car_number = sorted_car.get(i);
            int car_fee = money.getOrDefault(car_number, 0);
            answer[i] = car_fee;
        }
        return answer;
    }
}