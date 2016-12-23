package com.example.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.example.alexa.IntentSessionHandler;

@Component("yesintent_default")
public class YesIntentDefault implements IntentSessionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(YesIntentDefault.class);

		@Override
		public SpeechletResponse onIntent(IntentRequest request, Session session) {
			LOG.info("{} , {}", request, session);
			return null;
		}
	

}
