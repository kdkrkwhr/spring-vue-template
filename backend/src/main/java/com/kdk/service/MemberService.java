package com.kdk.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kdk.member.repo.Member;
import com.kdk.member.repo.MemberRepository;
import com.kdk.member.repo.MemberRequestDto;
import com.kdk.member.repo.QMember;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class MemberService {

  static final Logger logger = LoggerFactory.getLogger(MemberService.class);

  @Autowired
  private MemberRepository repo;

  @Autowired
  private EntityManager em;

  static private QMember qMember = QMember.member;

  public List<Member> selectMemberList(String ageGroup) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    JPAQuery<Member> query = queryFactory.selectFrom(qMember);
    switch (ageGroup) {
      case "10":
        query.where(qMember.age.between(10, 19));
        break;
      case "20":
        query.where(qMember.age.between(20, 29));
        break;
      case "30":
        query.where(qMember.age.between(30, 39));
        break;
      case "40":
        query.where(qMember.age.between(40, 49));
        break;
      case "50":
        query.where(qMember.age.between(50, 59));
        break;
      default:
        break;
    }

    return query.fetch();
  }

  public long insertMember(MemberRequestDto member) {
    long resultCode = 0;

    if (repo.save(Member.builder().memberName(member.getMemberName())
        .phoneNumber(member.getPhoneNumber()).address(member.getAddress())
        .age(member.getAge()).build()).getIdx() != 0) {

      resultCode = 1;
    };

    return resultCode;
  }

  @Transactional
  public long updateMember(Long idx, MemberRequestDto member) {
    long resultCode = 0;
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    resultCode = queryFactory.update(qMember)
        .where(qMember.idx.eq(idx))
        .set(qMember.memberName, member.getMemberName())
        .set(qMember.phoneNumber, member.getPhoneNumber())
        .set(qMember.address, member.getAddress())
        .set(qMember.age, member.getAge())
        .set(qMember.modifiedDate, LocalDateTime.now())
        .execute();

    return resultCode;
  }

  @Transactional
  public long deleteMember(Long idx) {
    long resultCode = 0;
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    resultCode = queryFactory.delete(qMember).where(qMember.idx.eq(idx)).execute();

    return resultCode;
  }
}
