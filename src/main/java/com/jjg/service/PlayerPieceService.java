package com.jjg.service;

import com.jjg.model.JumboJackpotPieceState;

public interface PlayerPieceService {

    boolean save(JumboJackpotPieceState jumboJackpotPieceState, long playerId);
}
