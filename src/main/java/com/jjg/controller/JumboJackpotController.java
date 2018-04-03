package com.jjg.controller;

import com.jjg.core.JumboJackpotFactory;
import com.jjg.model.JumboJackpot;
import com.jjg.model.vo.JumboJackpotPieceVo;
import com.jjg.service.JumboJackpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JumboJackpotController{

    @Autowired
    private JumboJackpotService jumboJackpotServiceImpl;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init() {
        List<JumboJackpot> jumboJackpots = jumboJackpotServiceImpl.getJumboJackpotAll();

        JumboJackpotFactory jumboJackpotFactory = JumboJackpotFactory.getInstance();
        jumboJackpotFactory.init(jumboJackpots);

        return "OK";
    }

    @RequestMapping(value = "/demo1", method = RequestMethod.GET)
    public String demo1() {
        JumboJackpotFactory jumboJackpotFactory = JumboJackpotFactory.getInstance();

        JumboJackpotPieceVo jumboJackpotPieceVo1 = jumboJackpotFactory.requestPiece(1L, 123456L);
        JumboJackpotPieceVo jumboJackpotPieceVo2 = jumboJackpotFactory.requestPiece(1L, 8888888L);
        return jumboJackpotPieceVo1.getJumboJackpotPieceState().getPieceName()+"-"+jumboJackpotPieceVo2.getJumboJackpotPieceState().getPieceName();
    }

    @RequestMapping(value = "/demo2", method = RequestMethod.GET)
    public String demo2() {
        JumboJackpotFactory jumboJackpotFactory = JumboJackpotFactory.getInstance();

        JumboJackpotPieceVo jumboJackpotPieceVo1 = jumboJackpotFactory.requestPiece(2L, 123456L);
        return jumboJackpotPieceVo1.getJumboJackpotPieceState().getPieceName();
    }


    @RequestMapping(value = "/demo3", method = RequestMethod.GET)
    public Integer demo3() {
        int num = 985 / 1000;
        return num;
    }
}
