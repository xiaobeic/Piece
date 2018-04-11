package com.jjg.controller;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.core.JumboJackpotPiecesFactory;
import com.jjg.core.JumboJackpotPiecesPool;
import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.bo.JumboJackpotBo;
import com.jjg.model.vo.JumboJackpotPieceVo;
import com.jjg.model.vo.JumboJackpotRestoreVo;
import com.jjg.service.JumboJackpotPieceStateService;
import com.jjg.service.JumboJackpotService;
import com.jjg.service.PlayerPieceService;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Api(description = "The Jumbo Jackpot Controller", name = "Jumbo Jackpot API")
@RequestMapping("/admin/jumboJackpot")
public class JumboJackpotController{

    @Autowired
    private JumboJackpotService jumboJackpotServiceImpl;

    @Autowired
    private JumboJackpotPieceStateService jumboJackpotPieceStateServiceImpl;

    @Autowired
    private PlayerPieceService playerPieceServiceImpl;

    private JumboJackpotPiecesFactory jumboJackpotPiecesFactory;

    @ModelAttribute
    public void jumboJackpotFactoryInit () {
        jumboJackpotPiecesFactory = JumboJackpotPiecesFactory.getInstance();
    }

    @ApiMethod(description = "Restore server")
    @RequestMapping(value = "/restore", method = RequestMethod.GET)
    public boolean restore() throws Exception {
        List<JumboJackpot> jumboJackpots = jumboJackpotServiceImpl.getJumboJackpotActiveAll();

        if (jumboJackpots.size() == 0) {
            return true;
        }

        List<JumboJackpotRestoreVo> jumboJackpotRestoreVos = new ArrayList<>();
        for (JumboJackpot jumboJackpot : jumboJackpots) {
            JumboJackpotRestoreVo jumboJackpotRestoreVo = new JumboJackpotRestoreVo();
            jumboJackpotRestoreVo.setJumboJackpot(jumboJackpot);

            HashMap<String, JumboJackpotPieceState> jumboJackpotPieces =
                    jumboJackpotPieceStateServiceImpl.getJumboJackpotPieceState(jumboJackpot.getJumboJackpotId());
            jumboJackpotRestoreVo.setJumboJackpotPieces(jumboJackpotPieces);

            jumboJackpotRestoreVo.setRarePlayer(playerPieceServiceImpl.getRarePlayer(jumboJackpot));

            jumboJackpotRestoreVo.setPlayersPieces(
                    playerPieceServiceImpl.getPlayerPieces(jumboJackpot.getJumboJackpotId()));


            jumboJackpotRestoreVos.add(jumboJackpotRestoreVo);
        }

        jumboJackpotPiecesFactory.jumboJackpotRestore(jumboJackpotRestoreVos);

        return true;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ApiMethod(description = "Create a Jumbo Jackpot")
    public @ApiResponseObject boolean createJumboJackpot(
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) throws Exception {
        JumboJackpot jumboJackpot = jumboJackpotServiceImpl.getJumboJackpotById(jumboJackpotId);
        if (jumboJackpot == null || jumboJackpot.getStatus() != JumboJackpotConstants.INIT) {
            return false;
        }

        JumboJackpotPiecesPool jumboJackpotPiecesPool = jumboJackpotPiecesFactory.generateJumboJackpot(jumboJackpot);
        if (jumboJackpotPiecesPool.getJumboJackpotPieces().size() == 0) {
            return false;
        }

        jumboJackpotPieceStateServiceImpl.saveJumboJackpotPieceState(jumboJackpotPiecesPool.getJumboJackpotPieces());
        return jumboJackpotServiceImpl.updateJumboJackpotState(jumboJackpotId, JumboJackpotConstants.ACTIVE);
    }

    @RequestMapping(value = "/getJumboJackpotPieces", method = RequestMethod.GET)
    @ApiMethod(description = "get a Jumbo Jackpot")
    public @ApiResponseObject
    JumboJackpotPiecesPool getJumboJackpotPieces (
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) throws Exception {
        if (!jumboJackpotServiceImpl.exists(jumboJackpotId)) {
            return null;
        }
        return jumboJackpotPiecesFactory.getJumboJackpot(jumboJackpotId);
    }

    @RequestMapping(value = "/getJumboJackpot", method = RequestMethod.GET)
    @ApiMethod(description = "Get a Jumbo Jackpot")
    public @ApiResponseObject JumboJackpot getJumboJackpot (
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) throws Exception{
        return jumboJackpotServiceImpl.getJumboJackpot(jumboJackpotId);
    }

    @RequestMapping(value = "/getJumboJackpotAll", method = RequestMethod.GET)
    @ApiMethod(description = "Get Jumbo Jackpot list")
    public @ApiResponseObject List<JumboJackpot> getJumboJackpotAll () throws Exception{
        return jumboJackpotServiceImpl.getJumboJackpotAll();
    }

    @RequestMapping(value = "/getJumboJackpotsId", method = RequestMethod.GET)
    @ApiMethod(description = "Get Jumbo Jackpots id")
    public @ApiResponseObject Set<Long> getJumboJackpotsId () {
        return jumboJackpotPiecesFactory.getJumboJackpotList().keySet();
    }

    @RequestMapping(value = "/removeJumboJackpot", method = RequestMethod.GET)
    @ApiMethod(description = "Remove a Jumbo Jackpot")
    public @ApiResponseObject Integer removeJumboJackpot (
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) throws Exception {
        if (!jumboJackpotServiceImpl.exists(jumboJackpotId)) {
            return JumboJackpotConstants.INVALIDPARAMETER;
        }

        if(!jumboJackpotPiecesFactory.removeJumboJackpot(jumboJackpotId)) {
            return JumboJackpotConstants.INOPERATION;
        }

        jumboJackpotServiceImpl.deleteJumboJack(jumboJackpotId);
        return JumboJackpotConstants.SUCCESS;
    }

    @RequestMapping(value = "/getPiece", method = RequestMethod.GET)
    @ApiMethod(description = "Get a piece by jumbo jackpot id and player id")
    public @ApiResponseObject boolean getPiece(
            @ApiQueryParam(name = "jumboJackpotId", description = "jumbo jackpot id") long jumboJackpotId,
            @ApiQueryParam(name = "playerId", description = "player id") long playerId) throws Exception {
        if (!jumboJackpotServiceImpl.isActive(jumboJackpotId)) {
            return false;
        }

        JumboJackpotPieceVo jumboJackpotPieceVo = jumboJackpotPiecesFactory.requestPiece(jumboJackpotId, playerId);

        if (!jumboJackpotPieceStateServiceImpl.updatePieceState(jumboJackpotPieceVo.getJumboJackpotPieceState())) {
            return false;
        }

        playerPieceServiceImpl.save(jumboJackpotPieceVo.getJumboJackpotPieceState(), playerId);

        if (jumboJackpotPieceVo.isCollectAll() || jumboJackpotPieceVo.isGiveOutAll()) {
            jumboJackpotServiceImpl.updateJumboJackpotState(jumboJackpotId, JumboJackpotConstants.INACTIVE);
        }

        return true;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiMethod(description = "Save jumbo jackpot")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "name", description = "jumbo jackpot name", required = true, clazz = String.class),
            @ApiQueryParam(name = "totalPieces", description = "total pieces", required = true, clazz = Integer.class),
            @ApiQueryParam(name = "racePieces", description = "race pieces", required = true, clazz = String.class),
            @ApiQueryParam(name = "pieceType", description = "pieces type", required = true, clazz = Integer.class),
            @ApiQueryParam(name = "raceRatio", description = "race piece ratio", required = true, clazz = Integer.class),
            @ApiQueryParam(name = "formDate", description = "jumbo jackpot start date", required = true, clazz = Date.class),
            @ApiQueryParam(name = "toDate", description = "jumbo jackpot end date",  required = true, clazz = Date.class)

//            @ApiQueryParam(name = "title", description = "jumbo jackpot title", required = true, clazz = String.class),
//            @ApiQueryParam(name = "rulesFile", description = "jumbo jackpot rules file", required = true, clazz = MultipartFile.class),
//            @ApiQueryParam(name = "emailsToNotify", description = "emails to notify", required = true, clazz = String.class),
//            @ApiQueryParam(name = "boardImage", description = "board image", required = true, clazz = MultipartFile.class),
//            @ApiQueryParam(name = "gameThumbnail", description = "game thumbnail", required = true, clazz = MultipartFile.class),
//            @ApiQueryParam(name = "gameIcon", description = "game icon", required = true, clazz = MultipartFile.class),
//            @ApiQueryParam(name = "promotions", description = "promotions id", required = true, clazz = String.class),
//            @ApiQueryParam(name = "distributions", description = "jumbo jackpot distributions", required = true, clazz = String.class),
//            @ApiQueryParam(name = "value", description = "value", required = true, clazz = Integer.class),
//            @ApiQueryParam(name = "attractVideos", description = "attract videos", required = true, clazz = MultipartFile.class),
//            @ApiQueryParam(name = "gamePiecesImages", description = "game pieces", required = true, clazz = MultipartFile.class),
//            @ApiQueryParam(name = "isDefault", description = "is default", required = true, clazz = Boolean.class)
    })
    public @ApiResponseObject boolean save(@ApiBodyObject JumboJackpotBo jumboJackpotBo) throws Exception {
        return jumboJackpotServiceImpl.saveJumboJackpot(jumboJackpotBo);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiMethod(description = "Save jumbo jackpot")
    @ApiParams(queryparams = {
            @ApiQueryParam(name = "jumboJackpotId", description = "jumbo jackpot id", required = true, clazz = Long.class),
            @ApiQueryParam(name = "name", description = "jumbo jackpot name", required = true, clazz = String.class),
            @ApiQueryParam(name = "totalPieces", description = "total pieces", required = true, clazz = Integer.class),
            @ApiQueryParam(name = "racePieces", description = "race pieces", required = true, clazz = String.class),
            @ApiQueryParam(name = "pieceType", description = "pieces type", required = true, clazz = Integer.class),
            @ApiQueryParam(name = "raceRatio", description = "race piece ratio", required = true, clazz = Integer.class),
            @ApiQueryParam(name = "formDate", description = "jumbo jackpot start date", required = true, clazz = Date.class),
            @ApiQueryParam(name = "toDate", description = "jumbo jackpot end date",  required = true, clazz = Date.class)
    })
    public @ApiResponseObject boolean update(@ApiBodyObject JumboJackpotBo jumboJackpotBo) throws Exception {
        return jumboJackpotServiceImpl.updateJumboJackpot(jumboJackpotBo);
    }

}
