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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Jj.alarcon10
 */

    public class InitializeDataUserMaster {
    
    
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
    
    
    
    
    
    
    
    
    /*        
    */
    
    

}
