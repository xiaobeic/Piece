package com.jjg.controller;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.core.JumboJackpotFactory;
import com.jjg.core.JumboJackpotPool;
import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.vo.JumboJackpotPieceVo;
import com.jjg.model.vo.JumboJackpotRestoreVo;
import com.jjg.service.JumboJackpotPieceStateService;
import com.jjg.service.JumboJackpotService;
import com.jjg.service.PlayerPieceService;
import com.jjg.service.PlayerPieceServiceImpl;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping("/admin/jumboJackpot")
public class JumboJackpotController{

    @Autowired
    private JumboJackpotService jumboJackpotServiceImpl;

    @Autowired
    private JumboJackpotPieceStateService jumboJackpotPieceStateServiceImpl;

    @Autowired
    private PlayerPieceService playerPieceServiceImpl;

    private JumboJackpotFactory jumboJackpotFactory;

    @ModelAttribute
    public void jumboJackpotFactoryInit () {
        jumboJackpotFactory = JumboJackpotFactory.getInstance();
    }

    @RequestMapping(value = "/restore", method = RequestMethod.GET)
    public boolean restore() {
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

        jumboJackpotFactory.jumboJackpotRestore(jumboJackpotRestoreVos);

        return true;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ApiMethod(description = "Create a Jumbo Jackpot")
    public @ApiResponseObject boolean createJumboJackpot(
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) {
        JumboJackpot jumboJackpot = jumboJackpotServiceImpl.getJumboJackpotById(jumboJackpotId);
        if (jumboJackpot == null || jumboJackpot.getStatus() != JumboJackpotConstants.INIT) {
            return false;
        }

        JumboJackpotPool jumboJackpotPool = jumboJackpotFactory.generateJumboJackpot(jumboJackpot);
        if (jumboJackpotPool.getJumboJackpotPieces().size() == 0) {
            return false;
        }

        jumboJackpotPieceStateServiceImpl.saveJumboJackpotPieceState(jumboJackpotPool.getJumboJackpotPieces());
        return jumboJackpotServiceImpl.updateJumboJackpotState(jumboJackpotId, JumboJackpotConstants.ACTIVE);
    }

    @RequestMapping(value = "/getJumboJackpot", method = RequestMethod.GET)
    @ApiMethod(description = "get a Jumbo Jackpot")
    public @ApiResponseObject JumboJackpotPool getJumboJackpot (
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) {
        if (!jumboJackpotServiceImpl.exists(jumboJackpotId)) {
            return null;
        }
        return jumboJackpotFactory.getJumboJackpot(jumboJackpotId);
    }

    @RequestMapping(value = "/getJumboJackpotList", method = RequestMethod.GET)
    @ApiMethod(description = "Get Jumbo Jackpot list")
    public @ApiResponseObject Hashtable<Long, JumboJackpotPool> getJumboJackpotList () {
        return jumboJackpotFactory.getJumboJackpotList();
    }

    @RequestMapping(value = "/removeJumboJackpot", method = RequestMethod.GET)
    @ApiMethod(description = "Remove a Jumbo Jackpot")
    public @ApiResponseObject boolean removeJumboJackpot (
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) {
        if (!jumboJackpotServiceImpl.exists(jumboJackpotId)) {
            return false;
        }

        if(!jumboJackpotFactory.removeJumboJackpot(jumboJackpotId)) {
            return false;
        }

        return jumboJackpotServiceImpl.updateJumboJackpotState(jumboJackpotId, JumboJackpotConstants.INACTIVE);
    }

    @RequestMapping(value = "/getPiece", method = RequestMethod.GET)
    @ApiMethod(description = "Get a piece by jumbo jackpot id and player id")
    public @ApiResponseObject boolean getPiece(
            @ApiQueryParam(name = "jumboJackpotId", description = "jumbo jackpot id") long jumboJackpotId,
            @ApiQueryParam(name = "playerId", description = "player id") long playerId) {
        if (!jumboJackpotServiceImpl.isActive(jumboJackpotId)) {
            return false;
        }

        JumboJackpotPieceVo jumboJackpotPieceVo = jumboJackpotFactory.requestPiece(jumboJackpotId, playerId);

        if (!jumboJackpotPieceStateServiceImpl.updatePieceState(jumboJackpotPieceVo.getJumboJackpotPieceState())) {
            return false;
        }

        playerPieceServiceImpl.save(jumboJackpotPieceVo.getJumboJackpotPieceState(), playerId);

        if (jumboJackpotPieceVo.isCollectAll()) {
            jumboJackpotServiceImpl.updateJumboJackpotState(jumboJackpotId, JumboJackpotConstants.INACTIVE);
        }

        return true;
    }

}
