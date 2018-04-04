package com.jjg.service;

import com.jjg.model.JumboJackpotPieceState;
import com.jjg.repository.JumboJackpotPieceStateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JumboJackpotPieceStateServiceImpl implements JumboJackpotPieceStateService {

    @Autowired
    private JumboJackpotPieceStateDao jumboJackpotPieceStateDao;


    @Override
    public void saveJumboJackpotPieceState(HashMap<String, JumboJackpotPieceState> jumboJackpotPieces) {
        Iterator iterator = jumboJackpotPieces.keySet().iterator();
        List<JumboJackpotPieceState> jumboJackpotPieceList = new ArrayList<>();
        while (iterator.hasNext()) {
            jumboJackpotPieceList.add(jumboJackpotPieces.get(iterator.next()));
        }
        jumboJackpotPieceStateDao.save(jumboJackpotPieceList);
    }

    @Override
    public boolean updatePieceState(JumboJackpotPieceState jumboJackpotPieceState) {
        JumboJackpotPieceState jumboJackpotPieceStateNew = jumboJackpotPieceStateDao.findByJumboJackpotIdAndPieceName(
                jumboJackpotPieceState.getJumboJackpotId(), jumboJackpotPieceState.getPieceName());

        if (jumboJackpotPieceStateNew == null || jumboJackpotPieceStateNew.getPieceNumber() <= 0) {
            return false;
        }

        jumboJackpotPieceStateNew.setPieceNumber(jumboJackpotPieceStateNew.getPieceNumber() - 1);
        jumboJackpotPieceStateNew.setUpdatedDate(new Date());
        jumboJackpotPieceStateDao.save(jumboJackpotPieceStateNew);

        return true;
    }

    @Override
    public HashMap<String, JumboJackpotPieceState> getJumboJackpotPieceState(Long jumboJackpotId) {

        HashMap<String, JumboJackpotPieceState> jumboJackpotPieces = new HashMap<>();

        List<JumboJackpotPieceState> jumboJackpotPiecesList = jumboJackpotPieceStateDao.findByJumboJackpotId(jumboJackpotId);

        for (JumboJackpotPieceState jumboJackpotPieceState : jumboJackpotPiecesList) {
            jumboJackpotPieces.put(jumboJackpotPieceState.getPieceName(), jumboJackpotPieceState);
        }

        return jumboJackpotPieces;
    }
}
