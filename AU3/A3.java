import java.util.ArrayList; 
import java.util.Scanner;
import java.util.Locale;

// -- CLASSE BASE Funcionario
class FUNCIONARIO {
    String nome;
    int matricula;
    double salarioBase = 2000; // Constante da regra

    public double calcularSalario() {
        return salarioBase; 
    }

    // Exibe detalhes do funcionário padrão
    public void exibirDetalhes() {
        System.out.println("\nNome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.printf("Salário Fixo: R$ %.2f%n", salarioBase);
        System.out.printf("Extras: R$ 0.00%n");
        System.out.printf("Salário Final: R$ %.2f%n", calcularSalario());
    }
}

// -- CLASSE COMISSÃO
class COMISSAO extends FUNCIONARIO {
    double vendas;
    double percentual;

    @Override 
    public double calcularSalario() {
        return salarioBase + (vendas * percentual / 100);
    }

    // Exibe detalhes do funcionário comissionado, mostrando a comissão separada
    @Override
    public void exibirDetalhes() {
        System.out.println("\nNome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.printf("Salário Fixo: R$ %.2f%n", salarioBase);
        System.out.printf("Comissão: R$ %.2f%n", (vendas * percentual / 100));
        System.out.printf("Salário Final: R$ %.2f%n", calcularSalario());
    }
}

// -- CLASSE PRODUÇÃO
class PRODUCAO extends FUNCIONARIO {
    int quantidade;
    double valorPorPeca;

    @Override
    public double calcularSalario() {
        return salarioBase + (quantidade * valorPorPeca);
    }

    // Exibe detalhes do funcionário de produção, mostrando o bônus de produtividade separado
    @Override
    public void exibirDetalhes() {
        System.out.println("\nNome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.printf("Salário Fixo: R$ %.2f%n", salarioBase);
        System.out.printf("Produtividade: R$ %.2f%n", (quantidade * valorPorPeca));
        System.out.printf("Salário Final: R$ %.2f%n", calcularSalario());
    }
}

public class A3 { // -- Main principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US); // Reconhece . ou , 
        ArrayList<FUNCIONARIO> lista = new ArrayList<>();
        int opcao;

        do { 
            // -- Menu de navegação colocado DENTRO do do-while
            System.out.println("\n1 -- Funcionário Padrão");
            System.out.println("2 -- Funcionário Comissionado");
            System.out.println("3 -- Funcionário Produção");
            System.out.println("4 -- Folha de Pagamento");
            System.out.println("5 -- Excluir Funcionário");
            System.out.println("0 -- Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            
            if (opcao < 0 || opcao > 5) {
                System.out.println("Opção inválida. Digite um número entre 0 e 5.");
                continue;
            }

            switch (opcao) {
                case 1: 
                    FUNCIONARIO F1 = new FUNCIONARIO();
                    System.out.print("Nome: ");
                    sc.nextLine(); 
                    F1.nome = sc.nextLine();
                    System.out.print("Matrícula: ");
                    F1.matricula = sc.nextInt();
                    
                    while (F1.matricula <= 0) { 
                        System.out.println("Valor de matrícula inválido. Por favor, informe um valor positivo.");
                        System.out.print("Matrícula: ");
                        F1.matricula = sc.nextInt();
                    }
                    lista.add(F1);
                    System.out.println("Funcionário padrão cadastrado com sucesso!");
                    break;

                case 2:
                    COMISSAO c = new COMISSAO();
                    System.out.print("Nome: ");
                    sc.nextLine(); 
                    c.nome = sc.nextLine();
                    System.out.print("Matrícula: ");
                    c.matricula = sc.nextInt();
                    
                    while (c.matricula <= 0) { 
                        System.out.println("Valor da matrícula inválido. Informe um valor positivo:");
                        System.out.print("Matrícula: ");
                        c.matricula = sc.nextInt();  
                    }
                    
                    System.out.print("Informe valor das vendas: ");
                    c.vendas = sc.nextDouble();
                    while (c.vendas < 0) {
                        System.out.println("Valor inválido! Digite um valor positivo para vendas:");
                        c.vendas = sc.nextDouble();                
                    }
                    
                    System.out.print("Informe comissão percentual: ");
                    c.percentual = sc.nextDouble();
                    while (c.percentual < 0 || c.percentual > 100) { 
                        System.out.println("Valor de percentual inválido. Informe um valor entre 0 e 100!");
                        System.out.print("Percentual: ");
                        c.percentual = sc.nextDouble();
                    }
                    lista.add(c);
                    System.out.println("Funcionário comissionado cadastrado com sucesso!");
                    break;

                case 3:
                    PRODUCAO p = new PRODUCAO(); 
                    System.out.print("Nome: ");
                    sc.nextLine(); 
                    p.nome = sc.nextLine();
                    System.out.print("Matrícula: ");
                    p.matricula = sc.nextInt();
                    
                    while (p.matricula <= 0) { 
                        System.out.println("Valor de matrícula inválido. Digite um valor positivo.");
                        System.out.print("Matrícula: ");
                        p.matricula = sc.nextInt();
                    }
                    
                    System.out.print("Informe qtde de peças: ");
                    p.quantidade = sc.nextInt();
                    while (p.quantidade < 0) { 
                        System.out.println("Valor de quantidade inválido. Digite um valor positivo.");
                        System.out.print("Quantidade: ");
                        p.quantidade = sc.nextInt();
                    }
                    
                    System.out.print("Informe valor da peça: ");
                    p.valorPorPeca = sc.nextDouble();
                    while (p.valorPorPeca < 0) { 
                        System.out.println("Valor por peça inválido. Digite um valor positivo.");
                        System.out.print("Valor por peça: ");
                        p.valorPorPeca = sc.nextDouble();
                    }
                    lista.add(p);
                    System.out.println("Funcionário de produção cadastrado com sucesso!");
                    break;

                case 4:
                    System.out.println("\n--- FOLHA DE PAGAMENTO ---");
                    System.out.println("Total de pessoas cadastradas: " + lista.size());

                    // Cada objeto chama sua própria versão de exibirDetalhes() (polimorfismo)
                    for (FUNCIONARIO f : lista) { 
                        f.exibirDetalhes();
                    }
                    break;

                case 5:
                    System.out.println("\n-- Excluir Funcionário --");
                    if (lista.isEmpty()) {
                        System.out.println("A lista está vazia. Não há ninguém para excluir.");
                        break;
                    }
                
                    System.out.print("Digite a matrícula do funcionário para excluir: ");
                    int matriculaBusca = sc.nextInt();

                    boolean encontrado = false;

                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).matricula == matriculaBusca) {
                            System.out.println("Funcionário encontrado: " + lista.get(i).nome);
                            lista.remove(i);
                            System.out.println("Funcionário excluído com sucesso!");
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("Funcionário com a matrícula " + matriculaBusca + " não foi encontrado.");
                    }
                    break;
            }

        } while (opcao != 0);

        System.out.println("Sistema encerrado.");
        sc.close();
    }
}

