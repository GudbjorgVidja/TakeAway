package vinnsla.takeaway;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

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
    public void testHeildarverdTomKarfa() {
        assert (karfa.getHeildarverd().get() == 0);
    }

    @Test
    public void testGetAddedVeitingar() {
        Veitingar v1 = new Veitingar("rettur", 1000);
        karfa.veitingarList().add(v1);
        assertEquals(karfa.veitingarList().get(0), v1);
        assertFalse(karfa.veitingarList().isEmpty());
    }

    @Test
    public void testHeildarverd() {

    }

}
