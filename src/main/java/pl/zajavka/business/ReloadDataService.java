package pl.zajavka.business;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ReloadDataService {

    private final CustomerService customerService;
    private final ProductService productService;
    private final RandomDataService randomDataService;

    @Transactional
    public void loadRandomData(){
        customerService.removeAll();
        productService.removeAll();
        randomDataService.create();
    }

}
