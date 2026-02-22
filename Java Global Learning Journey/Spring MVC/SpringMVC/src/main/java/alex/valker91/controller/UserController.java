package alex.valker91.controller;

import alex.valker91.controller.exception.BadRequestException;
import alex.valker91.controller.payload.NewUserPayload;
import alex.valker91.controller.payload.UpdateUserPayload;
import alex.valker91.facade.BookingFacade;
import alex.valker91.model.User;
import alex.valker91.model.impl.UserImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final BookingFacade bookingFacade;

    @GetMapping("main/page")
    public String getUsersMainPage() {
        return "users/main_page";
    }

    @GetMapping("create")
    public String getCreateUserPage() {
        return "users/new_user";
    }

    @GetMapping("list")
    public String getListUsersPage() {
        return "users/list_users";
    }

    @PostMapping("create")
    public String createUser(NewUserPayload payload
//            ,
//                             Model model,
//                             HttpServletResponse response
    ) {
//        try {
            User user = new UserImpl(payload.id(), payload.name(), payload.email());
            user = bookingFacade.createUser(user);
            return "redirect:/users/%d".formatted(user.getId());
//        } catch (BadRequestException exception) {
//            response.setStatus(HttpStatus.BAD_REQUEST.value());
//            model.addAttribute("payload", payload);
//            model.addAttribute("errors", exception.getErrors());
//            return "users/new_user";
//        }
    }

    @GetMapping("{userId}")
    public String getUser(@PathVariable("userId") int userId, Model model) {
        User user = bookingFacade.getUserById(userId);
        model.addAttribute("user", user);
        return "users/user";
    }

    @PostMapping("{userId}/delete")
    public String deleteUser(@PathVariable("userId") int userId) {
        boolean isDeleted = bookingFacade.deleteUser(userId);
        return "redirect:/users/main/page";
    }

    @GetMapping("{userId}/edit")
    public String getUserEditPage(@PathVariable("userId") int userId, Model model) {
        User user = bookingFacade.getUserById(userId);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("{userId}/edit")
    public String updateUser(@PathVariable("userId") int userId,
                              UpdateUserPayload payload
//            ,
//                              Model model,
//                              HttpServletResponse response
    ) {
//        try {
            User user = bookingFacade.getUserById(userId);
            user.setName(payload.name());
            User updatedUser = bookingFacade.updateUser(user);
            return "redirect:/users/%d".formatted(updatedUser.getId());
//        } catch (BadRequestException exception) {
//            response.setStatus(HttpStatus.BAD_REQUEST.value());
//            model.addAttribute("payload", payload);
//            model.addAttribute("errors", exception.getErrors());
//            return "users/edit";
//        }
    }

    @GetMapping("userbyemail")
    public String getUserByEmail(Model model, @RequestParam(name = "filter", required = false) String filter) {
        User user = bookingFacade.getUserByEmail(filter);
        if (user == null) {
            return "users/main_page";
        }
        model.addAttribute("user", user);
        model.addAttribute("filter", filter);
        return "users/user_by_email";
    }

    @GetMapping("list/by-name")
    public String getUsersByName(@RequestParam("name") String name,
                                 @RequestParam("pageSize") int pageSize,
                                 @RequestParam("pageNum") int pageNum,
                                 Model model) {
        model.addAttribute("name", name);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("usersByName", bookingFacade.getUsersByName(name, pageSize, pageNum));
        return "users/list_users";
    }
}
