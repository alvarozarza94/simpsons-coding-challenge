package com.alvarozarza94.simpsons.repository;

import com.alvarozarza94.simpsons.model.repository.Character;
import com.alvarozarza94.simpsons.model.repository.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, String> {
}
