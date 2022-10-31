package com.springboot.hello.parser;

import com.springboot.hello.domain.Hospital;

import java.time.LocalDateTime;
import java.util.Arrays;

public class HospitalParser implements Parser<Hospital> {
    @Override
    public Hospital parse(String str) {
//        assertEquals(1, hospital.getId()); // columnNum : 0
//        assertEquals("의원", hospital.getOpenServiceName()); // columnNum : 1
//        assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); // columnNum : 3
//        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); // columnNum : 4
//        assertEquals(LocalDateTime.of(1999, 6, 12, 0, 0, 0), hospital.getLicenseDate()); //19990612  // columnNum : 5
//        assertEquals(6, hospital.getBusinessStatus()); // columnNum : 7
//        assertEquals(13, hospital.getBusinessStatusCode()); // columnNum : 9
//        assertEquals("062-515-2875", hospital.getPhone()); // columnNum : 15
//        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); // columnNum : 18
//        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress()); // columnNum : 19
//        assertEquals("효치과의원", hospital.getHospitalName()); // columnNum : 21
//        assertEquals("치과의원", hospital.getBusinessTypeName()); // columnNum : 25
//        assertEquals(1, hospital.getHealthcareProviderCount()); // columnNum : 30
//        assertEquals(0, hospital.getPatientRoomCount()); // columnNum : 31
//        assertEquals(0, hospital.getTotalNumberOfBeds()); // columnNum : 31
//        assertEquals(52.29, hospital.getTotalAreaSize()); // columnNum : 32

        String[] row = str.split("\",\"");
        System.out.println(Arrays.toString(row));

        Hospital hospital = new Hospital();

        hospital.setId(Integer.parseInt(row[0].replace("\"","")));
        hospital.setOpenServiceName(row[1]);
        hospital.setOpenLocalGovernmentCode(Integer.parseInt(row[3]));
        hospital.setManagementNumber(row[4]);

        int year = Integer.parseInt(row[5].substring(0, 4));
        int month = Integer.parseInt(row[5].substring(4, 6));
        int day = Integer.parseInt(row[5].substring(6, 8));
        //        System.out.printf("%d %d %d %n", year, month, day);
        hospital.setLicenseDate(LocalDateTime.of(year, month, day, 0, 0, 0));

        hospital.setBusinessStatus(Integer.parseInt(row[7]));
        hospital.setBusinessStatusCode(Integer.parseInt(row[9]));
        hospital.setPhone(row[15]);
        hospital.setFullAddress(row[18]);
        hospital.setRoadNameAddress(row[19]);
        hospital.setHospitalName(row[21]);
        hospital.setBusinessTypeName(row[25]);
        hospital.setHealthcareProviderCount(Integer.parseInt(row[29]));
        hospital.setPatientRoomCount(Integer.parseInt(row[30]));
        hospital.setTotalNumberOfBeds(Integer.parseInt(row[31]));
        hospital.setTotalAreaSize(Float.parseFloat(row[32]));
//        hospital.setTotalAreaSize(Integer.parseInt(row[32].replace("\"", "")));

        return hospital;
    }
}
