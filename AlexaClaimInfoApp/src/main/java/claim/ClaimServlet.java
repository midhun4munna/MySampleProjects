package claim;

import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.speechlet.servlet.SpeechletServlet;

@WebServlet("/ClaimServlet")
public class ClaimServlet extends  SpeechletServlet{
	private static final Logger log = LoggerFactory.getLogger(ClaimServlet.class);
	
	public ClaimServlet() {
		log.info("ClaimServlet Called");
	    this.setSpeechlet(new ClaimSpeechlet1());
	  }

}
