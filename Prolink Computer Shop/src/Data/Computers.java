/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

/**
 *
 * @author TGPH
 */
public class Computers {

  

   private int id;
   private String Brand;
   private String processor;
   private String ram;
   private String iteam;

    public Computers( int id, String Brand, String processor, String ram, String iteam) {
        this.id = id;
        this.Brand = Brand;
        this.processor = processor;
        this.ram = ram;
        this.iteam = iteam;
    }
    
    
    public int getId() { 
        return id; 
    }
    
    public String getBrand() {
        return Brand;
    }

    public String getProcessor() {
        return processor;
    }

    public String getRam() {
        return ram;
    }

    public String getIteam() {
        return iteam;
    }
   
   
}
