package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	
	private String title;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd") //@InitBinder 대신 쓸 수 있다. 대신 binder어노테이션은 죽여야 된다.
	private Date dueDate;
}
