package vinnsla.takeaway;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

public class KarfaTest {
    private Karfa karfa;
    private final Veitingar v1 = new Veitingar("First", 1000);
    private final Veitingar v2 = new Veitingar("Second", 2000);

    @Before
    public void geraTomaKorfu() {
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
    public void testHeildarverdMedTveimur() {
        karfa.veitingarList().addAll(v1, v2);
        assertEquals(karfa.getHeildarverd().get(), v1.getVerd().get() + v2.getVerd().get());
    }

    @Test
    public void testHeildarverdRemoveOne() {
        karfa.veitingarList().addAll(v1, v2);
        karfa.veitingarList().remove(0);
        assertEquals(karfa.getHeildarverd().get(), reiknaKarfaHeildarverd());
    }

    @Test
    public void testHeildarverdTakaUr() {
        karfa.veitingarList().addAll(v1, v2);
        karfa.takaAfMatsedli(0);
        assertEquals(karfa.getHeildarverd().get(), reiknaKarfaHeildarverd());
    }

    @Test
    public void testHeildarverdTakaUrHandvirkt() {
        karfa.veitingarList().addAll(v1, v2);
        karfa.veitingarList().remove(0);
        assertEquals(karfa.getHeildarverd().get(), reiknaKarfaHeildarverd());
    }


    @Test
    public void testHeildarverdClearKarfa() {
        karfa.veitingarList().addAll(v1, v2);
        karfa.veitingarList().clear();
        assertEquals(karfa.getHeildarverd().get(), reiknaKarfaHeildarverd());
    }

    @Test
    public void testHeildarverdRemoveAllFromKarfa() {
        karfa.veitingarList().addAll(v1, v2);
        karfa.veitingarList().removeAll(v1, v2);
        assertEquals(karfa.getHeildarverd().get(), reiknaKarfaHeildarverd());
    }


    private int reiknaKarfaHeildarverd() {
        int reiknadHeildarverd = 0;
        for (Veitingar v : karfa.veitingarList()) {
            reiknadHeildarverd += v.getVerd().get();
        }
        return reiknadHeildarverd;
    }

}
