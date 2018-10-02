package za.org.rfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/members/update")
    public ResponseEntity<String> refreshmembers(){
        try {
            logSheetsService.refreshLogSheet();
            return new ResponseEntity<>("LogSheet Saved", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Encountered an error");
        }
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getMembers(){
        try {
            List<Member> members = memberService.getAllMembers();
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Collections.EMPTY_LIST);
        }
    }
}
