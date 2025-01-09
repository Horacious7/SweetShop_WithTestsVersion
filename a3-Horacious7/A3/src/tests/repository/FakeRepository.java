package tests.repository;

import main.domain.BirthdayCake;
import main.repository.IRepository;

public class FakeRepository implements IRepository<Integer, BirthdayCake<Integer>> {
    public boolean findAllShouldThrowException = false;

    @Override
    public Integer add(BirthdayCake<Integer> cake) { return null; }

    @Override
    public void update(Integer id, BirthdayCake<Integer> updatedCake) {}

    @Override
    public void delete(Integer id){}

    @Override
    public Iterable<BirthdayCake<Integer>> getAll() { //I am testing it in InMemoryFilteredRepositoryTest
        if (findAllShouldThrowException) {
            throw new RuntimeException("The repository is empty!");
        }
        return null;
    }



}
