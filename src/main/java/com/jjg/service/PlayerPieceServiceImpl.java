package com.jjg.service;

import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.PlayerPiece;
import com.jjg.repository.PlayerPieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerPieceServiceImpl implements PlayerPieceService {

    @Autowired
    private PlayerPieceRepository playerPieceRepository;

    @Override
    public boolean save(JumboJackpotPieceState jumboJackpotPieceState, long playerId) throws Exception {
        PlayerPiece playerPiece = new PlayerPiece();
        playerPiece.setJumboJackpotId(jumboJackpotPieceState.getJumboJackpotId());
        playerPiece.setPieceName(jumboJackpotPieceState.getPieceName());
        playerPiece.setPlayerId(playerId);
        playerPiece.setLatest(true);
        playerPiece.setCreatedDate(new Date());
        playerPieceRepository.save(playerPiece);
        return true;
    }

    @Override
    public List<Long> getRarePlayer(JumboJackpot jumboJackpot) throws Exception {
        List<String> racePieces = Arrays.asList(jumboJackpot.getRacePieces().split(","));
        List<Long> rarePlayers = new ArrayList<>();
        List<PlayerPiece> playerPieces = playerPieceRepository.findByjumboJackpotId(jumboJackpot.getJumboJackpotId());

        for (PlayerPiece playerPiece : playerPieces) {
            if (racePieces.contains(playerPiece.getPieceName()) && !rarePlayers.contains(playerPiece.getPlayerId())) {
                rarePlayers.add(playerPiece.getPlayerId());
            }
        }

        return rarePlayers;
    }

    @Override
    public HashMap<Long, List<String>> getPlayerPieces(Long jumboJackpotId) throws Exception {
        HashMap<Long, List<String>> playersPieces = new HashMap<>();
        List<PlayerPiece> playerPieces = playerPieceRepository.findByjumboJackpotId(jumboJackpotId);

        for (PlayerPiece playerPiece : playerPieces) {
            List<String> playerPiecesList;
            if (playersPieces.containsKey(playerPiece.getPlayerId())) {
                playerPiecesList = playersPieces.get(playerPiece.getPlayerId());
            } else {
                playerPiecesList = new ArrayList<>();
            }
            playerPiecesList.add(playerPiece.getPieceName());
            playersPieces.put(playerPiece.getPlayerId(), playerPiecesList);
        }

        return playersPieces;
    }
}
