package com.herokuapp.JuhMesquitaViagens.repository;

import com.herokuapp.JuhMesquitaViagens.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}