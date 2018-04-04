package com.jjg.service;

import com.jjg.constants.JumboJackpotConstants;
import com.jjg.model.JumboJackpot;
import com.jjg.repository.JumboJackpotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JumboJackpotServiceImpl implements JumboJackpotService {
    @Autowired
    private JumboJackpotDao jumboJackpotDao;

    @Override
    public List<JumboJackpot> getJumboJackpotActiveAll() {
        return jumboJackpotDao.findByStatus(JumboJackpotConstants.ACTIVE);
    }

    @Override
    public JumboJackpot getJumboJackpotById(Long jumboJackpotId) {
        return jumboJackpotDao.findOne(jumboJackpotId);
    }

    @Override
    public boolean updateJumboJackpotState(Long jumboJackpotId, Integer status) {
        JumboJackpot jumboJackpot = jumboJackpotDao.findOne(jumboJackpotId);
        jumboJackpot.setStatus(status);
        jumboJackpot.setUpdatedDate(new Date());
        jumboJackpotDao.save(jumboJackpot);
        return true;
    }

    @Override
    public boolean exists(Long jumboJackpotId) {
        return jumboJackpotDao.exists(jumboJackpotId);
    }

    @Override
    public boolean isActive(Long jumboJackpotId) {
        JumboJackpot jumboJackpot = jumboJackpotDao.findOne(jumboJackpotId);
        if (jumboJackpot != null && jumboJackpot.getStatus() == JumboJackpotConstants.ACTIVE) {
            return true;
        }
        return false;
    }
}
