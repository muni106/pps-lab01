package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    public static final int MAX_ATTEMPTS = 5;
    public SmartDoorLock smartDoorLock;
    private int pin;
    private int wrongPin = 11111;

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
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.setPin(wrongPin));
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(wrongPin));
    }

    @Test
    public void block() {
        for (int i = 0; i <= MAX_ATTEMPTS; i++) {
            smartDoorLock.unlock(wrongPin);
        }
        assertTrue(smartDoorLock.isBlocked());
    }
}
