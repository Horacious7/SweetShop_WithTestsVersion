package tests.repository;

import main.filter.AbstractFilter;

public class TestFilter implements AbstractFilter<TestEntity> {
    private String nameFilter;

    public TestFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    @Override
    public boolean apply(TestEntity item) {
        return item.getName().equalsIgnoreCase(nameFilter);
    }
}
