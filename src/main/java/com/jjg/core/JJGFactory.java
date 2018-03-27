package com.jjg.core;

import com.jjg.model.JJG;
import com.jjg.model.vo.PieceResult;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class JJGFactory {

    // JJG id map JJG
    Dictionary<Integer, JJGPool> jjgFactory = new Hashtable<>();

    public void init(List<JJG> jjgs){
        for(JJG jjg :jjgs){
            JJGPool jjgPool = new JJGPool();
            jjgPool.init(jjg);
            jjgFactory.put(jjg.getId(), jjgPool);
        }
    }

    /**
     * 生成JJG
     * @param pieceTypeNumber piece type 数量
     * @param pieceTotalNumber piece 总数量
     * @return
     */
    public JJG generateJJG(int pieceTypeNumber, int pieceTotalNumber){
        JJG jjg = new JJG();
        randomPiece(jjg, pieceTypeNumber, pieceTotalNumber);

        JJGPool jjgPool = new JJGPool();
        jjgPool.init(jjg);

        jjgFactory.put(jjg.getId(), jjgPool);

        return null;
    }

    /**
     * 随机为JJG产生碎片
     * @param jjg
     * @param pieceTypeNumber
     * @param pieceTotalNumber
     */
    public void randomPiece(JJG jjg, Integer pieceTypeNumber, Integer pieceTotalNumber){

    }

    public PieceResult requestPiece(Integer jjgId, Integer userId){
        JJGPool jjgPool = jjgFactory.get(jjgId);
        PieceResult pieceResult = jjgPool.getPiece(userId);

        //TODO 根据result 对jjfFactory 进行修改

        return pieceResult;
    }
}
