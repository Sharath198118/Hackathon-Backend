
package com.example.capstone.DTO;

import jakarta.validation.constraints.NotNull;

public class AddIdeaDTO {
	@NotNull(message="idea title should not be null")
	private String ideaTitle;
	@NotNull(message="idea body should not be null")
	private String ideaBody;
	@NotNull(message="idea domain should not be null")
	private String ideaDomain;

	public AddIdeaDTO() {

	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public String getIdeaBody() {
		return ideaBody;
	}

	public void setIdeaBody(String ideaBody) {
		this.ideaBody = ideaBody;
	}

	public String getIdeaDomain() {
		return ideaDomain;
	}

	public void setIdeaDomain(String ideaDomain) {
		this.ideaDomain = ideaDomain;
	}

}
