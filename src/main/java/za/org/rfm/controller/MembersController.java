package za.org.rfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.org.rfm.entity.Member;
import za.org.rfm.service.LogSheetsService;
import za.org.rfm.service.MemberService;

import java.util.Collections;
import java.util.List;


@RestController
public class MembersController {

    @Autowired
    LogSheetsService logSheetsService;

    @Autowired
    MemberService memberService;

    @PostMapping("/members/update/{id}")
    public ResponseEntity<String> refreshmembers( @PathVariable("id") Integer id){
        try {
            logSheetsService.refreshLogSheet(id);
            return new ResponseEntity<>("LogSheet Saved", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Encountered an error");
        }
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<List<Member>> getMembers( @PathVariable("id") Integer id){
        try {
            List<Member> members = memberService.getAllMembers(id);
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Collections.EMPTY_LIST);
        }
    }

    @PostMapping("/members/{id}")
    public ResponseEntity<Void> addMember(@RequestBody Member member, @PathVariable("id") Integer id, UriComponentsBuilder builder) {
        memberService.addMember(member,id);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("{id}").buildAndExpand(member.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
