package com.example.kiber.Repository;

import com.example.kiber.Model.city;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;


public interface CityRepository extends CrudRepository<city,Long> {
    List<city> findAllByNameAndMounthAndDay(String name, int mounth, int day);
    List<city> findAllByNameAndMounth(String name, int mounth);
    int countRecordsByTemperatureEqualsAndNameIsAndDateAfterAndDateBefore(int temp, String region, Date startDate, Date endDate);
    int countRecordsByDirectionIsAndNameIsAndDateAfterAndDateBefore(String wind, String region, Date startDate, Date endDate);
    int countRecordsBySpeedIsAndNameIsAndDateAfterAndDateBefore(int speed, String region, Date startDate, Date endDate);
    int countRecordsBySpeedIsAndDirectionIsAndNameIsAndDateAfterAndDateBefore(int speed, String wind, String region, Date startDate, Date endDate);
    @Query("select distinct speed from city where direction = ?1 and name = ?2 and Date between ?3 and ?4")
    List<Integer> findDistinctSpeedByDirection(String wind, String region, Date startDate, Date endDate);
}
