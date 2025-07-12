package com.eypa.msa.product_microservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping("api/categories")
@Data
public class CategoryController {

	@Value("${app.testProp}")
	public String testProp;

	@GetMapping("test-prop")
	public String getTestProp() {
		return this.testProp;
	}

}
