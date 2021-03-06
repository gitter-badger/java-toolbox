package com.javatoolbox.unit;

import com.javatoolbox.HomepageController;
import com.javatoolbox.ToolboxApplication;
import com.javatoolbox.categories.CategoriesRepository;
import com.javatoolbox.categories.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ToolboxApplication.class})
@WebAppConfiguration
public class HomepageControllerTest {
    @InjectMocks
    HomepageController homepageController;

    @Mock
    CategoriesRepository categoriesRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(homepageController).build();
    }

    @Test
    public void homepageIndex_rendersIndex() throws Exception {
        List<Category> categories = new ArrayList<>();

        when(categoriesRepository.findAllByOrderByNameAsc()).thenReturn(categories);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("categories", categories));
    }
}