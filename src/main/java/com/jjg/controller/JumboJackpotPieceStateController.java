package com.jjg.controller;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.core.JumboJackpotPiecesFactory;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.vo.JumboJackpotPieceVo;
import com.jjg.service.JumboJackpotPieceStateService;
import com.jjg.service.JumboJackpotService;
import com.jjg.service.PlayerPieceService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@Api(description = "The Jumbo Jackpot Piece State Controller", name = "Jumbo Jackpot Piece State API")
@RequestMapping("/admin/jumboJackpotPieceState")
public class JumboJackpotPieceStateController {

    private JumboJackpotPiecesFactory jumboJackpotPiecesFactory;

    @Autowired
    private JumboJackpotService jumboJackpotServiceImpl;

    @Autowired
    private PlayerPieceService playerPieceServiceImpl;

    @Autowired
    private JumboJackpotPieceStateService jumboJackpotPieceStateServiceImpl;

    @ModelAttribute
    public void jumboJackpotFactoryInit () {
        jumboJackpotPiecesFactory = JumboJackpotPiecesFactory.getInstance();
    }

    @RequestMapping(value = "/getPiece", method = RequestMethod.GET)
    @ApiMethod(description = "Get a piece by jumbo jackpot id and player id")
    public @ApiResponseObject JumboJackpotPieceVo getPiece(
            @ApiQueryParam(name = "jumboJackpotId", description = "jumbo jackpot id") long jumboJackpotId,
            @ApiQueryParam(name = "playerId", description = "player id") long playerId) throws Exception {
        long startTime = System.currentTimeMillis();

        JumboJackpotPieceVo jumboJackpotPieceVo = jumboJackpotPiecesFactory.requestPiece(jumboJackpotId, playerId);
        if (jumboJackpotPieceVo == null) {
            return null;
        }
        jumboJackpotPieceVo.setPlayerId(playerId);

        jumboJackpotPieceStateServiceImpl.updatePieceState(jumboJackpotPieceVo.getJumboJackpotPieceState());
        playerPieceServiceImpl.save(jumboJackpotPieceVo.getJumboJackpotPieceState(), playerId);

        if (jumboJackpotPieceVo.isCollectAll() || jumboJackpotPieceVo.isGiveOutAll()) {
            jumboJackpotServiceImpl.updateJumboJackpotState(jumboJackpotId, JumboJackpotConstants.INACTIVE);
        }

        jumboJackpotPieceVo.setProcessTime(System.currentTimeMillis() - startTime);
        return jumboJackpotPieceVo;
    }

    @RequestMapping(value = "/getJumboJackpotPieces", method = RequestMethod.GET)
    @ApiMethod(description = "get a Jumbo Jackpot")
    public @ApiResponseObject
    List<JumboJackpotPieceState> getJumboJackpotPieces (
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) throws Exception {
        List<JumboJackpotPieceState> jumboJackpotPieces = new ArrayList<>();
        if (!jumboJackpotPiecesFactory.isInOperation(jumboJackpotId)) {
            return jumboJackpotPieces;
        }

        HashMap<String, JumboJackpotPieceState> jumboJackpotPieceState =
                jumboJackpotPieceStateServiceImpl.getJumboJackpotPieceState(jumboJackpotId);
        Iterator iterator = jumboJackpotPieceState.keySet().iterator();
        while (iterator.hasNext()) {
            jumboJackpotPieces.add(jumboJackpotPieceState.get(iterator.next()));
        }

        return jumboJackpotPieces;
    }
}
