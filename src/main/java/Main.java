import com.example.config.AppConfig;
import com.example.entities.Product;
import com.example.repositories.Cart;
import com.example.repositories.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);

        Scanner scanner = new Scanner(System.in);
        Cart cart = context.getBean(Cart.class);

        while (true) {
            System.out.println("1. Додати товар до кошика");
            System.out.println("2. Видалити товар з кошика");
            System.out.println("3. Показати кошик");
            System.out.println("0. Вийти");
            System.out.print("Виберіть дію: ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    System.out.print("Введіть ID товару: ");
                    int productId = scanner.nextInt();
                    Product productToAdd = productRepository.getProductById(productId);
                    if (productToAdd != null) {
                        cart.addItem(productToAdd);
                        System.out.println("Товар додано до кошика.");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;
                case 2:
                    System.out.print("Введіть ID товару для видалення: ");
                    int productIdToRemove = scanner.nextInt();
                    cart.removeItem(productIdToRemove);
                    System.out.println("Товар видалено з кошика.");
                    break;
                case 3:
                    int priceAllItems = 0;
                    if (cart.getItems() == null || cart.getItems().isEmpty()) {
                        System.out.println("У кошику немає товарів");
                    } else {
                        System.out.println("Товари у кошику:");
                    }

                    for (Product item : cart.getItems()) {
                        System.out.println(item.getName() + " - " + item.getPrice());
                        priceAllItems += item.getPrice();
                    }
                    System.out.println("Ціна товарів у кошику: " + priceAllItems);
                    break;
                default:
                    System.out.println("Невірний вибір.");
            }
        }

        System.out.println("Програма завершила роботу.");
    }
}
