package com.company;

import java.util.List;
import java.util.Map;

public class Game {
    private static int turn;
    private List<Player> players;
    private List<Square> squares;
    private Map<Square, Player> positions;
    private int playingCode;

    public Game() {
    }

    public Game(List<Player> players, List<Square> squares, Map<Square, Player> positions, int playingCode) {
        this.turn = 1;
        this.players = players;
        this.squares = squares;
        this.positions = positions;
        this.playingCode = playingCode;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Map<Square, Player> getPositions() {
        return positions;
    }

    public void setPositions(Map<Square, Player> positions) {
        this.positions = positions;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }

    public int getPlayingCode() {
        return playingCode;
    }

    public void setPlayingCode(int playingCode) {
        this.playingCode = playingCode;
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", squares=" + squares +
                ", positions=" + positions +
                ", playingCode=" + playingCode +
                '}';
    }
}
