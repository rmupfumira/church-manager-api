package za.org.rfm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import za.org.rfm.entity.LogSheet;
import za.org.rfm.entity.Member;
import za.org.rfm.utils.SheetsServiceUtil;
import org.apache.velocity.app.VelocityEngine;

import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LogSheetsServiceImpl implements LogSheetsService {

    private static String SPREADSHEET_ID = "1FVcIACCeLqmUs_vzROnJ0Exk2btJ6Rsw7A4ch8j4ago";
    private static String SUBJECT_LOGSHEET_CREATED = "Logsheet Uploaded";

    String range = "Attendance Members2018";

    @Autowired
    MemberService memberService;

    private static final String CHARSET_UTF8 = "UTF-8";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void addLogSheet(LogSheet logSheet) throws Exception{
        SheetsServiceUtil.updateAttendanceGoogleSheet(logSheet,SPREADSHEET_ID,range);
    }

    @Override
    public void refreshLogSheet(Integer id)throws GeneralSecurityException,Exception {
        List<Member> members = SheetsServiceUtil.getAllMembersFromSpreadSheet(SPREADSHEET_ID,range);

        memberService.updateMembersDataBase(members,id);
    }

    private void sendConfirmationEmail(final LogSheet logSheet) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo("russel@premierlink.co.za");
                message.setSubject(SUBJECT_LOGSHEET_CREATED);

                Map model = new HashMap<>();
                model.put("logSheet", logSheet);

                message.setText(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine
                        , "registration-confirmation.vm", CHARSET_UTF8, model), true);
            }
        };

        this.javaMailSender.send(preparator);
    }
}
