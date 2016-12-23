package com.example.alexa;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;

@Component
public class DefaultSpeechlet implements Speechlet {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultSpeechlet.class);
	@Autowired
	private Map<String, IntentSessionHandler> intentSessionhandlerMap;
	private static int INVALID_REQUEST_COUNT;
	private static final int SESSION_INVALIDATE = 10;

	@Override
	public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
		LOG.debug(intentRequest.getIntent().getName());
		final Intent intent = intentRequest.getIntent();

		if (intent == null) {
			throw new SpeechletException("No intent found");
		}
		String intentName = intentRequest.getIntent().getName();
		String stage = (String) session.getAttribute("SESSION_STAGE");
		String intentStage = intentName + "_" + stage;

		if (StringUtils.isNotEmpty(intentStage)
				&& intentSessionhandlerMap.containsKey(StringUtils.lowerCase(intentStage))) {
			IntentSessionHandler intentSession = intentSessionhandlerMap.get(StringUtils.lowerCase(intentStage));
			if (intentSession != null) {
				intentSession.onIntent(intentRequest, session);
			} else {
				INVALID_REQUEST_COUNT++;
				if (INVALID_REQUEST_COUNT >= SESSION_INVALIDATE) {

				}
			}
		} else {
			INVALID_REQUEST_COUNT++;
			if (INVALID_REQUEST_COUNT >= SESSION_INVALIDATE) {

			}
			throw new SpeechletException("No such intent session");
		}

		return null;
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
		return null;
	}

	@Override
	public void onSessionEnded(SessionEndedRequest arg0, Session arg1) throws SpeechletException {

	}

	@Override
	public void onSessionStarted(SessionStartedRequest arg0, Session arg1) throws SpeechletException {

	}

}
