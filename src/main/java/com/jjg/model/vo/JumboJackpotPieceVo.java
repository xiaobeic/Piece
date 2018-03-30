package com.jjg.model.vo;

import com.jjg.model.JumboJackpotPiece;

public class JumboJackpotPieceVo {
    private boolean collectAll;

    private JumboJackpotPiece jumboJackpotPiece;

    public boolean isCollectAll() { return collectAll; }

    public void setCollectAll(boolean collectAll) { this.collectAll = collectAll; }

    public JumboJackpotPiece getJumboJackpotPiece() { return jumboJackpotPiece; }

    public void setJumboJackpotPiece(JumboJackpotPiece jumboJackpotPiece) { this.jumboJackpotPiece = jumboJackpotPiece; }
}
