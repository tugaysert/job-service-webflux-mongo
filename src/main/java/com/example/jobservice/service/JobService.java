package com.example.jobservice.service;

import com.example.jobservice.dto.JobDto;
import com.example.jobservice.repository.JobRepository;
import com.example.jobservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class JobService {


    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Flux<JobDto> allJobs() {
        return this.jobRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Flux<JobDto> jobsBySkillsIn(Set<String> skills) {
        return this.jobRepository.findBySkillsIn(skills)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<JobDto> save(Mono<JobDto> mono) {
        return mono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.jobRepository::save)
                .map(EntityDtoUtil::toDto);
    }
}
