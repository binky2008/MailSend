/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailsend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @author mathide
 */
public class MyDataGet {

    private Vector<String> titles;
    private Vector<LinkedHashMap> contents;

    public MyDataGet() {

    }

    public void getData(String url,String user,String pwd,String sql) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.postgresql.Driver").newInstance();
        try {
            Connection con = DriverManager.getConnection(url,user,pwd);

            Statement st1 = con.createStatement();
            ResultSet rs = st1.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int countOfColumn = rsmd.getColumnCount();
            setTitles(new Vector<String>());
            for (int i = 1; i <= countOfColumn; i++) {
                getTitles().add(rsmd.getColumnName(i));
            }

            setContents(new Vector<LinkedHashMap>());          
            while (rs.next()) {
                LinkedHashMap hashMap = new  LinkedHashMap();
                for (String title : getTitles()) {
                    hashMap.put(title, rs.getString(title));
                }
                getContents().add(hashMap);
            }

            rs.close();
            st1.close();
            con.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Vector<String> getTitles() {
        return titles;
    }

    public void setTitles(Vector<String> titles) {
        this.titles = titles;
    }

    public Vector<LinkedHashMap> getContents() {
        return contents;
    }

    public void setContents(Vector<LinkedHashMap> contents) {
        this.contents = contents;
    }

}
