package kr.co.rwm.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Running implements Serializable {
	
	private static final long serialVersionUID = 18L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "running_id")
	private Integer runningId;
	
	@Column(name = "user_id", nullable = true)
	private Integer userId;
	
	@Column(name = "polyline", columnDefinition = "LONGTEXT")
	private String polyline;
	
	@Column(name = "start_time")
	private LocalDateTime startTime;
	
	@Column(name = "end_time")
	private LocalDateTime endTime;
	
	@Column(name = "accumulated_distance", nullable = false)
	private double accDistance;
	
	@Column(name = "accumulated_time", nullable = false)
	private Long accTime;
	
	@Column(name = "thumbnail", length = 300)
	private String thumbnail;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "running", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RunningArea> runningArea;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "running_id")
    private List<Record> record;
	
}