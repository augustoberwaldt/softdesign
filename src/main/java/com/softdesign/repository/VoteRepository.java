package com.softdesign.repository;

import com.softdesign.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByAssociate_CpfAndSchedule_Id(String cpf, Long id);
}
