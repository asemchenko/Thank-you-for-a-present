package com.example.presentator.modules.profile;

import com.example.presentator.model.entities.User;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileController implements IProfileController {
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final ProfileService profileService = new ProfileService(this);
    private ProfileView view;
    private User user;

    public ProfileController(ProfileView view) {
        this.view = view;
    }

    public void loadUserInfo() {
        profileService.loadUserInfo(auth.getCurrentUser().getUid());
    }

    private void setEmail() {
        view.setEmail(auth.getCurrentUser().getEmail());
    }

    private void setFullName() {
        view.setFullName(user.getName());
    }

    private void setNickName() {
        view.setNickName(user.getNick());
    }

    private void setUserIcon() {
        view.setUserIcon(user.getImageURL());
    }

    @Override
    public void setUserInfo(User user) {
        this.user = user;
        setFieldsInView();
    }

    private void setFieldsInView() {
        setFullName();
        setEmail();
        setUserIcon();
        setNickName();
    }

    public String  getUserUid() {
        return auth.getCurrentUser().getUid();
    }
}
