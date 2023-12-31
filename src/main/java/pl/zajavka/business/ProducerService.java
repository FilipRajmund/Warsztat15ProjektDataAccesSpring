package pl.zajavka.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.domain.Producer;

@Service
@AllArgsConstructor
public class ProducerService {
    private final ProductService productService;
    private final ProducerRepository producerRepository;
    @Transactional
    public void removeAll(){
        productService.removeAll();
        producerRepository.removeAll();
    }

    @Transactional
    public Producer create(Producer producer) {
        return producerRepository.create(producer);
    }
}
