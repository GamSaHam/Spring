package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {


    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {

        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPelple() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMode = selectPersonById(id);

        if(personMode == null){
            return  0;
        }
        DB.remove(personMode.get());
        return 1;

    }

    @Override
    public int updatePersonById(UUID id, Person update) {

        return selectPersonById(id)
                .map(person ->{
                   int indexOfPersonDelete = DB.indexOf(person);
                   if(indexOfPersonDelete >= 0){
                       DB.set(indexOfPersonDelete, new Person(id, update.getName()));
                       return 1;
                   }

                   return 0;
                }).orElse(0);

    }


}
