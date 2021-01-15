package com.authorization.authentication.helpers;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.authorization.authentication.requests.emails.EmailRequest;
import com.authorization.authentication.responses.GeneralResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import org.springframework.beans.factory.annotation.Value;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Email;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class EmailService {

	@Value("${system-config.mailjet.apiKey}")
	private String apiKey;
	
	@Value("${system-config.mailjet.secretKey}")
    private String secretKey;
	
	@Autowired
	private Configuration config;

	GeneralResponse generalResponse = new GeneralResponse();
	
	public GeneralResponse sendEmail(EmailRequest emailRequest) throws MailjetException, MailjetSocketTimeoutException {
		try {

			MailjetClient client;
			MailjetRequest request;

			Map<String, Object> model = new HashMap<>();
			model.put("Name", emailRequest.getName());
			model.put("subject", emailRequest.getSubject());

			Template t = config.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			client = new MailjetClient(apiKey, secretKey);
			request = new MailjetRequest(Email.resource)
								.property(Email.FROMEMAIL, emailRequest.getFrom())
								.property(Email.FROMNAME, "Lanthanion Business")
								.property(Email.SUBJECT, emailRequest.getSubject())
								.property(Email.TEXTPART, emailRequest.getSubject())
								.property(Email.HTMLPART, html)
								.property(Email.RECIPIENTS, new JSONArray()
						.put(new JSONObject()
							.put("Email", emailRequest.getTo())))
								.property(Email.ATTACHMENTS, new JSONArray()
						.put(new JSONObject()
							.put("Content-type", "text/plain")
							.put("Filename", new ClassPathResource("logo.png"))
							.put("content", "VGhpcyBpcyB5b3VyIGF0dGFjaGVkIGZpbGUhISEK")));
			client.post(request);
			generalResponse.setMessage("mail send to : " + emailRequest.getTo());
			generalResponse.setStatus(200);
			return generalResponse;

		} catch ( IOException | TemplateException e) {
			generalResponse.setMessage("Mail Sending failure : "+e.getMessage());
			generalResponse.setStatus(500);
		}

		return generalResponse;
	}
}