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

	private static final String SESSION_STAGE = "stage";

	private static final int ASK_ALL_CLAIM_STAGE = 1;
	private static final int CLAIM_EXPLAINED_STAGE = 2;
	private static final int SESSION_END_STAGE = 3;

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
			if ((Integer) session.getAttribute(SESSION_STAGE) == CLAIM_EXPLAINED_STAGE) {
				return getClaimDetailsResponse(session);
			} else {
				PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
				outputSpeech.setText("Invalid Input");
				return SpeechletResponse.newTellResponse(outputSpeech);
			}
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
		StringBuilder speechOutputBuilder = new StringBuilder();
		String repromptText = "Please give a response!";
		try {
			claimDAO = getClaimInstace();
			HashMap<Integer, String> userClaimMap = claimDAO.fetchClaims();
			int numberOfClaims = userClaimMap.size();
			if (numberOfClaims != 0) {
				System.out.println("Number of Claims " + numberOfClaims);
				speechOutputBuilder.append(
						"You have " + "<say-as interpret-as=\"digits\">" + numberOfClaims + "</say-as> claims. ");
				Set setOfKeys = userClaimMap.keySet();
				Iterator iterator = setOfKeys.iterator();
				
				while (iterator.hasNext()) {
					int key = (Integer) iterator.next();
					String value = (String) userClaimMap.get(key);
					System.out.println("userClaimMap value " + value);
					String claimId = value.substring(0, 5) + "<say-as interpret-as=\"digits\">" + value.substring(5)
							+ "</say-as>";
					System.out.println("userClaimMap claimId " + claimId);
					speechOutputBuilder.append("<p>");
					speechOutputBuilder.append(key + " . " + claimId);
					speechOutputBuilder.append("</p>");
				}
				speechOutputBuilder.append("<p>");
				speechOutputBuilder.append("Please select a claim.");
				speechOutputBuilder.append("</p>");
				session.setAttribute("userMap", userClaimMap);
				session.setAttribute(SESSION_STAGE, ASK_ALL_CLAIM_STAGE);
			} else {
				PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
				outputSpeech.setText("You do not have any claims.");
				return SpeechletResponse.newTellResponse(outputSpeech);
			}
			System.out.println("OutPut Text Speech : " + speechOutputBuilder.toString());
			System.out.println("Completed getClaimDetailsResponse Function");
		} catch (Exception ex) {
			PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
			outputSpeech.setText("Sorry. Some error occured. Please try again.");
			return SpeechletResponse.newTellResponse(outputSpeech);
		}
		return newAskResponse("<speak>" + speechOutputBuilder.toString() + "</speak>", true, repromptText, false);
	}

	private SpeechletResponse getSelectedClaimResponse(Intent intent, Session session) {
		System.out.println("Starting getSelectedClaimResponse Function");
		StringBuilder speechOutputBuilder = new StringBuilder();
		String repromptText = "Please say yes or No!";
		try {
			if (session.getAttribute(SESSION_STAGE) != null && ((Integer) session.getAttribute(SESSION_STAGE) == ASK_ALL_CLAIM_STAGE || (Integer) session.getAttribute(SESSION_STAGE) == CLAIM_EXPLAINED_STAGE)) {
				HashMap<Integer, String> claimMap = (HashMap<Integer, String>) session.getAttribute("userMap");
				System.out.println("usreMap Size : " + claimMap.size());
				Slot selectionSlot = intent.getSlot("selection");
				System.out.println("selectionSlot value recieved is " + selectionSlot.getValue());
				String claimNumber = (String) claimMap.get(selectionSlot.getValue());
				System.out.println("claimNumber : " + claimNumber);
				if (claimNumber != null) {
					String claimDetails = claimDAO.fetchClaimDetails(claimNumber);
					speechOutputBuilder.append("<p>");
					speechOutputBuilder.append(claimDetails);
					speechOutputBuilder.append("</p>");
					speechOutputBuilder.append("<p>");
					speechOutputBuilder.append("Do you want to know about other Claim Details?");
					speechOutputBuilder.append("</p>");
					session.setAttribute(SESSION_STAGE, CLAIM_EXPLAINED_STAGE);
				} else {
					repromptText = "Please provide any input!";
					speechOutputBuilder.append("<p>");
					speechOutputBuilder.append(" Invalid Input Please provide a valid input ");
					speechOutputBuilder.append("</p>");
				}
				System.out.println("Completed getSelectedClaimResponse Function");
			} else {
				repromptText = "Please provide a correct option!";
				speechOutputBuilder.append("<p>");
				speechOutputBuilder.append("Invalid option. Please provide a correct option");
				speechOutputBuilder.append("</p>");
			}
		} catch (Exception ex) {
			PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
			outputSpeech.setText("Sorry. Some error occured. Please try again.");
			return SpeechletResponse.newTellResponse(outputSpeech);
		}
		return newAskResponse("<speak>" + speechOutputBuilder.toString() + "</speak>", true, repromptText, false);
	}

	private static ClaimDAO getClaimInstace() {
		System.out.println("Started getClaimInstace");
		if (claimDAO == null) {
			claimDAO = new ClaimDAO();
		}
		System.out.println("Completed getClaimInstace");
		return claimDAO;
	}

	private SpeechletResponse newAskResponse(String stringOutput, boolean isOutputSsml, String repromptText,
			boolean isRepromptSsml) {
		System.out.println("Processing newAskResponse function with inputs ,stringOutput [" + stringOutput
				+ "] isOutputSsml [" + isOutputSsml + "] repromptText [" + repromptText + "] isRepromptSsml["
				+ isRepromptSsml + "]");
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

}
