package AIGame;

import javafx.scene.control.Button;
import utils.AIGameUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CheckVictory {
    public static Player checkWin(Map<Button, Player> board) {
        Map<Button, Player> match = new LinkedHashMap<>();
        for (int i = 0; i < AIGameUtils.getNumberOfRows() * AIGameUtils.getNumberOfRows(); i++) {
            int x = i % AIGameUtils.getNumberOfRows();
            int y = i / AIGameUtils.getNumberOfRows();

            match = checkMatch(x, y, 1, 1, i, board); //diag principala

            if (match == null) {
                match = checkMatch(x, y, 1, -1, i, board); //diag secundara
            }
            if (match == null) {
                match = checkMatch(x, y, 1, 0, i, board); // orizontal
            }

            if (match == null) {
                match = checkMatch(x, y, 0, 1, i, board); //vertical
            }
            if (match != null) {
                return match.entrySet().iterator().next().getValue();
            }
        }
        return null;
    }

    private static Map<Button, Player> checkMatch(int x, int y, int xDirection,
                                                  int yDirection, int index, Map<Button, Player> board) {
        Map<Button, Player> match = new LinkedHashMap<>();
        int type = -1;
        int count = 0;
        while (count < AIGameUtils.getNumberOfRows() &&
                index < AIGameUtils.getNumberOfRows() * AIGameUtils.getNumberOfRows() &&
                x >= 0 && x <= AIGameUtils.getNumberOfRows() - 1 && y >= 0 && y <= AIGameUtils.getNumberOfRows() - 1) {
            boolean found = false;
            Button button = null;
            Player player = null;
            int i = 0;
            for (Map.Entry<Button, Player> iterator : board.entrySet()) {
                if (i == x * AIGameUtils.getNumberOfRows() + y) {
                    button = iterator.getKey();
                    player = iterator.getValue();
                    break;
                }
                i++;
            }
            if (player != null) {
                if (type == -1) {
                    type = player.getIndex();
                }
                if (player.getIndex() == type) {
                    match.put(button, player);
                    found = true;
                }
            }
            if (!found && match.size() < 3) {
                match.clear();
                type = -1;
            }
            x += xDirection;
            y += yDirection;
            index++;
            count++;
        }
        return match.size() >= 3 ? match : null;
    }
}
