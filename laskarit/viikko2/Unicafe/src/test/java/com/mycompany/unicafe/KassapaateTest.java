package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate paate;
    Maksukortti kortti;

    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void kassassaOikeaMaaraRahaa() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void myytyjenEdullistenMaaraAlussa(){
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenMaukkaidenMaaraAlussa(){
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKateinen(){
        int vaihtoraha = paate.syoEdullisesti(500);
        assertEquals(260, vaihtoraha);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKateinen(){
        int vaihtoraha = paate.syoMaukkaasti(500);
        assertEquals(100, vaihtoraha);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKateinenLiianPieniMaksu(){
        int vaihtoraha = paate.syoEdullisesti(100);
        assertEquals(100, vaihtoraha);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKateinenLiianPieniMaksu(){
        int vaihtoraha = paate.syoMaukkaasti(100);
        assertEquals(100, vaihtoraha);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKorttiaVeloitetaan(){
        boolean arvo = paate.syoEdullisesti(kortti);
        assertEquals(true, arvo);
    }
    
    @Test
    public void syoMaukkaastiKorttiaVeloitetaan(){
        boolean arvo = paate.syoMaukkaasti(kortti);
        assertEquals(true, arvo);
    }
    
    @Test
    public void syoEdullisestiKorttiaEiVeloiteta(){
        Maksukortti k = new Maksukortti(100);
        boolean arvo = paate.syoEdullisesti(k);
        assertEquals(false, arvo);
    }
    
    @Test
    public void syoMaukkaastiKorttiaEiVeloiteta(){
        Maksukortti k = new Maksukortti(100);
        boolean arvo = paate.syoMaukkaasti(k);
        assertEquals(false, arvo);
    }
    
    @Test
    public void myydytEdullisetKasvaaKortti(){
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydytMaukkaatKasvaaKortti(){
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void myydytEdullisetEiKasvaKortti(){
        Maksukortti k = new Maksukortti(0);
        paate.syoEdullisesti(k);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myydytMaukkaatEiKasvaKortti(){
        Maksukortti k = new Maksukortti(0);
        paate.syoMaukkaasti(k);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinLataaminenKasvattaaKassaa(){
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
    }
    
    @Test
    public void kortinLataaminenSummaNegatiivinen(){
        paate.lataaRahaaKortille(kortti, -10);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
    }
    
    
}
