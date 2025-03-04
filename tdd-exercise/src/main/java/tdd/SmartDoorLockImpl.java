package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean locked;
    private int pin;
    private int attempts;
    private int maxAttempts;

    public SmartDoorLockImpl(int maxAttempts, int pin) {
        locked = false;
        attempts = 0;
        this.maxAttempts = maxAttempts;
        this.pin = pin;
    }

    private void isValidPin(int pin) {
        if (pin > 9999) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setPin(int pin) {
        isValidPin(pin);
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        isValidPin(pin);
        if (this.pin == pin) {
            locked = false;
        } else {
            attempts = attempts + 1;
        }
    }

    @Override
    public void lock() {
        locked = true;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isBlocked() {
        return attempts >= maxAttempts;
    }

    @Override
    public int getMaxAttempts() {
        return maxAttempts - attempts;
    }

    @Override
    public int getFailedAttempts() {
        return attempts;
    }

    @Override
    public void reset() {

    }
}
