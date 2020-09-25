package cy.study.sty.dao;

import cy.study.sty.domain.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();
}
