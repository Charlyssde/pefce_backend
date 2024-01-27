package com.devx.software.ferias.Entities.Profiles;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProfileMenuKey implements Serializable {
    @Column(name = "perfil_id")
    private Long perfil;
    @Column(name = "menu_id")
    private Long menu;

    public ProfileMenuKey() {
    }

    public ProfileMenuKey(Long perfil, Long menu) {
        this.perfil = perfil;
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.perfil);
        hash = 79 * hash + Objects.hashCode(this.menu);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProfileMenuKey other = (ProfileMenuKey) obj;
        if (!Objects.equals(this.perfil, other.perfil)) {
            return false;
        }
        return Objects.equals(this.menu, other.menu);
    }
}
