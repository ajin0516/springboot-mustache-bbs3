package com.springbootmustache.bbs3.domain.dto;

import com.springbootmustache.bbs3.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "nation_wide_hospitals")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hospital {

    //  entity만들 때 적절한 타입 매핑 필요함
    @Id
    private Integer id;

    @Column(name = "hospital_name")
    private String hospitalName;

    private String roadNameAddress;



//    private Integer patientRoomCount;
//    private Integer totalNumberOfBeds;
//    private Float totalAreaSize;

    public static Hospital of(Integer id){
        return Hospital.builder().id(id).build();
    }
}
