package com.vuthehuyht.userservice.repositories;

import com.vuthehuyht.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = "select * from token t where t.expired = false and t.user_id = :userId", nativeQuery = true)
    List<Token> findAllTokenByUserId(Long userId);
}
