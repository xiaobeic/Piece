package com.jjg.service;

import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.PlayerPiece;
import com.jjg.model.vo.PageVo;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface PlayerPieceService {

    boolean save(JumboJackpotPieceState jumboJackpotPieceState, long playerId) throws Exception;

    List<Long> getRarePlayer(JumboJackpot jumboJackpot) throws Exception;

    HashMap<Long,List<String>> getPlayerPiecesByGroup(Long jumboJackpotId) throws Exception;

    PageVo<List<PlayerPiece>> getPlayerPieces(Long jumboJackpotId, String playerId, Pageable pageable) throws Exception;
}
