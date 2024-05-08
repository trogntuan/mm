package com.member.management.repository;

import com.member.management.model.entity.Member;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, String>, JpaSpecificationExecutor<Member> {
    @Query("SELECT m.name FROM Member m WHERE m.id = ?1")
    String getNameById(String id);
}
