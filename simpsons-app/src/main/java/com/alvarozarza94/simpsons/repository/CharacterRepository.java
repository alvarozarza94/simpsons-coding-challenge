package com.alvarozarza94.simpsons.repository;

import com.alvarozarza94.simpsons.model.repository.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, String> {
}
