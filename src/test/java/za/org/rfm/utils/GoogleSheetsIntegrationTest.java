package za.org.rfm.utils;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.org.rfm.entity.LogSheet;
import za.org.rfm.entity.Member;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import static org.junit.Assert.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)

public class GoogleSheetsIntegrationTest {

    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "1wKbphg-g0h_m8c3fR0siys9kJH3UfFLLJK3FOWTxyaU";

    @BeforeClass
    public static void setup() throws GeneralSecurityException, IOException {
        sheetsService = SheetsServiceUtil.getSheetsService();
    }

    @Test
    public void testReadSheet() throws IOException, GeneralSecurityException {

        String range = "C2:E3";

        ValueRange result = SheetsServiceUtil.getValues(SPREADSHEET_ID,range);
        int numRows = result.getValues() != null ? result.getValues().size() : 0;
        assertEquals(2,numRows);
        List<Object> firstRow = result.getValues().get(0);
        assertEquals("Angeline Milazi",firstRow.get(0));
        assertEquals("F",firstRow.get(1));
        assertEquals("0631582356",firstRow.get(2).toString().replace(" ",""));

        List<Object> secondRow = result.getValues().get(1);
        assertEquals("Asiphe Piedt",secondRow.get(0));
        assertEquals("F",secondRow.get(1));
        assertEquals("0783379346",secondRow.get(2).toString().replace(" ",""));
    }

    @Test
    public void testUpdateSheet() throws IOException, GeneralSecurityException,Exception {

        Map<String, String> people = new HashMap<>();

        people.put("Angeline Milazi",Constants.ABSENT);
        people.put("Avela Mthini",Constants.ABSENT);

        LogSheet logSheet = new LogSheet();
        logSheet.setEventDate("1/21/2018");
        logSheet.setAttendance(people);

        String range = "Attendance";

        UpdateValuesResponse response = SheetsServiceUtil.updateAttendanceGoogleSheet(logSheet,SPREADSHEET_ID,range);


       assertEquals(11852, response.getUpdatedCells().intValue());


    }


    @Test
    public void testUploadMembersFromGoogleSheet()throws IOException, GeneralSecurityException {
        String range = "Attendance";
        List<Member> members = SheetsServiceUtil.getAllMembersFromSpreadSheet(SPREADSHEET_ID, range);

        assertEquals(231,members.size());
    }
}
