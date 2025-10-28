import java.util.*;
import java.io.*;

class Main {
    static class Player {
        int level;
        String nickname;
        Player (int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }
    }

    static class Room {
        List<Player> playerList;
        int firstPlayerLevel;
        Room (List<Player> playerList, int firstPlayerLevel) {
            this.playerList = playerList;
            this.firstPlayerLevel = firstPlayerLevel;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int playerCnt = Integer.parseInt(st.nextToken());
        int roomSize = Integer.parseInt(st.nextToken());

        List<Room> roomList = new ArrayList<>();
        for (int i = 0; i < playerCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();

            boolean chk = false;
            int idx = -1;
            for (int j = 0; j < roomList.size(); j++) {
                int firstPlayerLevel = roomList.get(j).firstPlayerLevel;
                if (roomList.get(j).playerList.size() < roomSize &&
                   level >= firstPlayerLevel - 10 && level <= firstPlayerLevel + 10) {
                    chk = true;
                    idx = j;
                    break;
                }
            }

            if (chk) roomList.get(idx).playerList.add(new Player(level, nickname));
            else {
                roomList.add(new Room(new ArrayList<>(), level));
                roomList.get(roomList.size()-1).playerList.add(new Player(level, nickname));
            }
        }

        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).playerList.size() == roomSize) bw.write("Started!\n");
            else bw.write("Waiting!\n");
            Collections.sort(roomList.get(i).playerList, (o1, o2) -> o1.nickname.compareTo(o2.nickname));
            for (int j = 0; j < roomList.get(i).playerList.size(); j++) {
                bw.write(roomList.get(i).playerList.get(j).level + " " + roomList.get(i).playerList.get(j).nickname + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}