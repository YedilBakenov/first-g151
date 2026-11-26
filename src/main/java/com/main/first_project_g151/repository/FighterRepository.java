package com.main.first_project_g151.repository;

import com.main.first_project_g151.model.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Integer> {


    @Query("SELECT f FROM Fighter f WHERE f.fullName ilike concat('%', :s, '%') ")
    List<Fighter> findFightersByWord(String s);
}
