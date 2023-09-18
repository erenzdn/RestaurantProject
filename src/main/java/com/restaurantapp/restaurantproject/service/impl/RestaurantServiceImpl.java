package com.restaurantapp.restaurantproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurantapp.restaurantproject.entity.Restaurant;
import com.restaurantapp.restaurantproject.model.DTO.BranchDto;
import com.restaurantapp.restaurantproject.model.DTO.RestaurantDto;
import com.restaurantapp.restaurantproject.repository.RestaurantRepository;
import com.restaurantapp.restaurantproject.service.BranchService;
import com.restaurantapp.restaurantproject.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final BranchService branchService;

	public RestaurantServiceImpl(RestaurantRepository restaurantRepository, BranchService branchService) {
		super();
		this.restaurantRepository = restaurantRepository;
		this.branchService = branchService;
	}

	public List<RestaurantDto> getAll() {
		List<Restaurant> restaurantList = restaurantRepository.findAll();
		List<RestaurantDto> restaurantDtoList = new ArrayList<RestaurantDto>();
		for (Restaurant restaurant : restaurantList) {
			restaurantDtoList.add(convert(restaurant));
		}
		return restaurantDtoList;

	}

	private RestaurantDto convert(Restaurant restaurant) {
		RestaurantDto restaurantDto = new RestaurantDto();
		List<BranchDto> branchDto = branchService.findByRestaurantId(restaurant.getId());
		restaurantDto.setId(restaurant.getId());
		restaurantDto.setName(restaurant.getName());
		restaurantDto.setAddress(restaurant.getAddress());
		restaurantDto.setPhoneNumber(restaurant.getPhoneNumber());
		restaurantDto.setBranchs(branchDto);
		return restaurantDto;
	}

	public RestaurantDto create(Restaurant restaurant) {
		return convert(restaurantRepository.save(restaurant));
	}

	public RestaurantDto update(int id, Restaurant restaurant) {
		Optional<Restaurant> restaurantOp = restaurantRepository.findById(id);

		if (restaurantOp.isPresent()) {
			Restaurant existingRestaurant = restaurantOp.get();
			existingRestaurant.setId(restaurant.getId());

			existingRestaurant.setAddress(restaurant.getAddress());
			existingRestaurant.setPhoneNumber(restaurant.getPhoneNumber());

			return convert(restaurantRepository.save(existingRestaurant));
		} else {
			throw new RuntimeException("Restaurant is not found with id:" + id);
		}

	}

	public void delete(int id) {
		Optional<Restaurant> restaurantOp = restaurantRepository.findById(id);

		if (restaurantOp.isPresent()) {

			restaurantRepository.delete(restaurantOp.get());
		} else {
			throw new RuntimeException("Restaurant is not found with id:" + id);
		}
	}

}
