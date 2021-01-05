package com.company.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.util.Constants.*;


public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public void insertLine(String csvLine) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(CSV_FILE_NAME, true);
            fileWriter.append(csvLine);
            fileWriter.append(NEW_LINE_SEPARATOR);

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter!");
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing or flushing fileWriter!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> readCsv() {
        try {
            List<String> records = new ArrayList<>();
            try (BufferedReader bf = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
                String line;
                boolean firstLineFlag = false;
                while ((line = bf.readLine()) != null) {
                    if (!firstLineFlag) {
                        firstLineFlag = true;
                        continue;
                    }
                    records.add(line);
                }
            }
            return records;
        } catch (IOException e) {
            System.out.println("Error while reading CSV!!!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteLine(int employeeId) {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            br = new BufferedReader(new FileReader(CSV_FILE_NAME));
            sb = new StringBuilder();
            String line;
            boolean firstLineFlag = true;
            while ((line = br.readLine()) != null) {
                if (firstLineFlag) {
                    firstLineFlag = false;
                    sb.append(line);
                    sb.append(NEW_LINE_SEPARATOR);
                } else {
                    String[] lineValues = line.split(COMMA_DELIMITER);
                    if (Integer.parseInt(lineValues[0]) != employeeId) {
                        sb.append(line);
                        sb.append(NEW_LINE_SEPARATOR);
                    }
                }
            }
        } catch (Exception e) {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        writeAllInformationToDatabase(sb);
    }


    @Override
    public void updateLine(int employeeId, String employeeDetails) {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            br = new BufferedReader(new FileReader(CSV_FILE_NAME));
            sb = new StringBuilder();
            String line;
            boolean firstLineFlag = true;
            while ((line = br.readLine()) != null) {
                if (firstLineFlag) {
                    firstLineFlag = false;
                    sb.append(line);
                } else {
                    String[] lineValues = line.split(COMMA_DELIMITER);
                    try {
                        if (Integer.parseInt(lineValues[0]) == employeeId) {
                            sb.append(employeeDetails);
                        } else {
                            sb.append(line);
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
                sb.append(NEW_LINE_SEPARATOR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeAllInformationToDatabase(sb);
    }

    private void writeAllInformationToDatabase(StringBuilder sb) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(CSV_FILE_NAME);
            fw.write(sb.toString());
        } catch (Exception e) {
            System.out.println("There was an error while writing information after delete!");
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.flush();
                    fw.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}