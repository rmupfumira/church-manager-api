package za.org.rfm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import za.org.rfm.dao.LogSheetRepository;
import za.org.rfm.entity.Assembly;
import za.org.rfm.entity.LogSheet;
import za.org.rfm.entity.Member;
import za.org.rfm.entity.User;
import za.org.rfm.mail.Mail;
import za.org.rfm.service.AssemblyService;
import za.org.rfm.service.LogSheetsService;
import za.org.rfm.service.MailService;
import za.org.rfm.service.MemberService;
import za.org.rfm.utils.Constants;
import za.org.rfm.utils.SheetsServiceUtil;
import org.apache.velocity.app.VelocityEngine;

import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LogSheetsServiceImpl implements LogSheetsService {

    private static String SPREADSHEET_ID = "1FVcIACCeLqmUs_vzROnJ0Exk2btJ6Rsw7A4ch8j4ago";
    private static String SUBJECT_LOGSHEET_CREATED = "Logsheet Uploaded";

    String range = "Attendance Members2018";


    @Autowired
    private MailService mailService;

    @Autowired
    VelocityEngine velocityEngine;

    @Autowired
    MemberService memberService;

    @Autowired
    AssemblyService assemblyService;


    @Autowired
    LogSheetRepository logSheetRepository;

    private static final String CHARSET_UTF8 = "UTF-8";

    @Override
    public void addLogSheet(LogSheet logSheet) throws Exception{
        SheetsServiceUtil.updateAttendanceGoogleSheet(logSheet,SPREADSHEET_ID,range);
        logSheet.setAssemblyId(1);
        Assembly as = assemblyService.getAssemblyById(logSheet.getAssemblyId());
        logSheet.setAssembly(as);
        logSheetRepository.addLogSheet(logSheet);
        Assembly assembly = logSheet.getAssembly();
        assembly.setUsers(new ArrayList<>());
        User user = new User();
        user.setFullName("Russel");
        user.setEmailAddress("russel@rfm.org.za");
        assembly.getUsers().add(user);
/*
        User user2 = new User();
        user2.setFullName("Shelton");
        user2.setEmailAddress("shelton@premierlink.co.za");
        assembly.getUsers().add(user2);

        User user3 = new User();
        user3.setFullName("Tinarwo Chabuka");
        user3.setEmailAddress("tinachab.tc@gmail.com");
        assembly.getUsers().add(user3);*/
        if(assembly != null){
            if(assembly.getUsers() != null && !assembly.getUsers().isEmpty())
                for (User usr:
                        assembly.getUsers()) {
                    sendConfirmationEmail(logSheet,usr);
                }

        }

    }

    @Override
    public void refreshLogSheet(Integer id)throws GeneralSecurityException,Exception {
        List<Member> members = SheetsServiceUtil.getAllMembersFromSpreadSheet(SPREADSHEET_ID,range);

        memberService.updateMembersDataBase(members,id);
    }

    private void sendConfirmationEmail(final LogSheet logSheet, User user) {
        Mail mail = new Mail();
        mail.setMailFrom(Constants.EMAIL_FROM_ADDRESS);
        mail.setMailTo(user.getEmailAddress());
        mail.setMailSubject("New Logsheet Created: "+logSheet.getEventDate());

        Map< String, Object > model = new HashMap<>();
        model.put("eventDate", logSheet.getEventDate());
        model.put("name", user.getFullName());
        mail.setModel(model);
        String contentAsString = getContentFromTemplate(model);
        mailService.sendEmail(mail,contentAsString);
    }

    public String getContentFromTemplate(Map< String, Object > model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/templates/velocity/email-template.vm", model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
