package com.ceos20.instagram.user.repository;

import com.ceos20.instagram.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // id로 조회
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    // nickname으로 조회
    public Optional<User> findByNickname(String nickname) {
        List<User> userList = entityManager.createQuery(
                "SELECT u FROM User u " +
                "WHERE u.nickname = :nickname",
                User.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return userList.stream().findAny();
    }


    // 저장
    public void save(User user) {
        entityManager.persist(user);
    }

    // 삭제
    public void delete(User user) {
        entityManager.remove(user);
    }


}
