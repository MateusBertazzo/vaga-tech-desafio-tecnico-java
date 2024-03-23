import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ProductManagement {
    private static List<Product> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("**Cadastro de Produtos**");
            System.out.println("1 - Cadastrar novo produto");
            System.out.println("2 - Listar produtos");
            System.out.println("0 - Sair do Aplicativo");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    registerProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void registerProduct() {
        System.out.print("Nome do produto: ");
        String nome = scanner.next();
        scanner.nextLine();

        System.out.print("Descrição do produto: ");
        String description = scanner.nextLine();

        double price;
        while (true) {

            System.out.print("Valor do produto: ");

            try {
                // aqui poderia adicionar mais verificações deixei só como exemplo
                // no caso se for um numero saíra do loop se não for irá pedir para digitar novamente
                price = scanner.nextDouble();

                break;

            } catch (InputMismatchException e) {
                scanner.nextLine();

                System.out.println();
                System.out.println("Valor do produto deve ser um número. Tente novamente.");
                System.out.println();
            }
        }

        System.out.print("Disponível para venda (sim/não): ");
        String availableStr = scanner.next();
        boolean availableForSale = "sim".equalsIgnoreCase(availableStr);

        Product product = new Product(nome, description, price, availableForSale);
        products.add(product);

        System.out.println("Produto cadastrado com sucesso!");
        System.out.println();

        listProducts();
        saveProductsToFile(products);
    }

    private static void listProducts() {
        System.out.println("**Lista de Produtos**");
        System.out.println("-----------------------");

        // primeiro converto a lista de produtos para um stream, depois ordeno os produtos pelo preço,
        // e por fim imprimo cada produto.
        products.stream().sorted(Comparator.comparingDouble(Product::getPrice))
                .forEach(product -> {
                    System.out.println(product);
                    System.out.println();
                });
    }

    // Este metodo é responsavel por adicionar um produto cadastrado a um arquivo txt, simulando um banco.
    // Não foi pedido nos requisitos, mas quis fazer um extra.
    public static void saveProductsToFile(List<Product> products)  {

        // crio o arquivo para guardar os produtos
        File file = new File("produtos.txt");

        try (FileWriter fileWriter = new FileWriter(file)) {

            // percorro cada produto escrevendo-os no arquivo
            for (Product product : products) {
                fileWriter.write(product.toString() + "\n");
                fileWriter.write("-----------------------\n");
            }

            // "file.getAbsolutePath()" é o caminho completo até o arquivo desde da raiz do PC
            System.out.println("-----------------------");
            System.out.println("Produto salvo com sucesso no arquivo " + file.getAbsolutePath());
            System.out.println("-----------------------");
            System.out.println();

        } catch (IOException e) {
            System.err.println("Erro ao salvar produto no arquivo: " + e.getMessage());
        }
    }
}