package tests.repository.file;

import main.domain.BirthdayCake;
import main.repository.file.text.TextFileCakeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TextFileCakeRepositoryTest {
    private static final String filename = "test_cakes.txt";
    private TextFileCakeRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new TextFileCakeRepository(filename);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAdd_addANewCake_true(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Jungle", "Banana", 88.5);
        repository.add(cake);
        List<BirthdayCake<Integer>> cakes = (List<BirthdayCake<Integer>>) repository.getAll();
        assertEquals(1, cakes.size());
    }

    @Test
    public void testDelete_deleteACake_true(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Jungle", "Banana", 88.5);
        repository.add(cake);
        repository.delete(cake.getId());
        List<BirthdayCake<Integer>> cakes = (List<BirthdayCake<Integer>>) repository.getAll();
        assertTrue(cakes.isEmpty(),"Repo should be empty!");
    }

    @Test
    public void testModify_updateACakeInRepository_true(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Jungle", "Banana", 88.5);
        repository.add(cake);
        cake.setName("Dream");
        cake.setFlavour("GingerBread");
        cake.setPrice(66.5);
        repository.update(cake.getId(), cake);

        List<BirthdayCake<Integer>> cakes = (List<BirthdayCake<Integer>>) repository.getAll();
        assertEquals("Dream",cakes.getFirst().getName());
    }


    @Test
    public void testEnsureFileExists_fileExists_fileCreated(){
        File file = new File(filename);
        file.delete();

        repository = new TextFileCakeRepository(filename);
        assertTrue(file.exists(), "File should be created!");
    }

    @Test
    public void testEnsureFileExists_FileDoesNotExist_FileCreated() {
        File file = new File(filename);
        file.delete();

        repository = new TextFileCakeRepository(filename);

        assertTrue(file.exists(), "The file should be created if it doesn't exist.");
    }

}
