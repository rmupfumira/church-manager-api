package za.org.rfm.service;


import za.org.rfm.entity.LogSheet;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface LogSheetsService {

    public void addLogSheet(LogSheet logSheet) throws Exception;


    public void refreshLogSheet() throws Exception ;
}
