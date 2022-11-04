package com.example.FirstSecurityApp.services;

import com.example.FirstSecurityApp.models.Person;
import com.example.FirstSecurityApp.repositories.PeopleRepository;
import com.example.FirstSecurityApp.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found!!!");
        return new PersonDetails(person.get());
    }

    @Transactional
    public void update( Person person) {
        String username = person.getUsername();
        Optional<Person> updatedPerson = peopleRepository.findByUsername(username);
        if (updatedPerson.isPresent()) {
            updatedPerson.get().setPassword(person.getPassword());
            peopleRepository.save(updatedPerson.get());
        }
    }
    public Optional<Person> findByUsername(String username){
        return peopleRepository.findByUsername(username);
    }

}
