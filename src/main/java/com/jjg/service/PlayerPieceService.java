package com.jjg.service;

import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;

import java.util.HashMap;
import java.util.List;

public interface PlayerPieceService {

    boolean save(JumboJackpotPieceState jumboJackpotPieceState, long playerId) throws Exception;

    List<Long> getRarePlayer(JumboJackpot jumboJackpot) throws Exception;

    HashMap<Long,List<String>> getPlayerPieces(Long jumboJackpotId) throws Exception;
}
