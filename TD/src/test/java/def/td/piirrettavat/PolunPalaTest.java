/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package def.td.piirrettavat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jemisalo
 */
public class PolunPalaTest {
    
    private PolunPala pala;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.pala = new PolunPala(new int[]{100,100});
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void sijainti(){
        assertEquals(this.pala.sijainti()[0],100);
        assertEquals(this.pala.sijainti()[1],100);
    }
    @Test
    public void sadeOnKymmenen(){
        assertEquals(this.pala.getSade(),10);
    }
}
