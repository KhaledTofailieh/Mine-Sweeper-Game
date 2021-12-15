/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game1;

import java.io.Serializable;

/**
 *
 * @author Vision
 */
class Mine implements Serializable{
    private String ID;
    Mine()
    {
    ID="null";
    }
    public void setid(String id)
    {
    ID=id;
    }
    
    public String getmine()
    {
    return ID;
    }
    
}
