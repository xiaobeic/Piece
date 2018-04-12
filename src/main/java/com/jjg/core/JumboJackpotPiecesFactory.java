package com.jjg.core;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.vo.JumboJackpotPieceVo;
import com.jjg.model.vo.JumboJackpotRestoreVo;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class JumboJackpotPiecesFactory {

    // JJG id map JJG
    Hashtable<Long, JumboJackpotPiecesPool> jumboJackpotList = new Hashtable<>();

    private static class LazyHolder {
        private static  final JumboJackpotPiecesFactory INSTANCE = new JumboJackpotPiecesFactory();
    }

    public static final JumboJackpotPiecesFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void jumboJackpotRestore(List<JumboJackpotRestoreVo> jumboJackpotRestoreVos){
        jumboJackpotList.clear();

        Date now = new Date();
        for(JumboJackpotRestoreVo jumboJackpotRestoreVo :jumboJackpotRestoreVos){
            JumboJackpot jumboJackpot = jumboJackpotRestoreVo.getJumboJackpot();

            if (now.before(jumboJackpot.getToDate()) && now.after(jumboJackpot.getFormDate())) {
                JumboJackpotPiecesPool jumboJackpotPiecesPool = new JumboJackpotPiecesPool();

                JumboJackpotPiecesChecker jumboJackpotPiecesChecker = new JumboJackpotPiecesChecker();
                jumboJackpotPiecesChecker.setJumboJackpot(jumboJackpot);
                jumboJackpotPiecesChecker.setRarePlayer(jumboJackpotRestoreVo.getRarePlayer());
                jumboJackpotPiecesChecker.setPlayersPieces(jumboJackpotRestoreVo.getPlayersPieces());

                jumboJackpotPiecesPool.setJumboJackpotPiecesChecker(jumboJackpotPiecesChecker);
                jumboJackpotPiecesPool.setJumboJackpot(jumboJackpot);
                jumboJackpotPiecesPool.setJumboJackpotPieces(jumboJackpotRestoreVo.getJumboJackpotPieces());

                jumboJackpotPiecesPool.generateIntervalMark();

                jumboJackpotList.put(jumboJackpot.getJumboJackpotId(), jumboJackpotPiecesPool);
            }
        }
    }

    /**
     * Generate JJG
     * @param jumboJackpot
     */
    public JumboJackpotPiecesPool generateJumboJackpot(JumboJackpot jumboJackpot){
        Date now = new Date();
        JumboJackpotPiecesPool jumboJackpotPiecesPool = null;
        if (!jumboJackpotList.containsKey(jumboJackpot.getJumboJackpotId())
                && now.before(jumboJackpot.getToDate())
                && now.after(jumboJackpot.getFormDate())) {
            jumboJackpotPiecesPool = new JumboJackpotPiecesPool(jumboJackpot);
            jumboJackpot.setStatus(JumboJackpotConstants.ACTIVE);

            jumboJackpotList.put(jumboJackpot.getJumboJackpotId(), jumboJackpotPiecesPool);
        }
        return jumboJackpotPiecesPool;
    }

    /**
     * Return a jumbo jackpot pool
     * @return jumboJackpotPool
     */
    public HashMap<String, JumboJackpotPieceState> getJumboJackpot (Long jumboJackpotId) {
        return jumboJackpotList.get(jumboJackpotId).getJumboJackpotPieces();
    }

    /**
     * Return jumbo jackpot list
     * @return jumboJackpotList
     */
    public Hashtable<Long, JumboJackpotPiecesPool> getJumboJackpotList () {
        return jumboJackpotList;
    }


    /**
     * Remove a jumbo jackpot
     * @param jumboJackpotId
     * @return
     */
    public boolean isInOperation (Long jumboJackpotId) {
        if (jumboJackpotList.containsKey(jumboJackpotId)){
            return true;
        }
        return false;
    }

    /**
     * return a piece
     * @param jumboJackpotId
     * @param playerId
     * @return jumboJackpotPieceVo
     */
    public JumboJackpotPieceVo requestPiece(Long jumboJackpotId,  Long playerId){
        JumboJackpotPiecesPool jumboJackpotPiecesPool = jumboJackpotList.get(jumboJackpotId);
        if (jumboJackpotPiecesPool == null) {
            return null;
        }

        JumboJackpotPieceVo jumboJackpotPieceVo = jumboJackpotPiecesPool.getPiece(playerId);

        if (jumboJackpotPieceVo.isCollectAll() || jumboJackpotPieceVo.isGiveOutAll()) {
            jumboJackpotList.remove(jumboJackpotId);
        }

        return jumboJackpotPieceVo;
    }
}
