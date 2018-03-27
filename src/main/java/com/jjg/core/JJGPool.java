package com.jjg.core;


import com.jjg.model.JJG;
import com.jjg.model.JJGToPiece;
import com.jjg.model.Piece;
import com.jjg.model.User;
import com.jjg.model.vo.PieceResult;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class JJGPool {
    private JJG jjg;

    // 用户 所拥有的Piece
    // Integer 用户Id
    // List piece 列表
    private Dictionary<Integer, List> userDict = new Hashtable<>();

    //有稀有卡片的用户
    private List<Integer> rareUsers;

    // 池中 piece 信息
    private List<JJGToPiece> jjgToPieces;


    // 池的状态
    // 0 初始化
    // 1 活动
    // 2 结束
    private int status;

    private int[] lock = new int[0];


    // 初始化
    public void init(JJG jjg){
        this.jjg = jjg;
    }

    // 如果结束则返回null
    public PieceResult getPiece(Integer userId){
        PieceResult pieceResult = new PieceResult();
        pieceResult.setCollectAll(false);
        Piece piece = randomPiece();

        // 如果卡片是稀有卡片 把此用户加入持稀有卡片用户列表
        if(checkPieceRace(piece)){
            rareUsers.add(userId);
        }

        if(checkUserDangerous(userId)){
            synchronized(lock){
                if(checkUserCollect(userId)){
                    pieceResult.setCollectAll(true);
                    status =2;
                }
            }
        }
        pieceResult.setPiece(piece);

        if(status == 2){
            pieceResult = null;
        }
        return pieceResult;
    }

    // 根据Piece 生成概率大小 和池中Piece信息，随机产生一个Piece
    private Piece randomPiece(){

        return null;
    }

    // 判断piece 是否是稀有卡片
    private boolean checkPieceRace(Piece piece){

        return false;
    }

    // 判断用户是否持有稀有卡片
    private boolean checkUserDangerous(Integer userId){

        return false;
    }

    // 判断用户是否集满卡片
    private boolean checkUserCollect(Integer userId){
        return  false;
    }
}
