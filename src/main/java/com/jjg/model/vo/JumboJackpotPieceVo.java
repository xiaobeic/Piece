package com.jjg.model.vo;

import com.jjg.model.JumboJackpotPieceState;

import java.util.Date;

public class JumboJackpotPieceVo {
    private boolean collectAll;

    private boolean giveOutAll;

    private long playerId;

    private Date createDate;

    private long processTime;

    private JumboJackpotPieceState jumboJackpotPieceState;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }

    public boolean isGiveOutAll() { return giveOutAll; }

    public void setGiveOutAll(boolean giveOutAll) { this.giveOutAll = giveOutAll; }

    public boolean isCollectAll() { return collectAll; }

    public void setCollectAll(boolean collectAll) { this.collectAll = collectAll; }

    public JumboJackpotPieceState getJumboJackpotPieceState() { return jumboJackpotPieceState; }

    public void setJumboJackpotPieceState(JumboJackpotPieceState jumboJackpotPieceState) { this.jumboJackpotPieceState = jumboJackpotPieceState; }
}
