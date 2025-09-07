package br.com.petpoints.back.features.user.repository;

import br.com.petpoints.back.features.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByEmailAndPassword(String email, String passowrd);
}
