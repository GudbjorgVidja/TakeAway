package vinnsla.takeaway;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VeitingarTest {
    private Veitingar veitingar;
    private int verd;
    private String rettur;

    @Before
    public void makeVeitingar() {
        verd = 2500;
        rettur = "rettur";
        veitingar = new Veitingar(rettur, verd);
    }

    @Test
    public void testVeitingarVerd() {
        assertEquals(veitingar.getVerd().get(), verd);
    }
    

}
