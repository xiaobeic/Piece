package com.jjg.core;


import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.vo.JumboJackpotPieceVo;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JumboJackpotPool {
    private JumboJackpot jumboJackpot;

    private JumboJackpotChecker jumboJackpotChecker = null;

    private Lock lock = new ReentrantLock();

    // The piece information in the pool
    private HashMap<String, JumboJackpotPieceState> jumboJackpotPieces = new HashMap<>();

    // init
    public void init(JumboJackpot jumboJackpot){
        this.jumboJackpot = jumboJackpot;
        jumboJackpotChecker = new JumboJackpotChecker(jumboJackpot);
        jumboJackpot.setStatus(0);
        generateJumboJackpotPieces();
    }

    /**
     * Randomly generate JJG fragments.
     */
    public void generateJumboJackpotPieces(){
        int pieceType = jumboJackpot.getPieceType();
        int raceRatio = jumboJackpot.getRaceRatio();
        int totalPieces = jumboJackpot.getTotalPieces();
        List<String> racePieces = Arrays.asList(jumboJackpot.getRacePieces().split(","));

        int totalRacePieces = totalPieces * raceRatio / 1000;
        int perRacePiecesNum = totalRacePieces / racePieces.size();
        int perOrdinariesPiecesNum = (totalPieces - totalRacePieces) / (pieceType - racePieces.size());

        for (int i = 1; i <= pieceType; i++) {
            if (racePieces.contains(String.valueOf(i))) {
                createJumboJackpotPiece(String.valueOf(i), perRacePiecesNum);
            } else {
                createJumboJackpotPiece(String.valueOf(i), perOrdinariesPiecesNum);
            }
        }
    }

    private void createJumboJackpotPiece (String piecesName, int pieceNumber) {
        JumboJackpotPieceState jumboJackpotPieceState = new JumboJackpotPieceState();
        jumboJackpotPieceState.setJumboJackpotId(jumboJackpot.getJumboJackpotId());
        jumboJackpotPieceState.setPieceName(piecesName);
        jumboJackpotPieceState.setPieceNumber(pieceNumber);
        jumboJackpotPieceState.setCreatedDate(new Date());
        jumboJackpotPieces.put(piecesName, jumboJackpotPieceState);
    }

    /**
     * According to the information in the piece pool get a piece at random.
     * @param playerId
     * @return jumboJackpotPiece
     */
    public JumboJackpotPieceVo getPiece(Long playerId){
        String targetPiece = "0";

        lock.lock();
        try {
            System.out.println(Thread.currentThread() + "gets the piece lock");
            List<String> piecesNumber = new ArrayList<>();

            // Gets the remaining piece type in the pool.
            Iterator iterator = jumboJackpotPieces.keySet().iterator();
            while (iterator.hasNext()) {
                JumboJackpotPieceState jumboJackpotPieceState = jumboJackpotPieces.get(iterator.next());
                if (jumboJackpotPieceState.getPieceNumber() > 0) {
                    piecesNumber.add(jumboJackpotPieceState.getPieceName());
                }
            }

            if (piecesNumber.size() == 0) {
                return null;
            }

            int randomNumber = (int)(Math.random() * piecesNumber.size());

            targetPiece = piecesNumber.get(randomNumber);

            // The corresponding piece minus one.
            JumboJackpotPieceState jumboJackpotPiecePool = jumboJackpotPieces.get(targetPiece);
            jumboJackpotPiecePool.setPieceNumber(jumboJackpotPiecePool.getPieceNumber() - 1);
            jumboJackpotPieces.put(targetPiece, jumboJackpotPiecePool);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(Thread.currentThread() + "releases the piece lock");
            lock.unlock();
        }

        // Determine jumbo jackpot status.
        JumboJackpotPieceVo jumboJackpotPieceVo = new JumboJackpotPieceVo();

        if (!targetPiece.equals("0")) {
            JumboJackpotPieceState jumboJackpotPieceState = new JumboJackpotPieceState();
            jumboJackpotPieceState.setPieceNumber(1);
            jumboJackpotPieceState.setJumboJackpotId(jumboJackpot.getJumboJackpotId());
            jumboJackpotPieceState.setPieceName(targetPiece);

            boolean result = jumboJackpotChecker.jumboJackpotHandler(jumboJackpotPieceState, playerId);

            jumboJackpotPieceVo.setCollectAll(result);
            jumboJackpotPieceVo.setJumboJackpotPieceState(jumboJackpotPieceState);
        }

        return jumboJackpotPieceVo;
    }
}
