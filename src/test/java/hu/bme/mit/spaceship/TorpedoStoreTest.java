package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_TooMany_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act & Assert
        // Kivételt kell dobnia, mert 2-t kérünk, de csak 1 van
        assertThrows(IllegalArgumentException.class, () -> store.fire(2));
    }

    @Test
    void fire_Zero_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act & Assert
        // Kivételt kell dobnia, mert 1-nél kevesebbet nem lehet lőni
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_Failure_DueToHighFailureRate() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertFalse(result);
        assertEquals(1, store.getTorpedoCount());
    }

    @Test
    void isEmpty_True() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0);
        
        // Act & Assert
        assertTrue(store.isEmpty());
    }

    @Test
    void isEmpty_False() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);
        
        // Act & Assert
        assertFalse(store.isEmpty());
    }

}
