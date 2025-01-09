package main.repository;

import main.domain.BirthdayCake;
import main.domain.Order;
import main.repository.file.binary.BinaryFileCakeRepository;
import main.repository.file.binary.BinaryFileOrderRepository;
import main.repository.file.text.TextFileCakeRepository;
import main.repository.file.text.TextFileOrderRepository;
import main.repository.memory.InMemoryCakeRepository;
import main.repository.memory.InMemoryOrderRepository;


public class CreateRepository {
    public static IRepository<Integer, BirthdayCake<Integer>> createCakeRepository(String repositoryType, String filename) {
        return switch (repositoryType.toLowerCase()) {
            case "binary" -> new BinaryFileCakeRepository(filename);
            case "text" -> new TextFileCakeRepository(filename);
            default -> new InMemoryCakeRepository();
        };
    }

    public static IRepository<Integer, Order<Integer>> createOrderRepository(String repositoryType, String filename) {
        return switch (repositoryType.toLowerCase()) {
            case "binary" -> new BinaryFileOrderRepository(filename);
            case "text" -> new TextFileOrderRepository(filename);
            default -> new InMemoryOrderRepository();
        }
        ;
    }
}
