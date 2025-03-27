package com.example.capstone.scheduler;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.capstone.Entity.HackathonStatus;
import com.example.capstone.Entity.Judge;
import com.example.capstone.Entity.Panelist;
import com.example.capstone.Entity.Status;
import com.example.capstone.Entity.Team;
import com.example.capstone.Repository.HackathonRepository;
import com.example.capstone.Service.MailService;
import com.google.api.client.util.Value;

@Component
public class HackathonScheduler {
	@Autowired
	private HackathonRepository hackathonRepository;

	@Value("${custom.feature.adminEmail}")
	private String adminEmail;
	
	@Value("${custom.feature.originTimeZone}")
	private String originTimeZone;

	@Value("${custom.feature.destinationTimeZone}")
	private String destinationTimeZone;

	@Autowired
	private MailService mailService;

	@Scheduled(fixedDelay = 60000) // Execute every minute (adjust as needed)
	public void updateHackathonStatus() {
		LocalDateTime now = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();

		System.out.println("checking hackathon status");

		hackathonRepository.findAll().forEach(hackathon -> {
			if (hackathon.getStartDate().isBefore(now) && hackathon.getHackathonStatus() == HackathonStatus.created
					&& hackathon.getPanelists().size() > 0 && hackathon.getJudges().size() > 0) {
				hackathon.setHackathonStatus(HackathonStatus.started);
			} else if (hackathon.getStartDate().isBefore(now)
					&& hackathon.getHackathonStatus() == HackathonStatus.created
					&& (hackathon.getPanelists().size() == 0 || hackathon.getJudges().size() == 0)) {
				hackathon.setHackathonStatus(HackathonStatus.cancelled);
			}
			hackathonRepository.save(hackathon);
		});
	}

	public String formatDate(LocalDateTime dateTime) {
		int year = dateTime.getYear();
		int month = dateTime.getMonthValue();
		int day = dateTime.getDayOfMonth();
		int hour = dateTime.getHour();
		int minute = dateTime.getMinute();
		int second = dateTime.getSecond();

		String secondString = (second < 10) ? "0" + second : String.valueOf(second);
		String minuteString = (minute < 10) ? "0" + minute : String.valueOf(minute);

		return day + "/" + month + "/" + year + "  " + hour + ":" + minuteString + ":" + secondString;
	}

	@Scheduled(fixedDelay = 60000 * 60 * 12)
	public void checkPanelistAssignedOrNot() {
		LocalDateTime now = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
		hackathonRepository.findAll().forEach(hackathon -> {
			if (now.isBefore(hackathon.getStartDate())) {
				if (hackathon.getPanelists().size() == 0) {
					String subject = "Panelists Not Assigned";
					String body = "Subject: Reminder: Assign Panelists for " + hackathon.getName() + "\r\n" + "\r\n"
							+ "Dear Admin,\r\n" + "\r\n" + "I hope this email finds you well.\r\n" + "\r\n"
							+ "As we prepare for the upcoming [Hackathon Name], we kindly remind you to assign panelists to participate in the shortlisting process. The panelists play a crucial role in evaluating the ideas of the participants.\r\n"
							+ "\r\n" + "Hackathon Details:\r\n" + "- Name: " + hackathon.getName() + "\r\n"
							+ "- Start Date:" + formatDate(hackathon.getStartDate()) + "\r\n" + "- End Date:"
							+ formatDate(hackathon.getReviewEndTime()) + "\r\n" + "\r\n"
							+ "Please ensure that the panelists cover a diverse range of expertise and backgrounds relevant to the hackathon theme. Their input will contribute significantly to the success of the event and the quality of the outcomes.\r\n"
							+ "\r\n"
							+ "Once you have finalized the selection of panelists, kindly update the hackathon details accordingly.\r\n"
							+ "\r\n" + "Thank you for your attention to this matter and your ongoing support in making "
							+ hackathon.getName() + " a success.\r\n" + "\r\n" + "Best regards,\r\n" + "HackerHub\r\n"
							+ "";
					mailService.sendEmail(adminEmail, body, subject);
				}
				if (hackathon.getJudges().size() == 0) {
					String subject = "Judges Not Assigned";
					String body = "Subject: Reminder: Assign Judges for " + hackathon.getName() + "\r\n" + "\r\n"
							+ "Dear Admin,\r\n" + "\r\n" + "I hope this email finds you well.\r\n" + "\r\n"
							+ "As we prepare for the upcoming" + hackathon.getName()
							+ ", we kindly remind you to assign judges to participate in the judging process. The panelists play a crucial role in evaluating the submissions and providing valuable feedback to the participants.\r\n"
							+ "\r\n" + "Hackathon Details:\r\n" + "- Name: " + hackathon.getName() + "\r\n"
							+ "- Start Date:" + formatDate(hackathon.getStartDate()) + "\r\n" + "- End Date:"
							+ formatDate(hackathon.getReviewEndTime()) + "\r\n" + "\r\n"
							+ "Please ensure that the Judges cover a diverse range of expertise and backgrounds relevant to the hackathon theme. Their input will contribute significantly to the success of the event and the quality of the outcomes.\r\n"
							+ "\r\n"
							+ "Once you have finalized the selection of panelists, kindly update the hackathon details accordingly.\r\n"
							+ "\r\n"
							+ "Thank you for your attention to this matter and your ongoing support in making [Hackathon Name] a success.\r\n"
							+ "\r\n" + "Best regards,\r\n" + "HackerHub\r\n" + "";
					mailService.sendEmail(adminEmail, body, subject);
				}
			}
		});
	}

	@Scheduled(fixedDelay = 60000 * 60 * 12)
	public void checkIdeasShortlisted() {
		LocalDateTime now = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
		hackathonRepository.findAll().forEach(hackathon -> {
			if (now.isAfter(hackathon.getIdeaSubmissionDeadline()) && now.isBefore(hackathon.getShortListDeadLine())) {
				int flag = 0;
				for (Panelist panelist : hackathon.getPanelists()) {
					for (Team team : panelist.getTeam()) {
						if (team.getStatus() == Status.submitted) {
							flag = 1;
							break;
						}
					}
					if (flag == 1) {
						String subject = "Reminder: Deadline Approaching - Please Shortlist Ideas for the Hackathon";
						String body = "Dear Panelist,\r\n" + "\r\n" + "I hope this email finds you well.\r\n" + "\r\n"
								+ "I am writing to remind you that the deadline for shortlisting ideas for the upcoming hackathon is fast approaching. Your input is crucial in selecting the most promising ideas to move forward with.\r\n"
								+ "\r\n"
								+ "Please take some time to review the submitted ideas and shortlist those you believe have the most potential for success. Your expertise and insight will greatly contribute to the success of our event.\r\n"
								+ "\r\n" + "The deadline for shortlisting ideas is "
								+ formatDate(hackathon.getShortListDeadLine())
								+ ". We appreciate your prompt attention to this matter.\r\n" + "\r\n"
								+ "Thank you for your participation and commitment to making this hackathon a success.\r\n"
								+ "\r\n" + "Best regards,\r\n" + "HackerHub";
						mailService.sendEmail(panelist.getUser().getEmail(), body, subject);
					}
				}
			}
		});
	}
	
    @Scheduled(fixedDelay = 60000*12*60)
	public void checkTeamsReviewed() {
		LocalDateTime now = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
		hackathonRepository.findAll().forEach(hackathon -> {
			if (hackathon.getReviewStartTime().isBefore(now) && hackathon.getReviewEndTime().isAfter(now)) {
				for (Judge judge : hackathon.getJudges()) {

					String subject = "Reminder- Deadline Approaching - Please review the implementations of Ideas";
					String body = "Dear Judges,\r\n" + "\r\n" + "I hope this email finds you well.\r\n" + "\r\n"
							+ "I am reaching out to inform you of the next stage in our hackathon process. We have now shortlisted the ideas submitted by participants, and it's time for the judging panel to review the implementations of these shortlisted ideas.\r\n"
							+ "\r\n"
							+ "Your role as a judge is critical in evaluating the implementations based on predefined criteria and selecting the winners of the hackathon.\r\n"
							+ "\r\n"
							+ "Please access the platform HackerHub to review the implementations assigned to you. If you have already completed your reviews, please disregard this message.\r\n"
							+ "\r\n"
							+ "Your timely review and feedback are essential in ensuring a fair and successful outcome for our hackathon participants.\r\n"
							+ "\r\n"
							+ "Thank you for your dedication and support in making this hackathon a memorable and impactful event.\r\n"
							+ "\r\n" + "Best regards,\r\n" + "HackerHub";
					mailService.sendEmail(judge.getUser().getEmail(), body, subject);
				}
			}
		});
	}

}
