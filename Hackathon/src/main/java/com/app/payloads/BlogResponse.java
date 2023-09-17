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
public class BlogResponse {

	private List<BlogDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalpages;
	private boolean lastpage;
}
