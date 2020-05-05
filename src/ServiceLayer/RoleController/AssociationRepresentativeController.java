package ServiceLayer.RoleController;
import BusinessLayer.Controllers.AssociationRepresentativeBusinessController;
import BusinessLayer.Controllers.RefereeBusinessController;
import BusinessLayer.OtherCrudOperations.*;
import BusinessLayer.RoleCrudOperations.AssociationRepresentative;
import BusinessLayer.RoleCrudOperations.Owner;
import BusinessLayer.RoleCrudOperations.Referee;
import javafx.util.Pair;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AssociationRepresentativeController {

    AssociationRepresentativeBusinessController associationRepresentativeBusinessController;

    public AssociationRepresentativeController(AssociationRepresentative associationRepresentative) {
        associationRepresentativeBusinessController = new AssociationRepresentativeBusinessController(associationRepresentative);
    }

    /**
     * create new league and put teams inside
     */
    public String createNewLeague(String name, List<String> teamNames) {
        if(teamNames == null || name == null){
            return  "null";
        }else {
            return associationRepresentativeBusinessController.createNewLeague(name, teamNames);
        }
    }

    /**
     * set season to league by year
     */
    public String setYearToLeague(String leagueName, String year) {
        if(leagueName == null || year == null){
            return  "";
        }else{

            return associationRepresentativeBusinessController.setYearToLeague(leagueName, year);
        }
    }

    /**
     * remove specific referee
     */
    public String deleteReferee(String userName) {
        if(userName == null){
            return "";
        }else{
             return associationRepresentativeBusinessController.deleteReferee(userName);
        }
    }

    /**
     * creates an account and adds it as a referee to the system
     */
    public String addNewReferee(String training, String name, String stringAge, String userName, String password) {
        if(training == null || name == null || stringAge==null || userName==null || password ==null)  {
            return "";
        }else{
            return associationRepresentativeBusinessController.addNewReferee(training, name, stringAge, userName, password);
        }

    }

    /**
     * add referee to a league in specific season , add referee to SLsetting referee list
     */
    public String addRefereeToLeague(String userName, String leagueName, String seasonName) { // to fix uc
        if(userName == null || leagueName == null || seasonName == null){
            return  "";
        }
        return associationRepresentativeBusinessController.addRefereeToLeague(userName, leagueName, seasonName);
    }

    /**
     * set league pointCalc policy in specific season
     */
    public String setLeaguePointCalcPolicy(String leagueName, String policyID, String seasonName, String pointCalc) {
        if(leagueName == null || policyID == null || seasonName == null || pointCalc ==null){
            return  "";
        }else{
            return associationRepresentativeBusinessController.setLeaguePointCalcPolicy(leagueName, policyID, seasonName, pointCalc);
        }
    }

    /**
     * set league game Schedule policy in specific season
     */
    public String setLeagueGameSchedualPolicy(String leagueName, String policyID, String seasonName, String gameSchedule) {
        if(leagueName == null || policyID == null || seasonName == null || gameSchedule ==null){
            return  "";

        }else{
            return associationRepresentativeBusinessController.setLeagueGameSchedualPolicy(leagueName, policyID, seasonName, gameSchedule);
        }
    }

    /**
     * Sets new season in specific year
     *
     * @param year the year in which the season will take place
     * @return
     */
    public String setNewSeason(String year) {
        if(year==null){
            return "";
        }
        return associationRepresentativeBusinessController.setNewSeason(year);
    }

    public void addAmountToAssociationBudget(String amountName) {
        associationRepresentativeBusinessController.addAmountToAssociationBudget(amountName);
    }

    public void subtractAmountToAssociationBudget(String amountName) {
        associationRepresentativeBusinessController.subtractAmountToAssociationBudget(amountName);
    }

    public String approveTeam(String teamName, String userName) {
        if (teamName == null || userName == null) {
            return "";
        } else {
            return associationRepresentativeBusinessController.approveTeam(teamName, userName);
        }
    }

    public HashMap<String, Pair<Method,List<String>>> showUserMethods() throws NoSuchMethodException
    {
        HashMap<String, Pair<Method,List<String>>> options=new HashMap<>();
        List<String> showUserList=new ArrayList<>();
        showUserList.add("League name@League");
        showUserList.add("Policy ID");
        showUserList.add("Season name@Season");
        showUserList.add("Game Schedule:");
        options.put("Set Schedule Policy to League",new Pair<>(this.getClass().getDeclaredMethod("setLeagueGameSchedualPolicy",String.class,String.class,String.class,String.class),showUserList));

        showUserList=new ArrayList<>();
        showUserList.add("League name@League");
        showUserList.add("Policy ID");
        showUserList.add("Season name@Season");
        showUserList.add("Point Calculation:");
        options.put("Set Point Calculation Policy to League",new Pair<>(this.getClass().getDeclaredMethod("setLeaguePointCalcPolicy",String.class,String.class,String.class,String.class),showUserList));

        showUserList=new ArrayList<>();
        showUserList.add("Referee name@Referee");
        showUserList.add("League name@League");
        showUserList.add("Season name@Season");
        options.put("Add a Referee to a League",new Pair<>(this.getClass().getDeclaredMethod("addRefereeToLeague",String.class,String.class,String.class),showUserList));

        showUserList=new ArrayList<>();
        showUserList.add("Team name");
        showUserList.add("Owner username@Account");
        options.put("Approve the opening of a team",new Pair<>(this.getClass().getDeclaredMethod("approveTeam",String.class,String.class),showUserList));

        return options;
    }

    public List<String> getAlerts()
    {
        return associationRepresentativeBusinessController.getAlerts();
    }

}
