package za.org.rfm.dao;

import za.org.rfm.entity.LogSheet;

import java.util.List;

public interface LogSheetRepository {

    LogSheet addLogSheet(LogSheet logSheet);

    List<LogSheet> findLogSheetsByAssembly(Integer assemblyId, Integer limit);
}
