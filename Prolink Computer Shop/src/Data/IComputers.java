/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Data;

import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 *
 * @author TGPH
 */
public interface IComputers {
    public abstract boolean insert(Computers computers);
    public abstract ArrayList<Computers> view();
    public abstract ArrayList<Computers> search(String keyword);
    public abstract boolean delete(int id);
    public abstract boolean update(Computers Computer);

}
