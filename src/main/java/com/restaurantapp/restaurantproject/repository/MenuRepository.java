package com.restaurantapp.restaurantproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantapp.restaurantproject.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
