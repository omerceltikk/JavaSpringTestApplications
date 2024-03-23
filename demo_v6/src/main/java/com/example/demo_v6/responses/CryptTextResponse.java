package com.example.demo_v6.responses;


import com.example.demo_v6.entities.CryptText;

import lombok.Data;

@Data
public class CryptTextResponse {
	Integer textId;
	String text;
	Long userId;
	
	public CryptTextResponse(CryptText entity) {
		this.textId = entity.getTextId();
		this.text = entity.getCryptText();
		this.userId = entity.getUser().getUserId();
	}
}


	
	

