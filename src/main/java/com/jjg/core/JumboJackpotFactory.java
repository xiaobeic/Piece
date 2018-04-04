package com.jjg.core;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.model.JumboJackpot;
import com.jjg.model.vo.JumboJackpotPieceVo;
import com.jjg.model.vo.JumboJackpotRestoreVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class JumboJackpotFactory {

    // JJG id map JJG
    Hashtable<Long, JumboJackpotPool> jumboJackpotList = new Hashtable<>();

    private static class LazyHolder {
        private static  final JumboJackpotFactory INSTANCE = new JumboJackpotFactory();
    }

    public static final JumboJackpotFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void jumboJackpotRestore(List<JumboJackpotRestoreVo> jumboJackpotRestoreVos){
        jumboJackpotList.clear();

        Date now = new Date();
        for(JumboJackpotRestoreVo jumboJackpotRestoreVo :jumboJackpotRestoreVos){
            JumboJackpot jumboJackpot = jumboJackpotRestoreVo.getJumboJackpot();

            if (now.before(jumboJackpot.getToDate()) && now.after(jumboJackpot.getFormDate())) {
                JumboJackpotPool jumboJackpotPool = new JumboJackpotPool();

                JumboJackpotChecker jumboJackpotChecker = new JumboJackpotChecker();
                jumboJackpotChecker.setJumboJackpot(jumboJackpot);
                jumboJackpotChecker.setRarePlayer(jumboJackpotRestoreVo.getRarePlayer());
                jumboJackpotChecker.setPlayersPieces(jumboJackpotRestoreVo.getPlayersPieces());

                jumboJackpotPool.setJumboJackpotChecker(jumboJackpotChecker);
                jumboJackpotPool.setJumboJackpot(jumboJackpot);
                jumboJackpotPool.setJumboJackpotPieces(jumboJackpotRestoreVo.getJumboJackpotPieces());

                jumboJackpotPool.generateIntervalMark();

                jumboJackpotList.put(jumboJackpot.getJumboJackpotId(), jumboJackpotPool);
            }
        }
    }

    /**
     * Generate JJG
     * @param jumboJackpot
     */
    public JumboJackpotPool generateJumboJackpot(JumboJackpot jumboJackpot){
        Date now = new Date();
        JumboJackpotPool jumboJackpotPool = new JumboJackpotPool(jumboJackpot);
        if (!jumboJackpotList.containsKey(jumboJackpot.getJumboJackpotId())
                && now.before(jumboJackpot.getToDate())
                && now.after(jumboJackpot.getFormDate())) {
            jumboJackpot.setStatus(JumboJackpotConstants.ACTIVE);

            jumboJackpotList.put(jumboJackpot.getJumboJackpotId(), jumboJackpotPool);
        }
        return jumboJackpotPool;
    }

    /**
     * Return a jumbo jackpot pool
     * @return jumboJackpotPool
     */
    public JumboJackpotPool getJumboJackpot (Long jumboJackpotId) {
        return jumboJackpotList.get(jumboJackpotId);
    }

    /**
     * Return jumbo jackpot list
     * @return jumboJackpotList
     */
    public Hashtable<Long, JumboJackpotPool> getJumboJackpotList () {
        return jumboJackpotList;
    }


    /**
     * Remove a jumbo jackpot
     * @param jumboJackpotId
     * @return
     */
    public boolean removeJumboJackpot (Long jumboJackpotId) {
        if (jumboJackpotList.containsKey(jumboJackpotId)){
            jumboJackpotList.remove(jumboJackpotId);
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
        JumboJackpotPool jumboJackpotPool = jumboJackpotList.get(jumboJackpotId);
        if (jumboJackpotPool == null) {
            return null;
        }

        JumboJackpotPieceVo jumboJackpotPieceVo = jumboJackpotPool.getPiece(playerId);

        if (jumboJackpotPieceVo.isCollectAll()) {
            removeJumboJackpot(jumboJackpotId);
        }

        return jumboJackpotPieceVo;
    }
}
