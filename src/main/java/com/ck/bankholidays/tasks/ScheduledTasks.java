package com.ck.bankholidays.tasks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.time.Duration;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ck.bankholidays.data.Holiday;
import com.ck.bankholidays.data.HolidayRepository;

@Component
public class ScheduledTasks {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

	@Autowired
	private HolidayRepository repository;
	
    @Scheduled(cron="0 0 0 * * *")
	public void syncEvents() {
        logger.info("Holiday sync in progress");

		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://www.gov.uk/bank-holidays.json")).GET().build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

				
			if (response.statusCode() == 200) {
				String result = response.body();
				JSONObject root = new JSONObject(result);
				JSONObject englandWalesJsonObject = root.getJSONObject("england-and-wales");

				englandWalesJsonObject.getJSONArray("events").forEach( events -> {
					
					JSONObject holidayJSONObject = (JSONObject)events;
					Date holidayDate = Date.valueOf(holidayJSONObject.getString("date"));
					if (repository.findByDate(holidayDate) == null) {
						repository.save(Holiday.newInstance(holidayDate, holidayJSONObject.getString("title")));
					}
				});	
			}
		}
		catch (Exception ex) {
			logger.error(ex.getLocalizedMessage(), ex);
		}


		logger.info("Holiday sync is done");
	}
}
