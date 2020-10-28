package kr.co.rwm.service;

import java.util.Collections;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.rwm.entity.User;
import kr.co.rwm.repo.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		System.out.println(userEmail);
		return userRepository.findByUserEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
	}

	@Transactional
	public Optional<User> findByUserEmail(String userEmail) {
		return userRepository.findByUserEmail(userEmail);
	}

	public User join(User user, String password) {
		return userRepository.save(User.builder().gugunId(user.getGugunId()).userEmail(user.getUserEmail())
				.userName(user.getUsername()).userPw(password).roles(Collections.singletonList("USER")).build());
	}

	public Optional<User> findByUserId(int userId) {
		return userRepository.findByUserId(userId);
	}

	@Transactional
	public void delete(String userEmail) {
		userRepository.deleteByUserEmail(userEmail);
	}

	public void update(Optional<User> user,User changeUser) {
		User temp = user.get();
		user.ifPresent(selectUser->{
			selectUser.setUserId(temp.getUserId());
			selectUser.setUserPw(changeUser.getChangePw());
			selectUser.setUserName(changeUser.getUsername());
			selectUser.setProfile(changeUser.getProfile());
			selectUser.setGugunId(changeUser.getGugunId());
			userRepository.save(selectUser);
		});
	}
}
