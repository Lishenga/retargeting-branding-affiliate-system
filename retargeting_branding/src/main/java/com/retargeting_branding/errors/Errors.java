package com.retargeting_branding.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Errors {

    private int status;
	private String message;
}