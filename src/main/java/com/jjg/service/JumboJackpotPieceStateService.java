package com.jjg.service;

import com.jjg.model.JumboJackpotPieceState;

import java.util.HashMap;

public interface JumboJackpotPieceStateService {
    void saveJumboJackpotPieceState(HashMap<String, JumboJackpotPieceState> jumboJackpotPieces) throws Exception;

    boolean updatePieceState(JumboJackpotPieceState jumboJackpotPieceState) throws Exception;

    HashMap<String,JumboJackpotPieceState> getJumboJackpotPieceState(Long jumboJackpotId) throws Exception;
}
