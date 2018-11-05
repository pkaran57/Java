package com.ker.springboot.home;

import com.ker.springboot.zip.ZipDaoJDBC;
import com.ker.springboot.zip.ZipDaoJPA;
import com.ker.springboot.zip.ZipDaoSpringJpa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

  @MockBean
  ZipDaoJPA zipDaoJPA;

  @MockBean
  ZipDaoSpringJpa zipDaoSpringJpa;

  @MockBean
  ZipDaoJDBC zipDaoJDBC;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testHomePage() throws Exception {
    mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("home"))
            .andExpect(content().string(
                    containsString("Welcome to...")));
  }
}