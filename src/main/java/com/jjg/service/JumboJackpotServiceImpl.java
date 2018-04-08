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
}
