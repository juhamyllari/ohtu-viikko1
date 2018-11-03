package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toisellaKonstruktorillaUudellaVarastollaOikeaTilavuus() {
        varasto = new Varasto(1.0, 0.5);
        assertEquals(1.0, varasto.getTilavuus(), vertailuTarkkuus);
        
        varasto = new Varasto(-1.0, 0.5);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toisellaKonstruktorillaUudellaVarastollaOikeaSaldo() {
        varasto = new Varasto(1.0, 0.5);
        assertEquals(0.5, varasto.getSaldo(), vertailuTarkkuus);
        
        varasto = new Varasto(1.0, -0.5);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellisellaVarastollaNollaTilavuus() {
        Varasto createdNegative = new Varasto(-10.0);
        assertEquals(0.0, createdNegative.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto = new Varasto(10, 8.4);
        varasto.lisaaVarastoon(-87.3);

        // saldon pitäisi olla sama kuin ennen lisäystä
        assertEquals(8.4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiYlitaTilavuutta() {
        varasto.lisaaVarastoon(28.4);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPalauttaaNollan() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void varastoTyhjentyyOikein() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(9);

        assertEquals(8.0, saatuMaara, vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tulostuuOikein() {
        varasto.lisaaVarastoon(8);

        String expected = "saldo = 8.0, vielä tilaa 2.0"; 
        assertEquals(expected, varasto.toString());
    }
    
}