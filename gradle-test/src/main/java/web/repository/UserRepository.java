package web.repository;

import org.springframework.data.repository.CrudRepository;

import web.model.OrangeUser;

public interface UserRepository extends CrudRepository<OrangeUser, Integer>{
	
}
