package utils;

import com.company.user.Player;
import javafx.scene.control.Button;

import java.util.LinkedHashMap;
import java.util.Map;

public class CheckVictory {

    public static Player checkWin(Map<Button, Player> board) {
        Map<Button, Player> win = new LinkedHashMap<>();
        for (int i = 0; i < AIGameUtils.getNumberOfRows() * AIGameUtils.getNumberOfRows(); i++) {
            int column = i % AIGameUtils.getNumberOfRows();
            int line = i / AIGameUtils.getNumberOfRows();

            win = checkMatch(line, column, 1, 1, i, board); //diag principala

            if (win == null) {
                win = checkMatch(line, column, 1, -1, i, board); //diag secundara
            }
            if (win == null) {
                win = checkMatch(line, column, 1, 0, i, board); // orizontal
            }

            if (win == null) {
                win = checkMatch(line, column, 0, 1, i, board); //vertical
            }
            if (win != null) {
                return win.entrySet().iterator().next().getValue();
            }
        }
        return null;
    }

    public static Map<Button, Player> checkMatch(int line, int column, int xDirection,
                                                 int yDirection, int index, Map<Button, Player> board) {
        Map<Button, Player> match = new LinkedHashMap<>();
        int type = -1;
        int count = 0;
        while (count < AIGameUtils.getNumberOfRows() &&
                index < AIGameUtils.getNumberOfRows() * AIGameUtils.getNumberOfRows() &&
                line >= 0 && line <= AIGameUtils.getNumberOfRows() - 1 && column >= 0 && column <= AIGameUtils.getNumberOfRows() - 1) {
            boolean found = false;
            Button button = null;
            Player player = null;
            int i = 0;
            for (Map.Entry<Button, Player> iterator : board.entrySet()) {
                if (i == line * AIGameUtils.getNumberOfRows() + column) {
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
            line += xDirection;
            column += yDirection;
            index++;
            count++;
        }
        if (match.size() >= 3) {
            return match;
        } else {
            return null;
        }
    }
}