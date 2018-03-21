package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldonKasvatusToimii(){
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void saldoEiVaheneJosRahaaEiTarpeeksi(){
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikein(){
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void palauttaaTrueKunRahatRiittaa(){
        boolean arvo = kortti.otaRahaa(10);
        assertEquals(true, arvo);
    }
    
    @Test 
    public void palauttaaFalseKunRahaEiRiita(){
        boolean arvo = kortti.otaRahaa(100);
        assertEquals(false, arvo);
    }
}
