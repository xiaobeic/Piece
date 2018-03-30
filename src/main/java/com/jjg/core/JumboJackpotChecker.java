package com.jjg.core;

import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPiece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JumboJackpotChecker {

    // User list with has rare piece
    private List<Long> rarePlayer = new ArrayList<>();

    // Lock
    private Lock lock = new ReentrantLock();

    private HashMap<Long, List<String>> playersPieces = new HashMap<>();

    private JumboJackpot jumboJackpot;

    public JumboJackpotChecker (JumboJackpot jumboJackpot) {
        this.jumboJackpot = jumboJackpot;
    }

    // Get a piece at random.
    public boolean jumboJackpotHandler(JumboJackpotPiece jumboJackpotPiece, Long playerId){
        savePlayerPiece(jumboJackpotPiece, playerId);

        // If is rare piece add current user to rareUsers
        if(isRacePiece(jumboJackpotPiece) && !rarePlayer.contains(playerId)){
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
    private void savePlayerPiece(JumboJackpotPiece jumboJackpotPiece, Long playerId) {
        List<String> playerPieces;
        if (playersPieces.containsKey(playerId)) {
            playerPieces = playersPieces.get(playerId);
        } else {
            playerPieces = new ArrayList<>();
        }
        playerPieces.add(jumboJackpotPiece.getPieceName());
        playersPieces.put(playerId, playerPieces);
    }

    // Determine whether is a rare piece.
    private boolean isRacePiece(JumboJackpotPiece jumboJackpotPiece){
        List<String> racePieces = Arrays.asList(jumboJackpot.getRacePieces().split(","));
        if (racePieces.contains(jumboJackpotPiece.getPieceName())) {
            return true;
        }
        return false;
    }

    private void checkPlayerPieces(Thread thread, Long playerId) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"gets the check lock");

            if(jumboJackpot.getStatus() == 1 && isCollected(playerId)){
                jumboJackpot.setStatus(2);
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
