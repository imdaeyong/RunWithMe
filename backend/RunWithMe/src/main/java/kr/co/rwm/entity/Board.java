package kr.co.rwm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Integer boardId;
	
	@Column(name = "writer_id", nullable = false)
	private Integer writerId;
	
	@Column(name = "board_title", nullable = false)
	private String boardTitle;
	
	@Column(name = "board_content", nullable = false)
	private String boardContent;
	
	@Column(name = "board_regdate", nullable = false)
	private Date boardRegdate;
	
	@Column(name = "board_editdate", nullable = false)
	private Date boardEditdate;
	
	@Column(name = "read_count", nullable = false)
	private Integer readCount;
	
	
	
}
