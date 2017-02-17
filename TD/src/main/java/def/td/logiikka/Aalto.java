/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import java.util.ArrayList;

/**
 *
 * @author jemisalo
 */
public class Aalto {
    
    private final ArrayList<Object> syote;
    private int odotus;
    private int syoteIndex;
    
    public Aalto(ArrayList<Object> syote){
        this.syote = syote;
        this.odotus = 0;
        this.syoteIndex = 0;
    }
    
    public boolean tyhja(){
        return this.syoteIndex>=this.syote.size();
    }
    
    public String tick(){
        if(this.odotus>0){
            this.odotus--;
            return null;
        }
        if(this.syoteIndex>=this.syote.size()){
            return null;
        }
        Object alkio = this.syote.get(this.syoteIndex);
        this.syoteIndex++;
        try{
            this.odotus = (int) alkio - 1;
        }catch(Exception e){
            return (String) alkio;
        }
        return null;
    }
}
