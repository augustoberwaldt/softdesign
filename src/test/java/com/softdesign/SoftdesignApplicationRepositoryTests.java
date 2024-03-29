package com.softdesign;


import com.softdesign.entity.Associate;
import com.softdesign.entity.Schedule;
import com.softdesign.repository.AssociateRepository;
import com.softdesign.repository.ScheduleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class SoftdesignApplicationRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    public void whenFindByCpf_thenReturnAssociate() {
        Associate associate = new Associate();
        associate.setCpf("01610383010");
        associate.setName("Augusto");
        associate.setEmail("augusto.berwaldt@gmail.com");
        entityManager.persist(associate);
        entityManager.flush();

        Associate associateFound = associateRepository.findByCpf("01610383010");
        assertThat(associate.getCpf()).isEqualTo("01610383010");
    }

    @Test
    public void whenFindById_thenReturnSchedule() {
        Schedule schedule = new Schedule();
        schedule.setTitle("Pauta sobre TesteUnitario");
        schedule.setDescription("Pauta sobre TesteUnitario");

        entityManager.persist(schedule);
        entityManager.flush();

        Schedule scheduleFound = scheduleRepository.findByTitle("Pauta sobre TesteUnitario");
        assertThat(scheduleFound.getTitle()).isEqualTo("Pauta sobre TesteUnitario");
    }


}
