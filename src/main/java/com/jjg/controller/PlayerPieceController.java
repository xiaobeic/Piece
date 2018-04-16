package com.jjg.controller;

import com.jjg.model.PlayerPiece;
import com.jjg.model.vo.PageVo;
import com.jjg.service.PlayerPieceService;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "The Player Piece Controller", name = "Player Piece API")
@RequestMapping("/admin/playerPiece")
public class PlayerPieceController {

    @Autowired
    private PlayerPieceService playerPieceServiceImpl;

    @RequestMapping(value = "/getPlayerPieces", method = RequestMethod.POST)
    @ApiMethod(description = "get player piece")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "page", description = "Option! Page you want to retrieve, 0 indexed and defaults to 0.", required = false),
            @ApiQueryParam(name = "size", description = "Option! Size of the page you want to retrieve, defaults to 20.", required = false),
            @ApiQueryParam(name = "sort", description = "Option! Properties that should be sorted by in the format property,property(,ASC|DESC)", required = false)})
    public @ApiResponseObject PageVo<List<PlayerPiece>> getPlayerPieces (
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId,
            @ApiQueryParam(name = "playerId",description = "playerId id", required = false) String playerId, Pageable pageable) throws Exception {

        PageVo<List<PlayerPiece>> playerPieces = playerPieceServiceImpl.getPlayerPieces(jumboJackpotId, playerId, pageable);

        return playerPieces;
    }
}
