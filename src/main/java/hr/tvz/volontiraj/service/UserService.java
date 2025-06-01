package hr.tvz.volontiraj.service;

import hr.tvz.volontiraj.model.UserEntity;
import hr.tvz.volontiraj.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity update(Long id, UserEntity user) {
        UserEntity existing = findById(id);
        existing.setUsername(user.getUsername());
        existing.setPassword(user.getPassword());
        existing.setProfilePicturePath(user.getProfilePicturePath());
        return userRepository.save(existing);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
