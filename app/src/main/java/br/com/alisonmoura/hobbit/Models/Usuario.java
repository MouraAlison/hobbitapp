package br.com.alisonmoura.hobbit.Models;

import java.io.Serializable;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Created by alisonmoura on 25/10/15.
 */
@Table("Usuario")
public class Usuario extends Model implements Serializable {

    @AutoIncrement
    @Key
    @Column("id")
    private long id;
    @Column("nome")
    private String nome;
    @Column("email")
    private String email;
    @Column("login")
    private String login;
    @Column("senha")
    private String senha;
    @Column("foto")
    private Integer foto;

    public Usuario() {

    }

    //Getters e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    //Hash Code and Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != usuario.id) return false;
        if (nome != null ? !nome.equals(usuario.nome) : usuario.nome != null) return false;
        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        if (login != null ? !login.equals(usuario.login) : usuario.login != null) return false;
        if (senha != null ? !senha.equals(usuario.senha) : usuario.senha != null) return false;
        return !(foto != null ? !foto.equals(usuario.foto) : usuario.foto != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        result = 31 * result + (foto != null ? foto.hashCode() : 0);
        return result;
    }


    //toSring

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", foto=" + foto +
                '}';
    }
}
