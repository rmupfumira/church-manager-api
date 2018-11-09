package za.org.rfm.client;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import za.org.rfm.SpringBootWebApplication;
import za.org.rfm.dao.AssemblyRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
@WebAppConfiguration
public class AssemblyTest {

    private MockMvc mockMvc;

    @Autowired
    private AssemblyRepository assemblyRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
/*        this.mockMvc = webApplicationContext(webApplicationContext).build();

        this.assemblyRepository.d();
        this.accountRepository.deleteAllInBatch();

        this.account = accountRepository.save(new Account(username, "password"));
        this.bookmarkList.add(bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/1/" + username, "A description")));
        this.bookmarkList.add(bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/2/" + username, "A description")));*/
    }

    @Test
    public void userNotFound() throws Exception {
   /*     mockMvc.perform(post("/george/bookmarks/")
                .content(this.json(new Bookmark(null, null, null)))
                .contentType(contentType))
                .andExpect(status().isNotFound());*/
    }

}
