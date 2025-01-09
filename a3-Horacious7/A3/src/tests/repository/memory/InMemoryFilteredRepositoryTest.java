package tests.repository.memory;


import main.filter.CakeFilters.FilterCakeByFlavour;
import main.repository.memory.InMemoryFilteredRepository;
import org.junit.jupiter.api.Test;
import tests.repository.FakeRepository;

public class InMemoryFilteredRepositoryTest {
    public FilterCakeByFlavour<Integer> filterByFlavour = new FilterCakeByFlavour<>("ananas");


    @Test
    public void test_findAll_emptyRepository_throwsException(){
        FakeRepository fakeRepository = new FakeRepository();
        fakeRepository.findAllShouldThrowException = true;
        InMemoryFilteredRepository filteredRepository = new InMemoryFilteredRepository<>(fakeRepository, filterByFlavour);
        try{
            filteredRepository.getAll();
            assert false;
        }catch (Exception e){}
    }
}
