package FlightResrvationSystem;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ASHISH
 */

public class DisplayMgr implements DisplayMgrInterface
{  
    Screen1 s1;
    Screen2 s2;
    Screen3 s3;
    Screen4 s4;
    FRSManager mgr;
    DisplayMgr(FRSManager mgr)
    {
        this.mgr=mgr;
        s1=new Screen1(mgr);
        s2=new Screen2(mgr);
        s3=new Screen3(mgr);
        s4=new Screen4(mgr);
     }
    void addDateCombo()
    {
        String MONTH="OCT";
        String YEAR="2015";
        for(int i=1;i<=24;i++)
        {
            if (i<10)
            s1.jComboBox1.addItem(MONTH+" 0"+i+", "+YEAR);
            else
            s1.jComboBox1.addItem(MONTH+" "+i+", "+YEAR);
            
        }
    }
    void addCityCombo()
    {
        s1.jComboBox2.addItem("Delhi");
        s1.jComboBox2.addItem("Mumbai");
        s1.jComboBox2.addItem("Pune");
    }
    void showResult()
    {
        DefaultTableModel model=(DefaultTableModel) s2.jTable1.getModel();
        while(model.getRowCount()>0)
        {
            for(int j=0;j<model.getRowCount();j++)
                model.removeRow(j);
        }
        int n=mgr.ResCombo.size();
        for(int i=0;i<n;i++)
        {
            ComboFlight res=mgr.ResCombo.get(i);
            String[] data=new String[9];           
            data[0]=res.Source;
            
            data[1]=res.SpiceNo;
            
            data[2]=res.timeDSpice;
            data[3]=res.timeASpice;
            data[4]=String.valueOf(res.timeDelay);
            data[5]=res.via;
            data[6]=res.SilkNo;
            data[7]=res.timeDSilk;
            data[8]=res.timeASilk;
           
            model.addRow(data);
        }
    }
    void addPassengerCombo()
    {
        for(int i=1;i<=10;i++)
            s1.jComboBox3.addItem(i);
       
    }
    public void ShowScreen1()
    {
        
        s2.setVisible(false);
        s4.setVisible(false);
        s1.jComboBox1.removeAllItems();
        s1.jComboBox2.removeAllItems();
        s1.jComboBox3.removeAllItems();
        addDateCombo();
        addCityCombo();
        addPassengerCombo();
        s1.setVisible(true);
    }
    public void ShowScreen2()
    {
        s1.setVisible(false);
        s2.jLabel5.setText(mgr.SearchCity);
        s1.jLabel12.setVisible(false);
        mgr.ResCombo=mgr.sMgr.Combine(mgr.Spice, mgr.Silk, mgr.SearchCity, mgr.SearchDate);
        if(mgr.ResCombo.isEmpty())
        {
            s1.jLabel12.setVisible(true);
            mgr.dspMgr.ShowScreen1();
        }
        else
        {    
            showResult();
            s2.setVisible(true);
        }
    } 
    public void ShowScreen3()
    {
        s2.setVisible(false);
        ComboFlight res=mgr.ResCombo.get(mgr.selectedResult);
        s3.jLabel9.setText(mgr.passNum+"");
        s3.jLabel4.setText(res.SpiceNo);
        s3.jLabel5.setText(res.dateSpice);
        s3.jLabel7.setText(res.SilkNo);
        s3.jLabel8.setText(res.dateSilk);
        s3.setVisible(true);
    }
    public void ShowScreen4()
    {
        s1.jLabel12.setVisible(false);
        s3.setVisible(false);
        s4.jLabel4.setText(mgr.genTicket.BookId);
        s4.jLabel5.setText(mgr.genTicket.Name);
        s4.jLabel6.setText("SPICEJET: "+mgr.genTicket.Spice+" "+mgr.genTicket.DateSpice);
        s4.jLabel7.setText("SILKAIR: "+mgr.genTicket.Silk+" "+mgr.genTicket.DateSilk);
        s4.setVisible(true);
    }
    void Screen1retLast()
    {
        s1.jComboBox1.setSelectedIndex(0);
        s1.jComboBox2.setSelectedIndex(0);   
        s4.setVisible(false);
        s1.setVisible(true);
    }
}
