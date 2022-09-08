package de.hsba.bi.demo.web.user;

import de.hsba.bi.demo.task.Status;
import de.hsba.bi.demo.task.Task;
import de.hsba.bi.demo.user.User;
import de.hsba.bi.demo.web.task.TaskForm;
import org.springframework.stereotype.Component;

@Component
public class UserFormConverter {

    UserForm toForm(User user) {
        UserForm form = new UserForm();
        form.setName((user.getName()));
        form.setUsername(user.getUsername());
        form.setPassword(user.getPassword());
        form.setRole(user.getRole());
        return form;
    }

    User update(User user, UserForm form) {
        user.setName(form.getName());
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setRole(form.getRole());
        user.setUserIsOnEdit(false);
        return user;
    }
}
