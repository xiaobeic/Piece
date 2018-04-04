package com.jjg.service;

import com.jjg.model.JumboJackpotPieceState;

import java.util.HashMap;

public interface JumboJackpotPieceStateService {
    void saveJumboJackpotPieceState(HashMap<String, JumboJackpotPieceState> jumboJackpotPieces);

    boolean updatePieceState(JumboJackpotPieceState jumboJackpotPieceState);

    HashMap<String,JumboJackpotPieceState> getJumboJackpotPieceState(Long jumboJackpotId);
}
