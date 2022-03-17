/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea3;

import java.awt.LayoutManager;
import java.awt.PopupMenu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.swing.JTextField;

import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * oficial 
 * @author Luisa 
 */

public class Graficar {
    
    public  String x_data[];
    public Double y_data[];
    public  String x_title, y_title;
    private String info_File;
    private int cont_Lineas =0;
    public ChartFactory cf1;
    public void leer(File file) throws IOException{
        
        StringBuilder sb = new StringBuilder();        
        if (file.exists() && file.canRead()){
            BufferedReader br = null;  
            try{
                br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
                String linea;
                while((linea = br.readLine() )!=null){
                    sb.append(linea + "\n");
                    cont_Lineas++;
                }               
            } catch(IOException ex){                
                ex.printStackTrace();
            } finally {
                if (br != null){
                    try {
                        br.close();
                    } catch(IOException e){
                        e.printStackTrace();                        
                    }
                }                
            }            
        }
        info_File = sb.toString();
    }    
    public void rellenar(){
        String lineas[]= info_File.split("\n");
        x_data = new String[lineas.length-1];
        y_data = new Double[lineas.length-1];
        String titulo[]= lineas[0].split(",");
        x_title =titulo[0];
        y_title = titulo[1];
        for (int i=1; i<lineas.length;i++){        
            String columnas[] = lineas[i].split(",");
            System.out.println(columnas.length); 
            x_data[i-1]= columnas[0];
            y_data[i-1] = Double.parseDouble(columnas[1]);            
        }              
    } 
    
    public ChartPanel graficar(String title){
        
       
      DefaultCategoryDataset cd = new DefaultCategoryDataset();
      
      for(int j = 0; j < x_data.length; j++){
      cd.addValue(y_data[j],x_data[j], x_data[j]);    
      }
      JFreeChart barChart = ChartFactory.createBarChart(title, x_title, y_title, cd);
      ChartPanel chartpanel = new  ChartPanel(barChart);
//      chartpanel.setVisible(true);
      chartpanel.setPreferredSize(new java.awt.Dimension(500, 500));
      
      return chartpanel;
      
      
      
            
    }
}
