package hr.tvz.volontiraj.service;

import hr.tvz.volontiraj.model.UserEntity;
import hr.tvz.volontiraj.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public interface UserService {


    public UserEntity findById(Long id);

    public UserEntity save(UserEntity user);

    public UserEntity update(Long id, UserEntity user);

    public void deleteById(Long id);
}
