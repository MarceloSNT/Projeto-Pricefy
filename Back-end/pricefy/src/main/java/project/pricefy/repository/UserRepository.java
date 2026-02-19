package project.pricefy.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.pricefy.model.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByIdUser (Long idUser);

    Optional<UserModel> findByDsEmail (String dsEmail);

    Optional<UserModel> findByUsername (String username);

    boolean existsByDsEmail(String dsEmail);

    boolean existsByUsername(String username);
}
