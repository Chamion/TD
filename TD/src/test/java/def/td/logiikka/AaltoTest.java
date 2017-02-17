/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.logiikka;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class AaltoTest {
    
    private ArrayList<Object> syote;
    
    @Before
    public void setUp() {
        this.syote = new ArrayList<>();
    }
    
    @Test
    public void tyhjallaSyotteellaTickOnNull(){
        Aalto aalto = new Aalto(this.syote);
        assertEquals(null, aalto.tick());
    }
    
    @Test
    public void stringSyotePalautuu(){
        this.syote.add("pa11/1");
        Aalto aalto = new Aalto(this.syote);
        assertEquals("pa11/1", aalto.tick());
        assertEquals(null, aalto.tick());
    }
    
    @Test
    public void numeroSyotePaluttaaNullnKertaa(){
        this.syote.add(2);
        this.syote.add("pa5/1");
        this.syote.add(1);
        this.syote.add("pa10/1");
        Aalto aalto = new Aalto(this.syote);
        assertEquals(null, aalto.tick());
        assertEquals(null, aalto.tick());
        assertEquals("pa5/1", aalto.tick());
        assertEquals(null, aalto.tick());
        assertEquals("pa10/1", aalto.tick());
        assertEquals(null, aalto.tick());
    }
    
    @Test
    public void tyhjaTrueKunAlkioitaEiJaljella(){
        this.syote.add(1);
        this.syote.add("pa100/0");
        this.syote.add(2);
        Aalto aalto = new Aalto(this.syote);
        assertFalse(aalto.tyhja());
        aalto.tick();
        assertFalse(aalto.tyhja());
        aalto.tick();
        assertFalse(aalto.tyhja());
        aalto.tick();
        assertTrue(aalto.tyhja());
        aalto.tick();
        assertTrue(aalto.tyhja());
    }
}
