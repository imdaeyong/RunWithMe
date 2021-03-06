package kr.co.rwm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "challenge_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChallengeUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "challenge_user_id")
	private Integer challengeUserId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "challenge_id")
	private Challenge challengeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User userId;
	
	@Column(name = "accumulated_distance")
	private double accDistance;
	
	@Column(name = "success")
	private boolean success;
	
	@Column(name = "donation")
	private Integer donation;
}
