package com.jjg.config;

import com.jjg.core.JumboJackpotPiecesFactory;
import com.jjg.model.JumboJackpot;
import com.jjg.model.JumboJackpotPieceState;
import com.jjg.model.vo.JumboJackpotRestoreVo;
import com.jjg.service.JumboJackpotPieceStateService;
import com.jjg.service.JumboJackpotService;
import com.jjg.service.PlayerPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Order(1)
public class JumboJackpotRestoreRunner implements ApplicationRunner {

    @Autowired
    private JumboJackpotService jumboJackpotServiceImpl;

    @Autowired
    private JumboJackpotPieceStateService jumboJackpotPieceStateServiceImpl;

    @Autowired
    private PlayerPieceService playerPieceServiceImpl;

    private JumboJackpotPiecesFactory jumboJackpotPiecesFactory = JumboJackpotPiecesFactory.getInstance();

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<JumboJackpot> jumboJackpots = jumboJackpotServiceImpl.getJumboJackpotActiveAll();

        if (jumboJackpots.size() != 0) {
            List<JumboJackpotRestoreVo> jumboJackpotRestoreVos = new ArrayList<>();
            for (JumboJackpot jumboJackpot : jumboJackpots) {
                JumboJackpotRestoreVo jumboJackpotRestoreVo = new JumboJackpotRestoreVo();
                jumboJackpotRestoreVo.setJumboJackpot(jumboJackpot);

                HashMap<String, JumboJackpotPieceState> jumboJackpotPieces =
                        jumboJackpotPieceStateServiceImpl.getJumboJackpotPieceState(jumboJackpot.getJumboJackpotId());
                jumboJackpotRestoreVo.setJumboJackpotPieces(jumboJackpotPieces);

                jumboJackpotRestoreVo.setRarePlayer(playerPieceServiceImpl.getRarePlayer(jumboJackpot));

                jumboJackpotRestoreVo.setPlayersPieces(
                        playerPieceServiceImpl.getPlayerPiecesByGroup(jumboJackpot.getJumboJackpotId()));


                jumboJackpotRestoreVos.add(jumboJackpotRestoreVo);
            }

            jumboJackpotPiecesFactory.jumboJackpotRestore(jumboJackpotRestoreVos);
        }
    }
}
