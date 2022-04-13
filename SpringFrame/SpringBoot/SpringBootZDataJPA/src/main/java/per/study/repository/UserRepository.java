package per.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import per.study.domain.User;

public interface UserRepository extends JpaRepository<User, Long>
{
}
