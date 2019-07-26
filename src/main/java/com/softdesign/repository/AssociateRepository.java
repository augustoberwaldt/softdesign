package com.softdesign.repository;

import com.softdesign.entity.Associate;
import com.softdesign.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {
    Associate findByCpf(String cpf);
}
