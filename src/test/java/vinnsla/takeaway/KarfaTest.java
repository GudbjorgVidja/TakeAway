package vinnsla.takeaway;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class KarfaTest {
    private Karfa karfa;

    /*
   testa:
   tóm karfa er tóm

     */

    @Before
    public void geraNyjaKorfu() {
        karfa = new Karfa();
    }

    @Test
    public void testTomKarfaErTom() {
        assert (karfa.getObsVeitingar().isEmpty());
    }

    @Test
    public void testKarfaMedEinuEkkiTom() {
        assertFalse(karfa.getObsVeitingar().isEmpty());
    }

    @Test
    public void testHeildarverdTomKarfa() {
        assert (karfa.getHeildarverd().get() == 0);
    }

}
