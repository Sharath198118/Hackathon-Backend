package com.example.capstone.DTO;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class AddEvaluatorsDTO {
	@NotNull(message = "hackathon needed")
	private int hackathonId;
	@NotNull(message = "evaluators needed who are going to be assigned")
	List<EvaluatorDTO> evaluators;

	public int getHackathonId() {
		return hackathonId;
	}

	public void setHackathonId(int hackathonId) {
		this.hackathonId = hackathonId;
	}

	public List<EvaluatorDTO> getEvaluators() {
		return evaluators;
	}

	public void setEvaluators(List<EvaluatorDTO> evaluators) {
		this.evaluators = evaluators;
	}

}
