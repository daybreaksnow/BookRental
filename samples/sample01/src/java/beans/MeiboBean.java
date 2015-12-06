/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@RequestScoped        
public class MeiboBean {

    
    @IdPattern(from=100,to=200)
    @NotNull
    private Integer number;
    
    private String name;

    @EJB
    MeiboDb db;
    
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String next(){
        create();
        toConsole();
        return "output.xhtml";
    }
    
    public void toConsole(){
        System.out.println(number + " " + name);
    }

    private void create() {
        Meibo meibo = new Meibo();
        meibo.setId(number);
        try{
            db.create(meibo);
        }catch(Exception e){
            e.printStackTrace();;
        }
    }
}
