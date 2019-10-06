package wallet.storage;

import wallet.model.record.Budget;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BudgetStorage extends Storage<Budget> {
    public static final String DEFAULT_STORAGE_FILEPATH_BUDGET = "./data/budget.txt";

    /**
     * loads the list of Budget.
     * @return list of set budget.
     */
    @Override
    public ArrayList<Budget> loadFile() {
        ArrayList<Budget> budgetList = new ArrayList<>();

        try {
            RandomAccessFile raf = new RandomAccessFile(DEFAULT_STORAGE_FILEPATH_BUDGET, "r");
            String str;
            while (raf.getFilePointer() != raf.length()) {
                str = raf.readLine();
                String[] data = str.split(",");
                if (!data[0].isEmpty() && !data[1].isEmpty() && !data[2].isEmpty()) {
                    Budget budget = new Budget(Double.parseDouble(data[0]),
                            Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                    budgetList.add(budget);
                }
            }
            raf.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved expenses found.");
        } catch (IOException e) {
            System.out.println("End of file.");
        }

        return budgetList;
    }

    /**
     * Writes budget to file.
     * @param budget Budget object.
     */
    @Override
    public void writeToFile(Budget budget) {
        try {
            RandomAccessFile raf = new RandomAccessFile(DEFAULT_STORAGE_FILEPATH_BUDGET, "rws");
            raf.seek(raf.length());
            if (raf.getFilePointer() != 0) {
                raf.writeBytes("\r\n");
            }
            raf.writeBytes(budget.writeToFile());
            raf.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    /**
     * Updates Budget to file.
     * @param object  The object to be modified.
     * @param index The index of the object in the list.
     */
    @Override
    public void updateToFile(Budget object, int index) {
        //Blank
    }

    /**
     * Remove Budget from file.
     * @param objectList The list to update.
     * @param index The index of the object in the list to be deleted.
     */
    @Override
    public void removeFromFile(ArrayList<Budget> objectList, int index) {
        //Blank
    }

}