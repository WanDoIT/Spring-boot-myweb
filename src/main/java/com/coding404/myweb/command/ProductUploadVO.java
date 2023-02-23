package com.coding404.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUploadVO {
	/*
	############################파일업로드 테이블############################
	CREATE TABLE PRODUCT_UPLOAD (
	   UPLOAD_NO INT PRIMARY KEY auto_increment,
	  FILENAME varchar(100) not null, ##실제파일명
	  FILEPATH varchar(100) not null, ##폴더명
	   UUID varchar(50) not null, ##UUID명
	  REGDATE TIMESTAMP default now(),
	   PROD_ID INT, ##FK
	   PROD_WRITER VARCHAR(20) ##FK
	);
	*/
	private int upload_no;
	private String filename;
	private String filepath;
	private String uuid;
	private LocalDateTime regdate;
	
	private int prod_id;
	private String prod_writer;
}
