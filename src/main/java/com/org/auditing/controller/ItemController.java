package com.org.auditing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.auditing.entity.Item;
import com.org.auditing.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController
{
	private final ItemService itemService;

	public ItemController(ItemService itemService)
	{
		this.itemService = itemService;
	}

	// Create Item
	@PostMapping("/add")
	public ResponseEntity<Item> createItem(@RequestBody Item item)
	{
		return ResponseEntity.ok(itemService.saveItem(item));
	}

	// Get All Items
	@GetMapping
	public ResponseEntity<List<Item>> getAllItems()
	{
		return ResponseEntity.ok(itemService.getAllItems());
	}

	// Get Item by ID
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable long id)
	{
		Optional<Item> item = itemService.getItemById(id);
		return item.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Update Item
	@PutMapping("update/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item item)
	{
		item.setOrderId(id);
		return ResponseEntity.ok(itemService.saveItem(item));
	}

	// Delete Item
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable long id)
	{
		itemService.deleteItem(id);
		return ResponseEntity.noContent().build();
	}
}
