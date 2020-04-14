import java.util.*;


public class SystemManager extends Role {

  HashMap<String,String> complaintAndComments;

  public SystemManager(String aName)
  {
    super(aName);
    complaintAndComments= new HashMap<>();
  }

  public HashMap<String, String> getComplaintAndComments() {
    return complaintAndComments;
  }

  public void setComplaintAndComments(HashMap<String, String> complaintAndComments) {
    this.complaintAndComments = complaintAndComments;
  }

  /**
   * delete team for Permanently, saving all her actions, notify owner and team manger and delete team's
   * page from all fans.
   * @param team
   * @return
   */
  public boolean DeleteTeamPermanently(Team team){
    boolean wasSet = false;
    if(team == null){
      return  wasSet;
    }
    wasSet = removeTeamFromDm(team);
    //saveAction(team);
    notifyOnDelete(team);
    deleteFromAllFollowers(team);
    team.delete();
    return wasSet;
  }

  private boolean removeTeamFromDm(Team aTeam) {
    boolean wasRemoved = true;
    DataManager.removeTeam(aTeam);
    wasRemoved = true;
    return wasRemoved;
  }

  private void notifyOnDelete(Team team) {
    for (Owner owner : team.getOwners()){
      owner.addAlert(new Alert("Delete Team Permanently: " + team.getName()));
    }
    for(TeamManager teamManager : team.getTeamManagers()){
      teamManager.addAlert(new Alert("Delete Team Permanently: " + team.getName()));
    }
  }

  /**
   * delete team's page from all fans of the team (follower)
   * @param team
   */
  private void deleteFromAllFollowers(Team team) {
    Page teamPageToDelete = team.getPage();
    List<Fan> fans = teamPageToDelete.getFans();
    for (Fan fan : fans) {
      for (Page page : fan.getPages()) {
        if (page.equals(teamPageToDelete)) {
          page.delete();
          break;
        }
      }
    }

  }

//  private void saveAction(Team team) { }

  /**
   * remove account from system
   * @param account
   * @return
   */
  public boolean deleteAccount(Account account){
    boolean wasSet = false;
    if (account == null){
      return  wasSet;
    }
    List <Role> roles = account.getRoles();
    boolean isOwner= false;
    boolean isSystemManger = false;
    for(Role role : roles){
      if(role instanceof Owner){
        isOwner = true;
      }
      if(role instanceof SystemManager){
        isSystemManger = true;
      }
    }
    if(isOwner){
      return false;// to find someone else who will be next owner before remove this one
    }
    if(isSystemManger){
      List<Account> accounts = DataManager.getAccounts();
      for(Account account1 : accounts){
        for(Role role : account1.getRoles()){
          if(role instanceof SystemManager && !account.equals(account1)){
            wasSet = DataManager.removeAccount(account);
            return  wasSet;
          }
        }
      }
    }

    wasSet = DataManager.removeAccount(account);
    return wasSet;
  }

  /**
   * show all the complaints of the accounts in the system
   */
  public void showComplaints(){
    for(Map.Entry <String,String> complain : complaintAndComments.entrySet()) {
      System.out.println(complain.getKey().toString());
    }
  }

  /**
   * add comment to the complaint
   */
  public void addComplain(String complain){
    complaintAndComments.put(complain,null);
  }

  /**
   * add comment to the complaint
   */
  public void addComment(String comment,String acomplain){
    for(Map.Entry <String,String> complain : complaintAndComments.entrySet()){
      if(complain.getKey().equals(acomplain)){
        complaintAndComments.put(acomplain,comment);
      }
    }
  }

  /**
   * show system logger
   */
  public void showSystemLog(){
    System.out.println(Logger.getInstance().readLoggerFile());
  }

  /**
   * build recommendation system
   */
  public void buildRecommendationSystem(){ }

  public void addAlert(Alert alert){ throw new UnsupportedOperationException(); }
  public void clearAlerts() { throw new UnsupportedOperationException(); }
  public void removeAlert(String s) { throw new UnsupportedOperationException(); }


}