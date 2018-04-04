package com.jjg.service;

import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;

import java.util.HashMap;
import java.util.List;

public interface PlayerPieceService {

    boolean save(JumboJackpotPieceState jumboJackpotPieceState, long playerId);

    List<Long> getRarePlayer(JumboJackpot jumboJackpot);

    HashMap<Long,List<String>> getPlayerPieces(Long jumboJackpotId);
}
