package com.jjg.core;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JumboJackpotChecker {

    // User list with has rare piece
    private List<Long> rarePlayer;

    private HashMap<Long, List<String>> playersPieces;

    // Lock
    private Lock lock = new ReentrantLock();

    private JumboJackpot jumboJackpot;

    public void setJumboJackpot(JumboJackpot jumboJackpot) {
        this.jumboJackpot = jumboJackpot;
    }

    public void setRarePlayer(List<Long> rarePlayer) {
        this.rarePlayer = rarePlayer;
    }

    public void setPlayersPieces(HashMap<Long, List<String>> playersPieces) {
        this.playersPieces = playersPieces;
    }

    public JumboJackpotChecker() {}

    public JumboJackpotChecker (JumboJackpot jumboJackpot) {
        this.jumboJackpot = jumboJackpot;
        rarePlayer = new ArrayList<>();
        playersPieces = new HashMap<>();
    }

    // Get a piece at random.
    public boolean jumboJackpotHandler(JumboJackpotPieceState jumboJackpotPieceState, Long playerId){
        savePlayerPiece(jumboJackpotPieceState, playerId);

        // If is rare piece add current user to rareUsers
        if(isRacePiece(jumboJackpotPieceState) && !rarePlayer.contains(playerId)){
            rarePlayer.add(playerId);
        }

        if(rarePlayer.contains(playerId)){
            checkPlayerPieces(Thread.currentThread(), playerId);
        }

        if(jumboJackpot.getStatus() == 2){
            return true;
        }
        return false;
    }

    // Save player piece in pool
    private void savePlayerPiece(JumboJackpotPieceState jumboJackpotPieceState, Long playerId) {
        List<String> playerPieces;
        if (playersPieces.containsKey(playerId)) {
            playerPieces = playersPieces.get(playerId);
        } else {
            playerPieces = new ArrayList<>();
        }
        playerPieces.add(jumboJackpotPieceState.getPieceName());
        playersPieces.put(playerId, playerPieces);
    }

    // Determine whether is a rare piece.
    private boolean isRacePiece(JumboJackpotPieceState jumboJackpotPieceState){
        List<String> racePieces = Arrays.asList(jumboJackpot.getRacePieces().split(","));
        if (racePieces.contains(jumboJackpotPieceState.getPieceName())) {
            return true;
        }
        return false;
    }

    private void checkPlayerPieces(Thread thread, Long playerId) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"gets the check lock");

            if(jumboJackpot.getStatus() == 1 && isCollected(playerId)){
                jumboJackpot.setStatus(JumboJackpotConstants.INACTIVE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println(thread.getName()+"releases the check lock");
            lock.unlock();
        }
    }

    // Determine whether the user is full of piece.
    private boolean isCollected(Long playerId){
        List<String> playerPieces = playersPieces.get(playerId);
        if (playerPieces == null || playerPieces.size() == 0) {
            return false;
        }

        int pieceType = jumboJackpot.getPieceType();
        for (int i = 1; i <= pieceType; i++) {
            if (!playerPieces.contains(String.valueOf(i))) {
                return false;
            }
        }
        return true;
    }
}
