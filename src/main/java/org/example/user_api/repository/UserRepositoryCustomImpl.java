package org.example.user_api.repository;

import org.example.user_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<User> findUsersByRole(String role, Pageable pageable) {
        Query query = new Query();

        if (role != null && !role.isEmpty()) {
            query.addCriteria(Criteria.where("role").is(role));
        }

        long total = mongoTemplate.count(query, User.class);

        query.with(pageable);

        List<User> users = mongoTemplate.find(query, User.class);

        return new PageImpl<>(users, pageable, total);
    }

}
