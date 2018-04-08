package com.jjg.model.vo;

import com.jjg.model.JumboJackpotPieceState;

public class JumboJackpotPieceVo {
    private boolean collectAll;

    private boolean giveOutAll;

    private JumboJackpotPieceState jumboJackpotPieceState;

    public boolean isGiveOutAll() { return giveOutAll; }

    public void setGiveOutAll(boolean giveOutAll) { this.giveOutAll = giveOutAll; }

    public boolean isCollectAll() { return collectAll; }

    public void setCollectAll(boolean collectAll) { this.collectAll = collectAll; }

    public JumboJackpotPieceState getJumboJackpotPieceState() { return jumboJackpotPieceState; }

    public void setJumboJackpotPieceState(JumboJackpotPieceState jumboJackpotPieceState) { this.jumboJackpotPieceState = jumboJackpotPieceState; }
}
