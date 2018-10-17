package za.org.rfm.service;


import za.org.rfm.entity.LogSheet;

public interface LogSheetsService {

    public void addLogSheet(LogSheet logSheet) throws Exception;


    public void refreshLogSheet(Integer id) throws Exception ;
}
