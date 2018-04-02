package com.jjg.model.vo;

import com.jjg.model.JumboJackpotPieceState;

public class JumboJackpotPieceVo {
    private boolean collectAll;

    private JumboJackpotPieceState jumboJackpotPieceState;

    public boolean isCollectAll() { return collectAll; }

    public void setCollectAll(boolean collectAll) { this.collectAll = collectAll; }

    public JumboJackpotPieceState getJumboJackpotPieceState() { return jumboJackpotPieceState; }

    public void setJumboJackpotPieceState(JumboJackpotPieceState jumboJackpotPieceState) { this.jumboJackpotPieceState = jumboJackpotPieceState; }
}
