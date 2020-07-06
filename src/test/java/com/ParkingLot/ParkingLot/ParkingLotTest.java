package com.ParkingLot.ParkingLot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.Environment;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by prasadus on 26/06/16.
 */
public class ParkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    @Test
    public void createParkingLot() throws Exception {
        parkingLot.createParkingLot("6");
        assertEquals(6, parkingLot.MAX_SIZE);
        assertEquals(6, parkingLot.availableSlotList.size());
        assertTrue("created parkinglot with 6 slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
    }

    @Test
    public void park() throws Exception {
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Sorry,parkinglotisnotcreated",  "error","");
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        assertEquals(4, parkingLot.availableSlotList.size());
    }

    @Test
    public void leave() throws Exception {
        parkingLot.leave("2","2");
        assertEquals("Sorry,parkinglotisnotcreated123", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.leave("4","2");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "Slotnumber 4 is already empty",  "Insert Slot number 4","");
    }

    @Test
    public void status() throws Exception {
        parkingLot.status();
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.status();
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "SlotNo.\tRegistrationNo.\tColor\n" +
                "1\tKA-01-HH-1234\tWhite\n" +
                "2\tKA-01-HH-9999\tWhite",  "success","");
    }

    @Test
    public void getRegistrationNumbersFromColor() throws Exception {
        parkingLot.getRegistrationNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.getRegistrationNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "\n" +
                "KA-01-HH-1234,KA-01-HH-9999", "","");
        parkingLot.getRegistrationNumbersFromColor("Red");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "\n" +
                "KA-01-HH-1234,KA-01-HH-9999Notfound",  "","");
    }

    @Test
    public void getSlotNumbersFromColor() throws Exception {
        parkingLot.getSlotNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.getSlotNumbersFromColor("White");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "\n" +
                "1,2", "","");
        parkingLot.getSlotNumbersFromColor("Red");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "\n" +
                "1,2\n" +
                "Notfound", "","");
    }

    @Test
    public void getSlotNumberFromRegNo() throws Exception {
        parkingLot.getSlotNumberFromRegNo("KA-01-HH-1234");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.getSlotNumberFromRegNo("KA-01-HH-1234");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "1",  "","");
        parkingLot.getSlotNumberFromRegNo("KA-01-HH-9999");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "1\n" +
                "2",  "","");
        parkingLot.leave("1","1");
        parkingLot.getSlotNumberFromRegNo("KA-01-HH-1234");
        assertEquals("Sorry,parkinglotisnotcreated\n" +
                "\n" +
                "Createdparkinglotwith6slots\n" +
                "\n" +
                "Allocatedslotnumber:1\n" +
                "\n" +
                "Allocatedslotnumber:2\n" +
                "\n" +
                "1\n" +
                "2\n" +
                "Slotnumber1isfree\n" +
                "\n" +
                "Notfound",  "Notfound","");
    }

}