package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    public SmartDoorLock smartDoorLock;
    @BeforeEach
    void beforeEach(){
        smartDoorLock = new SmartDoorLockImpl();
        smartDoorLock.setPin(1111);
    }
    @Test
    public void unlock() {
        smartDoorLock.lock();
        smartDoorLock.unlock(1111);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    public void lock() {
        smartDoorLock.lock();
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    public void wrongPinUnlock() {
        smartDoorLock.lock();
        smartDoorLock.unlock(2222);
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    public void correctPinFormat() {
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.setPin(11111));
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(11111));
    }
}
