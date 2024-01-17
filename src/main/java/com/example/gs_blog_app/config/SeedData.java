package com.example.gs_blog_app.config;
import com.example.gs_blog_app.entities.Account;
import com.example.gs_blog_app.entities.Authority;
import com.example.gs_blog_app.repositories.AuthorityRepository;
import com.example.gs_blog_app.services.AccountService;
import com.example.gs_blog_app.services.AuthorityService;
import com.example.gs_blog_app.services.PostService;
import com.example.gs_blog_app.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private AuthorityRepository authorityRepository;

  @Autowired
  private AuthorityService authorityService;

  //@Autowired
  //private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) {

    List<Post> posts = postService.getAll();

    if (posts.size() == 0) {

      Authority user = new Authority();
      user.setName("ROLE_USER");
      authorityService.save(user);

      Authority admin = new Authority();
      admin.setName("ROLE_ADMIN");
      authorityService.save(admin);

      Account account1 = new Account();
      account1.setFirstName("Test_user");
      account1.setLastName("Test_user_L");
      account1.setEmail("user1@gsblog.com");
      account1.setPassword("user_pass");
      Set<Authority> authorities1 = new HashSet<>();
      authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
      account1.setAuthorities(authorities1);
      //account1.setRole("ROLE_USER");

      Account account2 = new Account();
      account2.setFirstName("admin");
      account2.setLastName("admin_lastL");
      account2.setEmail("admin@gsblog.com");
      account2.setPassword("admin_pass");
      Set<Authority> authorities2 = new HashSet<>();
      authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
      account2.setAuthorities(authorities2);
      //account2.setRole("ROLE_ADMIN");

      accountService.save(account1);
      accountService.save(account2);

      Post post1 = new Post();
      post1.setTitle("Test post 1");
      post1.setBody("Post 1 body: jhf fzef uzefg ezfg ezgf ezgf gzef ezgfzegfzegfjgzekuziu eufeu");
      post1.setAccount(account1);

      Post post2 = new Post();
      post2.setTitle("Test post 2");
      post2.setBody("Post 2 body: jjha irhg ttr tgitrgtr hijjre erigjer gerigjerge rgeuhfs fdsfdhf dsfhsgdkf gfr");
      post2.setAccount(account2);


      postService.save(post1);
      postService.save(post2);
   }
  }
}
