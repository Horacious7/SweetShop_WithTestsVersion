package tests.repository.memory;

import main.repository.memory.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.repository.TestEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryRepositoryTest {
    private InMemoryRepository<Integer, TestEntity> repository;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryRepository<>();
    }

    @Test
    public void testAdd_addNewEntity_successful(){
        TestEntity testEntity = new TestEntity(1, "Name");
        Integer id = repository.add(testEntity);

        boolean entityFound = false;
        for (TestEntity entity : repository.getAll()) {
            if (entity.getId().equals(id)) {
                entityFound = true;
                break;
            }
        }

        assertTrue(entityFound, "Entity should be added successfully!");
    }

    @Test
    public void testFindAll_addTwoEntitiesThenSizeIsTwo_successful(){
        TestEntity firstTestEntity = new TestEntity(1, "Name1");
        TestEntity secondTestEntity = new TestEntity(2, "Name2");

        repository.add(firstTestEntity);
        repository.add(secondTestEntity);
        Iterable<TestEntity> entities = repository.getAll();
        ArrayList<TestEntity> entityList = new ArrayList<>();
        entities.forEach(entityList::add);

        assertEquals(2, entityList.size());
    }

    @Test
    public void testFindById_addEntityThenFindById_successful(){
        TestEntity firstTestEntity = new TestEntity(1, "Name1");
        Integer id = repository.add(firstTestEntity);

        TestEntity foundEntity = null;
        for (TestEntity entity : repository.getAll()) {
            if (entity.getId().equals(id)) {
                foundEntity = entity;
                break;
            }
        }

        assertNotNull(foundEntity, "Entity should be found successfully!");
    }

    @Test
    public void testModify_modifyOneEntity_successful(){
        TestEntity firstTestEntity = new TestEntity(1, "Name1");
        Integer id = repository.add(firstTestEntity);

        TestEntity newTestEntity = new TestEntity(1, "New Name");
        newTestEntity.setId(id);
        repository.update(id, newTestEntity);

        TestEntity foundEntity = null;
        for (TestEntity entity : repository.getAll()) {
            if (entity.getId().equals(id)) {
                foundEntity = entity;
                break;
            }
        }

        assertNotNull(foundEntity);
        assertEquals("New Name", foundEntity.getName());
    }

    @Test
    public void testDelete_addEntityThenDeleteIt_successful(){
        TestEntity testEntity = new TestEntity(1, "Name1");
        Integer id = repository.add(testEntity);

        repository.delete(id);

        TestEntity foundEntity = null;
        for (TestEntity entity : repository.getAll()) {
            if (entity.getId().equals(id)) {
                foundEntity = entity;
                break;
            }
        }

        assertNull(foundEntity, "Entity should not be found successfully!");
    }
}