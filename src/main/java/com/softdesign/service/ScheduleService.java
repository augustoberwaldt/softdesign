package com.softdesign.service;

import com.softdesign.entity.Schedule;
import com.softdesign.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    /**
     * save
     * @param Schedule  schedule
     */
    public void save(Schedule schedule) {

        this.scheduleRepository.save(schedule);
    }



    /**
     * Calcula diferenca entre duas  datas
     *
     * @param Date a
     * @param Date b
     */
    public int daysBetween(Date a, Date b) {
        Calendar dInicial = Calendar.getInstance();
        dInicial.setTime(a); Calendar dFinal = Calendar.getInstance(); dFinal.setTime(b);
        int count = 0;
        while (dInicial.get(Calendar.DAY_OF_MONTH) != dFinal.get(Calendar.DAY_OF_MONTH)){
            dInicial.add(Calendar.DAY_OF_MONTH, 1);
            count ++;
        }

        return count;
    }

}
