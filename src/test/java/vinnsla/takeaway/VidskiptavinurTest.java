package vinnsla.takeaway;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VidskiptavinurTest {
    private Vidskiptavinur vidskiptavinur;
    private String nafn;
    private String heimilisfang;

    @Before
    public void makeVidskiptavinur() {
        nafn = "Jónatan";
        heimilisfang = "Birkiteigur 15";
        vidskiptavinur = new Vidskiptavinur(nafn, heimilisfang);
    }
    

    @Test
    public void testNafnIsName() {
        assertEquals(vidskiptavinur.getNafn().get(), nafn);
    }

    @Test
    public void testHeimilisfangIsAddress() {
        assertEquals(vidskiptavinur.getHeimilisfang().get(), heimilisfang);
    }

}
