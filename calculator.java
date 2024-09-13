//repeating dac
//answered
//double suffix
//ctrl C V
//result beyond borders
//input beyond borders
//frame size error
//continuous operation
//log ab
//Inspect if inputed

package calculator;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class calculator implements ActionListener,KeyListener
{
	
	int array = 16;
	JFrame frame;
	JTextField textfield,face;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[10];
	JButton[] tri = new JButton[array];
	JButton addButton,subButton,mulButton,divButton;
	JButton decButton, equButton, delButton, clrButton, negButton,always;
	JPanel panel;
	JButton sin, cos, tan, asin, acos, atan, squ, root,log,logab,pi,fac,rec,cub,cubr,per;
	JButton switchbtn;
	
	Font myFont = new Font("Impact",Font.PLAIN,35);
	Font newfont = new Font("",Font.PLAIN,24);
	
	int t,l,r,b;
	double num1=0,num2=0,result=0,prevnum2=0;
	boolean swi = true;
	boolean ontop = true;
	boolean resultDisplayed = false;
	boolean first = true;
	boolean operated = false;
	boolean inputed = false;
	String str,operator;
	
	calculator()
	{
		
		frame = new JFrame("functional calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(536,531); // error amount top:31 right,bottom,left:8
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.addKeyListener(this);
		frame.setFocusable(true);
		
		textfield = new JTextField("0");
		textfield.setBounds(10, 25, 300, 60);
		textfield.setFont(new Font("Monaco",Font.PLAIN,35));
		textfield.setBackground(Color.BLACK);
		textfield.setForeground(Color.YELLOW);
		textfield.setEditable(false);
		textfield.setFocusable(false);
		
		face = new JTextField("☺");//☹☻
		face.setBounds(520,25,190,140);
		face.setFont(new Font("",Font.PLAIN,100));
		face.setBackground(Color.BLACK);
		face.setForeground(Color.YELLOW);
		face.setBorder(BorderFactory.createEmptyBorder());
		face.setHorizontalAlignment(JTextField.CENTER);
		face.setEditable(false);
		face.setFocusable(false);
		
		class exceptionhandler implements Thread.UncaughtExceptionHandler 
		{
	        @Override
	        public void uncaughtException(Thread t, Throwable e) 
	        {
	        	textfield.setText("error");
	        	resultDisplayed = true;
	        	//e.printStackTrace();
	        	face.setText("☹");
	        }
		}
		Thread.setDefaultUncaughtExceptionHandler(new exceptionhandler());
		
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("×");
		divButton = new JButton("÷");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Del");
		clrButton = new JButton("AC");
		negButton = new JButton("+/-");
		always = new JButton("置頂");
		
		sin = new JButton("sin");
		cos = new JButton("cos");
		tan = new JButton("tan");
		asin = new JButton("sin⁻¹");
		acos = new JButton("cos⁻¹");
		atan = new JButton("tan⁻¹");
		squ = new JButton("x²");//³
		root = new JButton("√x");
		log = new JButton("log₁₀");
		logab = new JButton("logₓy");
		pi = new JButton("𝝿");
		fac = new JButton("x!");
		rec = new JButton("1/x");
		cub = new JButton("x³");
		cubr = new JButton("∛x");
		per = new JButton("C");
		
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		functionButtons[9] = always;
		
		tri[0] = sin;
		tri[1] = cos;
		tri[2] = tan;
		tri[3] = squ;
		tri[4] = asin;
		tri[5] = acos;
		tri[6] = atan;
		tri[7] = root;
		tri[8] = log;
		tri[9] = logab;
		tri[10] = pi;
		tri[11] = fac;
		tri[12] = rec;
		tri[13] = cub;
		tri[14] = cubr;
		tri[15] = per;
		
		for(int i =0;i<10;i++) 
		{
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setBackground(Color.BLACK);
			functionButtons[i].setForeground(Color.GREEN);
			functionButtons[i].setFocusable(false);
		}
		
		for(int i =0;i<10;i++) 
		{
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setBackground(Color.BLACK);
			numberButtons[i].setForeground(Color.GREEN);
			numberButtons[i].setFocusable(false);
		}
		for(int n = 0;n<array;n++)
		{
			tri[n].setFont(newfont);
			tri[n].setBackground(Color.BLACK);
			tri[n].setForeground(Color.GREEN);
			tri[n].addActionListener(this);
			tri[n].setFocusable(false);
			frame.add(tri[n]);
		}
		
		log.setForeground(Color.GREEN);
		logab.setForeground(Color.GREEN);
		negButton.setBounds(10,100,100,50);
		delButton.setBounds(110,100,100,50);
		clrButton.setBounds(210,100,100,50);
		always.setBounds(320,25,190,125);
		
		
		sin.setBounds(320,180,90,68);
		cos.setBounds(320,258,90,68);
		tan.setBounds(320,336,90,68);
		squ.setBounds(320,414,90,68);
		root.setBounds(420,414,90,68);
		asin.setBounds(420,180,90,68);
		acos.setBounds(420,258,90,68);
		atan.setBounds(420,336,90,68);
		log.setBounds(520,180,90,68);
		logab.setBounds(520,258,90,68);
		pi.setBounds(520,336,90,68);
		fac.setBounds(520,414,90,68);
		rec.setBounds(620,180,90,68);
		cub.setBounds(620,258,90,68);
		cubr.setBounds(620,336,90,68);
		per.setBounds(620,414,90,68);
		
		
		panel = new JPanel();
		panel.setBounds(10, 180, 300, 302);
		panel.setLayout(new GridLayout(4,4,10,10));
		panel.setBackground(Color.BLACK);
		
		always.setFont(new Font("NSimSun",Font.PLAIN,35));
		
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);
		
		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(always);
		frame.add(textfield);
		frame.add(face);
		
		frame.setVisible(true);
		frame.setResizable(false);
		Insets insets = frame.getInsets(); //fix frame size error
		t = insets.top;
		l = insets.left;
		r = insets.right;
		b = insets.bottom;
		frame.setSize(520+r+l,492+t+b);
	}
	
	public static void main(String[] args)
	{
		calculator calc = new calculator();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == always)
		{
		
			if(ontop == true)
			{
				frame.setAlwaysOnTop(true);
				ontop = false;
				always.setBackground(Color.RED);
				always.setText("已置頂");
			}
			else
			{
				frame.setAlwaysOnTop(false);
				ontop = true;
				always.setBackground(Color.BLACK);
				always.setText("置頂");
			}
		}
		for(int i=0;i<10;i++) 
		{
			
			if(e.getSource() == numberButtons[i])//bug
			{	
				if (resultDisplayed)
				{
	                textfield.setText("");
	                resultDisplayed = false;
	            }
				if(textfield.getText().equals("0"))
				{
					textfield.setText("");
				}
				if(textfield.getText().length()<=14)
				{
					clrButton.setText("C");
					textfield.setText(textfield.getText().concat(String.valueOf(i)));
				}
				inputed=true;
				face.setText("☺");
			}
		}
		if(e.getSource()==decButton)
		{	
			str = textfield.getText();
			if(str.indexOf('.')!= -1)
			{
				
			}
			else
			{
				textfield.setText(textfield.getText().concat("."));
			}
		}
		if(e.getSource()==addButton) 
		{
			operate("+");
		}
		if(e.getSource()==subButton) 
		{
			operate("-");
		}
		if(e.getSource()==mulButton) 
		{
			operate("*");
		}
		if(e.getSource()==divButton) 
		{
			operate("/");
		}
		if(e.getSource()==logab)
		{
			num1 = Double.parseDouble(textfield.getText());
			operator="loga";
			operated=true;
	        first=true;
	        resultDisplayed=true;
	        inputed=false;
		}
		if(e.getSource()==sin)
		{
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText(formatNumber(sin(num1)));
		}
		if(e.getSource()==cos)
		{
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText(formatNumber(cos(num1)));
		}
		if(e.getSource()==tan)
		{
			num1 = Double.parseDouble(textfield.getText());
			if(num1%90 == 0 && num1%180 != 0)
			{
				textfield.setText("error");
				resultDisplayed = true;
				face.setText("☹");
			}
			else
			{
				textfield.setText(formatNumber(tan(num1)));
			}
		}
		if(e.getSource()==squ)
		{
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText(formatNumber(squ(num1)));
		}
		if(e.getSource()==root)
		{
			num1 = Double.parseDouble(textfield.getText());
			if(num1<0)
			{
				textfield.setText("error");
				resultDisplayed = true;
				face.setText("☹");
			}
			else
			{
				textfield.setText(formatNumber(root(num1)));
			}
		}
		if(e.getSource()==asin)
		{
			num1 = Double.parseDouble(textfield.getText());
			if(num1>1 || num1<-1)
			{
				textfield.setText("error");
				resultDisplayed = true;
				face.setText("☹");
			}
			else
			{
				textfield.setText(formatNumber(asin(num1)));
			}
		}
		if(e.getSource()==acos)
		{
			num1 = Double.parseDouble(textfield.getText());
			if(num1>1 || num1<-1)
			{
				textfield.setText("error");
				resultDisplayed = true;
				face.setText("☹");
			}
			else
			{
				textfield.setText(formatNumber(acos(num1)));
			}
		}
		if(e.getSource()==atan)
		{
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText(formatNumber(atan(num1)));
		}
		if(e.getSource()==log)
		{
			num1 = Double.parseDouble(textfield.getText());
			if(num1<=0)
			{
				textfield.setText("error");
				resultDisplayed = true;
				face.setText("☹");
			}
			else
			{
				textfield.setText(formatNumber(log(num1)));
			}
		}
		if(e.getSource() == pi)
		{
			textfield.setText(String.valueOf(3.14159265358979));
		}
		if(e.getSource() == fac)
		{
			num1 = Double.parseDouble(textfield.getText());
			if(num1<0||num1%1!=0)
			{
				textfield.setText("error");
				resultDisplayed = true;
				face.setText("☹");
			}
			else
			{
				textfield.setText(formatNumber(factorial(num1)));
			}
		}
		if(e.getSource() == rec)
		{
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText(formatNumber(1/num1));
		}
		if(e.getSource()==cub)
		{
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText(formatNumber(Math.pow(num1,3)));
		}
		if(e.getSource()==cubr)
		{
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText(formatNumber(Math.pow(num1,1.0/3.0)));
		}
		if(e.getSource()==per)
		{
			num1 = Double.parseDouble(textfield.getText());
			operator="per";
			operated=true;
	        first=true;
	        resultDisplayed=true;
	        inputed=false;
		}
		if(e.getSource()==equButton) 
		{
			if(first == true)
			{
				num2=Double.parseDouble(textfield.getText());
				
				prevnum2 = num2;
				
				switch(operator) 
				{
				case"+":
					result=num1+num2;
					break;
				case"-":
					result=num1-num2;
					break;
				case"*":
					result=num1*num2;
					break;
				case"/":
					result=num1/num2;
					break;
				case"loga":
					if(num1<=0||num2<0||num1==1)
					{
						result=Double.valueOf("error");
						face.setText("☹");
					}
					else
					{
						result=(Math.log10(num2)/Math.log10(num1));
					}
					break;
				case"per":
					if(num1<0||num2<0||num1<num2)
					{
						result=Double.valueOf("error");
						face.setText("☹");
					}
					else
					{
						result=(factorial(num1)/(factorial(num2)*factorial(num1-num2)));
					}
					break;
				}
			}
			else
			{
				switch(operator)
				{
				case"+":
					result=num1+prevnum2;
					break;
				case"-":
					result=num1-prevnum2;
					break;
				case"*":
					result=num1*prevnum2;
					break;
				case"/":
					result=num1/prevnum2;
					break;	
				}
			}
			operated=false;
			textfield.setText((formatNumber(result)));
			num1=result;
			first = false;
		}
		if(e.getSource()==clrButton) 
		{	
			if(clrButton.getText().equals("C"))
			{
				clrButton.setText("AC");
			}
			else
			{
				num1=num2=prevnum2=0;
			}
			first = true;
			textfield.setText("0");
			inputed=false;
			face.setText("☺");
		}
		if(e.getSource()==delButton) 
		{
			if (textfield.getText().equals("0")) 
		    {
		        // do nothing
		    } 
		    else if (textfield.getText().length() == 1||textfield.getText().equals("error")) 
		    {
		        textfield.setText("0");
		    } 
		    else 
		    {
		        String string = textfield.getText();
		        textfield.setText("");
		        for (int i = 0; i < string.length() - 1; i++) 
		        {
		            textfield.setText(textfield.getText() + string.charAt(i));
		        }
		    }
		}
		if(e.getSource()==negButton) {
			double temp = Double.parseDouble(textfield.getText());
			temp*=-1;
			textfield.setText(formatNumber(temp));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
		for (int i = 0; i < 10; i++) 
		{
			if(e.getKeyChar() == Character.forDigit(i, 10)) 
			{
				if (resultDisplayed)
				{
	                textfield.setText("");
	                resultDisplayed = false;
	            }
				if(textfield.getText().equals("0"))
				{
					textfield.setText("");
				}
				if(textfield.getText().length()<=14)
				{
					clrButton.setText("C");
					textfield.setText(textfield.getText().concat(String.valueOf(i)));
				}
				inputed=true;
				face.setText("☺");
			}
		}
		if(e.getKeyChar() == '+') //ok
		{
			operate("+");
		}
		if(e.getKeyChar()== '-' ) //ok
		{
			operate("-");
		}
		//decimal point ................
		if(e.getKeyChar()== '*') //ok
		{
			operate("*");
		}
		if(e.getKeyChar()== '/') //ok
		{
			operate("/");
		}
		if(e.getKeyChar()== 10) //ok
		{	
			if(first == true)
			{
				num2=Double.parseDouble(textfield.getText());
				
				prevnum2 = num2;
				
				switch(operator) 
				{
				case"+":
					result=num1+num2;
					break;
				case"-":
					result=num1-num2;
					break;
				case"*":
					result=num1*num2;
					break;
				case"/":
					result=num1/num2;
					break;	
				case"loga":
					if(num1<=0||num2<0||num1==1)
					{
						result=Double.valueOf("error");
					}
					else
					{
						result=(Math.log10(num2)/Math.log10(num1));
					}
					break;
				case"per":
					if(num1<0||num2<0||num1<num2)
					{
						result=Double.valueOf("error");
						face.setText("☹");
					}
					else
					{
						result=(factorial(num1)/(factorial(num2)*factorial(num1-num2)));
					}
					break;
				}
			}
			else
			{
				switch(operator)
				{
				case"+":
					result=num1+prevnum2;
					break;
				case"-":
					result=num1-prevnum2;
					break;
				case"*":
					result=num1*prevnum2;
					break;
				case"/":
					result=num1/prevnum2;
					break;	
				}
			}
			resultDisplayed = true;
			operated=false;
			textfield.setText((formatNumber(result)));
			num1=result;
			first = false;
		}
		if(e.getKeyChar()=='.') //ok
		{
			str = textfield.getText();
			if(str.indexOf('.')!=-1)
			{
				
			}
			else
			{
				textfield.setText(textfield.getText().concat("."));
			}
		}
		if(e.getKeyChar()==8) //ok
		{
			if (textfield.getText().equals("0")) 
			{
			        
			} 
			else if (textfield.getText().length() == 1||textfield.getText().equals("error"))
			{
				
				textfield.setText("0");
			}
			else 
			{
			    String string = textfield.getText();
			    textfield.setText("");
			    for (int i = 0; i < string.length() - 1; i++) 
			    {
			    	textfield.setText(textfield.getText() + string.charAt(i));
			    }
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			if(swi == true)
			{
				frame.setSize(720+r+l,492+t+b);//200
				swi = false;
			}
			else
			{
				frame.setSize(520+r+l,492+t+b);
				swi = true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			if(clrButton.getText().equals("C"))
			{
				clrButton.setText("AC");
			}
			else
			{
				num1=num2=prevnum2=0;
			}
			first = true;
			textfield.setText("0");
			inputed=false;
			face.setText("☺");
		}
		if (e.getKeyCode() == KeyEvent.VK_C && e.getModifiers() == KeyEvent.CTRL_MASK) 
		{	
			StringSelection selection = new StringSelection(textfield.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null);
		}
		if (e.getKeyCode() == KeyEvent.VK_V && e.getModifiers() == KeyEvent.CTRL_MASK)
		{
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        
	        // Get the contents of the clipboard
	        Transferable contents = clipboard.getContents(null);
	        
	        // Check if the clipboard contains text
	        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor))
	        {
	            try 
	            {
	                String data = (String) contents.getTransferData(DataFlavor.stringFlavor);
	                if (data.matches("-?\\d+(\\.\\d+)?")) 
	                {
	                    // data is a number
	                    if(Double.parseDouble(data)%1==0)
	        			{
	        				textfield.setText(String.valueOf((long)Double.parseDouble(data)));
	        			}
	        			else
	        			{
	        				textfield.setText(String.valueOf(Double.parseDouble(data)));
	        			}

	                } 
	                else 
	                {
	                	
	                }
	            } 
	            catch (UnsupportedFlavorException | IOException ex) 
	            {
	                textfield.setText("error");
	            }
	        }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	double sin(double angle)
	{	  
		  float rad = (float)Math.sin(Math.toRadians(angle));
		  if(angle%180 == 0)
		  { 
			  return 0;
		  }
		  else
		  {
			  return rad;
		  }
	}
	
	double cos(double angle)
	{
		float rad = (float)Math.cos(Math.toRadians(angle));
		if(angle%180!=0&&angle%90 ==0)
		{
			return 0;
		}
		else
		{
			return rad;
		}
	}
	
	double tan(double angle)
	{	
		  float rad = (float) Math.tan(Math.toRadians(angle));
		  if(angle%180 == 0)
		  { 
			  return 0;
		  }
		  else
		  {
			  return rad;
		  }
	}
	
	double squ(double i)
	{
		return Math.pow(i,2);
	}
	
	double root(double i)
	{
		return Math.sqrt(i);
	}
	
	double asin(double L)
	{
		float angle = (float)Math.toDegrees((float)Math.asin(L));	
		return angle;
	}
	
	double acos(double L)
	{
		float angle = (float)Math.toDegrees((float)Math.acos(L));
		return angle;
	}
	
	double atan(double L)
	{
		float angle = (float)Math.toDegrees((float)Math.atan(L));
		return angle;
	}
	
	double log(double num12)
	{
		return (float)Math.log10(num12);
	}
	
	double factorial(double a)
	{	
		
	    if (a == 0 || a == 1) 
	    {
	        return (double) 1;
	    } 
	    else 
	    {
	        return (a * factorial(a - 1));
	    }
	}
	
	String check(String result)//solve double suffix
	{	
		
		if (result.endsWith(".0")) 
		{
			return result.substring(0, result.length() - 2);
		}
		else
		{
			return result;
		}
	}
	
	String formatNumber(double num) //solve result beyond borders
	{
		resultDisplayed=true;
	    String numString = check(Double.toString(num));
	    if(num<(-Double.MAX_VALUE)||num>Double.MAX_VALUE)
	    {
	    	return "overflow";
	    }
	    else
	    {
	    	if(numString.contains("E"))
	    	{
	    		return ifbeyond(numString);
	    	}
	    	else
	    	{
	    		if(numString.length() > 16)
	    		{
	    			return ifbeyond(String.format("%.15E", num));
	    		}
	    		else 
	    		{
	    			return numString;
	    		}
	    	}
	    }
	}
	String ifbeyond(String input) //simplify result
	{
	    
		while (input.length() > 16)
	    {
			int eIndex = input.indexOf("E");
	        if (eIndex > 0) 
	        {
	        	input = input.substring(0, eIndex-1) + input.substring(eIndex);
	        } 
	        else 
	        {
	        	input = input.substring(1);
	        }
	    }
	        return input;
	}
	void operate(String op)
	{
		if(inputed)
		{
			if (operated) 
			{
				switch(op)
				{
					case"+":
						num1 += Double.parseDouble(textfield.getText());
						break;
					case"-":
						num1 += Double.parseDouble(textfield.getText());
						break;
					case"*":
						num1 += Double.parseDouble(textfield.getText());
						break;
					case"/":
						num1 += Double.parseDouble(textfield.getText());
						break;
				} 
	            textfield.setText(formatNumber(num1));
	        } 
			else 
	        {
	            num1 = Double.parseDouble(textfield.getText());
	        }
		}
		operator = op;
        operated = true;
        first=true;
        resultDisplayed=true;
        inputed=false;
	}
}