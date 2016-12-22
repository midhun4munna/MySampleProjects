package claim;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;

import dao.ClaimDAO;

public class ClaimSpeechlet1 implements Speechlet {
	private static final Logger log = LoggerFactory.getLogger(ClaimSpeechlet1.class);

	private static ClaimDAO claimDAO;

	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
		System.out.println("onIntent requestId={, sessionId={}");
		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		if ("QuestionIntent".equals(intentName)) {
			return getClaimDetailsResponse(session);
		} else if ("SelectionIntent".equals(intentName)) {
			return getSelectedClaimResponse(intent, session);
		} else if ("YesIntent".equals(intentName)) {
			return getClaimDetailsResponse(session);
		} else if ("AMAZON.StopIntent".equals(intentName)) {
			PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
			outputSpeech.setText("Goodbye");
			return SpeechletResponse.newTellResponse(outputSpeech);
		} else {
			throw new SpeechletException("Invalid Intent");
		}
	}

	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		return getWelcomeResponse();
	}

	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
	}

	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
	}

	private SpeechletResponse getClaimDetailsResponse(Session session) {
		System.out.println("Starting getClaimDetailsResponse Function");
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		StringBuilder speechOutputBuilder = new StringBuilder();
		String repromptText =
				"With This Appointment application, we will know the appointments scheduled for your claims";
		try {
			claimDAO = getClaimInstace();
			HashMap<Integer, String> userClaimMap = claimDAO.fetchClaims();
			int numberOfClaims = userClaimMap.size();
			System.out.println("Number of Claims " + numberOfClaims);
			String speechText = "";
			speechOutputBuilder.append("You have " + numberOfClaims + " claims. ");
			Set setOfKeys = userClaimMap.keySet();
			Iterator iterator = setOfKeys.iterator();
			while (iterator.hasNext()) {
				int key = (Integer) iterator.next();
				String value = (String) userClaimMap.get(key);
				System.out.println("userClaimMap value " + value);
				speechOutputBuilder.append("<p>");
				speechOutputBuilder.append(key + " " + value);
				speechOutputBuilder.append("</p>");
			}
			speechText = speechOutputBuilder.toString();
			session.setAttribute("test", "testing Session");
			session.setAttribute("userMap", userClaimMap);
			System.out.println("OutPut Text Speech : " + speechText);
			speech.setText(speechText);
			System.out.println("Completed getClaimDetailsResponse Function");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newAskResponse("<speak>"+speechOutputBuilder.toString() +"</speak>", true, repromptText, false);
	}

	private SpeechletResponse getSelectedClaimResponse(Intent intent, Session session) {
		System.out.println("Starting getSelectedClaimResponse Function");
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		StringBuilder speechOutputBuilder = new StringBuilder();
		String repromptText =
				"With This Appointment application, we will know the appointments scheduled for your claims";
		try{
		HashMap<Integer, String> claimMap = (HashMap<Integer, String>) session.getAttribute("userMap");
		System.out.println("usreMap Size : "+claimMap.size());
		Slot selectionSlot = intent.getSlot("selection");
		System.out.println("selectionSlot value recieved is " + selectionSlot.getValue());
		int selection = Integer.parseInt(selectionSlot.getValue());
		String speechText = "";
		String claimNumber = (String) claimMap.get(selectionSlot.getValue());
		System.out.println("claimNumber : "+claimNumber);
		if (claimNumber != null || !claimNumber.equals("")) {
			String claimDetails = claimDAO.fetchClaimDetails(claimNumber);
			speechOutputBuilder.append("<p>");
			speechOutputBuilder.append(" " + claimDetails);
			speechOutputBuilder.append("</p>");
			speechOutputBuilder.append("<p>");
			speechOutputBuilder.append("Do you want to know about other Claim Details");
			speechOutputBuilder.append("</p>");
			speechOutputBuilder.append(" Session  content : " + session.getAttribute("test"));
		} else {
			speechOutputBuilder.append("<p>");
			speechOutputBuilder.append(" Invalid Input");
			speechOutputBuilder.append("</p>");
		}
		speechText = speechOutputBuilder.toString();

		speech.setText(speechText);
		System.out.println("Completed getSelectedClaimResponse Function");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newAskResponse("<speak>"+speechOutputBuilder.toString() +"</speak>", true, repromptText, false);
	}

	private SpeechletResponse getWelcomeResponse() {
		String speechText = "Welcome to the Claim Info App";

		SimpleCard card = new SimpleCard();
		card.setTitle("Welcome to the Claim Info App");
		card.setContent(speechText);

		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}

	private static ClaimDAO getClaimInstace() {
		System.out.println("Started getClaimInstace");
		if (claimDAO == null) {
			claimDAO = new ClaimDAO();
		}
		System.out.println("Completed getClaimInstace");
		return claimDAO;
	}
	
	private SpeechletResponse newAskResponse(String stringOutput, boolean isOutputSsml,
			String repromptText, boolean isRepromptSsml) {
		OutputSpeech outputSpeech, repromptOutputSpeech;
		if (isOutputSsml) {
			outputSpeech = new SsmlOutputSpeech();
			((SsmlOutputSpeech) outputSpeech).setSsml(stringOutput);
		} else {
			outputSpeech = new PlainTextOutputSpeech();
			((PlainTextOutputSpeech) outputSpeech).setText(stringOutput);
		}

		if (isRepromptSsml) {
			repromptOutputSpeech = new SsmlOutputSpeech();
			((SsmlOutputSpeech) repromptOutputSpeech).setSsml(repromptText);
		} else {
			repromptOutputSpeech = new PlainTextOutputSpeech();
			((PlainTextOutputSpeech) repromptOutputSpeech).setText(repromptText);
		}
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(repromptOutputSpeech);
		return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
	}

}
