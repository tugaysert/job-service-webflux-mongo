package com.example.jobservice.util;

import com.example.jobservice.dto.JobDto;
import com.example.jobservice.entity.Job;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static JobDto toDto(Job job) {

        JobDto dto = new JobDto();
        BeanUtils.copyProperties(job, dto);
        return dto;

    }

    public static Job toEntity(JobDto dto) {

        Job job = new Job();
        BeanUtils.copyProperties(dto, job);
        return job;

    }

}
