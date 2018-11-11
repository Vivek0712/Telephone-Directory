import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.sql.*;
public class MyCloudApp
{
	public static void main(String []args)
		{
			MyFrame f1=new MyFrame(); 
		}
}
class MyFrame extends JFrame
{
	JTabbedPane myTab=new JTabbedPane();
	JPanel disPanel,delPanel,updPanel,insPanel,searPanel;
	JTextField dist1,dist2,delt1,inst1,inst2,seart1,updt1,updt2;
	JButton disb1,disb2,delb1,insb1,searb1,updb1;
	JLabel disl1,disl2,dell1,insl1,insl2,searl1,updl1,updl2;
	Connection con;
	Statement st;
	ResultSet rs;
	MyFrame()
  		{
  		Toolkit kit=Toolkit.getDefaultToolkit();
  		Dimension size=kit.getScreenSize();
  		setSize((int)size.getWidth(),(int)size.getHeight());
  		setVisible(true);
  		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		designUi();
		makeConnection();
		          try{
                String qu="select * from telebook";
                System.out.println(qu);
                rs=st.executeQuery(qu);
                if(rs.first())
                {
                    dist1.setText(rs.getString(1));
                    dist2.setText(rs.getString(2));
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Data not found");
                }}
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
                disb1.addActionListener(new ActionListener (){
                    public void actionPerformed(ActionEvent ae)
                    {
                        try{
                        if(rs.next())
                        {
                            dist1.setText(rs.getString(1));
                            dist2.setText(rs.getString(2));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"last element");
                        }}
                        catch(Exception ex)
                        {
                            System.out.println(ex);
                        }
                    }
                });
                disb2.addActionListener(new ActionListener (){
                    public void actionPerformed(ActionEvent ae)
                    {
                        try{
                        if(rs.previous())
                        {
                            dist1.setText(rs.getString(1));
                            dist2.setText(rs.getString(2));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"1st element");
                        }}
                        catch(Exception ex)
                        {
                            System.out.println(ex);
                        }
                    }
                });
                searb1.addActionListener(new ActionListener (){
                    public void actionPerformed(ActionEvent ae)
                    {
                        try{
                        String reqno=seart1.getText();
                        String qu="select * from telebook where phno="+reqno+"";
                        ResultSet rs=st.executeQuery(qu);
                        if(rs.next())
                        {
                            JOptionPane.showMessageDialog(null,"Phone number:"+rs.getString(1)+"Name:"+rs.getString(2));
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Number does not exist");
                        }}
                        catch(Exception ex)
                        {
                            System.out.println(ex);
                        }
                    }
                });
                  delb1.addActionListener(new ActionListener (){
                    public void actionPerformed(ActionEvent ae)
                    {
                        try{
                        String reqno=delt1.getText();
                        String qu="delete from telebook where phno="+reqno+"";
                        int x=st.executeUpdate(qu);
                        if(x!=0)
                        {
                            JOptionPane.showMessageDialog(null,"Deletion Successful");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Deletion Failed");
                        }}
                        catch(Exception ex)
                        {
                            System.out.println(ex);
                        }
                    }
                });
                updb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String r1=updt1.getText();
				String r2=updt2.getText();
				String q2="update telebook set name='"+r2+"'where phno='"+r1+"'";
				try{
				int x=st.executeUpdate(q2);
				if(x!=0)
				{
					JOptionPane.showMessageDialog(null,"Updated Successfully.");	
				}
				else

				{
					JOptionPane.showMessageDialog(null,"Data not found.");
				}
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		insb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String r1=inst1.getText();
				String r2=inst2.getText();
				try{
				String q3="insert into telebook values('"+r1+"','"+r2+"')";
				int x=st.executeUpdate(q3);
				if(x!=0)
				{
					JOptionPane.showMessageDialog(null,"Inserted Successfully.");				
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Error in inserting.");
				}
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		}
	public void designUi()
	{
		disPanel=new JPanel();
		disl1=new JLabel("Phone number:");
		disPanel.add(disl1);
		dist1=new JTextField(10);
		disPanel.add(dist1);
		disl2=new JLabel("Name:");
		disPanel.add(disl2);
		dist2=new JTextField(10);
		disPanel.add(dist2);
		disb1=new JButton("next");
		disb2=new JButton("previous");
		disPanel.add(disb1);
		disPanel.add(disb2);
		myTab.add("Display",disPanel);
		delPanel=new JPanel();
		dell1=new JLabel("Phone number:");
		delPanel.add(dell1);
		delt1=new JTextField(10);
		delPanel.add(delt1);
		delb1=new JButton("delete");
		delPanel.add(delb1);
		myTab.add("Delete",delPanel);
		insPanel=new JPanel();
		insl1=new JLabel("Phone number:");
		insPanel.add(insl1);
		inst1=new JTextField(10);
		insPanel.add(inst1);
		insl2=new JLabel("Name:");
		insPanel.add(insl2);
		inst2=new JTextField(10);
		insPanel.add(inst2);
		insb1=new JButton("insert");
		insPanel.add(insb1);
		myTab.add("Insert",insPanel);
		searPanel=new JPanel();
		searl1=new JLabel("Phone number:");
                searPanel.add(searl1);
		seart1=new JTextField(10);
                searPanel.add(seart1);
		searb1=new JButton("search");
		searPanel.add(searb1);
		myTab.add("Search",searPanel);
		updPanel=new JPanel();
		updl1=new JLabel("Phone number:");
                updPanel.add(updl1);
		updt1=new JTextField(10);
                updPanel.add(updt1);
		updl2=new JLabel("Name:");
		updPanel.add(updl2);
                updt2=new JTextField(10);
                updPanel.add(updt2);
		updb1=new JButton("update");
		updPanel.add(updb1);
		myTab.add("Update",updPanel);
		add(myTab);
   }
	public void makeConnection()
	{
		String dbName = System.getProperty("cricket");
  String userName = System.getProperty("vivek");
  String password = System.getProperty("vivekraja");
  String hostname = System.getProperty("cricket.cw6qnnvp0bla.us-east-1.rds.amazonaws.com");
  String port = System.getProperty("3306");
  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
    port + "/" + dbName + "?user=" + userName + "&password=" + password;
		try
			{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(jdbcUrl);
			st=con.createStatement();
			}
		catch(Exception ex)
			{
			System.out.println(ex);
			}
	}
}
