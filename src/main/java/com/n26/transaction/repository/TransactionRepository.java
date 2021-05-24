package com.n26.transaction.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.constraints.NotNull;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.n26.transaction.entity.Transaction;

/**
 * Storage of transactions and repository class
 */
@Repository
public class TransactionRepository implements CrudRepository<Transaction, UUID> {
    private final Map<UUID, Transaction> transactionsStorage = new ConcurrentHashMap<>();

    @Override
    public Transaction save(Transaction entity) {
        return transactionsStorage.put(entity.getId(), entity);
    }

    @Override
    public <S extends Transaction> Iterable<S> saveAll(Iterable<S> entities) {
        entities.forEach(transaction ->  transactionsStorage.put(transaction.getId(), transaction));
        return entities;
    }

    @Override
    public Optional<Transaction> findById(UUID uuid) {
        return Optional.ofNullable(transactionsStorage.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return transactionsStorage.containsKey(uuid);
    }

    @Override
    public Collection<Transaction> findAll() {
        return transactionsStorage.values();
    }

    @Override
    public List<Transaction> findAllById(@NotNull Iterable<UUID> uuids) {
        List<Transaction> find = new ArrayList();
        uuids.forEach(id -> find.add(transactionsStorage.get(id)));
        return find;
    }

    @Override
    public long count() {
        return transactionsStorage.size();
    }

    @Override
    public void deleteById(UUID uuid) {
        transactionsStorage.remove(uuid);
    }

    @Override
    public void delete(Transaction entity) {
        transactionsStorage.remove(entity.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends Transaction> entities) {
        entities.forEach(transaction -> transactionsStorage.remove(transaction.getId()));
    }

    @Override
    public void deleteAll() {
        transactionsStorage.clear();
    }
}
