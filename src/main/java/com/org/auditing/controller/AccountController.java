package com.org.auditing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.auditing.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController
{
	private final AccountService accountService;

	@PostMapping("/transfer")
	public String transfer(@RequestParam Long from,
			@RequestParam Long to,
			@RequestParam double amount)
	{
		accountService.transfer(from, to, amount);
		return "Transfer successful!";
	}
}
