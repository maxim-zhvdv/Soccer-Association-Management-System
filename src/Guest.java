import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Guest {


  private String id;

  public Guest(String aId)
  {
    id = aId;
  }

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }

  public String toString()
  {
    return super.toString() + "["+ "id" + ":" + getId()+ "]";
  }

  public Account LogIn(String UserName, String Password){
    List<Account> accountList=Controller.getAccounts();
    for(Account a:accountList){
      if(a.getUserName().equals(UserName)&&a.getPassword().equals(Password))
        return a;
    }
    return null;
  }

  public void SignIn(String Name, int Age, String UserName, String Password){
    Account newAccount=new Account(Name,Age,UserName,Password);
    newAccount.addRole(new Fan(newAccount.getName()));
    Controller.addAccount(newAccount);
  }

  public void ShowInfo(String InfoAbout){
    if(InfoAbout.equals("Teams")){
      for(Team team:Controller.getTeams())
        team.ShowTeam();
    }

    if(InfoAbout.equals("Players")){
      List<Player> players= Controller.getPlayersFromAccounts();
      for(Player player:players){
        player.ShowPlayer();
      }
    }
    if(InfoAbout.equals("Coaches")){
      List<Coach> coaches= Controller.getCoachesFromAccounts();
      for(Coach coach:coaches){
        coach.ShowCoach();
      }
    }

    if(InfoAbout.equals("Leagues")){
      for(League league:Controller.getLeagues())
        league.ShowLeague();
    }

    if(InfoAbout.equals("Seasons")){
      for(Season season:Controller.getSeasons())
        season.ShowSeason();
    }

  }

  //Need to be Changed//
  public void Search(String criterion, String query){
    if(criterion.equals("Name")){
      List<Team> teams=new LinkedList<>();
      for(Team team:Controller.getTeams()){
        if(team.getName().equals(query))
          teams.add(team);
      }
      List<Account> accounts=new LinkedList<>();
      for(Account account:Controller.getAccounts()){
        if(account.getName().equals(query))
          accounts.add(account);
      }
      List<League> leagues=new LinkedList<>();
      for(League league:Controller.getLeagues()){
        if(league.getName().equals(query))
          leagues.add(league);
      }
      List<Season> seasons=new LinkedList<>();
      for(Season season:Controller.getSeasons()){
        if(season.getName().equals(query))
          seasons.add(season);
      }

      System.out.println("Teams with the name: "+query);
      for(Team team:teams)
        team.ShowTeam();
      System.out.println("Accounts with the name:"+query);
      for(Account account:accounts)
        account.ShowAccount();
      System.out.println("Leagues with the name: "+query);
      for(League league:leagues)
        league.ShowLeague();
      System.out.println("Seasons with the name: "+query);
      for(Season season:seasons)
        season.ShowSeason();

    }
    if(criterion.equals("Category")){
      if(query.equals("Teams")){
        for(Team team:Controller.getTeams())
          team.ShowTeam();
      }
      if(query.equals("Accounts")){
        for(Account account:Controller.getAccounts())
          account.ShowAccount();
      }
      if(query.equals("Leagues")){
        for(League league:Controller.getLeagues())
          league.ShowLeague();
      }
      if(query.equals("Seasons")){
        for(Season season:Controller.getSeasons())
          season.ShowSeason();
      }
    }
//    if(criterion.equals("Keyword")){ } //***********************************************************
  }

  //Need to be Changed//
  public void Filter(String category,String roleFilter){
    if(category.equals("Role")){
      if(roleFilter.equals("Players")){
        List<Player> players= Controller.getPlayersFromAccounts();
        for(Player player:players)
          player.ShowPlayer();
      }

      if(roleFilter.equals("Coaches")){
        List<Coach> coaches=Controller.getCoachesFromAccounts();
        for(Coach coach:coaches)
          coach.ShowCoach();
      }

      if(roleFilter.equals("TeamManagers")){
        List<TeamManager> tms=Controller.getTeamManagersFromAccounts();
        for(TeamManager tm:tms)
          tm.ShowTeamManager();
      }

      if(roleFilter.equals("Owners")){
        List<Owner> owners=Controller.getOwnersFromAccounts();
        for(Owner owner:owners)
          owner.ShowOwner();
      }

      if(roleFilter.equals("Referee")){//************************************
        List<Referee> refs=Controller.getRefereesFromAccounts();
        for(Referee ref:refs)
          ref.ShowReferee();
      }

    }
    if(category.equals("Team")){
      for(Team team:Controller.getTeams())
        team.ShowTeam();
    }
    if(category.equals("League")){
      for(League league:Controller.getLeagues())
        league.ShowLeague();
    }
    if(category.equals("Season")){
      for(Season season:Controller.getSeasons())
        season.ShowSeason();
    }
  }













}