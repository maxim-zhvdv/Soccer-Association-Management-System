package BusinessLayer.Controllers;

import BusinessLayer.OtherCrudOperations.*;
import BusinessLayer.RoleCrudOperations.AssociationRepresentative;
import BusinessLayer.RoleCrudOperations.Owner;
import BusinessLayer.RoleCrudOperations.Referee;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AssociationRepresentativeBusinessController {

    AssociationRepresentative associationRepresentative;

    public AssociationRepresentativeBusinessController(AssociationRepresentative associationRepresentative) {
        this.associationRepresentative = associationRepresentative;
    }

    /**
     * create new league and put teams inside
     */
    public String createNewLeague(String name, List<String> teamNames) {
        List <Team> teams = new LinkedList<>();
        for(String teamName : teamNames){
            Team team = Team.convertStringToTeam(teamName);
            teams.add(team);
        }
        return associationRepresentative.createNewLeague(name, teams);
    }

    /**
     * set season to league by year
     */
    public String setYearToLeague(String leagueName, String year) {
        League league = League.convertStringToLeague(leagueName);
        return associationRepresentative.setYearToLeague(league, year);
    }

    /**
     * remove specific referee
     */
    public String deleteReferee(String userName) {
        Referee referee = RefereeBusinessController.convertStringToReferee(userName);
        return associationRepresentative.deleteReferee(referee);
    }

    /**
     * creates an account and adds it as a referee to the system
     */
    public String addNewReferee(String training, String name, String stringAge, String userName, String password) {
        int age = Integer.getInteger(stringAge);
        return associationRepresentative.addNewReferee(training, name, age, userName, password);
    }

    /**
     * add referee to a league in specific season , add referee to SLsetting referee list
     */
    public String addRefereeToLeague(String userName, String leagueName, String seasonName) { // to fix uc
        Referee referee = RefereeBusinessController.convertStringToReferee(userName);
        League league = League.convertStringToLeague(leagueName);
        Season season = Season.convertStringToSeason(seasonName);
        return associationRepresentative.addRefereeToLeague(referee, league, season);
    }

    /**
     * set league pointCalc policy in specific season
     */
    public String setLeaguePointCalcPolicy(String leagueName, String policyID, String seasonName, String pointCalc) {
        League league = League.convertStringToLeague(leagueName);
        Season season = Season.convertStringToSeason(seasonName);
        Policy policy = Policy.convertStringToPolicy(policyID);
        return associationRepresentative.setLeaguePointCalcPolicy(league, policy, season, pointCalc);
    }

    /**
     * set league game Schedule policy in specific season
     */
    public String setLeagueGameSchedualPolicy(String leagueName, String policyID, String seasonName, String gameSchedule) {
        Policy policy = Policy.convertStringToPolicy(policyID);
        League league = League.convertStringToLeague(leagueName);
        Season season = Season.convertStringToSeason(seasonName);
        return associationRepresentative.setLeagueGameSchedualPolicy(league, policy, season, gameSchedule);
    }

    /**
     * Sets new season in specific year
     *
     * @param year the year in which the season will take place
     * @return
     */
    public String setNewSeason(String year) {
        return associationRepresentative.setNewSeason(year);
    }

    public void addAmountToAssociationBudget(String amountName) {
        double amount = Double.parseDouble(amountName);
        associationRepresentative.addAmountToAssociationBudget(amount);
    }

    public void subtractAmountToAssociationBudget(String amountName) {
        double amount = Double.parseDouble(amountName);
        associationRepresentative.subtractAmountFromAssociationBudget(amount);
    }

    public String approveTeam(String teamName, String userName) {
        Owner owner = OwnerBusinessController.convertStringToOwner(userName);
        return associationRepresentative.approveTeam(teamName, owner);
    }

    public List<String> getAlerts() {
        List<Alert> alerts=associationRepresentative.getAlertList();
        List<String> strings=new ArrayList<>();
        for(Alert alert:alerts)
        {
            strings.add(alert.getDescription());
        }
        return strings;
    }

}
