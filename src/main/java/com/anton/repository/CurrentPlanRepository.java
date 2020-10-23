package com.anton.repository;

import com.anton.model.Batch;
import com.anton.model.Reactor;
import com.anton.model.Resin;
import com.anton.service.ReactorService;
import com.anton.service.WarehouseService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CurrentPlanRepository {
    private final ReactorService reactorService = ReactorService.getSingleReactorService();
    private final WarehouseService warehouseService = new WarehouseService();
    private final ResinRepository resinRepository = new ResinRepository();
    private final ArrayList<Resin> planForTomorrow;
    private final HashMap<Reactor, ArrayList<Batch>> planMap= new HashMap<>();
    private final ArrayList<Resin> leftovers = warehouseService.getLeftovers();

    public CurrentPlanRepository(String filename, String sheetName) throws IOException, ParseException {
        this.planForTomorrow = getPlanForTomorrow(filename, sheetName);
    }

    public HashMap<Reactor, ArrayList<Batch>> makePlanMap(String resinType) throws IOException, ParseException {
        ArrayList<Resin> resinsWithoutLeftovers = getResinsWithoutLeftovers(getSpecificTypeOfResin(planForTomorrow, resinType));
        addRectorsToMap(resinType);
        for (Resin resin: resinsWithoutLeftovers) {
            int amountOfBatches = getAmountOfBatches(resin.getAmount(), resinRepository.getMaxValue(resin.getType()));
            double targetValue = getValueOfBatches(resin.getAmount(), amountOfBatches, resinRepository.getMinValue(resin.getType()));
            addLeftovers(amountOfBatches, targetValue, resin);
            while (amountOfBatches > 0) {
                for (Reactor reactor : planMap.keySet()) {
                    if (!reactor.isBusy()) {
                        planMap.get(reactor).add(new Batch(new Resin(targetValue, resin.getName(), resin.getType()),reactor));
                        if(planMap.get(reactor).size() > 1) {
                            planMap.get(reactor).get(planMap.get(reactor).size() - 1).setDate(Date.from(planMap.get(reactor).get(planMap.get(reactor).size() - 2).getDate().toInstant().plusSeconds(resin.getDurationInSeconds())));
                        }
                        reactor.setBusy(true);
                        amountOfBatches--;
                        break;
                    }
                }
                noOneIsBusy(planMap.keySet());
            }
        }
        warehouseService.saveLeftovers(leftovers);
        return planMap;
    }
    private boolean isAllReactorsAreBusy(Iterable<Reactor> reactors){
        for (Reactor reactor: reactors){
            if(!reactor.isBusy()){
                return false;
            }
        }
        return true;
    }
    private void noOneIsBusy(Iterable<Reactor> reactors){
        if(isAllReactorsAreBusy(reactors)) {
            for (Reactor reactor : reactors) {
                reactor.setBusy(false);
                if(reactor.isUnderMaintenance()) {
                    reactor.setBusy(true);
                }
            }
        }
    }

    private double getValueOfBatches(double amount, int numberOfBatches, double minValue){
        return Math.max(amount/numberOfBatches, minValue);
    }

    private int getAmountOfBatches(double amount, Double max){
        return (int) Math.ceil(amount / max);
    }

    private ArrayList<Resin> getPlanForTomorrow(String fileName, String sheetName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(fileName));
        XSSFSheet sheet = workbook.getSheet(sheetName);
        ArrayList<Resin> newPlan = new ArrayList<>();
        Row row = getTomorrowDate(sheet);
        if (row != null) {
            for(Cell cell: row) {
                if (cell.getColumnIndex() > 0 && row.getRowNum() > 0) {
                    String resinName = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
                    double amountToCook = cell.getNumericCellValue();
                    String resinType = resinRepository.getTypeOfResin(resinName);
                    newPlan.add(new Resin(amountToCook, resinName, resinType));
                }
            }
        }
        return newPlan;
    }

    private Row getTomorrowDate(XSSFSheet sheet){
        for (Row row : sheet) {
            if (row.getCell(0).getCellType() == CellType.NUMERIC) {
                Date cell = row.getCell(0).getDateCellValue();
                if (cell.after(new Date())) {
                    return row;
                }
            }
        }
        return null;
    }

    public ArrayList<Resin> getSpecificTypeOfResin(ArrayList<Resin> resins, String type){
        return (ArrayList<Resin>) resins.stream().filter(resin -> resin.getType().equals(type)).collect(Collectors.toList());
    }

    public ArrayList<Resin> getResinsWithoutLeftovers(ArrayList<Resin> totalAmount){
        for(Resin resin:totalAmount){
            for(Resin leftover: leftovers){
                if(leftover.getName().equals(resin.getName())){
                    resin.setAmount(resin.getAmount()-leftover.getAmount());
                }
            }
        }
        return totalAmount;
    }

    private void addLeftovers(int amountOfBatches, double targetValue, Resin resin){
        leftovers.add(new Resin(amountOfBatches*targetValue - resin.getAmount(),resin.getName(),resin.getType()));
    }

    private void addRectorsToMap(String resinType){
        for(Reactor reactor:reactorService.listOfSpecificReactors(resinType)) {
            planMap.put(reactor, new ArrayList<>());
        }
    }
}
