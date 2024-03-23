package com.example.demo_v6.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_v6.entities.CryptText;
import com.example.demo_v6.requests.CryptTextCreateRequest;
import com.example.demo_v6.requests.DecryptRequest;
import com.example.demo_v6.responses.CryptTextResponse;
import com.example.demo_v6.responses.DeCryptResponse;
import com.example.demo_v6.services.CryptTextService;

@RestController
@RequestMapping("/posts")
public class CryptTextController{
	private CryptTextService cryptTextService;

	public CryptTextController(CryptTextService cryptTextService) {
		this.cryptTextService = cryptTextService;
	}
	@GetMapping
	public List<CryptTextResponse> getAllComments(@RequestParam Optional <Integer> userId){
		return cryptTextService.getAllCryptTexts(userId);
	}
	@GetMapping("/{textId}")
	public CryptText getOneCommentById(@PathVariable Integer textId) {
		return cryptTextService.getOneCryptTextById(textId);
	}
	@PostMapping("/decrypt")
	public DeCryptResponse getDecryptFunction(@RequestBody DecryptRequest request) {
		return cryptTextService.getDecryptFunction(request);
	}
	@PostMapping
	public CryptText createOneComment(@RequestBody CryptTextCreateRequest request ) {
		return cryptTextService.createOneRequest(request);
	}
}


