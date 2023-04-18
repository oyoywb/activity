package com.oywb.weixin.activities.dao;

import com.oywb.weixin.activities.entity.ResumeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Integer> {
    List<ResumeEntity> getAllByUserId(long userId);

    @Query(value = "select * from resume where online = 1 and if(?1 is not null, school=?1 ,1=1) " +
            "and if(?2 is not null, college=?2 ,1=1) " +
            "and if(?3 is not null, subject=?3 ,1=1) " +
            "and if(?4 is not null, grade=?4 ,1=1) ", nativeQuery = true)
    Page<ResumeEntity> getByFilter(String school, String college, String subject
            , String grade, Pageable pageable);

    @Query(value = "select rs.name,rs.grade,rs.college,rs.subject,rs.skill,rs.experience,rs.interest,rs.self_introduction,rs.online,rs.create_ts,rs.update_ts,rs.user_id,rs.avatar,rs.school,rd.id\tfrom resume rs, resume_delivery rd where rd.project_id=?1 and rd.pass =?2", nativeQuery = true)
    List<ResumeEntity> getResumeByProjectIdAndPass(long projectId, byte pass);
}
