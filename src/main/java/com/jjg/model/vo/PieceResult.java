package com.jjg.model.vo;

import com.jjg.model.Piece;

public class PieceResult {
    // 集满Piece 标志位
    private boolean collectAll;

    private Piece piece;

    public boolean isCollectAll() {
        return collectAll;
    }

    public void setCollectAll(boolean collectAll) {
        this.collectAll = collectAll;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
