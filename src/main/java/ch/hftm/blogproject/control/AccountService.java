package ch.hftm.blogproject.control;

import ch.hftm.blogproject.repository.AccountRepository;

import java.util.List;

import ch.hftm.blogproject.entity.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AccountService {

    @Inject
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.listAll();
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Transactional
    public Account addAccount(Account account) {
        accountRepository.persist(account);
        return account;
    }

    @Transactional
    public void updateAccount(Long id, Account author) {
        Account existingAuthor = accountRepository.findById(id);
        if (existingAuthor != null) {
            existingAuthor.setName(author.getName());
            existingAuthor.setEmail(author.getEmail());
            accountRepository.persist(existingAuthor);
        }
    }

    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id);
        if (account != null) {
            accountRepository.delete(account);
        }
    }
}