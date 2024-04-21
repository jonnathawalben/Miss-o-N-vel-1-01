/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastropoo;

/**
 *
 * @author jhon
 */
import model.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CadastroPOO {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static final PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            switch (opcao) {
                case 1 -> incluirPessoa();
                case 2 -> alterarPessoa();
                case 3 -> excluirPessoa();
                case 4 -> exibirPessoaPorId();
                case 5 -> exibirTodasPessoas();
                case 6 -> salvarDados();
                case 7 -> recuperarDados();
                case 0 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("======== Menu ========");
        System.out.println("1 - Incluir Pessoa");
        System.out.println("2 - Alterar Pessoa");
        System.out.println("3 - Excluir Pessoa");
        System.out.println("4 - Exibir Pessoa pelo ID");
        System.out.println("5 - Exibir Todos");
        System.out.println("6 - Persistir Dados");
        System.out.println("7 - Recuperar Dados");
        System.out.println("0 - Finalizar Programa");
        System.out.println("=======================");
        System.out.print("Escolha uma opção:");
    }

    private static int lerOpcao() {
        int opcao;
        try {
            opcao = scanner.nextInt();
        } catch (InputMismatchException e) {
            opcao = -1; 
        }
        return opcao;
    }

    private static void incluirPessoa() {
        System.out.println("Incluir Pessoa (1 - Física, 2 - Jurídica): ");
        int tipo = lerOpcao();
        switch (tipo) {
            case 1 -> incluirPessoaFisica();
            case 2 -> incluirPessoaJuridica();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void incluirPessoaFisica() {
        System.out.print("Digite o ID da pessoa física: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite o nome da pessoa física: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF da pessoa física: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a idade da pessoa física: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 
        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
        System.out.println("Pessoa física adicionada com sucesso!");
    }

    private static void incluirPessoaJuridica() {
        System.out.print("Digite o ID da pessoa jurídica: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite o nome da pessoa jurídica: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CNPJ da pessoa jurídica: ");
        String cnpj = scanner.nextLine();
        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
        System.out.println("Pessoa jurídica adicionada com sucesso!");
    }


    private static void alterarPessoaFisica() {
        System.out.println("Digite o ID da pessoa física a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        PessoaFisica pessoa = repoFisica.obter(id);
        if (pessoa != null) {
            System.out.println("Digite o novo nome da pessoa física: ");
            String nome = scanner.nextLine();
            System.out.println("Digite o novo CPF da pessoa física: ");
            String cpf = scanner.nextLine();
            System.out.println("Digite a nova idade da pessoa física: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setIdade(idade);
            repoFisica.alterar(pessoa);
            System.out.println("Pessoa física alterada com sucesso!");
        } else {
            System.out.println("Pessoa física não encontrada!");
        }
    }

    private static void alterarPessoaJuridica() {
        System.out.println("Digite o ID da pessoa jurídica a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        PessoaJuridica pessoa = repoJuridica.obter(id);
        if (pessoa != null) {
            System.out.println("Digite o novo nome da pessoa jurídica: ");
            String nome = scanner.nextLine();
            System.out.println("Digite o novo CNPJ da pessoa jurídica: ");
            String cnpj = scanner.nextLine();
            pessoa.setNome(nome);
            pessoa.setCnpj(cnpj);
            repoJuridica.alterar(id, pessoa);
            System.out.println("Pessoa jurídica alterada com sucesso!");
        } else {
            System.out.println("Pessoa jurídica não encontrada!");
        }
    }

    private static void excluirPessoa() {
        System.out.println("Escluir pessoa (1 - Física, 2 - Jurídica): ");
        int tipo = lerOpcao();
        switch (tipo) {
            case 1 -> excluirPessoaFisica();
            case 2 -> excluirPessoaJuridica();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void excluirPessoaFisica() {
        System.out.println("Digite o ID da pessoa física a ser excluída: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (repoFisica.obter(id) != null) {
            repoFisica.excluir(id);
            System.out.println("Pessoa física excluída com sucesso!");
        } else {
            System.out.println("Pessoa física não encontrada!");
        }
    }

    private static void excluirPessoaJuridica() {
        System.out.println("Digite o ID da pessoa jurídica a ser excluída: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (repoJuridica.obter(id) != null) {
            repoJuridica.excluir(id);
            System.out.println("Pessoa jurídica excluída com sucesso!");
        } else {
            System.out.println("Pessoa jurídica não encontrada!");
        }
    }

    private static void exibirPessoaPorId() {
        System.out.println("Exibir pessoa (1 - Física, 2 - Jurídica): ");
        int tipo = lerOpcao();
        switch (tipo) {
            case 1 -> exibirPessoaFisicaPorId();
            case 2 -> exibirPessoaJuridicaPorId();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void exibirPessoaFisicaPorId() {
        System.out.println("Digite o ID da pessoa física a ser exibida: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        PessoaFisica pessoa = repoFisica.obter(id);
        if (pessoa != null) {
            System.out.println("Pessoa física encontrada:");
            System.out.println(pessoa);
        } else {
            System.out.println("Pessoa física não encontrada!");
        }
    }

    private static void exibirPessoaJuridicaPorId() {
        System.out.println("Digite o ID da pessoa jurídica a ser exibida: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        PessoaJuridica pessoa = repoJuridica.obter(id);
        if (pessoa != null) {
            System.out.println("Pessoa jurídica encontrada:");
            System.out.println(pessoa);
        } else {
            System.out.println("Pessoa jurídica não encontrada!");
        }
    }

    private static void exibirTodasPessoas() {
        System.out.println("Exibir todas pessoas (1 - Física, 2 - Jurídica): ");
        int tipo = lerOpcao();
        switch (tipo) {
            case 1 ->                 {
                    List<PessoaFisica> pessoas = repoFisica.obterTodos();
                    if (!pessoas.isEmpty()) {
                        System.out.println("Pessoas físicas encontradas:");
                        for (PessoaFisica pessoa : pessoas) {
                            System.out.println(pessoa);
                        }
                    } else {
                        System.out.println("Nenhuma pessoa física cadastrada!");
                    }                      }
            case 2 ->                 {
                    List<PessoaJuridica> pessoas = repoJuridica.obterTodos();
                    if (!pessoas.isEmpty()) {
                        System.out.println("Pessoas jurídicas encontradas:");
                        for (PessoaJuridica pessoa : pessoas) {
                            System.out.println(pessoa);
                        }
                    } else {
                        System.out.println("Nenhuma pessoa jurídica cadastrada!");
                    }                      }
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void salvarDados() {
        System.out.println("Salvando dados...");
        try {
            repoFisica.persistir("pessoas_fisicas.dat");
            repoJuridica.persistir("pessoas_juridicas.dat");
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados() {
        System.out.println("Recuperando dados...");
        try {
            repoFisica.recuperar("pessoas_fisicas.dat");
            repoJuridica.recuperar("pessoas_juridicas.dat");
            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }

    private static void alterarPessoa() {
    System.out.println("Alterar pessoa (1 - Física, 2 - Jurídica): ");
    int tipo = lerOpcao();
    switch (tipo) {
        case 1 -> alterarPessoaFisica();
        case 2 -> alterarPessoaJuridica();
        default -> System.out.println("Opção inválida!");
    }
}
}