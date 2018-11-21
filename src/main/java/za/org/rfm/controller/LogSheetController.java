package za.org.rfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.org.rfm.entity.LogSheet;
import za.org.rfm.service.LogSheetsService;
import za.org.rfm.utils.Constants;
import za.org.rfm.utils.GeneralUtils;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class LogSheetController {


    @Autowired
    LogSheetsService logSheetsService;

    @PostMapping("/logsheets")
    public ResponseEntity<String> addLogSheet(@RequestBody LogSheet logSheet){
        try {
            if(logSheet.getEventDate() != null && !logSheet.getAttendance().isEmpty()) {
                logSheet.setEventDate(GeneralUtils.changeDateFormat(Constants.DATE_FORMAT_TXN,Constants.DATE_FORMAT_LOGSHEET,logSheet.getEventDate()));
                logSheetsService.addLogSheet(logSheet);
                return new ResponseEntity<>("LogSheet Saved", HttpStatus.OK);
            }
            else{
                return ResponseEntity.badRequest().body("Logsheet missing required fields");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Encountered an error");
        }
    }

    @RequestMapping(
        value = "/logsheets/{assemblyId}",
        params = {"limit"},
        method = GET
    )
    public ResponseEntity<?> getLogSheets(@PathVariable Integer assemblyId,@RequestParam Integer limit){

        List<LogSheet> logSheetList = logSheetsService.getLogSheets(assemblyId,limit);

        return new ResponseEntity<>(logSheetList, HttpStatus.OK);
    }
}
