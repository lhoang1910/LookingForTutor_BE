package com.ltf.tutorservice.dto.request;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddImageRequest {
	@Lob
	private byte[] cccdImage;

	@Lob
	private byte[] studentCardImage;
}
