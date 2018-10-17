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

    private static String SPREADSHEET_ID = "1FVcIACCeLqmUs_vzROnJ0Exk2btJ6Rsw7A4ch8j4ago";

    String range = "Attendance Members2018";

    @Autowired
    MemberService memberService;

    @Override
    public void addLogSheet(LogSheet logSheet) throws Exception{
        SheetsServiceUtil.updateAttendanceGoogleSheet(logSheet,SPREADSHEET_ID,range);
    }

    @Override
    public void refreshLogSheet(Integer id)throws GeneralSecurityException,Exception {
        List<Member> members = SheetsServiceUtil.getAllMembersFromSpreadSheet(SPREADSHEET_ID,range);

        memberService.updateMembersDataBase(members,id);
    }
}
