package com.jjg.model.vo;

import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;

import java.util.HashMap;
import java.util.List;

public class JumboJackpotRestoreVo {
    private JumboJackpot jumboJackpot;

    private HashMap<String, JumboJackpotPieceState> jumboJackpotPieces;

    private List<Long> rarePlayer;

    private HashMap<Long, List<String>> playersPieces;

    public JumboJackpot getJumboJackpot() {
        return jumboJackpot;
    }

    public void setJumboJackpot(JumboJackpot jumboJackpot) {
        this.jumboJackpot = jumboJackpot;
    }

    public HashMap<String, JumboJackpotPieceState> getJumboJackpotPieces() {
        return jumboJackpotPieces;
    }

    public void setJumboJackpotPieces(HashMap<String, JumboJackpotPieceState> jumboJackpotPieces) {
        this.jumboJackpotPieces = jumboJackpotPieces;
    }

    public List<Long> getRarePlayer() {
        return rarePlayer;
    }

    public void setRarePlayer(List<Long> rarePlayer) {
        this.rarePlayer = rarePlayer;
    }

    public HashMap<Long, List<String>> getPlayersPieces() {
        return playersPieces;
    }

    public void setPlayersPieces(HashMap<Long, List<String>> playersPieces) {
        this.playersPieces = playersPieces;
    }
}
