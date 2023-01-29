/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphismwithfiling;

import java.util.Scanner;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author Usman_Aslam
 */



class Manager extends Employee
{
    String title;
    int c_dues;
    Manager()
    {
        title=" ";
        c_dues=0;
    }
    @Override
    void setter(Scanner obj)
    {
        super.setter(obj);
        System.out.print("ENTER TITLE: ");
        title=obj.nextLine();
        System.out.print("ENTER CLUB DUES: ");
        c_dues=obj.nextInt();
        obj.nextLine();
    }
    @Override
    void getter()
    {
        super.getter();
        System.out.println("TITLE: " + title);
        System.out.println("CLUB DUES: " + c_dues);
    }
    @Override
    void saveFile(FileWriter obj)
    {
       
        try
        {
            obj.write("m#");
            super.saveFile(obj);
             
            obj.write(title + "#");
            obj.write(c_dues + "#");
        }
        catch(IOException e)
        {
            System.out.println("Error Occurred ...!!");
        }
        
    }
    @Override
    void readFile(Scanner obj)
    {
        
        super.readFile(obj);
        
        
        title = obj.next();
        c_dues = obj.nextInt();
    }
    
}
class Scientist extends Employee
{
    String r_f;
    int nop;
    Scientist()
    {
        r_f=" ";
        nop=0;
    }
    @Override
    void setter(Scanner obj)
    {
        super.setter(obj);
        System.out.print("ENTER YOUR RESEARCH FIELD: ");
        r_f=obj.nextLine();
        System.out.print("ENTE NO OF PUBLICATION: ");
        nop=obj.nextInt();
        obj.nextLine();
            
    }
    @Override
    void getter()
    {
        super.getter();
        System.out.println("RESEARCH FIELD: " + r_f);
        System.out.println("NO OF PUB: " + nop);
    }
    @Override
    void saveFile(FileWriter obj)
    {
        
        try
        {
            obj.write("s#");
            super.saveFile(obj);
            
            obj.write(r_f + "#");
            obj.write(nop + "#");
        }
        catch(IOException e)
        {
            System.out.println("Error Occurred ...!!");
        }
        
    }
    @Override
    void readFile(Scanner obj)
    {
        super.readFile(obj);
        r_f = obj.next();
        nop = obj.nextInt();
    }
    
}

class Employee {
    String name;
    int e_no;
    Employee()
    {
        name = "";
        e_no = 0;
          
    }
    void setter(Scanner obj)
    {
        System.out.print("ENTER NAME OF EMPLOY: ");
        name=obj.nextLine();
        System.out.print("ENTER NUM OF EMPLOY: ");
        e_no=obj.nextInt();
        obj.nextLine();
        
    }
    void getter()
    {
        System.out.println("NAME: " + name);
        System.out.println("EMPLOYE NO: " + e_no);
    }
    void saveFile(FileWriter obj)
    {
        try
        {
            
            obj.write(name + "#");
            obj.write(e_no + "#");
        }
        catch(IOException e)
        {
            System.out.println("Error Occurred ...!!");
        }
        
    }
    void readFile(Scanner obj)
    {
        
        name = obj.next();
        e_no = obj.nextInt();
    }

    
    
}
public class PolymorphismWithFiling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner obj=new Scanner(System.in);
        
        File myfile = new File("polymorphism.txt");
        Employee[] emp = new Employee[10];
        int n = 0;
        while(true)
        {
            
            System.out.println("-----------(MENU)----------");
            System.out.println("1) Manager ");
            System.out.println("2) Scientist ");
            System.out.println("3) Save To File ");
            System.out.println("4) Read From File ");
            System.out.println("5) Display All Data ");
            System.out.println("6) Exit \n");
            
            System.out.print("Enter Your Choice: ");
            char ch = obj.next().charAt(0);
            obj.nextLine();
            
            switch(ch)
            {
                case '1':
                    emp[n] = new Manager();
                    emp[n].setter(obj);
                    n++;
                    break;
                case '2':
                    emp[n] = new Scientist();
                    emp[n].setter(obj);
                    n++;
                    break;
                case '3':
                    try
                    {
                        FileWriter f_wr = new FileWriter(myfile);
                        
                        f_wr.write(n + "#");
                        for(int i=0; i<n; i++)
                        {
                            emp[i].saveFile(f_wr);
                        }
                        f_wr.close();
                        System.out.println("Data is Successfully Wrote.\n");
                        
                    }
                    catch(IOException e)
                    {
                        System.out.println("Error Occurred ...!!");
                    }
                    break;
                case '4':
                    Scanner f_rd = new Scanner(myfile);
                    f_rd.useDelimiter("#");
                    n = f_rd.nextInt();
                    System.out.println(n);
                    
                    for(int i=0; i<n; i++)
                    {
                        char type = f_rd.next().charAt(0);
                        
                        switch(type)
                        {
                            case 'm':
                                emp[i] = new Manager();
                                emp[i].readFile(f_rd);
                                break;
                            case 's':
                                emp[i] = new Scientist();
                                emp[i].readFile(f_rd);
                                break;
                                
                        }
                        
                    }
                    f_rd.close();
                    System.out.println("Data is Successfully Read.\n");
                    break;
                case '5':
                    for(int i=0; i<n; i++)
                    {
                        emp[i].getter();
                        System.out.println();
                    }
                    break;
                case '6':
                    System.exit(-1);
                    
                    
            }
   
        }
       
        
    }
    
}
