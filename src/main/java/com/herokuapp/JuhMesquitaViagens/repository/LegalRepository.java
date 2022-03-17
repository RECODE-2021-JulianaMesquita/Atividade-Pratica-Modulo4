package com.herokuapp.JuhMesquitaViagens.repository;

import com.herokuapp.JuhMesquitaViagens.model.Legal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalRepository extends JpaRepository<Legal, Integer>{

}