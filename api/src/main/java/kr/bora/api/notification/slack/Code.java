package kr.bora.api.notification.slack;

import java.util.*;

public class Code {
    public static void main(String[] args) {
        String[] player = {"a", "b", "c", "d"};
        Random random = new Random();
        List<String> isCoupled = new ArrayList<>();
        Map<Integer, List<String>> roundInfo = new HashMap<>();

        Map<Integer, Map<Integer, List<String>>> round = new HashMap<>();

        int stack = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < player.length; i++) {
                isCoupled.add(player[i]);

                isCoupled.add(player[random.nextInt(4)]);
                if (player[i].equals(isCoupled.get(1))) {
                    stack++;
                }
                roundInfo.put(i, isCoupled);

                if (roundInfo.size() == 4) {
                    round.put(j, roundInfo);
                }
                isCoupled.clear();

            }
//            roundInfo.clear();
        }

        System.out.println();
        System.out.println(roundInfo);
//        for (int t = 1; t < 3; t++) {
//            Map<Integer, List<String>> integerListMap = round.get(t);
//            if()
//        }
    }
}
