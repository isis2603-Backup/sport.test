/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.sport.master.utils.test;

import co.edu.uniandes.csw.address.logic.dto.AddressDTO;
import co.edu.uniandes.csw.sport.logic.dto.SportDTO;
import co.edu.uniandes.csw.user.logic.dto.UserDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Jj.alarcon10
 */

    public class InitializeDataUserMaster {
    
        public static String DB_URL= "jdbc:derby://localhost:1527/sun-appserv-samples;create=true;";
    
         /*
        insertDataSport crea entidades de Sport de acuerdo al número (number)
        */
        
      public static ArrayList<SportDTO> insertDataSport(int number){

        try{
           Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
           // Obtiene conexion jdbc
            //Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn;
            conn = DriverManager.getConnection(DB_URL);
            
            PodamFactory factory = new PodamFactoryImpl();
            Statement stmt;
            stmt = conn.createStatement();
           //Revisar atributo @Enable@, en la base de datos es de tipo smallint y en el dto es boolean
            ArrayList<SportDTO> sports = new ArrayList <>();
            for(long i=0; i<number; i++){
               SportDTO entity = factory.manufacturePojo(SportDTO.class);
               String sql="INSERT INTO SPORTENTITY (ID,MAXAGE,MINAGE,NAME) " 
                            + "VALUES (" + i + "," + entity.getMaxAge() + "," + entity.getMinAge()+ ",'" + entity.getName() + "')";
               stmt.executeUpdate(sql);
               sports.add(entity);
           }
           conn.close();
           return sports;
        }catch(Exception e){
                e.printStackTrace();
                return null;

        }    
    }
        
        public static void insertUserAddressSport(int number){
        
           try{
           Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
           // Obtiene conexion jdbc
           Connection conn = DriverManager.getConnection(DB_URL);
           PodamFactory factory = new PodamFactoryImpl();
           
           Statement stmt = conn.createStatement();
           UserDTO master = factory.manufacturePojo(UserDTO.class);
           master.setBirthDate("2014-10-12");
           //Revisar atributo @Enable@, en la base de datos es de tipo smallint y en el dto es boolean
           String sql1= "INSERT INTO USERENTITY (ID,USERNAME,FIRSTNAME,LASTNAME,BIRTHDATE,ENABLE,DOCNUMBER,DOCUMENTTYPEID,ROLEID) "
                    + "VALUES ("+ master.getId()+ ",'"+master.getUserName()+"', '" +master.getFirstName()+"', '"+ master.getLastName()+"','"
                   + master.getBirthDate()+"'," + 0 +",'" + master.getDocNumber()+"',"      
                   + master.getDocumenttypeId()+ "," + master.getRoleId()+")";
           
           stmt.executeUpdate(sql1);
           ArrayList<AddressDTO> addresses = new ArrayList<AddressDTO>();
           ArrayList<SportDTO> sports = new ArrayList<SportDTO>();
           for(long i=1; i<=number; i++){
               AddressDTO entityAddress = factory.manufacturePojo(AddressDTO.class);
               String sql="INSERT INTO ADDRESSENTITY (ID,STREET,AVENEU,CITYID) " 
                            + "VALUES (" + i + ",'" + entityAddress.getAveneu() + "','" + entityAddress.getStreet()+ "'," + entityAddress.getCityId() + ")";
               stmt.executeUpdate(sql);
                    
               SportDTO entitySport = factory.manufacturePojo(SportDTO.class);
               sql="INSERT INTO SPORTENTITY (ID,MAXAGE,MINAGE,NAME) " 
                            + "VALUES (" + i + "," + entitySport.getMaxAge() + "," + entitySport.getMinAge()+ ",'" + entitySport.getName() + "')";
               stmt.executeUpdate(sql);
               
               sql="INSERT INTO USERADDRESSENTITY (USERID,ADDRESSID) " 
                            + "VALUES (" + master.getId() + "," + i + ")";
               stmt.executeUpdate(sql);
               
               sql="INSERT INTO USERSPORTENTITY (SPORTID,USERID) " 
                            + "VALUES (" + i + "," + master.getId() + ")";
               stmt.executeUpdate(sql);
               
               addresses.add(entityAddress);
               sports.add(entitySport);
           }
           conn.close();
           
        }catch(Exception e){
                e.printStackTrace();
        
        }    
        
        
        
        
        
        
        }
      
      
      
      
      
      
      
      
      
      
      
        public static SportDTO generateSport() {
        try {
            PodamFactory factory = new PodamFactoryImpl();
            SportDTO entity = factory.manufacturePojo(SportDTO.class);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static UserDTO generateUser(){
        try{
            PodamFactory factory = new PodamFactoryImpl();
            UserDTO entity = factory.manufacturePojo(UserDTO.class);
            return entity;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static AddressDTO generateAddress(){
        try{
            PodamFactory factory = new PodamFactoryImpl();
            AddressDTO entity = factory.manufacturePojo(AddressDTO.class);
            return entity;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static void deleteBd(){
    
        try{
            //Obtiene la conexion jdbc
            Connection conn = DriverManager.getConnection(DB_URL);
            PodamFactory factory = new PodamFactoryImpl();
            Statement stmt = conn.createStatement();
            
            String sql = "DELETE FROM USERADDRESSENTITY";
            stmt.executeUpdate(sql);
            
            sql = "DELETE FROM USERSPORTENTITY";
            stmt.executeUpdate(sql);
        
            sql = "DELETE FROM USERENTITY";
            stmt.executeUpdate(sql);
            
            sql = "DELETE FROM SPORTENTITY";
            stmt.executeUpdate(sql);
            
            sql = "DELETE FROM ADDRESSENTITY";
            stmt.executeUpdate(sql);
            
            conn.close();
        }catch(Exception e){ 
            e.printStackTrace();
        
        }   
    }
    
                
            

    
    
    
    
    
    
    /*        
    */
    
    

}
