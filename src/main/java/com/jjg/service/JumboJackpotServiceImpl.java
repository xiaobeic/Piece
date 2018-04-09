package com.jjg.service;

import com.jjg.constants.ErrorConstants;
import com.jjg.constants.JumboJackpotConstants;
import com.jjg.model.JumboJackpot;
import com.jjg.model.bo.JumboJackpotBo;
import com.jjg.repository.JumboJackpotDao;
import com.jjg.utils.DataObjectConvertUtils;
import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JumboJackpotServiceImpl implements JumboJackpotService {
    @Autowired
    private JumboJackpotDao jumboJackpotDao;

    @Override
    public List<JumboJackpot> getJumboJackpotActiveAll()  throws Exception {
        return jumboJackpotDao.findByStatus(JumboJackpotConstants.ACTIVE);
    }

    @Override
    public JumboJackpot getJumboJackpotById(Long jumboJackpotId) throws Exception {
        return jumboJackpotDao.findOne(jumboJackpotId);
    }

    @Override
    public boolean updateJumboJackpotState(Long jumboJackpotId, Integer status) throws Exception {
        JumboJackpot jumboJackpot = jumboJackpotDao.findOne(jumboJackpotId);
        jumboJackpot.setStatus(status);
        jumboJackpot.setUpdatedDate(new Date());
        jumboJackpotDao.save(jumboJackpot);
        return true;
    }

    @Override
    public boolean exists(Long jumboJackpotId) throws Exception {
        return jumboJackpotDao.exists(jumboJackpotId);
    }

    @Override
    public boolean isActive(Long jumboJackpotId) throws Exception {
        JumboJackpot jumboJackpot = jumboJackpotDao.findOne(jumboJackpotId);
        if (jumboJackpot != null && jumboJackpot.getStatus() == JumboJackpotConstants.ACTIVE) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveJumboJackpot(JumboJackpotBo jumboJackpotBo) throws Exception {
        checkJumboJackpot(jumboJackpotBo);

        JumboJackpot jumboJackpot = new JumboJackpot();
        DataObjectConvertUtils.copyProperties(jumboJackpot, jumboJackpotBo);

        jumboJackpot.setStatus(JumboJackpotConstants.INIT);
        jumboJackpot.setCreatedDate(new Date());

        //Data processing

        jumboJackpotDao.save(jumboJackpot);
        return true;
    }

    private  void checkJumboJackpot(JumboJackpotBo jumboJackpotBo) throws Exception {
        if (jumboJackpotBo.getName() == null)
            throw new Exception(ErrorConstants.NAME);
        if (jumboJackpotBo.getTitle() == null)
            throw new Exception(ErrorConstants.TITLE);
        if (jumboJackpotBo.getRulesFile() == null)
            throw new Exception(ErrorConstants.RULESFILE);
        if (jumboJackpotBo.getEmailsToNotify() == null)
            throw new Exception(ErrorConstants.EMAILSTONOTIFY);
        if (jumboJackpotBo.getBoardImage() == null)
            throw new Exception(ErrorConstants.BOARDIMAGE);
        if (jumboJackpotBo.getGameThumbnail() == null)
            throw new Exception(ErrorConstants.GAMETHUMBNAIL);
        if (jumboJackpotBo.getGameIcon() == null)
            throw new Exception(ErrorConstants.GAMEICON);
        if (jumboJackpotBo.getDistributions() == null)
            throw new Exception(ErrorConstants.DISTRIBUTIONS);
        if (jumboJackpotBo.getTotalPieces() == null)
            throw new Exception(ErrorConstants.TOTALPIECES);
        if (jumboJackpotBo.getRacePieces() == null)
            throw new Exception(ErrorConstants.RACEPIECES);
        if (jumboJackpotBo.getPieceType() == null)
            throw new Exception(ErrorConstants.PIECETYPE);
        if (jumboJackpotBo.getRaceRatio() == null)
            throw new Exception(ErrorConstants.RACERATIO);
        if (jumboJackpotBo.getValue() == null)
            throw new Exception(ErrorConstants.VALUE);
        if (jumboJackpotBo.getAttractVideos() == null)
            throw new Exception(ErrorConstants.ATTRACTVIDEOS);
        if (jumboJackpotBo.getPromotions() == null)
            throw new Exception(ErrorConstants.PROMOTIONS);
        if (jumboJackpotBo.getGamePiecesImages() == null) {
            throw new Exception(ErrorConstants.GAMEPIECESIMAGES);
        }

        if (jumboJackpotBo.getFormDate().getTime() > jumboJackpotBo.getToDate().getTime())
            throw new Exception(ErrorConstants.DATEERROR);
    }
}
