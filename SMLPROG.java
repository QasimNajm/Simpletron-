import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class SMLPROG extends JFrame implements ActionListener
{	
	static final int READ=10, WRITE=11, LOAD=20, STORE=21, ADD=30, SUBTRACT=31, MULTIPLY=32, DIVIDE=33, HALT=43;
   
    private final JLabel a;
    private  final JLabel b;
	private final  JLabel c;
	private final  JLabel d;
	private  final JLabel e;
	private final JTextField f;
	private final JTextField g;
	private final JTextField h;
	private final JTextField i;
	private final JTextField j;
	private JTextField k;
	private final JTextArea area;
	private final JButton execute;
	int Accumulator=0;
	int InstCounter=0;
	int InstReg=0;
	int Opcode=0;
	int Operand=0;
	int mem []= new int [100];
	int index=0;
	public SMLPROG()
	 {
		super("Simulator");
		setLayout(new FlowLayout());
		

		a= new JLabel("Accumulator");
		add(a);
		f= new JTextField(Accumulator + "",5);
		add(f);
        f.setEditable(false); 
     
		b= new JLabel("InstCounter");
		add(b);
        g= new JTextField(InstCounter + "",5);
		add(g);
        g.setEditable(false); 
	 
		c= new JLabel("InstReg");
		add(c);
        h= new JTextField(InstReg + "",5);
		add(h);
        h.setEditable(false); 
		
		d= new JLabel("Opcode");
		add(d);
        i= new JTextField(Opcode + "",5);
		add(i);
        i.setEditable(false); 
		
		e= new JLabel("Operand");
		add(e);
        j= new JTextField(Operand + "",5);
		add(j);
        j.setEditable(false); 
		
	    k=new JTextField();
	 
		execute= new JButton("Execute next operation");
		add(execute);
		execute.setEnabled(true);
		execute.addActionListener( this );
	
		area = new JTextArea(30,20);
		add(area);
		area.setEditable(false);
	}
	public void executeCase()
	{
		InstReg=mem[InstCounter];
		Opcode=InstReg/100;
		Operand=InstReg%100;
		
		switch( Opcode)
		{
			case WRITE:
			int n=mem[Operand];
		    JOptionPane.showMessageDialog( null , "Value="+ n,"Writting",JOptionPane.PLAIN_MESSAGE);
			InstCounter++;
			break;
			
			case READ:
			String x=JOptionPane.showInputDialog("Input Integer");
			int v = Integer.parseInt(x);
			mem[Operand]=v;
			InstCounter++;
			break;
			
			case LOAD:
			Accumulator=mem[Operand];
			InstCounter++;
			break;
			
			case STORE:
			mem[Operand]=Accumulator;
			InstCounter++;
			break;
			
			case SUBTRACT:
			Accumulator -= mem[Operand];
			InstCounter++;
			break;

			case MULTIPLY:
			Accumulator*= mem[Operand];
			InstCounter++;
			break;
			
			case ADD:
			Accumulator+= mem[Operand];
			InstCounter++;
			
			case DIVIDE:
			Accumulator/=mem[Operand];
			InstCounter++;
			
			case HALT:
			execute.setEnabled( false );
			 JOptionPane.showMessageDialog( null , "Execution Ended","Execution Terminated",JOptionPane.PLAIN_MESSAGE);
			break;
			
		}
			}
			
			public String prepNumber(int number)
			{
				int count=0;
				int factor=1000;
				String output="";
				while(factor>=1)
				{
					output +=number/factor;
					number= number % factor;
					factor= factor/10;
				}
				return output;
				
			}
			
			public void dump()
			{
				area.setText( "        0       1        2      3" +"        4           5         6         7         8        9" +"\n" );
				
				
				for (int w=0; w<10; w++)
				{
					if (w==0)
						area.append(" "+w+" ");
					else 
						area.append((w*10)+" ");
					
					for( int x=0 ; x<10;x++)
						area.append(prepNumber(mem[w*10+x])+" ");
					
					
					area.append("\n");
				}
			}
			
			public void ReadProgram( String[] myArgs)
			{
				char program[]=new char[500];
		FileReader inputFile;
		try
		{
			inputFile = new FileReader(myArgs[0]);
		}
		catch(IOException ioEx)
		{
			JOptionPane.showMessageDialog(null,"Invalid File Name","Invalid File Name",JOptionPane.ERROR_MESSAGE);
			return;
		}
		int counter = 0;
		try
		{
			while(inputFile.read(program,counter,1) != -1)
				++counter;
		}
		catch(IOException ioEx)
		{
			JOptionPane.showMessageDialog(null,"File Read Error","Encountered An  Error While Reading File",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String stringToTokenize = new String(program);
		StringTokenizer tokens = new StringTokenizer(stringToTokenize,"\r\n\0",false);
		counter = 0;
		while(tokens.hasMoreTokens())
		{
				mem[counter++] = Integer.parseInt(tokens.nextToken());
		}
		for(int inst = 0; inst <counter;++inst){
		System.out.printf("\nInstruction[%d] = %d",inst,mem[inst]);}

	
	}  
public boolean validateNumber(int i)
{
	if ( i < 9999 && i < 9999)
		return false;
	else 
		return true;
}	


	@Override
		public void actionPerformed ( ActionEvent e )
		{ 
		if(e.getSource()==k &&index< 100){
			int temp=Integer.parseInt(k.getText());
			
			if (validateNumber(temp)==false)
			{
				mem[index++]= temp;
			}
			else 
			{
				if(validateNumber(temp)==false)
				{
					mem[Operand]=temp;
					
					execute.setEnabled(false);
				}
				else 
				{
					k.setText("");
					execute.setEnabled(false);
				}
			}
		}
		else if (e.getSource()== execute)
		{
			index++;
			executeCase();
		}
				
				
		f.setText(Accumulator + "");
		g.setText(InstCounter + "");
		h.setText(InstReg + "");
		i.setText(Opcode + "");
		j.setText(Operand + "");
		dump();
		}	
					
					
						
					
					
		}
	
	
