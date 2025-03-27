package com.example.capstone.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.example.capstone.DTO.AddIdeaDTO;
import com.example.capstone.DTO.IdeaDetailsRequestDTO;
import com.example.capstone.DTO.TeamCreationDTO;
import com.example.capstone.Entity.Hackathon;
import com.example.capstone.Entity.HackathonStatus;
import com.example.capstone.Entity.Panelist;
import com.example.capstone.Entity.Participant;
import com.example.capstone.Entity.Role;
import com.example.capstone.Entity.Status;
import com.example.capstone.Entity.Team;
import com.example.capstone.Entity.User;
import com.example.capstone.Exceptions.ResourceNotFoundException;
import com.example.capstone.Exceptions.TeamNotFoundException;
import com.example.capstone.Exceptions.UnauthorizedException;
import com.example.capstone.Repository.TeamRepository;

import jakarta.transaction.Transactional;

@Service
public class TeamService {
	@Autowired
	private HackathonService hackathonService;
	@Autowired
	private UserService userService;
	@Autowired
	private ParticipantService participantService;
	@Autowired
	private PanelistService panelistService;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MailService mailService;

	@Value("${custom.feature.isDevelopment}")
    private boolean isDevelopment;

	
	@Value("${custom.feature.originTimeZone}")
	private String originTimeZone;

	@Value("${custom.feature.destinationTimeZone}")
	private String destinationTimeZone;

	

	
	// Create a new team for a hackathon
	// This method creates a new team with the given name and assigns the given user
	// as the team leader.
	// It also adds the team to the given hackathon and sets the team's panelist.
	
	@Transactional
	public void CreateTeam(int hackathonid, int userid, TeamCreationDTO teamCreationDTO) {
		if (isDevelopment || checkTimeBound(hackathonid)) {
			Hackathon hackathon = hackathonService.findHackathon(hackathonid);
			if(hackathon.getHackathonStatus()==HackathonStatus.started)
			{
			Team team = new Team();
			team.setName(teamCreationDTO.getName());
			team.setStatus(Status.registered);
			User leader = userService.findUserById(userid);
			teamRepository.save(team);
			Participant participantLeader = participantService.getParticipant(team, leader, hackathon, true);
			leader.setParticipants(participantLeader);
			leader.setAvailable(false);
			leader.setAssignedHackathon(hackathon.getHackathonId());
			List<User> users = userService.getUsers(teamCreationDTO.getEmails());
			List<Participant> participants = team.getParticipants();
			participants.add(participantLeader);
			for (User user : users) {
				Participant participant = participantService.getParticipant(team, user, hackathon, false);
				user.setAvailable(false);
				user.setAssignedHackathon(hackathon.getHackathonId());
				user.setParticipants(participant);
				participants.add(participant);
			}
			team.setParticipants(participants);
			if(hackathon.getPanelists().size()!=0)
			{
			int index = (team.getTeamId() + 1) % (hackathon.getPanelists().size());
			Panelist panelist = hackathon.getPanelists().get(index);
			team.setPanelist(panelist);
			panelist.setTeam(team);
			hackathon.setTeams(team);
			team.setHackathon(hackathon);
			userService.updateUsers(users);
			panelistService.updatePanelist(panelist);
			hackathonService.updateHackathon(hackathon);
			}
			else
			{
				throw new ResourceNotFoundException("Panelist not assigned to the hackathon");
			}
			}
			else if(hackathon.getHackathonStatus()==HackathonStatus.created)
			{
				throw new UnauthorizedException("Hackathon not started");
			}
			else
			{
				throw new UnauthorizedException("Hackathon is completed");
			}
		}
			
	}

	public boolean checkTimeBound(int hackathonId) {
		Hackathon hackathon = hackathonService.findHackathon(hackathonId);
		LocalDateTime currentTime = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
		if (currentTime.isBefore(hackathon.getStartDate())) {
			throw new UnauthorizedException("Hackathon is not Started");
		} else if (currentTime.isAfter(hackathon.getIdeaSubmissionDeadline())) {
			throw new UnauthorizedException("Team registration and idea submmission is closed");
		} else {
			return true;
		}
	}

	// Update the team details with the provided idea details
	// This method updates the team's name, idea title, idea body, and idea domain
	// with the provided
	// details. It only updates the team's details if the user is a leader of the
	// team and the team
	// is participating in the given hackathon.
	// If the user is not a leader, the method throws an UnauthorizedException.
	@Transactional
	public void updateTeamDetails(AddIdeaDTO teamUpdateDTO, int userId, int hackathonId) {
		if (isDevelopment|| checkTimeBound(hackathonId)) {
			User user = userService.getUser(userId);
			if(user.getRole().equals(Role.participant))
			{
			List<Participant> participants = user.getParticipants();
			if (participants != null && !participants.isEmpty()) {
				boolean flag = true;
				for (Participant participant : participants) {
					//System.out.println(hackathonId+"-----------------------------------------"+participant.getTeam().getHackathon().getHackathonId());
					if(participant.getTeam().getHackathon().getHackathonId()==hackathonId)
					{
					if (participant.isLeader()) {
						Team team = participant.getTeam();
						if (team.getHackathon() != null && team.getHackathon().getHackathonId().equals(hackathonId)) {
							team.setIdeaTitle(teamUpdateDTO.getIdeaTitle());
							team.setIdeaBody(teamUpdateDTO.getIdeaBody());
							team.setIdeaDomain(teamUpdateDTO.getIdeaDomain());
							team.setStatus(Status.submitted);
							teamRepository.save(team);
						}
					} else {
						throw new UnauthorizedException("Sorry, your not a leader to add Idea ");
					}
					flag=false;
					}
					
				}
				if(flag == true) {
						throw new ResourceNotFoundException("Team not found");
				}
			}
			}
			else
			{
				throw new UnauthorizedException("your not participant add idea");
			}
		}
	}
	public boolean checkTimeBoundForPanelist(int hackathonId) {
		Hackathon hackathon = hackathonService.findHackathon(hackathonId);
		LocalDateTime currentTime = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
		if (currentTime.isBefore(hackathon.getIdeaSubmissionDeadline())) {
			throw new UnauthorizedException("Team shortlisting not started is not Started");
		} else if (currentTime.isAfter(hackathon.getShortListDeadLine())) {
			throw new UnauthorizedException("Team shortlisting closed");
		} else {
			return true;
		}
	}
	// Reject a team by setting status of team as rejected.
	// This method sets the team's status to "rejected" and sends an email to all
	// team members
	// notifying them that their submission did not advance to the next phase.
	@Transactional
	public void rejectTeam(int id) {
		Team team = teamRepository.findById(id).get();
		if(isDevelopment || checkTimeBoundForPanelist(team.getHackathon().getHackathonId()))
		{
		if (team != null) {
			for (Participant participant : team.getParticipants()) {
				User user = participant.getUser();
				user.setAvailable(true);
				user.setAssignedHackathon(-1);
				String subject = "Update on Your Idea Submission";
				String body = "Dear Team,\r\n" + "\r\n"
						+ "After careful consideration and review, we regret to inform you that your recent idea submission for hackathon "+team.getHackathon().getName()+" did not advance to the next phase.\r\n"
						+ "\r\n"
						+ "We appreciate the effort and creativity you put into your proposal."+" Please do not be disheartened, as there will be more opportunities to showcase your innovative ideas in the future.\r\n"
						+ "\r\n" + "Thank you for your contribution and continued dedication.\r\n" + "\r\n"
						+ "Best regards,\r\n" + "Team HackerHub";
				mailService.sendEmail(user.getEmail(), body, subject);
			}
			team.setStatus(Status.rejected);
			teamRepository.save(team);
		}
		}
	}

	// Update the team status to selected.
	// This method sets the team's status to "selected" and sends an email to all
	// team members
	// notifying them that they have been selected to move forward to the next step.
	// If the team is not found, the method throws a TeamNotFoundException.
	public void selectTeamForNextStep(int teamId) {
		Team team = teamRepository.findById(teamId).get();
		if(isDevelopment || checkTimeBoundForPanelist(team.getHackathon().getHackathonId()))
		{
		if (team != null) {
			for (Participant participant : team.getParticipants()) {
				String email = participant.getUser().getEmail();
				String subject = "Congratulations! You've Advanced to the Next Step";
				String body = "Dear Team,\r\n"
						+ " \r\n"
						+ "I hope this email finds you well. First and foremost, we would like to extend our sincerest gratitude for your participation in our Hackathon "+team.getHackathon().getName()+" and for submitting your innovative idea. We are thrilled to inform you that your idea submission has caught the attention of our review panel and has been selected for the next phase of the competition.\r\n"
						+ " \r\n"
						+ "Your idea shows great potential, and we are excited to see how it can be developed further. As part of the next steps, we kindly ask you to start working on the implementation phase of your project. This is your opportunity to bring your idea to life and showcase its feasibility and the impact it can make.\r\n"
						+ " \r\n"
						+ "Submission Guidelines for Implementation Phase:\r\n"
						+ " \r\n"
						+ "Preparation: Develop your idea into a tangible project. This may involve coding, creating prototypes, or producing detailed plans that highlight how your idea can be realized.\r\n"
						+ "Submission Format: Please submit your implementation in the form of repository links (e.g., GitHub, Bitbucket) or drive links (e.g., Google Drive, Dropbox) where your project files and documentation can be accessed.\r\n"
						+ "Documentation: Ensure your submission includes a README file or equivalent documentation that explains your project, the problem it solves, how it works, and any setup or usage instructions.\r\n"
						+ "Deadline: "+formatDate(team.getHackathon().getImplementationSubmissionDeadLine())+". It's important that you adhere to this timeline to remain eligible for the final evaluation.\r\n"
						+ "Please login to the HackerHub website to submit your project implementation.\r\n"
						+ " \r\n"
						+ "We understand that developing a project for implementation can be challenging, but we encourage you to utilize this opportunity to innovate and impress. Should you have any questions or require further clarification, please do not hesitate to reach out to us at Contact Us on HackerHub website.\r\n"
						+ " \r\n"
						+ "We look forward to seeing your idea come to fruition and wish you the best of luck as you embark on this exciting phase of the Hackathon.\r\n"
						+ " \r\n"
						+ "Best regards,\r\n"
						+ "Team HackerHub.";
				mailService.sendEmail(email, body, subject);
			}
		} else {
			throw new TeamNotFoundException("Team not found");
		}
		team.setStatus(Status.selected);
		teamRepository.save(team);
	}
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
	// Get team details by its ID.
	public Team getTeam(int id) {
		return teamRepository.findById(id).get();
	}

	// Update a team
	public void updateTeam(Team team) {
		teamRepository.save(team);
	}

	// Handle file submission for a team's idea
	// This method checks if the user is a leader of a selected team for the given
	// hackathon.
	// If the user is a leader of a selected team, the method updates the team's
	// idea repository and files
	// and saves the updated team to the database.
	// If the user is not a leader or the team is not selected, the method throws an
	// UnauthorizedException.
	public String FileSubmission(int hackathonId, int userId, IdeaDetailsRequestDTO requestBody) {
		User user = userService.getUser(userId);
		List<Participant> participants = user.getParticipants();
		Hackathon hackathon = hackathonService.findHackathon(hackathonId);
		LocalDateTime currentTime = LocalDateTime.now().atZone(ZoneId.of("Africa/Abidjan"))
                                       .withZoneSameInstant(ZoneId.of("Asia/Kolkata"))
                                       .toLocalDateTime();
		if (isDevelopment || currentTime.isBefore(hackathon.getImplementationSubmissionDeadLine())
				&& currentTime.isAfter(hackathon.getShortListDeadLine())) {
			boolean flag = true;
			for (Participant participant : participants) {
 
				if (participant.isLeader()
						&& participant.getTeam().getHackathon().getHackathonId().equals(hackathonId)) {
					Team team = participant.getTeam();
					if (team.getStatus().equals(Status.selected)) {
						team.setIdeaRepo(requestBody.getIdeaRepo());
						team.setIdeaFiles(requestBody.getIdeaFiles());
						team.setStatus(Status.implemented);
						teamRepository.save(team);
						flag = false;
						return "Your idea files have been submitted.";
					} else if (team.getStatus().equals(Status.rejected)) {
						throw new UnauthorizedException("Your team is not selected, Better luck next time champ!");
					} else if (team.getStatus().equals(Status.submitted)) {
						throw new UnauthorizedException("You are already submitted");
					}
				}
			}
			if (flag == true) {
 
				throw new UnauthorizedException("Oops, You are not a leader!");
 
			}
			return "Idea files submitted successfully";
		} else if (currentTime.isBefore(hackathon.getShortListDeadLine())) {
			throw new UnauthorizedException("Implementation submission not started");
		} else {
			throw new UnauthorizedException("Implementation submission Ended");
		}
	}
	public String FindTeamNameById(int id)
	{
		Optional<Team> team=teamRepository.findById(id);
		if(team.isPresent())
		{
			return team.get().getName();
		}
		return null;
	}
}
