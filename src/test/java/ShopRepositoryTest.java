import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void removeById_success() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(1, "Laptop", 1000);
        Product p2 = new Product(2, "Mouse", 50);

        repo.add(p1);
        repo.add(p2);

        // Удаляем существующий товар
        repo.removeById(1);

        // Проверяем, что p1 удалён, а p2 остался
        Product[] all = repo.findAll();
        assertEquals(1, all.length);
        assertEquals(2, all[0].getId());
    }

    @Test
    void removeById_notFound_throwsException() {
        ShopRepository repo = new ShopRepository();
        Product p = new Product(1, "Keyboard", 80);
        repo.add(p);

        // Пытаемся удалить несуществующий ID
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(999);
        });
    }
}
