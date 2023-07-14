package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.Objects;

public class MemberPermission {
    private static MemberPermission INSTANCE = null;
    private Role role_op = null;
    private Role role_staff = null;

    public MemberPermission() {
        role_op = Objects.requireNonNull(App.getJda().getGuildById(
                App.getGuildId()
        )).getRoleById(
            App.getConfiguration("R_OP")
        );

        role_staff = Objects.requireNonNull(App.getJda().getGuildById(
                App.getGuildId()
        )).getRoleById(
            App.getConfiguration("R_STAFF")
        );
    }

    public static MemberPermission getInstance() {
        if(MemberPermission.INSTANCE == null) {
            MemberPermission.INSTANCE = new MemberPermission();
        }

        return INSTANCE;
    }

    public boolean isOpMember(Member member) {
        if(member.getRoles().contains(role_op)){
            return true;
        }

        return false;
    }

    public boolean isStaffMember(Member member) {
        if(member.getRoles().contains(role_op) || member.getRoles().contains(role_staff)){
            return true;
        }

        return false;
    }

}
