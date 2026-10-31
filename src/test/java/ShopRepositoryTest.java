import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void add_success() {
        ShopRepository repo = new ShopRepository();
        Product p = new Product(1, "Headphones", 200);

        repo.add(p);

        Product[] all = repo.findAll();
        assertEquals(1, all.length);
        assertEquals(1, all[0].getId());
        assertEquals("Headphones", all[0].getTitle());
        assertEquals(200, all[0].getPrice());
    }

    @Test
    void add_duplicateId_throwsException() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(5, "Monitor", 15000);
        Product p2 = new Product(5, "Another Monitor", 14000); // тот же ID

        repo.add(p1);

        AlreadyExistsException thrown = assertThrows(
                AlreadyExistsException.class,
                () -> repo.add(p2)
        );

        assertTrue(thrown.getMessage().contains("5"));
        assertTrue(thrown.getMessage().contains("already exists"));
    }

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
