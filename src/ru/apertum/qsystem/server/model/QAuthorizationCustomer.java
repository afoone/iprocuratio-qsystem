/*
 *  Copyright (C) 2010 {Apertum}Projects. web: www.apertum.ru email: info@apertum.ru
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ru.apertum.qsystem.server.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class for additional information for customer. Es una clase para mantener
 * datos del customer, si se consideran necesarios
 *
 * @author Evgeniy Egorov
 */
@Entity
@Table(name = "clients_authorization")
public class QAuthorizationCustomer implements Serializable {

    /**
     * Versión para la serialización
     */
    private static final long serialVersionUID = -4373378736874515730L;

    /**
     * Constructor vacío
     */
    public QAuthorizationCustomer() {
    }

    /**
     * Constructor con el nombre de pila
     * 
     * @param name
     */
    public QAuthorizationCustomer(String name) {
        this.name = name;
    }

    /**
     * Identificador
     */
    @Id
    @Column(name = "id")
    @Expose
    @SerializedName("id")
    private Long id;

    /**
     * autorización ¿? no se para que es
     */
    @Column(name = "auth_id")
    @Expose
    @SerializedName("auth_id")
    private String authId;

    /**
     * Nombre de pila
     */
    @Column(name = "name")
    @Expose
    @SerializedName("name")
    private String name;

    /**
     * El 'middle name', podría usarse para el segundo apellido
     */
    @Column(name = "otchestvo")
    @Expose
    @SerializedName("otchestvo")
    private String otchestvo;

    /**
     * Apellido
     */
    @Column(name = "surname")
    @Expose
    @SerializedName("surname")
    private String surname;

    /**
     * Cumpleaños, no se para qué
     */
    @Column(name = "birthday")
    @Expose
    @SerializedName("birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    /**
     * Comentarios
     */
    @Column(name = "comments")
    @Expose
    @SerializedName("comments")
    private String comments;

    // *****************
    // GETTERS Y SETTERS
    // *****************

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

}
