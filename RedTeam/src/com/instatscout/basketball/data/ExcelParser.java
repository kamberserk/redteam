package com.instatscout.basketball.data;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.youngobject.redteam.Data2DB;
import com.youngobject.redteam.pojo.InstatTeam;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {
	
	
	public static final   String INSTAT_EXCEL_FILE_PATH = "/Users/kam/eclipse-workspace/RedTeam/src/com/instatscout/basketball/data/Team.xlsx";
	
	
	
	
    public void normalizeExcelFile() {
        try (FileInputStream fis = new FileInputStream(INSTAT_EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming you want the first sheet
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String cellValue = cleaner(getCellValueAsString(cell));
                    
                    cell.setCellValue(cellValue);
                    
                    // Process the cell value as a string
                  //  System.out.println(cellValue);
                }
            }
            
            
            try (FileOutputStream fos = new FileOutputStream(INSTAT_EXCEL_FILE_PATH)) {
                workbook.write(fos);
            }
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                // Format numeric values to string, consider adding locale-based formatting if necessary
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // Evaluate formula and convert result to string
                FormulaEvaluator formulaEvaluator = ((XSSFWorkbook) cell.getSheet().getWorkbook()).getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = formulaEvaluator.evaluate(cell);
                switch (cellValue.getCellType()) {
                    case STRING:
                        return cellValue.getStringValue();
                    case NUMERIC:
                        return String.valueOf(cellValue.getNumberValue());
                    case BOOLEAN:
                        return String.valueOf(cellValue.getBooleanValue());
                    default:
                        return "";
                }
            default:
                return "";
        }
    }

    
    
    
    
  public String  cleaner(String str) {
	  
	 str=  str.replaceAll("%", "");
	  
	  if (str.equals("")||str.equals("-"))
		  str="0.0";
	  
	 str=  str.replaceAll(",", ".");
	  
	 if (!str.contains(".")&str.length()<=3)
		 str=str+".0";
	 
	 
	  return str;
  }
  
  
  public static List<InstatTeam> parseExcelFile() {
      List<InstatTeam> teamList = new ArrayList<>();
      
     
      
      try (FileInputStream fis = new FileInputStream(INSTAT_EXCEL_FILE_PATH);
              Workbook workbook = new XSSFWorkbook(fis)) {

             Sheet sheet = workbook.getSheetAt(0);

             int counter =0;
             
             for (Row row : sheet) {
                 if (row.getRowNum() == 0) continue; // Skip header row

                 InstatTeam team = new InstatTeam();
                 team.setTeam(getCellStringValue(row.getCell(0)));
                 
             //   System.out.println( getCellStringValue(row.getCell(0)));
              //  System.out.println( getCellStringValue(row.getCell(1)));
                
                team.setGamesPlayed(Float.parseFloat(getCellStringValue(row.getCell(1))));
                team.setPossessions(Float.parseFloat(getCellStringValue(row.getCell(2))));
                team.setPoints(Float.parseFloat(getCellStringValue(row.getCell(3))));
                team.setPointsPerPossession(Float.parseFloat(getCellStringValue(row.getCell(4))));
                team.setFieldGoalsMade(Float.parseFloat(getCellStringValue(row.getCell(5))));
                team.setFieldGoalsAttempted(Float.parseFloat(getCellStringValue(row.getCell(6))));
                team.setFieldGoalsPercentage(Float.parseFloat(getCellStringValue(row.getCell(7))));
                team.setTwoPointFieldGoalsMade(Float.parseFloat(getCellStringValue(row.getCell(8))));
                team.setTwoPointFieldGoalsAttempted(Float.parseFloat(getCellStringValue(row.getCell(9))));
                team.setTwoPointFieldGoalsPercentage(Float.parseFloat(getCellStringValue(row.getCell(10))));
                team.setThreePointFieldGoalsMade(Float.parseFloat(getCellStringValue(row.getCell(11))));
                team.setThreePointFieldGoalsAttempted(Float.parseFloat(getCellStringValue(row.getCell(12))));
                team.setThreePointFieldGoalsPercentage(Float.parseFloat(getCellStringValue(row.getCell(13))));
                team.setFreeThrowsMade(Float.parseFloat(getCellStringValue(row.getCell(14))));
                team.setFreeThrowsAttempted(Float.parseFloat(getCellStringValue(row.getCell(15))));
                team.setFreeThrowsPercentage(Float.parseFloat(getCellStringValue(row.getCell(16))));
                team.setRebounds(Float.parseFloat(getCellStringValue(row.getCell(17))));
                team.setOffensiveRebounds(Float.parseFloat(getCellStringValue(row.getCell(18))));
                team.setDefensiveRebounds(Float.parseFloat(getCellStringValue(row.getCell(19))));
                team.setAssists(Float.parseFloat(getCellStringValue(row.getCell(20))));
                team.setSteals(Float.parseFloat(getCellStringValue(row.getCell(21))));
                team.setTurnovers(Float.parseFloat(getCellStringValue(row.getCell(22))));
                team.setBlocks(Float.parseFloat(getCellStringValue(row.getCell(23))));
                team.setFouls(Float.parseFloat(getCellStringValue(row.getCell(24))));
                team.setFoulsDrawn(Float.parseFloat(getCellStringValue(row.getCell(25))));
                team.setOffensiveRating(Float.parseFloat(getCellStringValue(row.getCell(26))));
                team.setDefensiveRating(Float.parseFloat(getCellStringValue(row.getCell(27))));
                team.setDefensiveEfficiency(Float.parseFloat(getCellStringValue(row.getCell(28))));
                team.setNetRating(Float.parseFloat(getCellStringValue(row.getCell(29))));
                team.setAssistsToTurnovers(Float.parseFloat(getCellStringValue(row.getCell(30))));
                team.setStealsToTurnovers(Float.parseFloat(getCellStringValue(row.getCell(31))));
                team.setDrawfoulRate(Float.parseFloat(getCellStringValue(row.getCell(32))));
                team.setEffectiveFieldGoalPercentage(Float.parseFloat(getCellStringValue(row.getCell(33))));
                team.setTrueShootingPercentage(Float.parseFloat(getCellStringValue(row.getCell(34))));
                team.setDeflections(Float.parseFloat(getCellStringValue(row.getCell(35))));
                team.setTransitionsMade(Float.parseFloat(getCellStringValue(row.getCell(36))));
                team.setTransitionsAttempted(Float.parseFloat(getCellStringValue(row.getCell(37))));
                team.setTransitionAttacksPercentage(Float.parseFloat(getCellStringValue(row.getCell(38))));
                team.setCatchAndShootMade(Float.parseFloat(getCellStringValue(row.getCell(39))));
                team.setCatchAndShootAttempted(Float.parseFloat(getCellStringValue(row.getCell(40))));
                team.setCatchAndShootShotsMadePercentage(Float.parseFloat(getCellStringValue(row.getCell(41))));
                team.setCatchAndDriveMade(Float.parseFloat(getCellStringValue(row.getCell(42))));
                team.setCatchAndDriveAttempted(Float.parseFloat(getCellStringValue(row.getCell(43))));
                team.setCatchAndDriveShotsMadePercentage(Float.parseFloat(getCellStringValue(row.getCell(44))));
                team.setScreensOffMade(Float.parseFloat(getCellStringValue(row.getCell(45))));
                team.setScreensOffAttempted(Float.parseFloat(getCellStringValue(row.getCell(46))));
                team.setScreensOffShotsPercentage(Float.parseFloat(getCellStringValue(row.getCell(47))));
                team.setPostsUpMade(Float.parseFloat(getCellStringValue(row.getCell(48))));
                team.setPostsUpAttempted(Float.parseFloat(getCellStringValue(row.getCell(49))));
                team.setPostsUpShotsPercentage(Float.parseFloat(getCellStringValue(row.getCell(50))));
                team.setIsolationsMade(Float.parseFloat(getCellStringValue(row.getCell(51))));
                team.setIsolationsAttempted(Float.parseFloat(getCellStringValue(row.getCell(52))));
                team.setIsolationShotsPercentage(Float.parseFloat(getCellStringValue(row.getCell(53))));
                team.setHandOffMade(Float.parseFloat(getCellStringValue(row.getCell(54))));
                team.setHandOffAttempted(Float.parseFloat(getCellStringValue(row.getCell(55))));
                team.setHandOffAttemptedPercentage(Float.parseFloat(getCellStringValue(row.getCell(56))));
                team.setCutsMade(Float.parseFloat(getCellStringValue(row.getCell(57))));
                team.setCutsAttempted(Float.parseFloat(getCellStringValue(row.getCell(58))));
                team.setCutsAttemptedPercentage(Float.parseFloat(getCellStringValue(row.getCell(59))));
                team.setPNRHandlersMade(Float.parseFloat(getCellStringValue(row.getCell(60))));
                team.setPNRHandlersAttempted(Float.parseFloat(getCellStringValue(row.getCell(61))));
                team.setPNRHandlersSuccessfulPercentage(Float.parseFloat(getCellStringValue(row.getCell(62))));
                team.setPNRRollersMade(Float.parseFloat(getCellStringValue(row.getCell(63))));
                team.setPNRRollersAttempted(Float.parseFloat(getCellStringValue(row.getCell(64))));
                team.setPNRRollersSuccessfulPercentage(Float.parseFloat(getCellStringValue(row.getCell(65))));
                team.setPNPMade(Float.parseFloat(getCellStringValue(row.getCell(66))));
                team.setPNPAttempted(Float.parseFloat(getCellStringValue(row.getCell(67))));
                team.setPNPPercentage(Float.parseFloat(getCellStringValue(row.getCell(68))));
                team.setDrivesMade(Float.parseFloat(getCellStringValue(row.getCell(69))));
                team.setDrivesWithShot(Float.parseFloat(getCellStringValue(row.getCell(70))));
                team.setDrivesPercentage(Float.parseFloat(getCellStringValue(row.getCell(71))));
                team.setUncontestedFieldGoalsMade(Float.parseFloat(getCellStringValue(row.getCell(72))));
                team.setUncontestedFieldGoals(Float.parseFloat(getCellStringValue(row.getCell(73))));
                team.setUncontestedFieldGoalsPercentage(Float.parseFloat(getCellStringValue(row.getCell(74))));
                team.setContestedFieldGoalsMade(Float.parseFloat(getCellStringValue(row.getCell(75))));
                team.setContestedFieldGoals(Float.parseFloat(getCellStringValue(row.getCell(76))));
                team.setContestedFieldGoalsPercentage(Float.parseFloat(getCellStringValue(row.getCell(77))));
                team.setPointsOffTurnovers(Float.parseFloat(getCellStringValue(row.getCell(78))));
                team.setDefensiveReboundsPercentage(Float.parseFloat(getCellStringValue(row.getCell(79))));
                team.setOffensiveReboundsPercentage(Float.parseFloat(getCellStringValue(row.getCell(80))));
                team.setTurnoverRatio(Float.parseFloat(getCellStringValue(row.getCell(81))));
                
            
                 teamList.add(team);
                 
               
                 
                 
             }

             // Now you have a list of InstatTeam objects populated with data
           //  System.out.println("Data successfully read from Excel.");
             // Process the list of teams as needed

         } catch (IOException e) {
             e.printStackTrace();
         }

      
      return teamList;
  }

  
  private static String getCellStringValue(Cell cell) {
      return cell == null ? "" : cell.getStringCellValue();
  }

  private static int getCellIntValue(Cell cell) {
      return cell == null ? 0 : (int) cell.getNumericCellValue();
  }

  private static float getCellFloatValue(Cell cell) {
      return cell == null ? 0 : (float) cell.getNumericCellValue();
  }
   
}
