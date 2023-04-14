package vinnsla.takeaway;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MatsedillTest {
    private ObservableList<Veitingar> veitingar;
    private final Veitingar v1 = new Veitingar("First", 1000);
    private final Veitingar v2 = new Veitingar("Second", 2000);

    @Before
    public void initializeEmptyVeitingarList() {
        veitingar = FXCollections.observableArrayList();

    }

    @Test
    public void testEmptyListIsEmpty() {
        assert (veitingar.isEmpty());
    }

    @Test
    public void testOneAddedNotEmpty() {
        veitingar.add(v1);
        assertFalse(veitingar.isEmpty());
    }

    @Test
    public void testFirstAddedIsFirst() {
        veitingar.addAll(v1, v2);
        assertEquals(veitingar.get(0), v1);
    }

    @Test
    public void testContainsAddedVeitingar() {
        veitingar.addAll(v1, v2);
        assertEquals(veitingar.get(0), v1);
        assertEquals(veitingar.get(1), v2);
    }
    
}
