/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaRepo() {
        this.pessoasJuridicas = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(int id, PessoaJuridica pessoaJuridica) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJuridica pj = pessoasJuridicas.get(i);
            if (pj.getId() == id) {
                pessoasJuridicas.set(i, pessoaJuridica);
                return;
            }
        }
        throw new IllegalArgumentException("Pessoa Jurídica com o ID " + id + " não encontrada.");
    }

    public void excluir(int id) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJuridica pj = pessoasJuridicas.get(i);
            if (pj.getId() == id) {
                pessoasJuridicas.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Pessoa Jurídica com o ID " + id + " não encontrada.");
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pj : pessoasJuridicas) {
            if (pj.getId() == id) {
                return pj;
            }
        }
        throw new IllegalArgumentException("Pessoa Jurídica com o ID " + id + " não encontrada.");
    }

    public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoasJuridicas);
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(pessoasJuridicas);
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasJuridicas = (List<PessoaJuridica>) inputStream.readObject();
        }
    }

    public void alterar(PessoaJuridica pessoa) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}