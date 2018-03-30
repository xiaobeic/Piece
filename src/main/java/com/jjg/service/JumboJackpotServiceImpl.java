package com.jjg.service;

import com.jjg.model.JumboJackpot;
import com.jjg.repository.JumboJackpotDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JumboJackpotServiceImpl implements JumboJackpotService {
    @Autowired
    private JumboJackpotDao jumboJackpotDao;

    @Override
    public List<JumboJackpot> getJumboJackpotAll() {
        return (List<JumboJackpot>) jumboJackpotDao.findAll();
    }
}
