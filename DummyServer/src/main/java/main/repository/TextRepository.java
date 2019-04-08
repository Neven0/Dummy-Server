package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.messages.Text;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {

}
