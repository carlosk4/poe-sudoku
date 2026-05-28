package com.example.poesudoku.view;

import javafx.scene.layout.StackPane;

/**
 * Defines hover behavior for cell wrappers.
 *
 * @author Joan Lorenzo H. (carlosk4)
 * @author Abraham Y.
 * @version 1.0
 */
public interface Hoverable {
    /**
     * Applies hover effects to a wrapper.
     *
     * @param wrapper target wrapper
     */
    void applyHoverEffect(StackPane wrapper);
}
