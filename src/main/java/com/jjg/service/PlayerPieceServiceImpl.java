package com.jjg.service;

import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.PlayerPiece;
import com.jjg.repository.PlayerPieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlayerPieceServiceImpl implements PlayerPieceService {

    @Autowired
    private PlayerPieceRepository playerPieceRepository;

    @Override
    public boolean save(JumboJackpotPieceState jumboJackpotPieceState, long playerId) {
        PlayerPiece playerPiece = new PlayerPiece();
        playerPiece.setJumboJackpotId(jumboJackpotPieceState.getJumboJackpotId());
        playerPiece.setPieceName(jumboJackpotPieceState.getPieceName());
        playerPiece.setPlayerId(playerId);
        playerPiece.setLatest(true);
        playerPiece.setCreatedDate(new Date());
        playerPieceRepository.save(playerPiece);
        return true;
    }
}
