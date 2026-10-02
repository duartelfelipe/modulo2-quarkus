package br.com.quarkus.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Pessoa extends PanacheEntity {
    public String nome;
    public int idade;

    public static List<Pessoa> findByIdade(int idade) {
        return find("idade", idade).list();
    }
}
