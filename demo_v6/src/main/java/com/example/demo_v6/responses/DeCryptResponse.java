package com.example.demo_v6.responses;

import lombok.Data;

@Data
public class DeCryptResponse {
	String text;

	public DeCryptResponse(String text) {
		this.text = text;
	}

}
