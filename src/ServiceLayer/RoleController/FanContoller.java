package ServiceLayer.RoleController;
import BusinessLayer.Controllers.FanBusinessController;
import BusinessLayer.OtherCrudOperations.Account;
import BusinessLayer.RoleCrudOperations.Fan;
import BusinessLayer.RoleCrudOperations.Role;
import ServiceLayer.OurSystem;

import java.util.List;

public class FanContoller {

    private FanBusinessController fanBusinessController;

    public FanContoller(Fan fan){
        fanBusinessController=new FanBusinessController(fan);
    }


    //region Getters&&Setter
    public FanBusinessController getFanBusinessController() {
        return fanBusinessController;
    }

    public void setFanBusinessController(FanBusinessController fanBusinessController) {
        this.fanBusinessController = fanBusinessController;
    }
    //endregion

    //region Transition methods for version 3
    public void LogOut(){
        fanBusinessController.LogOut();
    }

    public String EditPersonalInfo(String newName,String newUserName,String newPassword){
        if(newName.length()==0) return "Name field is empty";
        if(newUserName.length()==0) return "Username field is empty";
        if(newPassword.length()==0) return "Password field is empty";
        return fanBusinessController.EditPersonalInfo(newName,newUserName,newPassword);
    }
    //endregion

    //region Transition methods for other UC
    public String ShowInfo(String InfoAbout){
        if(InfoAbout.length()==0) return "InfoAbout field is empty";
        if(!(InfoAbout.equals("Teams")||InfoAbout.equals("Players")||InfoAbout.equals("Coaches")||
                InfoAbout.equals("Leagues")||InfoAbout.equals("Seasons")))
            return "Wrong InfoAbout field";
        fanBusinessController.ShowInfo(InfoAbout);
        return "";
    }

    public String Search(String criterion, String query){
        if(criterion.length()==0) return "Criterion field is empty";
        if(query.length()==0) return "Query length is empty";
        if(!(criterion.equals("Name")||criterion.equals("Category"))) return "Wrong criterion";
        if(criterion.equals("Category")){
            if(!(query.equals("Teams")||query.equals("Accounts")||query.equals("Leagues")||query.equals("Seasons")))
                return "Wrong query";
        }
        fanBusinessController.Search(criterion,query);
        return "";
    }

    public String Filter(String category, String roleFilter){
        if(category.length()==0) return "Catergory field is empty";
        if(!(category.equals("Role")||category.equals("Team")||category.equals("League")||category.equals("Season")))
            return "Wrong category";
        if(category.equals("Role")){
            if(!(roleFilter.equals("Players")||roleFilter.equals("Coaches")||roleFilter.equals("TeamManagers")||
                    roleFilter.equals("Owners")||roleFilter.equals("Referees")))
                return "Wrong role filter";
        }
        fanBusinessController.Filter(category,roleFilter);
        return "";
    }



    public void SubscribeTrackPersonalPages(){
        fanBusinessController.SubscribeTrackPersonalPages();
    }

    public void SubscribeGetMatchNotifications(){fanBusinessController.SubscribeGetMatchNotifications();}

    public String Report(String report){
        if(report.length()==0)
            return "Report is blank";
        fanBusinessController.Report(report);
        return "";
    }

    public String ShowSearchHistory() throws Exception {
        return fanBusinessController.ShowSearchHistory();
    }

    public void ShowPersonalInfo(){
        fanBusinessController.ShowPersonalInfo();
    }
	
    public List<String> getAlerts()
    {
        return fanBusinessController.getAlerts();
    }


    //endregion
}
