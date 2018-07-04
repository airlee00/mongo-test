package com.mkyong;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mkyong.domain.Domain;
import com.mkyong.domain.DomainRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(DomainRepository domainRepository) {

        return args -> {

            Optional<Domain> obj = domainRepository.findById(7L);
            System.out.println("=================1:" + obj);

            Domain obj2 = domainRepository.findFirstByDomain("mkyong.com");
            System.out.println("===2:" + obj2);

            //obj2.setDisplayAds(true);
            //domainRepository.save(obj2);

            long n = domainRepository.updateDomain("mkyong.com", true);
            System.out.println("Number of records updated : " + n);

            //Domain obj3 = domainRepository.findOne(2000001L);
            //System.out.println(obj3);

            //Domain obj4 = domainRepository.findCustomByDomain("google.com");
            //System.out.println(obj4);

            //List<Domain> obj5 = domainRepository.findCustomByRegExDomain("google");
            //obj5.forEach(x -> System.out.println(x));

        };

    }

    //remove _class
        /*MappingMongoConverter converter =
                new MappingMongoConverter(mongoDbFactory, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));*/
    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
                                       MongoMappingContext context) {

        MappingMongoConverter converter =
                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

        return mongoTemplate;

    }

}
