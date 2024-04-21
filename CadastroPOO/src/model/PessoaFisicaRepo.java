/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PessoaFisicaRepo {
    private List<PessoaFisica> pessoasFisicas;

    public PessoaFisicaRepo() {
        this.pessoasFisicas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public void alterar(PessoaFisica pessoaFisica) {
        for (PessoaFisica pf : pessoasFisicas) {
            if (pf.getId() == pessoaFisica.getId()) {
                pf.setNome(pessoaFisica.getNome());
                pf.setCpf(pessoaFisica.getCpf());
                pf.setIdade(pessoaFisica.getIdade());
                return;
            }
        }
        throw new IllegalArgumentException("Pessoa Física com o ID " + pessoaFisica.getId() + " não encontrada.");
    }

    public void excluir(int id) {
        Iterator<PessoaFisica> iterator = pessoasFisicas.iterator();
        while (iterator.hasNext()) {
            PessoaFisica pf = iterator.next();
            if (pf.getId() == id) {
                iterator.remove();
                return;
            }
        }
        throw new IllegalArgumentException("Pessoa Física com o ID " + id + " não encontrada.");
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica pf : pessoasFisicas) {
            if (pf.getId() == id) {
                return pf;
            }
        }
        throw new IllegalArgumentException("Pessoa Física com o ID " + id + " não encontrada.");
    }

    public List<PessoaFisica> obterTodos() {
        return new ArrayList<>(pessoasFisicas);
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(pessoasFisicas);
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            pessoasFisicas = (List<PessoaFisica>) inputStream.readObject();
        }
    }
}
