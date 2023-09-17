package com.app.payloads;

import java.util.List;

import com.app.dto.BlogDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BlogUserCatResponse {
	private List<BlogDto> content;
}
