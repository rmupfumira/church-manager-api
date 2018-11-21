package za.org.rfm.service;


import za.org.rfm.entity.LogSheet;

import java.util.List;

public interface LogSheetsService {

    public void addLogSheet(LogSheet logSheet) throws Exception;


    public void refreshLogSheet(Integer id) throws Exception ;

    List<LogSheet> getLogSheets(Integer assemblyId,Integer limit);
}
