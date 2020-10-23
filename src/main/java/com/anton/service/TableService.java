package com.anton.service;

import com.anton.model.Batch;
import com.anton.model.Reactor;
import com.anton.repository.CurrentPlanRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public class TableService {
    private final ReactorService reactorService = ReactorService.getSingleReactorService();
    private final CurrentPlanRepository currentPlanRepository;
    private final String resinType;

    public TableService(String workBookName, String sheetName, String resinType) throws IOException, ParseException {
        this.currentPlanRepository = new CurrentPlanRepository(workBookName,sheetName);
        this.resinType = resinType;
    }

    public void createATable() throws IOException, ParseException {
        Map<Reactor, ArrayList<Batch>> plan = currentPlanRepository.makePlanMap(resinType);
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("List1");
        int rowIndex = 0;
        for (Reactor reactor: plan.keySet()){
            Row row = sheet.createRow(++rowIndex);
            Cell cell = row.createCell(0);
            cell.setCellValue(reactor.getName());
            int cellIndex = 1;
            for(Batch batch: plan.get(reactor)){
                Cell resinCell = row.createCell(cellIndex++);
                resinCell.setCellValue(String.format("%s, %.2f тонн. начать в %s",batch.getResin().getName(),batch.getResin().getAmount(), batch.getDate().toInstant().toString().substring(11,16)));
            }
        }
        try (FileOutputStream fos = new FileOutputStream("D://Book.xlsx")) {
            workbook.write(fos);
        }
    }

}
