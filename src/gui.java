import javax.swing.*;  
import java.awt.*;
import java.awt.event.*; 

public class gui {  
	public static boolean isCanBattle = false;
	public static void main(String[] args) {  
		JFrame f=new JFrame("GUI");//creating instance of JFrame
		/* ImageIcon danau = new ImageIcon("danautoba.jpg");
		JLabel wallpaper = new JLabel(danau);
		wallpaper.setBounds(0, 0, 1280, 800);
		f.add(wallpaper);
 */
				  
		JButton b=new JButton("START");//creating instance of JButton  
		b.setBounds(500,200,100, 40);//x axis, y axis, width, height  
		
		JLabel welcome = new JLabel ("Willy Wangky and the Engimon Factory");
		welcome.setBounds(500, 50, 500, 200);
		
		
		
		b.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				f.setVisible(false);//making the frame visible 		

					
				/* public class MyCanvas extends Canvas{  
      
				public void paint(Graphics g) {  
			  
					//Toolkit t=Toolkit.getDefaultToolkit();  
					//Image i=t.getImage(""); 
					
					g.drawImage(i, 120,100,this);  
					  
				}   */
/* 				MyCanvas m=new MyCanvas();
 */				
				JFrame game = new JFrame("Game");
				
				/* ImageIcon pura = new ImageIcon("pura.jpg");
				JLabel bg = new JLabel(pura);
				bg.setBounds(0, 0, 1280, 800);
				game.add(bg); */
						
				final JTextField tf=new JTextField();  
				tf.setBounds(50,600, 300,20);  
				
				JButton w = new JButton("W");
				JButton a = new JButton("A");
				JButton s = new JButton("S");
				JButton d = new JButton("D");
				
				w.setBounds(700,500,50, 50);
				a.setBounds(650,550,50, 50);
				s.setBounds(700,600,50, 50);
				d.setBounds(750,550,50, 50);
				
				w.addActionListener( new ActionListener(){
					public void actionPerformed(ActionEvent e){
						isCanBattle = true;
						tf.setText("Player berjalan ke atas");
						
					}
				});
				a.addActionListener( new ActionListener(){
					public void actionPerformed(ActionEvent e){
						isCanBattle = false;
						tf.setText("Player berjalan ke kiri");
					}
				});
				s.addActionListener( new ActionListener(){
					public void actionPerformed(ActionEvent e){
						tf.setText("Player berjalan ke bawah");
					}
				});
				d.addActionListener( new ActionListener(){
					public void actionPerformed(ActionEvent e){
						tf.setText("Player berjalan ke kanan");
					}
				});
				game.add(w);
				game.add(a);
				game.add(s);
				game.add(d);
				
				game.add(tf);
				
				JButton battle = new JButton("Battle");
				battle.setBounds(900, 550, 100, 50);
				battle.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (!isCanBattle){
							tf.setText("There is not any engimon wild to battle!");
						}else{
							tf.setText("Congratulations....");
						}
					}
				});
				game.add(battle);
				
				JButton learn = new JButton("Learn");
				battle.setBounds(900, 550, 100, 50);
				battle.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (!isCanBattle){
							tf.setText("There is not any engimon wild to battle!");
						}else{
							tf.setText("Congratulations....");
						}
					}
				});
				game.add(battle);
						
				ImageIcon apikecil = new ImageIcon("apikecil(1).jpg");
				ImageIcon laut = new ImageIcon("laut.jpeg");
				ImageIcon gunung = new ImageIcon("gunung.jpg");
				ImageIcon tundra = new ImageIcon("tundra.jpg");
				ImageIcon rumput = new ImageIcon("grass.jpg");
				/* JLabel label = new JLabel(apikecil);
				label.setBounds(50, 50, 500, 500);
				
				game.add(label);  
				 */
				/* JLabel label1 = new JLabel(apikecil);
				JLabel label2 = new JLabel(apikecil);
				JLabel label3 = new JLabel(apikecil);
				JLabel label4 = new JLabel(apikecil);
				JLabel label5 = new JLabel(apikecil);
				JLabel label6 = new JLabel(apikecil);
				JLabel label7 = new JLabel(apikecil);
				JLabel label8 = new JLabel(apikecil);
				JLabel label9 = new JLabel(apikecil);
				
				label1.setBounds(50,50,10,10);
				label2.setBounds(60,50,10,10);
				label3.setBounds(70,50,10,10);
				label4.setBounds(50,60,10,10);
				label5.setBounds(60,60,10,10);
				label6.setBounds(70,60,10,10);
				label7.setBounds(50,70,10,10);
				label8.setBounds(60,70,10,10);
				label9.setBounds(70,70,10,10);
				
				game.add(label1);
				game.add(label2);
				game.add(label3);
				game.add(label4);
				game.add(label5);
				game.add(label6);
				game.add(label7);
				game.add(label8);
				game.add(label9); */
				
				JLabel[][] peta = new JLabel[30][30];
				for (int i = 0; i < 30; i++){
					for (int j = 0; j < 30; j++){
						if (i<15 && j < 15){
							peta[i][j] = new JLabel(laut);
						}else if (i<15){
							peta[i][j] = new JLabel(rumput);
						}else if (j >15){
							peta[i][j] = new JLabel(tundra);
						}else{
							peta[i][j] = new JLabel(gunung);
						}
							
						peta[i][j].setBounds(50+j*18, 50+i*18, 18, 18);
						game.add(peta[i][j]);
					}
				}
				
				JPanel inventory = new JPanel();
				inventory.setBounds(650,  50, 200, 300);
				inventory.setBackground(Color.green);
				
				JScrollPane scrollable = new JScrollPane(inventory);  
				scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
				scrollable.setBounds(650,  50, 200, 300);
				game.add(scrollable);  
				
				//game.add(inventory);
				
				
				
				
				
				game.setSize(1280,800);//400 width and 500 height  
				game.setLayout(null);//using no layout managers  
				game.setVisible(true);//making the frame visible  
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				game.setResizable(true);
			}  
		});  
		
		f.add(welcome);		  
		f.add(b);//adding button in JFrame  
		
/* 		f.setContentPanel
 */		f.setSize(1280,800);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}  
}  