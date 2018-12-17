import java.io.*;
import java.util.*;
import javax.swing.*;


public class Simpletron 
{	
	
	public static void main(String[] myArgs)
	{
		SMLPROG A=new SMLPROG();
		A.setSize(900,530);
		A.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		A.setVisible(true);
		A.ReadProgram(myArgs);
		A.dump();
		
		
}}
