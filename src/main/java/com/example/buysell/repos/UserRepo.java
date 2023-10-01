package com.example.buysell.repos;

import com.example.buysell.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email); //для дальнейшей конфигурации security поиск user по userName
}