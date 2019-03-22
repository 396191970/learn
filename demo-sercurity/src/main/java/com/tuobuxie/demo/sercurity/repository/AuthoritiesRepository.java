package com.tuobuxie.demo.sercurity.repository;

import com.tuobuxie.demo.sercurity.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shaofeng Li
 */
@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,String> {


    Authorities findByUserId(String username);

}