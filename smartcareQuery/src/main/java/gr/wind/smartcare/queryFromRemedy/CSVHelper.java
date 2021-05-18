package gr.wind.smartcare.queryFromRemedy;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVHelper
{
    public static List<String[]> readAllAsList(String file) throws Exception
    {
        try {
            // Create an object of file reader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader).build();
            List<String[]> allData = csvReader.readAll();

            return allData;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public static List<Map<String,String>> readAllAsMap(String file) throws Exception
    {
        List<String[]> csvData = readAllAsList(file);
        List<Map<String,String>> outputMap = new ArrayList<Map<String,String>>();

        if (csvData.size()>=2)
        {
            String[] headers = csvData.get(0);

            for (int i_row = 1; i_row < csvData.size(); i_row++) {
                String[] row = csvData.get(i_row);
                Map<String, String> rowMap = new LinkedHashMap<String, String>();
                for (int i_col = 0; i_col < row.length; i_col++) {
                    rowMap.put(headers[i_col], row[i_col]);
                }
                outputMap.add(rowMap);
            }
        }

        return outputMap;
    }

    public static String getTabulatedString(List<String[]> csvData)
    {
        String outputTable = "";
        for (int i_row=0; i_row < csvData.size(); i_row++)
        {
            String lineData = "";
            for (int j_col=0; j_col < csvData.get(i_row).length; j_col++)
            {
                if (j_col < csvData.get(i_row).length-1)
                {
                    lineData += csvData.get(i_row)[j_col] + "\t";
                }
                else
                {
                    lineData += csvData.get(i_row)[j_col];
                }
            }
            if (i_row == 0)
            {
                outputTable = lineData;
            }
            else
            {
                outputTable += System.lineSeparator() + lineData;
            }
        }
        return outputTable;
    }
}
