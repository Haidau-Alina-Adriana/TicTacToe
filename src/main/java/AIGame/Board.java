package AIGame;

import javafx.scene.control.Button;

import java.util.*;

public class Board {
    private Map<Button, Player> grid;
    private List<Player> players;

    public Board() {
        this.grid = new LinkedHashMap<>();
        this.players = new ArrayList<>();
    }

    public Board(Map<Button, Player> grid) {
        this.grid = new LinkedHashMap<>();
        this.grid = grid;
    }

    public Board(Map<Button, Player> grid, List<Player> players) {
        this.grid = new LinkedHashMap<>();
        this.players = new ArrayList<>();
        this.grid = grid;
        this.players = players;
    }

    public Map<Button, Player> getGrid() {
        return grid;
    }

    public void setGrid(Map<Button, Player> grid) {
        this.grid = grid;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPiece(Button button) {
       this.grid.put(button, null);
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    @Override
    public String toString() {
        return "Board{" +
                "grid=" + grid +
                ", players=" + players +
                '}';
    }
}
