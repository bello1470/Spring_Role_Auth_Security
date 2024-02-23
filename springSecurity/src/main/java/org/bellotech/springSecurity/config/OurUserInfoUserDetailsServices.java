package org.bellotech.springSecurity.config;


import org.bellotech.springSecurity.model.OurUserModel;
import org.bellotech.springSecurity.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class OurUserInfoUserDetailsServices implements UserDetailsService {

    @Autowired
    private OurUserRepo ourUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<OurUserModel> user = ourUserRepo.findByEmail(username);


        return user.map(OurUserInfoDetails::new).orElseThrow(()-> new UsernameNotFoundException("User Not Exist"));
    }
}
