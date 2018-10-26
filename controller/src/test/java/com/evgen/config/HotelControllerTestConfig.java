package com.evgen.config;

import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.evgen.dao.HotelDao;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.evgen.controller")
public class HotelControllerTestConfig {

  @Bean
  public HotelDao hotelDao() {
    return EasyMock.createMock(HotelDao.class);
  }

}
