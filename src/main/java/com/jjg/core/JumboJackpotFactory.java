package com.jjg.core;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.model.JumboJackpot;
import com.jjg.model.vo.JumboJackpotPieceVo;

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

    public List<Long> init(List<JumboJackpot> jumboJackpots){
        List<Long> initStatus = new ArrayList<>();
        jumboJackpotList.clear();

        for(JumboJackpot jumboJackpot :jumboJackpots){

            //........

            initStatus.add(jumboJackpot.getJumboJackpotId());
        }

        return initStatus;
    }

    /**
     * Generate JJG
     * @param jumboJackpot
     */
    public JumboJackpotPool generateJumboJackpot(JumboJackpot jumboJackpot){
        Date now = new Date();
        JumboJackpotPool jumboJackpotPool = new JumboJackpotPool();
        if (!jumboJackpotList.containsKey(jumboJackpot.getJumboJackpotId())
                && now.before(jumboJackpot.getToDate())
                && now.after(jumboJackpot.getFormDate())) {
            jumboJackpotPool.init(jumboJackpot);
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

        return jumboJackpotPieceVo;
    }
}
