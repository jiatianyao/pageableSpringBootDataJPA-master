package guru.springframework.bootstrap;

import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;

    private Logger log = Logger.getLogger(ProductLoader.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Product shirt = new Product();
        shirt.setDescription("Spring Framework Guru Shirt");
        shirt.setPrice(new BigDecimal("21.95"));
        shirt.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_shirt-rf412049699c14ba5b68bb1c09182bfa2_8nax2_512.jpg");
        shirt.setProductId("235268845711068308");
        productRepository.save(shirt);

        log.info("Saved Shirt - id: " + shirt.getId());

        Product mug = new Product();
        mug.setDescription("Spring Framework Guru Mug");
        mug.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug.setProductId("168639393495335947");
        mug.setPrice(new BigDecimal("29.95"));
        productRepository.save(mug);


        Product mug2 = new Product();
        mug2.setDescription("Spring Framework Guru Mug");
        mug2.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug2.setProductId("168639393495335947");
        mug2.setPrice(new BigDecimal("28.95"));
        productRepository.save(mug2);

        Product mug3 = new Product();
        mug3.setDescription("Spring Framework Guru Mug");
        mug3.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug3.setProductId("168639393495335947");
        mug3.setPrice(new BigDecimal("27.95"));
        productRepository.save(mug3);


        Product mug4 = new Product();
        mug4.setDescription("Spring Framework Guru Mug");
        mug4.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug4.setProductId("168639393495335947");
        mug4.setPrice(new BigDecimal("26.95"));
        productRepository.save(mug4);


        Product mug5 = new Product();
        mug5.setDescription("Spring Framework Guru Mug");
        mug5.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug5.setProductId("168639393495335947");
        mug5.setPrice(new BigDecimal("25.95"));
        productRepository.save(mug5);


        Product mug6 = new Product();
        mug6.setDescription("Spring Framework Guru Mug");
        mug6.setImageUrl("https://springframework.guru/wp-content/uploads/2015/04/spring_framework_guru_coffee_mug-r11e7694903c348e1a667dfd2f1474d95_x7j54_8byvr_512.jpg");
        mug6.setProductId("168639393495335947");
        mug6.setPrice(new BigDecimal("24.95"));
        productRepository.save(mug6);





        log.info("Saved Mug - id:" + mug.getId());
    }
}
