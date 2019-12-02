package edu.mum.cs544;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("edu.mum.cs544")
public class Config {

    @Bean
    public List<IBookSupplier> getSuppliers() {
        List<IBookSupplier> suppliers = new ArrayList<IBookSupplier>();

        IBookSupplier amazon = new Amazon();
        IBookSupplier barnesandnoble = new BarnesAndNoble();
        IBookSupplier ebooks = new EBooks();
        IBookSupplier borders = new Borders();

        suppliers.add(amazon);
        suppliers.add(barnesandnoble);
        suppliers.add(ebooks);
        suppliers.add(borders);

        return suppliers;
    }
}
