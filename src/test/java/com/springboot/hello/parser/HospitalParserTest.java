package com.springboot.hello.parser;

import com.springboot.hello.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";
    String line2 = "";

    @Test
    @DisplayName("csv 1줄을 Hospital로 잘 만드는지 테스트")
    void convertToHospital() {

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId()); // columnNum : 0
        assertEquals("의원", hospital.getOpenServiceName()); // columnNum : 1
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); // columnNum : 3
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); // columnNum : 4
        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612  // columnNum : 5
        assertEquals(7, hospital.getBusinessStatus()); // columnNum : 7
        assertEquals(13, hospital.getBusinessStatusCode()); // columnNum : 9
        assertEquals("062-515-2875", hospital.getPhone()); // columnNum : 15
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); // columnNum : 18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress()); // columnNum : 19
        assertEquals("효치과의원", hospital.getHospitalName()); // columnNum : 21
        assertEquals("치과의원", hospital.getBusinessTypeName()); // columnNum : 25
        assertEquals(1, hospital.getHealthcareProviderCount()); // columnNum : 30
        assertEquals(0, hospital.getPatientRoomCount()); // columnNum : 31
        assertEquals(0, hospital.getTotalNumberOfBeds()); // columnNum : 32
        assertEquals(52.29f, hospital.getTotalAreaSize()); // columnNum : 33

    }
}