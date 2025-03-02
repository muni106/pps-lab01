package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    public static final int MAX_ATTEMPTS = 5;
    public SmartDoorLock smartDoorLock;
    private int pin;

    @BeforeEach
    void beforeEach(){
        pin = 1111;
        smartDoorLock = new SmartDoorLockImpl(MAX_ATTEMPTS, pin);
        smartDoorLock.lock();
    }
    @Test
    public void unlock() {
        smartDoorLock.unlock(pin);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    public void lock() {
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    public void wrongPinUnlock() {
        int wrongPin = 2222;
        smartDoorLock.unlock(wrongPin);
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    public void correctPinFormat() {
        int wronglyFormattedPin = 111111;
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.setPin(wronglyFormattedPin));
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(wronglyFormattedPin));
    }

    @Test
    public void blockDoor() {
        int wrongPin = 2222;
        assertFalse(smartDoorLock.isBlocked());
        for (int i = 0; i <= MAX_ATTEMPTS; i++) {
            smartDoorLock.unlock(wrongPin);
        }
        assertTrue(smartDoorLock.isBlocked());
    }

    @Test
    public void failedAndMaxAttempts() {
        int remainingAttempts = MAX_ATTEMPTS / 2;
        int wrongPin = 2222;
        for (int i = 0; i < MAX_ATTEMPTS - remainingAttempts; i++) {
            smartDoorLock.unlock(wrongPin);
        }
        assertEquals(remainingAttempts, smartDoorLock.getMaxAttempts());
        assertEquals(smartDoorLock.getFailedAttempts(), MAX_ATTEMPTS - remainingAttempts);
    }

    @Test
    public void blockAndReset() {
        int wrongPin = 2222;
        for (int i = 0; i <= MAX_ATTEMPTS; i++) {
            smartDoorLock.unlock(wrongPin);
        }



    }





}
