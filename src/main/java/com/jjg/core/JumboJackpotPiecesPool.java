package com.jjg.core;


import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.vo.JumboJackpotPieceVo;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JumboJackpotPiecesPool {
    private JumboJackpot jumboJackpot;

    private JumboJackpotPiecesChecker jumboJackpotPiecesChecker = null;

    private Lock lock = new ReentrantLock();

    // The piece information in the pool
    private HashMap<String, JumboJackpotPieceState> jumboJackpotPieces = new HashMap<>();

    private List<Integer> piecesIntervalMark = new ArrayList<>();
    private List<Integer> piecesMap = new ArrayList<>();
    private int remainPieces = 0;

    public void setJumboJackpot(JumboJackpot jumboJackpot) {
        this.jumboJackpot = jumboJackpot;
    }

    public HashMap<String, JumboJackpotPieceState> getJumboJackpotPieces() {
        return jumboJackpotPieces;
    }

    public void setJumboJackpotPieces(HashMap<String, JumboJackpotPieceState> jumboJackpotPieces) {
        this.jumboJackpotPieces = jumboJackpotPieces;
    }

    public void setJumboJackpotPiecesChecker(JumboJackpotPiecesChecker jumboJackpotPiecesChecker) {
        this.jumboJackpotPiecesChecker = jumboJackpotPiecesChecker;
    }

    public JumboJackpotPiecesPool() {}

    // create jumbo jackpot pool
    public JumboJackpotPiecesPool(JumboJackpot jumboJackpot){
        this.jumboJackpot = jumboJackpot;
        jumboJackpotPiecesChecker = new JumboJackpotPiecesChecker(jumboJackpot);
        generateJumboJackpotPieces();
    }

    /**
     * Randomly generate JJG fragments.
     */
    private void generateJumboJackpotPieces(){
        //int raceRatio = jumboJackpot.getRaceRatio();
        List<String> racePieces = Arrays.asList(jumboJackpot.getRacePieces().split(","));
        int pieceType = jumboJackpot.getPieceType();
        int totalPieces = jumboJackpot.getTotalPieces();
        int totalRacePieces = racePieces.size();
        int preciousPiecesSize = Math.round((pieceType - totalRacePieces) * 0.25f);
        int ordinariesPiecesSize = pieceType - totalRacePieces - preciousPiecesSize;

        List<String> preciousPieces = getPreciousPieces(preciousPiecesSize, pieceType, racePieces);

        int perPreciousPiecesNum = Math.round(totalPieces * 0.05f) / preciousPiecesSize;
        int totalPreciousPieces = perPreciousPiecesNum * preciousPiecesSize;
        int perOrdinariesPiecesNum = (totalPieces - totalRacePieces - totalPreciousPieces) / ordinariesPiecesSize;
        int totalOrdinariesPieces = perOrdinariesPiecesNum * ordinariesPiecesSize;

        int deviationPiece = totalPieces - totalRacePieces - totalPreciousPieces - totalOrdinariesPieces;
        int deviationRatio = deviationPiece > ordinariesPiecesSize ? 2 : 1;

        for (int i = 1; i <= pieceType; i++) {
            if (racePieces.contains(String.valueOf(i))) {
                createJumboJackpotPiece(String.valueOf(i), 1);
            } else if (preciousPieces.contains(String.valueOf(i))) {
                createJumboJackpotPiece(String.valueOf(i), perPreciousPiecesNum);
            } else {
                if (deviationPiece > 0) {
                    deviationPiece -= deviationRatio;
                    if (deviationPiece < 0) {
                        deviationRatio = 1;
                    }
                    createJumboJackpotPiece(String.valueOf(i), perOrdinariesPiecesNum + deviationRatio);
                } else {
                    createJumboJackpotPiece(String.valueOf(i), perOrdinariesPiecesNum);
                }
            }
        }

        generateIntervalMark();
    }

    private List<String> getPreciousPieces(int totalPreciousPieces, int pieceType, List<String> racePiece) {
        List<String> preciousPieces = new ArrayList<>();
        do {
            String pieceNumber = String.valueOf(1 + (int)(Math.random() * pieceType));
            if (!preciousPieces.contains(pieceNumber) && !racePiece.contains(pieceNumber)) {
                preciousPieces.add(pieceNumber);
            }
        } while (preciousPieces.size() != totalPreciousPieces);

        return  preciousPieces;
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
     * Generate the piece interval.
     */
    public void  generateIntervalMark () {
        remainPieces = 0;
        piecesIntervalMark.clear();
        piecesMap.clear();
        Iterator iterator = jumboJackpotPieces.keySet().iterator();
        while (iterator.hasNext()) {
            JumboJackpotPieceState jumboJackpotPieceState = jumboJackpotPieces.get(iterator.next());
            if (jumboJackpotPieceState.getPieceNumber() > 0) {
                remainPieces += jumboJackpotPieceState.getPieceNumber();
                piecesIntervalMark.add(remainPieces);
                piecesMap.add(Integer.valueOf(jumboJackpotPieceState.getPieceName()));
            }
        }
    }

    /**
     * According to the information in the piece pool get a piece at random.
     * @param playerId
     * @return jumboJackpotPiece
     */
    public JumboJackpotPieceVo getPiece(Long playerId){
        String targetPiece = "";
        JumboJackpotPieceVo jumboJackpotPieceVo = new JumboJackpotPieceVo();
        if (remainPieces == 0 || jumboJackpot.getStatus() != 1) {
            return null;
        }

        lock.lock();
        try {
            System.out.println(Thread.currentThread() + "gets the piece lock");

            int randomNumber = 1 + (int)(Math.random() * remainPieces);

            int pieceIndex = 0;
            for (int i = 0; i < piecesIntervalMark.size(); i++) {
                if (randomNumber <= piecesIntervalMark.get(i)) {
                    pieceIndex = i;
                    break;
                }
            }
            targetPiece = String.valueOf(piecesMap.get(pieceIndex));

            if (targetPiece.equals("")) {
                return null;
            }

            // The corresponding piece minus one.
            JumboJackpotPieceState jumboJackpotPieceState = jumboJackpotPieces.get(targetPiece);
            int currentPieceNumber = jumboJackpotPieceState.getPieceNumber();
            jumboJackpotPieceState.setPieceNumber(currentPieceNumber - 1);
            jumboJackpotPieces.put(targetPiece, jumboJackpotPieceState);

            if (currentPieceNumber == 1) {
                generateIntervalMark();
            }

            if (remainPieces == 0) {
                jumboJackpotPieceVo.setGiveOutAll(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(Thread.currentThread() + "releases the piece lock");
            lock.unlock();
        }

        // Determine jumbo jackpot status.
        JumboJackpotPieceState jumboJackpotPieceState = new JumboJackpotPieceState();
        jumboJackpotPieceState.setPieceNumber(1);
        jumboJackpotPieceState.setJumboJackpotId(jumboJackpot.getJumboJackpotId());
        jumboJackpotPieceState.setPieceName(targetPiece);

        boolean result = jumboJackpotPiecesChecker.jumboJackpotHandler(jumboJackpotPieceState, playerId);

        jumboJackpotPieceVo.setCreateDate(new Date());
        jumboJackpotPieceVo.setCollectAll(result);
        jumboJackpotPieceVo.setJumboJackpotPieceState(jumboJackpotPieceState);

        return jumboJackpotPieceVo;
    }
}
