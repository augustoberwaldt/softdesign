package com.softdesign.controller;

import com.softdesign.config.Translator;
import com.softdesign.entity.Associate;
import com.softdesign.entity.Schedule;
import com.softdesign.entity.Vote;
import com.softdesign.service.AssociateService;
import com.softdesign.service.ScheduleService;

import com.softdesign.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    private final ScheduleService scheduleService;

    private final AssociateService  associateService;

    private final VoteService voteService;


    public ApiController(ScheduleService scheduleService, AssociateService  associateService,  VoteService voteService) {
        this.scheduleService = scheduleService;
        this.associateService = associateService;
        this.voteService = voteService;
    }


    @RequestMapping(method=RequestMethod.POST,  path = "/schedule/createSchedule",  produces = "application/json",  consumes = "application/json")
    public  ResponseEntity<String>   createSchedule(@RequestBody Schedule schedule) {

        if (logger.isDebugEnabled()) {
            logger.info("Requisição de entrada createSchedule :" + schedule.toString());
        }

        try {

            this.scheduleService.save(schedule);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(Translator.toLocale("ok"));
    }

    @RequestMapping(method=RequestMethod.POST,  path = "/schedule/addVote",  produces = "application/json",  consumes = "application/json")
    public  ResponseEntity<String>   createSchedule(@RequestBody Map<String, Object> payload) {

        try {

          this.voteService.validateData(payload);
          Vote vote = this.voteService.parser(payload);
          this.voteService.save(vote);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("success");
    }


    @RequestMapping(method=RequestMethod.POST,  path = "/associate/createAssociate",  produces = "application/json",  consumes = "application/json")
    public  ResponseEntity<String>   createAssociate(@RequestBody Associate associate) {

        if (logger.isDebugEnabled()) {
            logger.info("Requisição de entrada  createAssociate :" + associate.toString());
        }

        try {

            if (this.associateService.getByCpf(associate.getCpf()) != null) {
                throw new IllegalArgumentException(Translator.toLocale(Translator.toLocale("cpf_exist")));
            }

            this.associateService.save(associate);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(Translator.toLocale("ok"));
    }


}
