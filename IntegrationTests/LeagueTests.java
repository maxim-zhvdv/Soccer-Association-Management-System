
import BusinessLayer.DataController;
import BusinessLayer.OtherCrudOperations.League;
import BusinessLayer.OtherCrudOperations.Policy;
import BusinessLayer.OtherCrudOperations.SLsettings;
import BusinessLayer.OtherCrudOperations.Season;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class LeagueTests {
    League league = new League("first league");

    @Before
    public void setUp(){
        DataController.clearDataBase();
    }


    @Test
    public void addSLsettingsToSeason() {
        Season season = new Season("2019");
        SLsettings sLsetting = new SLsettings(new Policy("point","game"));
        HashMap<Season,SLsettings> sLsettings = league.getsLsetting();
        league.addSLsettingsToSeason(season,sLsetting);
        assertEquals(sLsetting,league.getSLsettingsBySeason(season));
        assertEquals(sLsetting,season.getSLsettingsByLeague(league));
    }

    @Test
    public void removeSLsettingsFromSeason() {
        Season season = new Season("2019");
        SLsettings sLsetting = new SLsettings(new Policy("point","game"));
        HashMap<Season,SLsettings> sLsettings = league.getsLsetting();
        league.addSLsettingsToSeason(season,sLsetting);
        league.removeSLsettingsFromSeason(season,true);
        assertEquals(0,league.getsLsetting().size());
    }

}
