/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gaming;
import java.util.*;
/**
 *
 * @author Az'ulwazi
 */
public class Gaming {

    private static int i;
    @SuppressWarnings("empty-statement")
    public static void main(String [] args){
   String[] cities = {"City1 ","City2","City3"};
   String[] consoles = {"PS5","XBOX","SWITCH"};
   
   int[][] sales = {
       {1000,2000,3000},
       {2000,3000,4000},
       {1500,1100,1200}
        
       };
           
    System.out.println("PS5","XBOX","SWITCH","Total");
    System.out.println("----------------------");
   
   int maxSales= -1;
String topCity="";
for (int i = 0; i < sales length; i++){
int cityTotal = 0;
System.out.println(cities[i]);

for (int j = 0; j < sales[i].length; j++){
System.out.println(sales[i][j]);
cityTotal += sales[i][j];

System.out.print(cityTotal);

      if (cityTotal > maxSales) {
maxSales =cityTotal;
topCity = cities[i];
}
}
System.out.println("----------------------");
System.out.println("Top Performing City:" + topCity + maxSales +sales.);
}
}
    }