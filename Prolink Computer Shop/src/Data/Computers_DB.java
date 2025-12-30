/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author TGPH
 */
public class Computers_DB implements IComputers {

    Computers computers;
    File file = new File("C:\\Users\\TGPH\\Documents\\NetBeansProjects\\GUI\\database\\Computers.txt");

    @Override
    public boolean insert(Computers computers) {

        try {
            FileWriter filewriter = new FileWriter(file, true);
            filewriter.write(computers.getId() + " , " + computers.getBrand() + " , " + computers.getProcessor() + " , " + computers.getRam() + " , " + computers.getIteam() + "\n");
            filewriter.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Computers_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Computers> view() {
        ArrayList<Computers> computerlist = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }
                // split by comma, optional spaces
                String arr[] = line.split("\\s*,\\s*");
                // arr[0], arr[1], arr[2], arr[3] safe now

                if (arr.length < 5) {
                    continue; // skip incomplete lines
                }

                Computers c = new Computers(
                        Integer.parseInt(arr[0].trim()), // ID
                        arr[1].trim(), // brand
                        arr[2].trim(), // processor
                        arr[3].trim(), // ram
                        arr[4].trim() // item
                );
                computerlist.add(c);
            }
            scan.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Computers_DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return computerlist;
    }

    @Override
    public ArrayList<Computers> search(String keyword) {
        ArrayList<Computers> list = new ArrayList<>();
        keyword = keyword.trim().toLowerCase();

        try (Scanner scan = new Scanner(file)) {

            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }
                String[] arr = line.split("\\s*,\\s*"); // split by comma, trim spaces
                if (arr.length < 5) {
                    continue; // skip incomplete lines
                }
                int id = Integer.parseInt(arr[0].trim());
                String brand = arr[1].trim();
                String processor = arr[2].trim();
                String ram = arr[3].trim();
                String item = arr[4].trim();

                // 🔥 SEARCH IN ALL FIELDS
                if (brand.toLowerCase().contains(keyword)
                        || processor.toLowerCase().contains(keyword)
                        || ram.toLowerCase().contains(keyword)
                        || item.toLowerCase().contains(keyword)) {
                    // Include ID when adding to search results
                    list.add(new Computers(id, brand, processor, ram, item));
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Computers_DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public boolean delete(int id) {

        ArrayList<Computers> list = view(); // get all current records
        boolean removed = false;

        // Loop through the list and remove the matching ID
        for (int i = 0; i < list.size(); i++) {
            Computers c = list.get(i);
            if (c.getId() == id) {
                list.remove(i);
                removed = true;
                break; // remove only the first match
            }
        }

        if (!removed) {
            return false; // nothing was deleted
        }
        // Write the remaining records back to the file
        try (FileWriter fw = new FileWriter(file)) {
            for (Computers c : list) {
                fw.write(c.getId() + " , " + c.getBrand() + " , " + c.getProcessor() + " , " + c.getRam() + " , " + c.getIteam() + "\n");
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Computers_DB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

   @Override
public boolean update(Computers updatedComputer) {

    ArrayList<Computers> list = view(); // read all records
    boolean found = false;

    try {
        FileWriter fw = new FileWriter(file); // rewrite file

        for (Computers c : list) {

            if (c.getId() == updatedComputer.getId()) {
                // write UPDATED record
                fw.write(
                    updatedComputer.getId() + " , " +
                    updatedComputer.getBrand() + " , " +
                    updatedComputer.getProcessor() + " , " +
                    updatedComputer.getRam() + " , " +
                    updatedComputer.getIteam() + "\n"
                );
                found = true;
            } else {
                // write OLD record
                fw.write(
                    c.getId() + " , " +
                    c.getBrand() + " , " +
                    c.getProcessor() + " , " +
                    c.getRam() + " , " +
                    c.getIteam() + "\n"
                );
            }
        }

        fw.close();
        return found;

    } catch (IOException ex) {
        Logger.getLogger(Computers_DB.class.getName()).log(Level.SEVERE, null, ex);
    }

    return false;
}

}
