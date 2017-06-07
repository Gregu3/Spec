package com.grzegorz.repository;

import com.grzegorz.model.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by grzeg on 07.06.2017.
 */
@Service
@Transactional
public class AdService {

    @Autowired
    private AdvRepository advRepository;


    public void save(Advertisement advertisement) {
        advRepository.save(advertisement);
    }

    public Optional<Advertisement> findOne(long id) {
        return Optional.ofNullable(advRepository.findOne(id));
    }

    public List<Advertisement> findAll() {
        Iterable<Advertisement> all = advRepository.findAll();
        List<Advertisement> result = new ArrayList<>();
        all.forEach(result::add);
        return result;
    }

    public void remove(long id) {
        Optional<Advertisement> one = findOne(id);
        if (one.isPresent()) {
            advRepository.delete(id);
        }
    }

}
