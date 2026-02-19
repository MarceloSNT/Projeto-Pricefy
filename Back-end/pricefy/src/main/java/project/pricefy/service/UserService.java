package project.pricefy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.pricefy.dto.request.UsersRequestDto;
import project.pricefy.dto.response.UsersResponseDto;
import project.pricefy.exception.UsernameAlreadyExistsException;
import project.pricefy.model.UserModel;
import project.pricefy.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UsersResponseDto createUser(UsersRequestDto usersRequestDto) {
        UserModel user = new UserModel();

        if (userRepository.existsByDsEmail(usersRequestDto.dsEmail())){
            throw new UsernameAlreadyExistsException("Email já cadastrado");
        }

        if (userRepository.existsByUsername(usersRequestDto.username())){
            throw new UsernameAlreadyExistsException("Usuário já cadastrado");
        }

        user.setUsername(usersRequestDto.username());
        user.setDsEmail(usersRequestDto.dsEmail());
        user.setDsPassword(usersRequestDto.dsPassword());

        UserModel userSaved = userRepository.save(user);
        return new UsersResponseDto(
                userSaved.getIdUser(),
                userSaved.getUsername(),
                userSaved.getDsEmail(),
                userSaved.getDsPassword()
        );
    }

    @Transactional
    public Optional<UsersResponseDto> ListByIdUser(Long idUser) {
        return userRepository.findByIdUser(idUser)
                .map(user -> new UsersResponseDto(
                        user.getIdUser(),
                        user.getUsername(),
                        user.getDsEmail(),
                        user.getDsPassword()
                ));
    }

    @Transactional
    public Optional<UsersResponseDto> ListByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UsersResponseDto(
                        user.getIdUser(),
                        user.getUsername(),
                        user.getDsEmail(),
                        user.getDsPassword()
                ));
    }

    @Transactional
    public Optional<UsersResponseDto> ListByDsEmail(String dsEmail) {
        return userRepository.findByDsEmail(dsEmail)
                .map(user -> new UsersResponseDto(
                        user.getIdUser(),
                        user.getUsername(),
                        user.getDsEmail(),
                        user.getDsPassword()
                ));
    }
}
