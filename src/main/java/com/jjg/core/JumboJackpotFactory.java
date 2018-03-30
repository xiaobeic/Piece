package com.jjg.core;

import com.jjg.model.JumboJackpot;
import com.jjg.model.vo.JumboJackpotPieceVo;

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

    public void init(List<JumboJackpot> jumboJackpots){
        jumboJackpotList.clear();

        for(JumboJackpot jumboJackpot :jumboJackpots){
            generateJumboJackpot(jumboJackpot);
        }
    }

    /**
     * Generate JJG
     * @param jumboJackpot
     */
    public void generateJumboJackpot(JumboJackpot jumboJackpot){
        Date now = new Date();
        if (!jumboJackpotList.containsKey(jumboJackpot.getJumboJackpotId())
                && now.before(jumboJackpot.getToDate())
                && now.after(jumboJackpot.getFormDate())) {
            JumboJackpotPool jumboJackpotPool = new JumboJackpotPool();
            jumboJackpotPool.init(jumboJackpot);
            jumboJackpot.setStatus(1);

            jumboJackpotList.put(jumboJackpot.getJumboJackpotId(), jumboJackpotPool);
        }
    }


    public JumboJackpotPieceVo requestPiece(Long jumboJackpotId,  Long playerId){
        JumboJackpotPool jumboJackpotPool = jumboJackpotList.get(jumboJackpotId);
        JumboJackpotPieceVo jumboJackpotPieceVo = jumboJackpotPool.getPiece(playerId);

        return jumboJackpotPieceVo;
    }
}
