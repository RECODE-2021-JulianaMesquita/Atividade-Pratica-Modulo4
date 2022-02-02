package com.herokuapp.JuhMesquitaViagens.repository;

import com.herokuapp.JuhMesquitaViagens.model.Physics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicsRepository extends JpaRepository<Physics, Long>{

}