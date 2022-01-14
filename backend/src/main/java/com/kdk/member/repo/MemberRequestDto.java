package com.kdk.member.repo;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

  @Column(nullable = false)
  private String memberName;

  @Column(nullable = false)
  private String phoneNumber;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private Long age;

}
