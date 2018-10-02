package za.org.rfm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.org.rfm.entity.LogSheet;
import za.org.rfm.entity.Member;
import za.org.rfm.utils.SheetsServiceUtil;

import java.security.GeneralSecurityException;
import java.util.List;


@Service
public class LogSheetsServiceImpl implements LogSheetsService {

    private static String SPREADSHEET_ID = "1wKbphg-g0h_m8c3fR0siys9kJH3UfFLLJK3FOWTxyaU";

    String range = "C9:BL245";

    @Autowired
    MemberService memberService;

    @Override
    public void addLogSheet(LogSheet logSheet) throws Exception{
        SheetsServiceUtil.updateAttendanceGoogleSheet(logSheet,SPREADSHEET_ID,range);
    }

    @Override
    public void refreshLogSheet()throws GeneralSecurityException,Exception {
        List<Member> members = SheetsServiceUtil.getAllMembersFromSpreadSheet(SPREADSHEET_ID);

        memberService.updateMembersDataBase(members);
    }
}
