package com.restaurantapp.restaurantproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurantapp.restaurantproject.entity.Menu;
import com.restaurantapp.restaurantproject.model.DTO.MenuDto;
import com.restaurantapp.restaurantproject.repository.MenuRepository;
import com.restaurantapp.restaurantproject.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	private final MenuRepository menuRepository;

	public MenuServiceImpl(MenuRepository menuRepository) {
		super();
		this.menuRepository = menuRepository;
	}

	public List<MenuDto> getAll() {

		List<Menu> menuList = menuRepository.findAll();
		List<MenuDto> menuDtoList = new ArrayList<MenuDto>();
		for (Menu menu : menuList) {
			menuDtoList.add(convert(menu));
		}
		return menuDtoList;
	}

	private MenuDto convert(Menu menu) {
		MenuDto menuDto = new MenuDto();
		menuDto.setId(menu.getId());
		menuDto.setFoodName(menu.getFoodName());
		menuDto.setItem(menu.getItem());
		menuDto.setPrice(menu.getPrice());
		menuDto.setSize(menu.getSize());

		return menuDto;
	}

	public MenuDto create(Menu menu) {

		return convert(menuRepository.save(menu));
	}

	public MenuDto update(int id, Menu menu) {
		Optional<Menu> menuOp = menuRepository.findById(id);

		if (menuOp.isPresent()) {
			Menu existingMenu = menuOp.get();
			existingMenu.setId(menu.getId());
			existingMenu.setFoodName(menu.getFoodName());
			existingMenu.setItem(menu.getItem());
			existingMenu.setPrice(menu.getPrice());
			existingMenu.setSize(menu.getSize());

			return convert(menuRepository.save(existingMenu));
		} else {
			throw new RuntimeException("Menu is not found with id:" + id);
		}

	}

	public void delete(int id) {
		Optional<Menu> menuOp = menuRepository.findById(id);

		if (menuOp.isPresent()) {

			menuRepository.delete(menuOp.get());
		} else {
			throw new RuntimeException("Menu is not found with id:" + id);
		}
	}

}
