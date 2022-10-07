package net.posapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.posapp.orm.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
