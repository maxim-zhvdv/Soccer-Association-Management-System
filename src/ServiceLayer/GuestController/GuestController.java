package ServiceLayer.GuestController;
import BusinessLayer.OtherCrudOperations.Account;
import BusinessLayer.OtherCrudOperations.Guest;
import ServiceLayer.OurSystem;

import java.util.ArrayList;
import java.util.List;

public class GuestController {
    private Guest guest;

    public GuestController(){
        guest=new Guest();
        OurSystem.addGuest(guest);
    }

    //region Getters&Setters
    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    //endregion

    //region Transition Methods
    public List<Object> LogIn(String UserName, String Password) {
        List<Object> list=new ArrayList<>();
        try {
            if(UserName.length()==0)
                list.add("Username length is 0");
            if(Password.length()==0)
                list.add("Password length is 0");
            Account account=guest.LogIn(UserName,Password);
            if(account==null)
                return null;
            OurSystem.addAccount(account);
            return OurSystem.makeControllersByRoles(account);
        } catch (Exception e) {
            list.add(e.getMessage());
        }
        return list;
    }

    public boolean SignIn(String name, int age, String UserName, String Password){
        if(name.length()==0||UserName.length()==0||Password.length()==0) return false;
        for(int i=0;i<name.length();i++){
            if(!Character.isAlphabetic(name.charAt(i)))
                return false;
        }
        if(age<=0)
            return false;
        return guest.SignIn(name,age,UserName,Password);
    }

    public boolean ShowInfo(String InfoAbout){
        if(InfoAbout.length()==0) return false;
        if(!(InfoAbout.equals("Teams")||InfoAbout.equals("Players")||InfoAbout.equals("Coaches")||
                InfoAbout.equals("Leagues")||InfoAbout.equals("Seasons")))
            return false;
        guest.ShowInfo(InfoAbout);
        return true;
    }

    public boolean Search(String criterion, String query){
        if(criterion.length()==0||query.length()==0) return false;
        if(!(criterion.equals("Name")||criterion.equals("Category")))
            return false;
        if(criterion.equals("Category")){
            if(!(query.equals("Teams")||query.equals("Accounts")||query.equals("Leagues")||query.equals("Seasons")))
                return false;
        }
        guest.Search(criterion,query);
        return true;
    }

    public boolean Filter(String category, String roleFilter){
        if(category.length()==0) return false;
        if(!(category.equals("BusinessLayer.RoleCrudOperations.Role")||category.equals("BusinessLayer.OtherCrudOperations.Team")||category.equals("BusinessLayer.OtherCrudOperations.League")||category.equals("BusinessLayer.OtherCrudOperations.Season")))
            return false;
        if(category.equals("BusinessLayer.RoleCrudOperations.Role")){
            if(!(roleFilter.equals("Players")||roleFilter.equals("Coaches")||roleFilter.equals("TeamManagers")||
                    roleFilter.equals("Owners")||roleFilter.equals("Referees")))
                return false;
        }
        guest.Filter(category,roleFilter);
        return true;
    }
    //endregion


}
