package za.org.rfm.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.util.StringUtils;
import za.org.rfm.entity.LogSheet;
import za.org.rfm.entity.Member;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SheetsServiceUtil {

    private static final String APPLICATION_NAME = "RFM-Sheets";

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = GoogleAuthorizeUtil.authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential).setApplicationName(APPLICATION_NAME).build();
    }

    public static ValueRange getValues(String spreadsheetId, String range) throws IOException,GeneralSecurityException {
        Sheets service = getSheetsService();
        // [START sheets_get_values]
        ValueRange result = service.spreadsheets().values().get(spreadsheetId, range).execute();
        int numRows = result.getValues() != null ? result.getValues().size() : 0;
        System.out.printf("%d rows retrieved.", numRows);
        // [END sheets_get_values]
        return result;
    }

    public static UpdateValuesResponse updateValues(String spreadsheetId, String range,
                                             String valueInputOption, List<List<Object>> _values)
            throws IOException,GeneralSecurityException {
        Sheets service = getSheetsService();
        // [START sheets_update_values]
        List<List<Object>> values = Arrays.asList(
                Arrays.asList(
                        // Cell values ...
                )
                // Additional rows ...
        );
        // [START_EXCLUDE silent]
        values = _values;
        // [END_EXCLUDE]
        ValueRange body = new ValueRange()
                .setValues(values);
        UpdateValuesResponse result =
                service.spreadsheets().values().update(spreadsheetId, range, body)
                        .setValueInputOption(valueInputOption)
                        .execute();
        System.out.printf("%d cells updated.", result.getUpdatedCells());
        // [END sheets_update_values]
        return result;
    }


    public static void updateCellsWithNewValues (int keyColumn,int columnToBeUpdated, Map<String,String> newValues, List<List<Object>> _values) {

        // Russel - present
        // Faith - absent

        Map<String, String> map = newValues;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            for (List<Object> rows : _values) {
                try {
                    Object keyCell = rows.get(keyColumn);
                    if (keyCell.toString().equalsIgnoreCase(entry.getKey())) {
                        rows.set(columnToBeUpdated-1, entry.getValue());
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("...error..."+e.getMessage());
                    break;
                }
            }
        }

    }

    public static UpdateValuesResponse updateAttendanceGoogleSheet(LogSheet logSheet, String SPREADSHEET_ID, String range) throws Exception {

        ValueRange spreadSheetData = getValues(SPREADSHEET_ID,range);;
        List<Object> headerColumn = spreadSheetData.getValues().get(0);

        int columnToBeUpdated = 0;
        int index = 0;
        int compareColumn = 2;

        for(Object header : headerColumn){
            index++;
            if(logSheet.getEventDate().equalsIgnoreCase(header.toString())){
                columnToBeUpdated = index;
            }
            if("Full Name".equalsIgnoreCase(header.toString().trim())){
                compareColumn = index-1;
            }
        }

        if(columnToBeUpdated > 0){

            //look for the name column




            updateCellsWithNewValues(compareColumn,columnToBeUpdated,logSheet.getAttendance(),spreadSheetData.getValues());

            return SheetsServiceUtil.updateValues(SPREADSHEET_ID,range,"RAW",spreadSheetData.getValues());

        }

        throw new Exception("Could not find column with corresponding date in GoogleSheet");

    }

    public static List<Member> getAllMembersFromSpreadSheet(String SPREADSHEET_ID) throws IOException,GeneralSecurityException{
        List<Member> members = new ArrayList<>();

        String range = "Attendance";

        ValueRange result = SheetsServiceUtil.getValues(SPREADSHEET_ID,range);


        List<List<Object>> values = result.getValues();
        int index = 0;
        Member member = null;
        Iterator<List<Object>> listIterator = values.iterator();
        while (listIterator.hasNext()){
            List<Object> memberRow = listIterator.next();
            if(index != 0){
                member = new Member();

                try {
                    if(!StringUtils.isEmpty( memberRow.get(2))){
                        member.setFullName(memberRow.get(2).toString());
                        member.setGender(memberRow.get(3).toString());
                        member.setPhone(memberRow.get(4).toString());
                        member.setNationality(memberRow.get(5).toString());
                        member.setMaritalStatus(memberRow.get(6).toString());
                        member.setDepartment(memberRow.get(7).toString());
                        member.setHomeChurch(memberRow.get(9).toString());
                        members.add(member);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds...continue!");
                }
            }
            index = index + 1;
        }

        return members;
    }

}
