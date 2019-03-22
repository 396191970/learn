package com.tuobuxie.demo.sercurity.repository;

import com.tuobuxie.demo.sercurity.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shaofeng Li
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,String> {


    Users findByUserId(String username);

}