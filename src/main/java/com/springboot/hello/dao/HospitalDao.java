package com.springboot.hello.dao;

import com.springboot.hello.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;  // Autowired 대신 private final = DI

    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getCount() {
        String sql = "select count(id) from nation_wide_hospitals";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // List<Hospital> - 11만건이 들어있음. Hospital
    public void add(Hospital hospital) { // 따라서 Hospital이 들어간다
        String sql = "INSERT INTO `likelion-db`.`nation_wide_hospitals` (`id`, `open_service_name`, `open_local_government_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area_size`) " +
                     "VALUES (?, ?, ?," +
                     "?, ?, ?," + // ? 뒤에 , 잘 확인할 것
                     "?, ?, ?," +
                     "?, ?, ?," +
                     "?, ?, ?," +
                     "?);"; // 총 16개
        this.jdbcTemplate.update(sql,
                hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(),
                hospital.getManagementNumber(), hospital.getLicenseDate(), hospital.getBusinessStatus(),
                hospital.getBusinessStatusCode(), hospital.getPhone(), hospital.getFullAddress(),
                hospital.getRoadNameAddress(), hospital.getHospitalName(), hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(),
                hospital.getTotalAreaSize()
                ); // 업데이트 하려면 위의 sql이 필요
    }
}
