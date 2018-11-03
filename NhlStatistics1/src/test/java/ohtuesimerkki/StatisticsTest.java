package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSearch() {
        Player kurriRetrieved = stats.search("Kurri");
        assertEquals("Kurri", kurriRetrieved.getName());
        
        Player foomanRetrieved = stats.search("Fooman");
        assertEquals(null, foomanRetrieved);
    }

    @Test
    public void testTeam() {
        List<Player> teamPlayers = stats.team("DET");
        assertEquals(1, teamPlayers.size());
        assertEquals("Yzerman", teamPlayers.get(0).getName());
    }

    @Test
    public void testTopScorers() {
        List<Player> topPlayers = stats.topScorers(2);
        assertEquals(2, topPlayers.size());
    }

}
