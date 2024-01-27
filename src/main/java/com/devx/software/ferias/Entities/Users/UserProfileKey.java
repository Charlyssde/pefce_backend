package com.devx.software.ferias.Entities.Users;

import java.io.Serializable;
import java.util.Objects;

public class UserProfileKey implements Serializable {
    private Long perfil;
    private Long usuario;

    public UserProfileKey() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.perfil);
        hash = 71 * hash + Objects.hashCode(this.usuario);
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
        final UserProfileKey other = (UserProfileKey) obj;
        if (!Objects.equals(this.perfil, other.perfil)) {
            return false;
        }
        return Objects.equals(this.usuario, other.usuario);
    }


}
