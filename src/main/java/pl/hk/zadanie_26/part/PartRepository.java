package pl.hk.zadanie_26.part;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {

    List<Part> findAllBySelected(boolean selected);
}
