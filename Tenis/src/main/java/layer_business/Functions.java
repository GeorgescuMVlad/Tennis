package layer_business;

import dto.AdminDTO;
import dto.UserDTO;
import layer_data_access.repo.AdminRepo;
import layer_data_access.repo.UserRepo;
import model.User;
import layer_data_access.repo.GenericRepo;

public class Functions {
    public UserDTO loginPlayer(String mail, String password) {

        UserDTO user = new UserDTO(UserRepo.findUserByMail(mail));
        //do operations on user
        return user;
    }

    public AdminDTO loginAdmin(String mail, String password) {

        AdminDTO admin = new AdminDTO(AdminRepo.findAdminByMail(mail));
        //do operations on user
        return admin;
    }

}
