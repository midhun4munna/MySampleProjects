package com.example.enums;

public enum Stages {

	DEFAULT                            ("default"),
	ASK_CLAIM                          ("claim_001"),
	CLAIM_EXPLAINED                    ("claim_002"),
	ASK_LOGIN_DETAILS                  ("faq_001"),
	ASK_FORGET_PASS_DETAILS            ("faq_002");
	

	private String stages;

	private Stages(String stage) {
		this.stages = stage;
	}

}
