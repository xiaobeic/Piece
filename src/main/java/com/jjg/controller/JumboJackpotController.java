package com.jjg.controller;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.core.JumboJackpotFactory;
import com.jjg.core.JumboJackpotPool;
import com.jjg.model.JumboJackpot;
import com.jjg.service.JumboJackpotPieceStateService;
import com.jjg.service.JumboJackpotService;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.List;

@RestController
@RequestMapping("/admin/jumboJackpot")
public class JumboJackpotController{

    @Autowired
    private JumboJackpotService jumboJackpotServiceImpl;

    @Autowired
    private JumboJackpotPieceStateService jumboJackpotPieceStateServiceImpl;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        List<JumboJackpot> jumboJackpots = jumboJackpotServiceImpl.getJumboJackpotAll();

        JumboJackpotFactory jumboJackpotFactory = JumboJackpotFactory.getInstance();
        jumboJackpotFactory.generateJumboJackpot(jumboJackpots.get(1));

        return "OK";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ApiMethod(description = "Create a Jumbo Jackpot")
    public @ApiResponseObject boolean createJumboJackpot(
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) {
        JumboJackpot jumboJackpot = jumboJackpotServiceImpl.getJumboJackpotById(jumboJackpotId);
        if (jumboJackpot == null) {
            return false;
        }
        JumboJackpotPool jumboJackpotPool = JumboJackpotFactory.getInstance().generateJumboJackpot(jumboJackpot);

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
        return JumboJackpotFactory.getInstance().getJumboJackpot(jumboJackpotId);
    }

    @RequestMapping(value = "/getJumboJackpotList", method = RequestMethod.GET)
    @ApiMethod(description = "Get Jumbo Jackpot list")
    public @ApiResponseObject Hashtable<Long, JumboJackpotPool> getJumboJackpotList () {
        return JumboJackpotFactory.getInstance().getJumboJackpotList();
    }

    @RequestMapping(value = "/removeJumboJackpot", method = RequestMethod.GET)
    @ApiMethod(description = "Remove a Jumbo Jackpot")
    public @ApiResponseObject boolean removeJumboJackpot (
            @ApiQueryParam(name = "jumboJackpotId",description = "jumbo jackpot id") long jumboJackpotId) {
        if (!jumboJackpotServiceImpl.exists(jumboJackpotId)) {
            return false;
        }

        if(!JumboJackpotFactory.getInstance().removeJumboJackpot(jumboJackpotId)) {
            return false;
        }

        return jumboJackpotServiceImpl.updateJumboJackpotState(jumboJackpotId, JumboJackpotConstants.INACTIVE);
    }

}
