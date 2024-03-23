package com.example.demo_v6.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo_v6.cryptFunctions.CryptFunction;
import com.example.demo_v6.entities.CryptText;
import com.example.demo_v6.entities.User;
import com.example.demo_v6.repos.CryptTextRepository;
import com.example.demo_v6.requests.CryptTextCreateRequest;
import com.example.demo_v6.requests.DecryptRequest;
import com.example.demo_v6.responses.CryptTextResponse;
import com.example.demo_v6.responses.DeCryptResponse;

@Service
public class  CryptTextService {
	private  CryptTextRepository cryptTextRepo;
	private UserService userService;
	
	public CryptTextService(CryptTextRepository cryptTextRepo,UserService userService) {
		this.cryptTextRepo = cryptTextRepo;
		this.userService = userService;
	}

	public List<CryptTextResponse> getAllCryptTexts( Optional<Integer> userId) {
		List <CryptText> list;
		 if(userId.isPresent()) {
			list = cryptTextRepo.findByUserUserId(userId.get());
		}
		
		list = cryptTextRepo.findAll();
		return list.stream().map((c) -> new CryptTextResponse(c)).collect(Collectors.toList());
	}

	public CryptText getOneCryptTextById(Integer textId) {
		CryptText cryptText = cryptTextRepo.findById(textId).orElse(null);
		CryptFunction cryptFunction = new CryptFunction();
		CryptText responsedCryptText = new CryptText();
		responsedCryptText.setCryptText(cryptFunction.DeCryptTexts(cryptText.getCryptText()));
		responsedCryptText.setTextId(textId);
		responsedCryptText.setUser(cryptText.getUser());
		return responsedCryptText;
	}

	public CryptText createOneRequest(CryptTextCreateRequest request) {
		User currUser = userService.getUserWithID(request.getUserId());
		CryptFunction cryptFunction = new CryptFunction();
		if(currUser != null) {
			CryptText commentToSave = new CryptText();
			commentToSave.setCryptText(cryptFunction.CreateCryptTexts(request.getText()));
			commentToSave.setUser(currUser);
			return cryptTextRepo.save(commentToSave);
		}else
		return null;
	}

	public DeCryptResponse getDecryptFunction(DecryptRequest request) {
		CryptFunction cryptFunction = new CryptFunction();
		String deCrypted = cryptFunction.DeCryptTexts(request.getText());
		DeCryptResponse deCryptResponse = new DeCryptResponse(deCrypted);
		
		return deCryptResponse;
	}

}
