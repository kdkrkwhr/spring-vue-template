package com.kdk.web;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.kdk.member.repo.MemberRequestDto;
import com.kdk.service.MemberService;
import io.swagger.annotations.Api;

@Api(value = "RestApiController")
@RestController
@RequestMapping("/api")
public class RestApiController {

  static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

  @Autowired
  private MemberService service;


  @RequestMapping(value = "/member/select", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<Object> selectMemberList()
      throws Exception {
    HashMap<String, Object > responseMp = new HashMap<String, Object>();

    responseMp.put("data", service.selectMemberList(""));

    return new ResponseEntity<>(responseMp, HttpStatus.OK);
  }

  @RequestMapping(value = "/member/select/{ageGroup}", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity<Object> selectMemberListGroup(@PathVariable("ageGroup") String ageGroup)
      throws Exception {
    HashMap<String, Object > responseMp = new HashMap<String, Object>();

    responseMp.put("data", service.selectMemberList(ageGroup));

    return new ResponseEntity<>(responseMp, HttpStatus.OK);
  }

  @RequestMapping(value = "/member/insert", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<Object> insertMember(@RequestBody MemberRequestDto member)
      throws Exception {
    HashMap<String, Object > responseMp = new HashMap<String, Object>();

    responseMp.put("resultCode", service.insertMember(member));

    return new ResponseEntity<>(responseMp, HttpStatus.OK);
  }

  @RequestMapping(value = "/member/update/{idx}", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity<Object> updateMember(@PathVariable("idx") String idx, @RequestBody MemberRequestDto member)
      throws Exception {
    HashMap<String, Object > responseMp = new HashMap<String, Object>();

    responseMp.put("resultCode", service.updateMember(Long.valueOf(idx), member));

    return new ResponseEntity<>(responseMp, HttpStatus.OK);
  }

  @RequestMapping(value = "/member/delete", method = RequestMethod.DELETE)
  public @ResponseBody ResponseEntity<Object> updateMember(String idx)
      throws Exception {
    HashMap<String, Object > responseMp = new HashMap<String, Object>();

    responseMp.put("resultCode", service.deleteMember(Long.valueOf(idx)));

    return new ResponseEntity<>(responseMp, HttpStatus.OK);
  }
}
