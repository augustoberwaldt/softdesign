package com.softdesign.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdesign.config.Translator;
import com.softdesign.entity.Associate;
import com.softdesign.entity.Schedule;
import com.softdesign.entity.Vote;
import com.softdesign.enumerable.ExternalApiEnum;
import com.softdesign.exception.BusinessException;
import com.softdesign.repository.AssociateRepository;
import com.softdesign.repository.ScheduleRepository;
import com.softdesign.repository.VoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final ScheduleRepository  scheduleRepository;

    private final AssociateRepository  associateRepository;

    public VoteService(VoteRepository voteRepository, ScheduleRepository  scheduleRepository, AssociateRepository  associateRepository) {
        this.voteRepository = voteRepository;
        this.scheduleRepository = scheduleRepository;
        this.associateRepository = associateRepository;

    }


    /**
     * save
     *
     * @param vote vote
     */
    public void save(Vote vote) {

        this.voteRepository.save(vote);
    }

    /**
     * Valida dados de entrada vindo requisicao
     *
     * @param data
     * @throws IllegalArgumentException
     */
    public void validateData(Map<String, Object> data) throws IllegalArgumentException, BusinessException {

        //TODO

        String cpf =  data.get("associate").toString();
        Long scheduleId =  Long.parseLong(data.get("schedule").toString());
        String vote =  data.get("vote").toString();

        if (cpf.isEmpty()) {
            throw new IllegalArgumentException(Translator.toLocale(Translator.toLocale("cpf_empty")));
        }

        Associate associate  = this.associateRepository.findByCpf(cpf);

        if (associate == null) {
            throw new IllegalArgumentException(Translator.toLocale(Translator.toLocale("cpf_associate")));
        }

        this.validateCpf(cpf);

        Optional<Schedule> scheduleOptional  = this.scheduleRepository.findById(scheduleId);

        if (!scheduleOptional.isPresent()) {
            throw new IllegalArgumentException(Translator.toLocale(Translator.toLocale("cpf_empty")));
        }

        Schedule schedule = scheduleOptional.get();

        Date now =  new Date();

        if (!(now.after(schedule.getDtStarted()) && now.before(schedule.getDtFinish()))) {
            throw new BusinessException(Translator.toLocale(Translator.toLocale("periodo_invalid")));
        }

    }

    /**
     * Converte dados de entrada para objeto Vote
     * @param data
     * @return
     * @throws IllegalArgumentException
     * @throws BusinessException
     */
    public Vote parser(Map<String, Object> data) throws IllegalArgumentException, BusinessException  {

        String cpf =  data.get("associate").toString();
        Long scheduleId =  Long.parseLong(data.get("schedule").toString());
        int vote =  Integer.parseInt(data.get("vote").toString());

        Vote oVote  = this.getVoteByCpfAndScheduleId(cpf, scheduleId);

        if (oVote !=null) {
            throw new IllegalArgumentException(Translator.toLocale(Translator.toLocale("cpf_vote")));
        }

        Optional<Schedule> scheduleOptional  = this.scheduleRepository.findById(scheduleId);

        oVote  = new  Vote();

        oVote.setAssociate(this.associateRepository.findByCpf(cpf));
        oVote.setSchedule(scheduleOptional.get());
        oVote.setVote(vote);

        return oVote;
    }

    /**
     * Busca na base de dados pelo cpf do associado e pelo Id, da pauta
     *
     * @param cpf
     * @param id
     * @return
     */
    public Vote getVoteByCpfAndScheduleId(String cpf, Long id) {
        return this.voteRepository.findByAssociate_CpfAndSchedule_Id(cpf, id);
    }


    /**
     * Verifica na API externa se cpf pode votar
     *
     * @param cpf
     * @throws IllegalArgumentException
     */
    public void  validateCpf(String cpf) throws IllegalArgumentException {

        RestTemplate restTemplate = new RestTemplate();

        try {

            ResponseEntity<String> response = restTemplate.getForEntity(ExternalApiEnum.VALIDATECPFURI.getValue() + "/" + cpf, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            String status = jsonNode.get("status").asText();

            if (response.getStatusCodeValue() != HttpServletResponse.SC_OK) {
                throw new IllegalArgumentException(Translator.toLocale("cpf"));
            }

            if (status.equals("UNABLE_TO_VOTE")) {
                throw new IllegalArgumentException(Translator.toLocale("cpf_validateApiUnable"));
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(Translator.toLocale("cpf_validateApi"));

        }

    }
}
