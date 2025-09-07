package br.com.petpoints.back.features.user.service;

import br.com.petpoints.back.features.user.forms.LoginForm;
import br.com.petpoints.back.features.user.forms.RegisterForm;
import br.com.petpoints.back.features.user.repository.UserRepository;
import br.com.petpoints.back.features.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String loginUser(LoginForm payload) {
        Optional<UserModel> user = userRepository.findByEmailAndPassword(payload.getEmail(), payload.getPassowrd());
        if (user.isEmpty())
            throw new RuntimeException("Usuário não encontrado!");
        return "Usuário encontrado" + user.get().getNome();
    }

    public String registerUser(RegisterForm payload) {
        UserModel newUser = new UserModel();
        newUser.setEmail(payload.getEmail());
        newUser.setEndereco(payload.getEndereco());
        newUser.setNome(payload.getNome());
        newUser.setPassword(payload.getPassword());
        newUser.setCpf(payload.getCpf());
        newUser.setDataNascimento(payload.getDataNascimento());
        userRepository.save(newUser);
        return "Usuário cadastrado!";
    }
}
