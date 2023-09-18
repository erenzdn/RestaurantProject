package com.restaurantapp.restaurantproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurantapp.restaurantproject.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

	public List<Branch> findByRestaurantId(int id);

}
