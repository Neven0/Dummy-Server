package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.messages.Emotion;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {

}
